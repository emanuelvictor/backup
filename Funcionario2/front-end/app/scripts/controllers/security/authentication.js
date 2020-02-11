'use strict';

/**
 * @ngdoc function
 * @name funcionario2App.controller:SecurityAuthenticationCtrl
 * @description
 * # SecurityAuthenticationCtrl
 * Controller of the funcionario2App
 */
angular.module('funcionario2App')
  .controller('SecurityAuthenticationCtrl', function ($scope, $http, $cookieStore, $location, $rootScope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

   	$scope.user = {};

    $scope.authenticate = function() {

      var config = {
        headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8', 'Accept': 'application/json', 'Authorization':'Basic ZnVuY2lvbmFyaW8yOjEyMzQ1Ng=='}
      };

      $http.post('http://funcionario2@localhost:8080/api/oauth/token?grant_type=password','&username='+$scope.user.email+'&password='+$scope.user.password , config).
        success(function(data, status, headers, config) {
        	
          $http.defaults.headers.common.Authorization = 'Bearer ' + data.access_token;

          $cookieStore.put('SESSION', data);

          $http.get('http://funcionario2@localhost:8080/api/oauth/principal/'+data.access_token)
            .success(function(data){
              $rootScope.user = data;
            });
            
          $location.path('/');
        });
    };

  });

