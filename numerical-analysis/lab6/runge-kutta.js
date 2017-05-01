var drawRungeKutta = function(){
    var fn_x = document.getElementById('runge-kutta-eq-x').value;
    var fn_y = document.getElementById('runge-kutta-eq-y').value;
    var x = math.eval(document.getElementById('runge-kutta-start-point-x').value);
    var y = math.eval(document.getElementById('runge-kutta-start-point-y').value);
    var t = 0;
    var upper_limit = math.eval(document.getElementById('runge-kutta-upper-limit').value);
    var intervals_count = math.eval(document.getElementById('runge-kutta-intervals-count').value);
    var interval = (upper_limit - t) / intervals_count;

    var rungeKuttaPlotOptions = {
        title: "Runge-Kutta",
        target: "#runge-kutta",
        width: 700,
        height: 500,
        data: []
    }

    for(var i=0; i<intervals_count; i++){
        var t_old = t;
        var x_old = x;
        var y_old = y;
        t += interval;
        x += interval * math.eval(fn_x, {x:x_old, y:y_old});
        y += interval * math.eval(fn_y, {x:x_old, y:y_old});

        rungeKuttaPlotOptions.data.push({
            points: [
                [t_old,x_old],
                [t,x]
            ],
            fnType: 'points',
            sampler: 'builtIn',
            graphType: 'polyline',
            color: 'blue'
        });

        rungeKuttaPlotOptions.data.push({
            points: [
                [t_old,y_old],
                [t,y]
            ],
            fnType: 'points',
            sampler: 'builtIn',
            graphType: 'polyline',
            color: 'red'
        });
    }

    try {
        functionPlot(rungeKuttaPlotOptions);
    }
    catch (err) {
      console.log(err);
      //alert(err);
    }
}

document.getElementById('runge-kutta-form').onsubmit = function (event) {
    event.preventDefault();
    drawRungeKutta();
};