const express = require('express');
const bodyParser = require('body-parser');
const request = require('ajax-request');
const fs = require('fs');
const path = require('path');

const databaseRouter = express.Router();
databaseRouter.use(bodyParser.json());

const dataDir = path.join(__dirname, "../data");

const getDatabaseByName = databaseName => new Promise((resolve, reject) => {
    const dbDir = path.join(dataDir, databaseName);
    if(!fs.existsSync(dbDir))
    reject(`Database "${req.params.databaseName}" doesn't exist.`);

    fs.readFile(path.join(dbDir, "db.json"), "utf8",
        (err, data) => {
            if(err) reject(err);
            resolve(JSON.parse(data));
        }
    );
});

const createDatabase = databaseName => new Promise((resolve, reject) => {
    const dbDir = path.join(dataDir, databaseName);
    if(fs.existsSync(dbDir))
        reject(`Database "${databaseName}" already exist.`);

    fs.mkdir(dbDir, err => {
        if(err) reject(err);

        const db = {
            name: databaseName,
            tables: []
        };

        fs.writeFile(path.join(dbDir, "db.json"), JSON.stringify(db), 'utf8', err => {
            if(err) reject(err);
            resolve(db);
        });
    });
})

databaseRouter.route('/')
    .get((req, res, next) => {
        // get all databases

        fs.readdir(dataDir, null, (err, files) => {
            if(err) throw err;

            const directories = files.filter(f => fs.statSync(path.join(dataDir, f)).isDirectory());
            res.json({ databases: directories });
        })
    });

databaseRouter.route('/:databaseName')
    .get((req, res, next) => {
        getDatabaseByName(req.params.databaseName)
            .then(res.json);
    })
    .post((req, res, next) => {
        createDatabase(req.params.databaseName)
            .then(res.json);
    });

databaseRouter.route('/:databaseName/table')
    .get((req, res, next) => {
        // get all tables

        getDatabaseByName(req.params.databaseName)
            .then(db => res.json(db.tables));
    });

module.exports = databaseRouter;