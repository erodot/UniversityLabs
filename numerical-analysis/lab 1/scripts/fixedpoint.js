function doFixedPoint(){
  var startingPoint = +document.getElementById('fixedpointStartingPoint').value;
  var accuracy = +document.getElementById('fixedpointAccuracy').value;
  console.log("Something is doing...");

  if(accuracy < Math.pow(10,-10)){
    alert('It seems like our compiler is too week to achieve your accuracy :\'(');
    return;
  }

  var oldPoint;
  var newPoint = startingPoint;
  var i=0;

  var lambda = 0.02;

  // var lambda = function(x) {
  //   return Math.sign(f_deriv(x)) / Math.pow(x,3);
  // }

  do{
    oldPoint = newPoint;
    newPoint = oldPoint - lambda * f(oldPoint);
    i++;
  } while ( Math.abs(newPoint-oldPoint) > accuracy);

  var fixedpointAnswer = 'Answer: ' + newPoint.toFixed(-Math.floor(Math.log10(accuracy))) + '<br />Iterations count: ' + i;
  $('#fixedpointAnswer').html(fixedpointAnswer);
  $('#fixedpointAnswer').show();
}
