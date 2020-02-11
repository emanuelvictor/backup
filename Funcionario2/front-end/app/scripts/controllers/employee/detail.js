'use strict';

/**
 * @ngdoc function
 * @name funcionario2App.controller:EmployeeDetailCtrl
 * @description
 * # EmployeeDetailCtrl
 * Controller of the funcionario2App
 */
angular.module('funcionario2App')
  .controller('EmployeeDetailCtrl', function ($location, $scope, $routeParams, Employee) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.init = function(){
    	$scope.employee = Employee.get({id:$routeParams.id});
    };

    $scope.edit = function(){
    	$location.path('employee/edit/'+$routeParams.id);
    };
  });
