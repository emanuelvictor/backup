'use strict';

/**
 * @ngdoc function
 * @name funcionario2App.controller:EmployeeNewCtrl
 * @description
 * # EmployeeNewCtrl
 * Controller of the funcionario2App
 */
angular.module('funcionario2App')
  .controller('EmployeeNewCtrl', function ($scope, $location, $rootScope, Employee, Position) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.positions = Position.getAll();

    $scope.employee = {}
    $rootScope.nameButtonEmployee = 'Funcionários';

    $rootScope.handler = function(){
      $location.path('/employee/find');
    };

    //TODO
    $scope.selectPosition = function() {
      $mdDialog.show({
        controller: 'FunctionController',
        templateUrl: 'app/view/function/function.tmpl.html',
      })
      .then(function(answer) {
        $scope.employee.position = answer;
      });
    };
    
    $scope.save = function(){
     Employee.save($scope.employee, function(data){
  	   $location.path('/employee/find');
     });
    };
  });
