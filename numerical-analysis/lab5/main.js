var drawRectangle = function(target, answer_target, fn, lower_limit, upper_limit, intervals_count){

    var functionPlotOptions = {
        title: "Rectangle",
        target: target,
        width: 700,
        height: 500,
        data: [{
            fn: fn,
            sampler: 'builtIn',
            graphType: 'polyline',
            range: [lower_limit, upper_limit],
            color: 'red'
        }]
    }

    var interval = (upper_limit - lower_limit) / intervals_count;
    var area = 0;

    for(var i = 0; i < intervals_count; i++){
        var left = lower_limit + i * interval;
        var right = lower_limit + (i + 1) * interval;

        var average = (left + right) / 2;
        var f_average = math.eval(fn, {x:average});

        area += f_average * interval;

        functionPlotOptions.data.push({
            points: [
                [left, 0],
                [left, f_average],
                [right, f_average],
                [right, 0]
            ],
            fnType: 'points',
            graphType: 'polyline',
            color: 'blue'
        });
    }

    try {
        document.getElementById(answer_target).innerHTML += "Rectangle method: " + area + "<br/>";
        return functionPlot(functionPlotOptions);
    }
    catch (err) {
      console.log(err);
      alert(err);
    }
};

var drawTrapezoid = function(target, answer_target, fn, lower_limit, upper_limit, intervals_count){

    var functionPlotOptions = {
        title: "Trapezoid",
        target: target,
        width: 700,
        height: 500,
        data: [{
            fn: fn,
            sampler: 'builtIn',
            graphType: 'polyline',
            range: [lower_limit, upper_limit],
            color: 'red'
        }]
    }

    var interval = (upper_limit - lower_limit) / intervals_count;
    var area = 0;

    for(var i = 0; i < intervals_count; i++){
        var left = lower_limit + i * interval;
        var right = lower_limit + (i + 1) * interval;

        var f_left = math.eval(fn, {x:left});
        var f_right = math.eval(fn, {x: right});

        var midline = (f_left + f_right) / 2;

        area += midline * interval;

        functionPlotOptions.data.push({
            points: [
                [left, 0],
                [left, f_left],
                [right, f_right],
                [right, 0]
            ],
            fnType: 'points',
            graphType: 'polyline',
            color: 'blue'
        });
    }

    try {
        document.getElementById(answer_target).innerHTML += "Trapezoid method: " + area + "<br/>";
        return functionPlot(functionPlotOptions);
    }
    catch (err) {
      console.log(err);
      alert(err);
    }
};

var getQuadFunctionByPoints = function(points){
    var first = points[0];
    var second = points[1];
    var third = points[2];

    var det = math.det([[first.x*first.x, first.x, 1],[second.x*second.x, second.x, 1],[third.x*third.x, third.x, 1]]);
    var det1 = math.det([[first.y, first.x, 1],[second.y, second.x, 1],[third.y, third.x, 1]]);
    var det2 = math.det([[first.x*first.x, first.y, 1],[second.x*second.x, second.y, 1],[third.x*third.x, third.y, 1]]);
    var det3 = math.det([[first.x*first.x, first.x, first.y],[second.x*second.x, second.x, second.y],[third.x*third.x, third.x, third.y]]);

    var a = det1/det;
    var b = det2/det;
    var c = det3/det;

    var fn = `${a}*x*x+${b}*x+${c}`;

    return fn;
};

var drawSimpson = function(target, answer_target, fn, lower_limit, upper_limit, intervals_count){

    var functionPlotOptions = {
        title: "Simpson",
        target: target,
        width: 700,
        height: 500,
        data: [{
            fn: fn,
            sampler: 'builtIn',
            graphType: 'polyline',
            range: [lower_limit, upper_limit],
            color: 'red'
        }]
    }

    var riemannFunctionPlotOptions = {
        title: "Riemann",
        target: "#riemann",
        width: 700,
        height: 500,
        data: []
    }

    var interval = (upper_limit - lower_limit) / intervals_count;
    var area = 0;

    for(var i = 0; i < intervals_count; i++){
        var old_area = area;
        var left = lower_limit + i * interval;
        var right = lower_limit + (i + 1) * interval;
        var average = (left + right) / 2;

        var f_left = math.eval(fn, { x:left });
        var f_average = math.eval(fn, { x:average });
        var f_right = math.eval(fn, { x:right });

        var multiplier = (f_left + 4 * f_average + f_right) / 6;

        area += multiplier * interval;

        functionPlotOptions.data.push({
            points: [
                [left, 0],
                [left, f_left]
            ],
            fnType: 'points',
            graphType: 'polyline',
            color: 'blue'
        });
        functionPlotOptions.data.push({
            points: [
                [right, f_right],
                [right, 0]
            ],
            fnType: 'points',
            sampler: 'builtIn',
            graphType: 'polyline',
            color: 'blue'
        });
        functionPlotOptions.data.push({
            fn: getQuadFunctionByPoints([{x:left,y:f_left}, {x:average, y:f_average}, {x:right,y:f_right}]),
            sampler: 'builtIn',
            graphType: 'polyline',
            range: [left, right],
            color: 'blue'
        });
        riemannFunctionPlotOptions.data.push({
            points: [
                [left,old_area],
                [right,area]
            ],
            fnType: 'points',
            sampler: 'builtIn',
            graphType: 'polyline',
            color: 'blue',
            closed: true
        });
    }

    console.log(functionPlotOptions);

    try {
        document.getElementById(answer_target).innerHTML += "&nbsp;Simpson&nbsp;&nbsp;method: " + area + "<br/>";
        functionPlot(riemannFunctionPlotOptions);
        return functionPlot(functionPlotOptions);
    }
    catch (err) {
      console.log(err);
      //alert(err);
    }
};

var draw = function() {

    document.getElementById('answer').innerHTML = "";

    var fn = document.getElementById('eq').value;
    var lower_limit = math.eval(document.getElementById('lower-limit').value);
    var upper_limit = math.eval(document.getElementById('upper-limit').value);
    var intervals_count = math.eval(document.getElementById('intervals-count').value);

    var a = drawRectangle('#rectangle', 'answer', fn, lower_limit, upper_limit, intervals_count);
    var b = drawTrapezoid('#trapezoid', 'answer', fn, lower_limit, upper_limit, intervals_count);
    var c = drawSimpson("#simpson", 'answer', fn, lower_limit, upper_limit, intervals_count);

    a.addLink(b, c);
    b.addLink(a, c);
    c.addLink(a, b);

    document.getElementById("rectangle").setAttribute("style", "display: inline; float: left;");
    document.getElementById("trapezoid").setAttribute("style", "display: inline; float: left;");
    document.getElementById("simpson").setAttribute("style", "display: inline; float: left;");
};


document.getElementById('form').onsubmit = function (event) {
    event.preventDefault();
    draw();
};