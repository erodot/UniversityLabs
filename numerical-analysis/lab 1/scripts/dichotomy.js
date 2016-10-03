function doDichotomy(){
  var leftLimit = +document.getElementById('dichotomyLeftLimit').value;
  var rightLimit = +document.getElementById('dichotomyRightLimit').value;
  var accuracy = +document.getElementById('dichotomyAccuracy').value;

  if(f(leftLimit)*f(rightLimit)>=0){
    alert('Function of borders must be in different signs!');
    return;
  }
  if((rightLimit - leftLimit) <= accuracy){
    alert('Try to choose more accurate accuracy!');
    return;
  }
  if(rightLimit <= leftLimit){
    alert('Select correct interval!');
    return;
  }
  if(accuracy < Math.pow(10,-10)){
    console.log(accuracy);
    alert('It seems like our compiler is too week to achieve your accuracy :\'(');
    return;
  }

  var i=0;

  while((rightLimit - leftLimit) > accuracy) {
    var medium = (rightLimit + leftLimit) / 2;
    if(f(leftLimit)*f(medium)<0){
      rightLimit = medium;
    } else if(f(leftLimit)*f(medium) == 0){
      leftLimit = medium;
      rightLimit = medium;
      break;
    } else {
      leftLimit = medium;
    }
    i++;
  }

  var dichotomyAnswer = 'Answer: [' + leftLimit.toFixed(-Math.floor(Math.log10(accuracy))) + ', ' + rightLimit.toFixed(-Math.floor(Math.log10(accuracy))) + ']' + '<br />Iterations count: ' + i;
  dichotomyAnswer += '<br />f(left): ' + f(leftLimit);
  $('#dichotomyAnswer').html(dichotomyAnswer);
  $('#dichotomyAnswer').show();
}
