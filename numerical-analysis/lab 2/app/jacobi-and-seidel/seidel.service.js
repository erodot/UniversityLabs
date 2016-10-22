(function() {
  'use strict';

  angular.module('NMApp')
  .service('SeidelService', SeidelService);

  // var params = {
  //   matrix: $scope.matrix,
  //   vector: $scope.vector,
  //   precision: $scope.precision,
  //   rounding: $scope.rounding,
  //   preparedAnswerFlag: $scope.preparedAnswerFlag
  // };

  function SeidelService(){
    this.solve = function(params){
      if(params.preparedAnswerFlag){
        return solvePrepared(params);
      }
      else {
        return solveUnprepared(params);
      }
    };
  }

  var solvePrepared = function(params){
    var A = [];
    for(var i = 0; i<params.matrix.length; i++){
      A[i] = [];
      for(var j = 0; j<params.matrix[i].length; j++){
        A[i].push(params.matrix[i][j]);
      }
    }

    var answer = [];
    params.vector.forEach(function(item){
      answer.push(item);
    });

    var b = [];
    for(var i=0; i<params.vector.length; i++){
      var sum=0;
      for(var j=0; j<params.vector.length; j++){
        sum+=A[i][j]*answer[j];
      }
      b.push(sum);
    }

    params.vector = b;

    var count = 0;

    var x = [];
    for(var i=0; i<params.vector.length; i++){
      x.push(b[i]*Math.random());
    }

    // norm ||x-answer||
    while(linear_norm(x,answer)>Math.pow(10,(-1)*params.precision)){
      count++;

      for(var i=0; i<x.length; i++){

        // b-Ax->sum
        var sum = b[i];
        for(var j=0; j<x.length; j++){
          sum-=x[j]*A[i][j];
        }
        x[i] = (sum+x[i]*A[i][i])/A[i][i];
      }
    }

    for(var i=0; i<x.length; i++){
      x[i]=x[i].toFixed(params.rounding);
    }

    return {
      right_answer: answer,
      answer: x,
      count: count
    };
  };

  var solveUnprepared = function(params){

    var A = [];
    for(var i = 0; i<params.matrix.length; i++){
      A[i] = [];
      for(var j = 0; j<params.matrix[i].length; j++){
        A[i].push(params.matrix[i][j]);
      }
    }

    var b = [];
    params.vector.forEach(function(item){
      b.push(item);
    });


    var count = 0;

    var x = [];
    for(var i=0; i<params.vector.length; i++){
      x.push(b[i]/100);
    }

    // norm ||Ax-b||
    while(norm(A,x,b)>Math.pow(10,(-1)*params.precision)){
      count++;

      for(var i=0; i<x.length; i++){

        // b-Ax->sum
        var sum = b[i];
        for(var j=0; j<x.length; j++){
          sum-=x[j]*A[i][j];
        }
        x[i] = (sum+x[i]*A[i][i])/A[i][i];
      }
    }

    for(var i=0; i<x.length; i++){
      x[i]=x[i].toFixed(params.rounding);
    }

    return {
      answer: x,
      count: count
    };
  };

  // norm ||Ax-b||
  var norm = function(A,x,b){
    var c=[];
    for(var i=0; i<b.length; i++){
      c.push(0);
      for(var j=0; j<b.length; j++){
        c[i]+=A[i][j]*x[j];
      }
      c[i]-=b[i];
    }

    var sum=0;
    for(var i=0; i<c.length; i++){
      sum+=c[i]*c[i];
    }

    return Math.sqrt(sum);
  }

  var linear_norm = function(x,answer){
    var sum = 0;
    for(var i=0; i<x.length; i++){
      sum += (x[i]-answer[i])*(x[i]-answer[i]);
    }

    return Math.sqrt(sum);
  }
})();
