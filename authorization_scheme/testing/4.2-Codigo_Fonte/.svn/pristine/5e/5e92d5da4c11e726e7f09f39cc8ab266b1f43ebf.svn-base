(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('autenticador', ['ngMaterial', 'ui.router', 'eits-dwr-broker', 'eits-md',
                                                 'eits.controls.dropdown', 'ui.tree', 'mdDataTable', 'ngMdIcons']);

	/**
	 *
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $mdThemingProvider ) {

        //-------
        //CONFIGURAÇÕES DE TEMA
        //-------
        $mdThemingProvider.theme('default')
            .primaryPalette('light-blue', {
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
        $urlRouterProvider.otherwise("/inicio/admin");


		$stateProvider.state('inicio',{
			abstract: true,
			url : "/inicio",
			controller : 'HomeController as homeController',
			template : '<div ui-view/>'
		}).state('inicio.admin',{
			url : "/admin",
			templateUrl : "./modules/autenticador/views/home/home-index.jsp"
		}).state('inicio.config',{
			url : "/configuracoes",
			templateUrl : "./modules/autenticador/views/configuracoes/configuracao-form.jsp"
		}).state('inicio.account',{
            url : "/minhaconta",
			//controller : 'UsuarioController as usuarioController',
            templateUrl : "./modules/autenticador/views/home/home-my-account.jsp"
        });

        //APLICATIVO
        $stateProvider.state('aplicativo',{
        	abstract: true,
        	url : "/aplicativo",
        	template: '<div ui-view/>',
        	controller : 'AplicativoController as aplicativoController'
        })
        .state('aplicativo.list',{
        	url : "/listar",
        	templateUrl : "./modules/autenticador/views/aplicativo/aplicativo-list.jsp"
        })
        .state('aplicativo.add',{
        	url : "/inserir",
        	templateUrl : "./modules/autenticador/views/aplicativo/aplicativo-form.jsp"
        })
        .state('aplicativo.edit',{
        	url : "/editar/{id:[0-9]{1,10}}",
        	templateUrl : "./modules/autenticador/views/aplicativo/aplicativo-form.jsp"
        })
        .state('aplicativo.detail',{
        	url : "/{id:[0-9]{1,10}}",
        	templateUrl : "./modules/autenticador/views/aplicativo/aplicativo-detail.jsp"
        });

      //USUARIO
        $stateProvider.state('usuario',{
        	abstract: true,
        	url : "/usuario",
        	template: '<div ui-view/>',
        	controller : 'UsuarioController as usuarioController'
        })
        .state('usuario.list',{
        	url : "/listar",
        	templateUrl : "./modules/autenticador/views/usuario/usuario-list.jsp"
        })
        .state('usuario.add',{
        	url : "/inserir",
        	templateUrl : "./modules/autenticador/views/usuario/usuario-form.jsp"
        })
        .state('usuario.edit',{
        	url : "/editar/{id:[0-9]{1,10}}",
        	templateUrl : "./modules/autenticador/views/usuario/usuario-form.jsp"
        })
        .state('usuario.detail',{
			url : "/{id:[0-9]{1,10}}",
			templateUrl : "./modules/autenticador/views/usuario/usuario-detail.jsp"
		});

	});

	/**
	 *
	 */
	module.run( function( $rootScope, $window, $state, $stateParams, $mdSidenav ) {
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
		
		$rootScope.handlerMenuButton = function () {
		  if($state.current.name.indexOf('list')  > -1 || $state.current.name.indexOf('inicio')  > -1 ){
			$mdSidenav('left').toggle();
		  }else{
			$rootScope.backHandler();
		  }
		};
	});

	/**
	 *
	 */
	angular.element(document).ready( function() {
		angular.bootstrap( document, ['autenticador']);
	});

})(window, window.angular);