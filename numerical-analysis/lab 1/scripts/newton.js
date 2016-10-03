function doNewtons(){
  var startingPoint = +document.getElementById('newtonsStartingPoint').value;
  var accuracy = +document.getElementById('newtonsAccuracy').value;

  if(accuracy < Math.pow(10,-10)){
    alert('It seems like our compiler is too week to achieve your accuracy :\'(');
    return;
  }

  var oldPoint;
  var newPoint = startingPoint;
  var i=0;

  do{
    oldPoint = newPoint;
    newPoint = oldPoint - f(oldPoint)/f_deriv(oldPoint);
    i++;
  } while ( Math.abs(newPoint-oldPoint) > accuracy);

  var newtonsAnswer = 'Answer: ' + newPoint.toFixed(-Math.floor(Math.log10(accuracy))) + '<br />Iterations count: ' + i;
  $('#newtonsAnswer').html(newtonsAnswer);
  $('#newtonsAnswer').show();
}
