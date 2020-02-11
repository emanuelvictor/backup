(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('authentication', ['ngMaterial', 'ui.router', 'eits-dwr-broker', 'eits-md', 'ngMessages']);

	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $mdThemingProvider ) {

    //-------
    //CONFIGURAÇÕES DE TEMA
    //-------
    $mdThemingProvider.theme('default')
      .primaryPalette('blue', {
        'default': '800', // by default use shade 400 from the pink palette for primary intentions
        'hue-1': '600', // use shade 100 for the <code>md-hue-1</code> class
        'hue-2': '400', // use shade 600 for the <code>md-hue-2</code> class
        'hue-3': 'A100' // use shade A100 for the <code>md-hue-3</code> class
      })
      // If you specify less than all of the keys, it will inherit from the
      // default shades
      .accentPalette('red', {
        'default': 'A200' // use shade 200 for default, and keep all other shades the same
      });

		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		
		//-------
		//URL Router
		//-------

		//HOME
        $urlRouterProvider.otherwise("/signin/login");

        $stateProvider.state('signin',{
			abstract: true,
        	url : "/signin",
        	controller : 'SigninController as signinController',
			template: '<div ui-view/>'
        }).state('signin.login',{
			url : "/login",
			templateUrl : "./modules/autenticacao/views/signin/signin-form.jsp"
		}).state('signin.recover',{
			url : "/recuperar",
			templateUrl : "./modules/autenticacao/views/signin/recover-password.jsp"
		}).state('signin.recover-success',{
			url : "/senha-enviada",
			templateUrl : "./modules/autenticacao/views/signin/recover-password-success.jsp"
		}).state('signin.redefine',{
			url : "/redefinir-senha",
			templateUrl : "./modules/autenticacao/views/signin/redefine-password.jsp"
		});
	});

  module.directive('sameAs', function() {
    return {
      require: 'ngModel',
      link: function(scope, elm, attrs, ctrl) {
        ctrl.$parsers.unshift(function(viewValue) {
          if (viewValue === scope[attrs.sameAs]) {
            ctrl.$setValidity('sameAs', true);
            return viewValue;
          } else {
            ctrl.$setValidity('sameAs', false);
            return undefined;
          }
        });
      }
    };
  });

	module.run( function( $rootScope, $window, $state, $stateParams ) {
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	angular.element(document).ready( function() {
		angular.bootstrap( document, ['authentication']);
	});

})(window, window.angular);