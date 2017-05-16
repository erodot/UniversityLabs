var stop = false;
var T = [];
var k, f, N, u0, u1, g, h, alpha, beta, tau, lastTime, currTime, firstTime, A, B, C, F;

var data = [];

var update = async function(){

    if(stop) 
        return;

    currTime = new Date().getTime();
    tau = currTime - lastTime;
    lastTime = currTime;

    var T_new = [];
    for(var i=0; i<N+1; i++){
        T_new.push(null);
    }

    T_new[0] = math.eval(u0, {t: (currTime - firstTime) / 1000});
    T_new[N] = math.eval(u1, {t: (currTime - firstTime) / 1000});

    alpha = [0]; beta = [T_new[0]];
    for(var i=1; i<N+1; i++) {

        A = k / (h*h);
        C = A;
        B = 2*k / (h*h) + 1 / tau;
        F = - T[i] / tau - math.eval(f, {x: i*h, t: (currTime - firstTime)/1000});

        alpha.push(A / (B - C * alpha[i-1]));
        beta.push((C * beta[i-1] - F) / (B - C * alpha[i-1]));
    }

    for(var i=N-1; i>0; i--)
        T_new[i] = alpha[i] * T_new[i+1]+beta[i];
    data[0].z[0] = T_new;
    
    Plotly.animate(document.getElementById('finite-difference-plot'), {data: data, traces: [0], layout: {}}, {
        transition: {
        duration: 10,
        easing: 'cubic-in-out'
        }
    });

    T = T_new;

    requestAnimationFrame(update);
}

var run = async function(){
    stop = false;
    //document.getElementById('finite-difference-plot').innerHTML="";

    k = math.eval(document.getElementById('finite-difference-k').value);
    f = document.getElementById('finite-difference-f-eq').value;
    N = 100;
    u0 = document.getElementById('finite-difference-u0-eq').value;
    u1 = document.getElementById('finite-difference-u1-eq').value;
    g = document.getElementById('finite-difference-g-eq').value;
    h = 1/N;

    for(var i=0; i<N+1; i++){
        T.push(math.eval(g, {x: i*h}));
    }

    lastTime = new Date().getTime();
    firstTime = lastTime;

    data = [{
            y: [" "],
            z: [T],
            type: "heatmap",
            colorscale: "Bluered" 
        }];

    Plotly.plot(document.getElementById('finite-difference-plot'), data, {}, function (err, msg) {
        console.log(msg);
    });

    requestAnimationFrame(update);
}

document.getElementById('finite-difference-form').onsubmit = function (event) {
    event.preventDefault();
    run();
};

document.getElementById('stop-button').onclick = function (event) {
    event.preventDefault();
    stop = true;
};