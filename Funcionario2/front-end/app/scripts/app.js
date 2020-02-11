'use strict';

/**
 * @ngdoc overview
 * @name funcionario2App
 * @description
 * # funcionario2App
 *
 * Main module of the application.
 */
angular
  .module('funcionario2App', [
    'ngMaterial',
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngResource',
    'ngRoute'
  ])
  .config(function ($routeProvider, $httpProvider,$mdThemingProvider) {

    $routeProvider
      .when('/', {
        templateUrl: 'views/employee/find.html',
        controller: 'EmployeeFindCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/security/authentication', {
        templateUrl: 'views/security/authentication.html',
        controller: 'SecurityAuthenticationCtrl'
      })
      .when('/employee/new', {
        templateUrl: 'views/employee/new.html',
        controller: 'EmployeeNewCtrl'
      })
      .when('/employee/find', {
        templateUrl: 'views/employee/find.html',
        controller: 'EmployeeFindCtrl'
      })
      .when('/employee/edit/:id', {
        templateUrl: 'views/employee/edit.html',
        controller: 'EmployeeEditCtrl'
      })
      .when('/employee/detail/:id', {
        templateUrl: 'views/employee/detail.html',
        controller: 'EmployeeDetailCtrl'
      })
      //Errors handlers
      .when('/errors/404', {
        templateUrl: 'views/errors/404.html',
        controller: 'Errors404Ctrl'
      })
      .when('/errors/400', {
        templateUrl: 'views/errors/400.html',
        controller: 'Errors400Ctrl'
      })
      .when('/errors/500', {
        templateUrl: 'views/errors/500.html',
        controller: 'Errors500Ctrl'
      })
      .when('/errors/401', {
        templateUrl: 'views/errors/401.html',
        controller: 'Errors401Ctrl'
      })
      .when('/errors/403', {
        templateUrl: 'views/errors/403.html',
        controller: 'Errors403Ctrl'
      })
      .otherwise({
        redirectTo: '/errors/404'
      });

      //Inserindo o interceptor
      $httpProvider.interceptors.push('requestErrorInterceptor', 'successInterceptor');

      $mdThemingProvider.theme('default')
      .primaryPalette('pink')
      .accentPalette('blue');


  }).run(function($rootScope, $mdDialog, $http, $cookieStore, $cookies, $location){
  

    if ($cookieStore.get('SESSION')) {
      $http.defaults.headers.common.Authorization = 'Bearer ' + $cookieStore.get('SESSION').access_token;
      $http.get('http://funcionario2@localhost:8080/api/oauth/principal/'+$cookieStore.get('SESSION').access_token)
        .success(function(data){
          $rootScope.user = data;
        });
    };

    $rootScope.logout = function() {
      $http.get('http://localhost:8080/api/oauth/revoke/'+$cookieStore.get('SESSION').access_token)
        .success(function() {
          $rootScope.user = {};
          $location.path('/security/authentication');
      });
    };

    $rootScope.managePositions = function() {
      $mdDialog.show({
        controller: 'FunctionController',
        templateUrl: '/app/view/function/manage_function.tmpl.html',
      });
    };

}).value('BaseURL', 'http://localhost:9000/api');