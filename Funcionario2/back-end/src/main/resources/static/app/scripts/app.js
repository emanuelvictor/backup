angular.module('App', ['ngMaterial', 'ui.router', 'ngResource', 'ngCookies'])
.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider) {
  
  $urlRouterProvider.otherwise('/funcionario');
  
  $stateProvider
    .state( 'employe', {
      url         : '/funcionario',
      templateUrl : '/app/view/employe/find.html',
      controller  : 'FindCtrl'
    }).state( 'new',{
      url         : '/funcionario/novo',
      templateUrl : '/app/view/employe/new.html',
      controller  : 'NewCtrl'
    }).state( 'detail',{
      url         : '/funcionario/:id',
      templateUrl : '/app/view/employe/detail.html',
      controller  : 'DetailCtrl'
    });
  
  $mdThemingProvider.theme('default')
  .primaryPalette('pink')
  .accentPalette('blue');
  
}).run(function($rootScope, $mdDialog, $mdToast, $http, $cookieStore, $cookies){
  
  $http.defaults.headers.common.Authorization = 'Bearer ' + $cookieStore.get('token').access_token;

  $rootScope.logout = function() {
	  $http.get('http://localhost:8080/logout/'+$cookieStore.get('token').access_token)
	  .success(function(data) {
	    $http.defaults.headers.common.Authorization = 'Bearer ';
		window.location = '/app';
	  });
  };

  $http.get('http://localhost:8080/verificaessaporra')
  .success(function() {
  }).error(function(){
	  window.location = '/app';
  });
  
//  if (!$cookieStore.get('token')) {
//	  window.location = '/app';
//  };

  $rootScope.toast = $mdToast.simple()
  .content('Erro ao realizar tranzação')
  .action('OK')
  .highlightAction(false)
  .position('top right');
  
  
  $rootScope.managePositions = function() {
    $mdDialog.show({
      controller: 'FunctionController',
      templateUrl: '/app/view/function/manage_function.tmpl.html',
    });
  };

});