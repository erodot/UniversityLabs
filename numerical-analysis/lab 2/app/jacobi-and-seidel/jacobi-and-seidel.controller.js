'use strict';

angular.module('NMApp')
.controller('JacobiAndSeidelController', JacobiAndSeidelController);

JacobiAndSeidelController.$inject = ['$scope', 'JacobiService', 'SeidelService', 'MatrixService'];

function JacobiAndSeidelController($scope, JacobiService, SeidelService, MatrixService){
  $scope.precision = 3;
  $scope.rounding = 3;

  $scope.solveJacobiAndSeidel = function () {
    if($scope.generateDominantingDiagonalFlag){
      $scope.matrix = MatrixService.createRandomDiagMatrix($scope.dimension);
    }
    else {
      $scope.matrix = MatrixService.createRandomMatrix($scope.dimension);
    }
    $scope.vector = MatrixService.createRandomVector($scope.dimension);

    var params = {
      matrix: $scope.matrix,
      vector: $scope.vector,
      precision: $scope.precision,
      rounding: $scope.rounding,
      preparedAnswerFlag: $scope.preparedAnswerFlag
    };

    $scope.jacobi = JacobiService.solve(params);
    $scope.seidel = SeidelService.solve(params);

    $scope.vector = params.vector;

    $scope.matrix.forEach(function(matrixRow, index){
      matrixRow.push('= ');
      matrixRow.push($scope.vector[index]);
      matrixRow.push(' | ');
      matrixRow.push(MatrixService.computeError(matrixRow, $scope.vector[index], $scope.jacobi.answer));
      matrixRow.push(MatrixService.computeError(matrixRow, $scope.vector[index], $scope.seidel.answer));
    });
  }
}
