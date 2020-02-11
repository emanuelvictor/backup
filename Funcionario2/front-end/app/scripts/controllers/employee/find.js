'use strict';

/**
 * @ngdoc function
 * @name funcionario2App.controller:EmployeeFindCtrl
 * @description
 * # EmployeeFindCtrl
 * Controller of the funcionario2App
 */
angular.module('funcionario2App')
  .controller('EmployeeFindCtrl', function ($rootScope, $scope, $location, Employee) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

		$rootScope.nameButtonEmployee = 'Novo funcionário';

	  $scope.init = function(){
	    $scope.employees = Employee.getAll();
	  };

	  $rootScope.handler = function(){
	  	$location.path('employee/new');
	  };

	  $scope.edit = function(employee){
	  	$location.path('employee/detail/'+employee.id);
	  };  
	

  });
