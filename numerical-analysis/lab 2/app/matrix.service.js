'use strict';

angular.module('NMApp')
.service('MatrixService', MatrixService);

function MatrixService(){

  var service = this;

  service.createRandomVector = function(dimension){
    var vector = [];
    for (var i = 0; i < dimension; i++) {
        vector[i] = Math.round(100 * (2 * Math.random() - 1));
    }
    return vector;
  }

  service.createRandomMatrix = function(dimension){
    var matrix = [];
    for (var i = 0 ; i < dimension; i++) {
        matrix[i] = [];
        for (var j = 0; j < dimension; j++) {
            matrix[i][j] = Math.round(100 * (2 * Math.random() - 1));
        }
    }
    return matrix;
  }

  service.createRandomDiagMatrix = function(dimension){
    console.log('createRandomDiagMatrix() is working...');
    var matrix = service.createRandomMatrix(dimension);
    for(var i=0; i<dimension; i++){
      for(var j=0; j<dimension; j++){
        matrix[i][i]+=Math.abs(matrix[i][j]);
      }
    }
    return matrix;
  }

}
