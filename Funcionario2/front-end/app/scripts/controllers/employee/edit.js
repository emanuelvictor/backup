'use strict';

/**
 * @ngdoc function
 * @name funcionario2App.controller:EmployeeEditCtrl
 * @description
 * # EmployeeEditCtrl
 * Controller of the funcionario2App
 */
angular.module('funcionario2App')
  .controller('EmployeeEditCtrl', function ($scope, Employee, Position, $routeParams, $location) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.init = function(){
    	//TODO encapsular este comportamente em uma factory
    	$scope.employee = Employee.get({id:$routeParams.id});
      $scope.positions = Position.getAll();
    };

    $scope.save = function(employee){
    	employee.$save(function(){
    		$location.path('/employee/find');
    	});
    };

    $scope.remove = function(){
    	Employee.remove({id:$routeParams.id});
    };
    
  });
