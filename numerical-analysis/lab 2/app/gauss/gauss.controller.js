'use strict';

angular.module('NMApp')
.controller('GaussController', GaussController);

GaussController.$inject = ['$scope', 'GaussService', 'MatrixService'];

function GaussController($scope, GaussService, MatrixService){

  $scope.precision = 3; //default precision

  $scope.solveGauss = function(){
    $scope.matrix = MatrixService.createRandomMatrix($scope.dimension);
    $scope.vector = MatrixService.createRandomVector($scope.dimension);
    $scope.answer = GaussService.solve($scope.matrix, $scope.vector, $scope.precision);
    $scope.matrix.forEach(function(matrixRow, index){
      matrixRow.push('= ');
      matrixRow.push($scope.vector[index]);
    });
    $scope.matrix.push($scope.answer);
  };

  $scope.toggleTable = function(){
    $scope.toggle = !$scope.toggle;
  }
}
