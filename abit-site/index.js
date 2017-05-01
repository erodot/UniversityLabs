const fs = require('fs');
const request = require('requestretry');
const cheerio = require('cheerio');
const $ = cheerio;

const url = 'http://vstup.info';
const year = 2016;
const db = {
    'regions': []
};

let schools_received = 0;
let schools_sent = 0;
let region_received = 0;
let region_sent = 0;

const get_about_info = (cell) => {
    return $(cell).children().last().text()
}

const format_amount = (cell_object) => {
    let amount = $('nobr', cell_object);
    const amount_obj = {
        'licensed': 0,
        'budget': 0
    };
    if(amount.length > 0)
        amount_obj.licensed = eval($(amount[0]).text().replace("ЛО: ",""));
    if(amount.length > 1)
        amount_obj.budget = eval($(amount[1]).text().replace("ДЗ: ",""));
    return amount_obj;
}

const format_contest_table_href = (cell_object) => {
    const a = $('a', cell_object);
    if(a.length > 0)
        return '/' + year + a.attr('href').slice(1).replace('#list', '');
    else return null;
}

request(url, (error, response, body)=>{
    const $ = cheerio.load(body);
    const region_promises = [];

    $('#abet td a').each((index, elem) => {
        const data = $(elem);
        const region = {
            'name': data.text(),
            'href': data.attr('href').replace("#reg","")
        }
        db.regions.push(region);
        region_promises.push((resolve,reject)=>{
            region_sent++;
            request(url + region.href, function(error, response, body){
                region_received++;
                if(error) reject(error);

                const $ = cheerio.load(body);
                region.vnz = [];
                const school_promises = ((selectors) => {
                    const school_promises = [];
                    selectors.forEach((selector) => {
                        $(`#${selector} td a`).each((index, elem)=>{
                            const data = $(elem);
                            const vnz = {
                                'name': data.text(),
                                'href': '/' + year + data.attr('href').slice(1).replace("#vnz", "")
                            }
                            region.vnz.push(vnz);
                            school_promises.push((resolve, reject)=>{
                                schools_sent++;
                                request({url:url + vnz.href, timeout: 5000}, function(error, response, body){
                                    schools_received++;
                                    if(error) {
                                        reject(`Rejected ${url+vnz.href}`);
                                    }
                                    console.log(`Received: ${schools_received}/${schools_sent}...`);

                                    const $ = cheerio.load(body);
                                    const about_table = $('#about tr');
                                    vnz.type = get_about_info(about_table[1]);
                                    vnz.zip_code = get_about_info(about_table[2]);
                                    vnz.location = get_about_info(about_table[3]);
                                    vnz.phones = get_about_info(about_table[4]);
                                    vnz.website = get_about_info(about_table[5]);
                                    vnz.email = get_about_info(about_table[6]).replace(" [ at ] ", "@");
                                    vnz.specialties = {
                                        'full_time': { 'bachelors': [], 'masters': [], 'specialists': [], 'junior_specialists': [] },
                                        'part_time': { 'bachelors': [], 'masters': [], 'specialists': [], 'junior_specialists': [] },
                                        'distance_learning': { 'bachelors': [], 'masters': [], 'specialists': [], 'junior_specialists': [] }
                                    };

                                    (input => {
                                        input.forEach((input_data => {
                                            $(`#${input_data[0]} tbody tr`).each((index, elem)=>{
                                                const data = $(elem).children(); //td's
                                                const speciality = {
                                                    'name': $(data[0]).text(),
                                                    'contest_scores': $(data[1]).text(),
                                                    'contest_table_href': format_contest_table_href(data[1]),
                                                    'amount': format_amount(data[2]),
                                                    'subjects': $(data[3]).text()
                                                }
                                                input_data[1].push(speciality);
                                            });
                                        }));
                                    })([
                                        ['denna1', vnz.specialties.full_time.bachelors], ['denna2', vnz.specialties.full_time.masters], ['denna3', vnz.specialties.full_time.specialists], ['denna4', vnz.specialties.full_time.junior_specialists],
                                        ['zaoch1', vnz.specialties.part_time.bachelors], ['zaoch2', vnz.specialties.part_time.masters], ['zaoch3', vnz.specialties.part_time.specialists], ['zaoch4', vnz.specialties.part_time.junior_specialists],
                                        ['distan1', vnz.specialties.distance_learning.bachelors], ['distan2', vnz.specialties.distance_learning.masters], ['distan3', vnz.specialties.distance_learning.specialists], ['distan4', vnz.specialties.distance_learning.junior_specialists],
                                    ]);

                                    resolve();
                                });
                            });
                        });
                    });
                    region.vnz_count = region.vnz.length;
                    return school_promises;
                })(['univ', 'akad', 'inst', 'college', 'ptu', 'vidpid', 'notype']);
                resolve(school_promises);
            });
        });
    });

    Promise
        .all(region_promises.map(rp => new Promise(rp)))
        .then(school_promises_array => {
            const school_promises_global = [];
            school_promises_array.forEach(school_promises => school_promises.forEach(school_promise => school_promises_global.push(school_promise)));
            return Promise.all(school_promises_global.map(sp => new Promise(sp)));
        })
        .then(() => new Promise((resolve,reject)=>{
            fs.writeFile('db.json', JSON.stringify( db, null, 4), function(err) {
                if(err) reject(err);
                else resolve('Data Saved to db.json file');
            })
        }))
        .then(console.log)
        .catch(console.error);
});