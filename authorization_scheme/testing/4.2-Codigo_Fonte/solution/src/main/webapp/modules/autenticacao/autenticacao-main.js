(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('authentication', ['ngMaterial', 'ui.router', 'eits-dwr-broker', 'eits-md', 'ngCookies']);

	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider ) {
		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		
		//-------
		//URL Router
		//-------

		//HOME
        $urlRouterProvider.otherwise("/login");

        $stateProvider.state('signin',{
			abstract: true,
        	url : "/",
        	controller : 'SigninController as signinController',
			template: '<div ui-view/>'

        }).state('login',{
			url : "/login",
			templateUrl : "./modules/autenticacao/views/signin/signin-form.jsp"
		}).state('recover',{
			url : "/recuperar",
			templateUrl : "./modules/autenticacao/views/signin/recover-password.jsp"
		}).state('recover-success',{
			url : "/senha-enviada",
			templateUrl : "./modules/autenticacao/views/signin/recover-password-success.jsp"
		});
	});

	module.run( function( $rootScope, $window, $state, $stateParams ) {
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	angular.element(document).ready( function() {
		angular.bootstrap( document, ['authentication']);
	});

})(window, window.angular);