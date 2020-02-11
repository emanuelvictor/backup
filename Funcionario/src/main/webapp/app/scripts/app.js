angular
  .module('App', ['ngMaterial', /*'ngCookies',*/ /*'ngRoute',*/ 'ui.router'])
.config(function($mdThemingProvider, $stateProvider, $urlRouterProvider) {
  
  $urlRouterProvider.otherwise('/funcionario');
  
  $stateProvider
    .state( 'funcionario', {
      url         : '/funcionario',
      templateUrl : 'view/funcionario/find.html',
      controller  : 'FindCtrl'
    }).state( 'new',{
      url         : '/funcionario/new',
      templateUrl : 'view/funcionario/new.html',
      controller  : 'NewCtrl'
    }).state( 'detail',{
      url         : '/funcionario/:id',
      templateUrl : 'view/funcionario/detail.html',
      controller  : 'DetailCtrl'
    }).state('autenticacao', { 
      url         :'/autenticacao',
      templateUrl :'view/autenticacao/login.html',
      controller  :'AutenticacaoCtrl'
    });
  
  $mdThemingProvider.theme('default')
  .primaryPalette('pink')
  .accentPalette('blue');
  
}).run(function($rootScope, $mdDialog, $location, /*$cookieStore,*/ $state, $http, $mdToast){
  
   $rootScope.toast = $mdToast.simple()
            .content('Erro ao realizar tranzação')
            .action('OK')
            .highlightAction(false)
            .position('top right');
  
  funcionarioService.getCurrentUser({
    callback : function(result) {
      $rootScope.funcionario = result;
    },
    errorHandler : function(message, exception) {
      $scope.message = {type:"error", text: message};
    }
  });
  
  $rootScope.gerenciarCargos = function() {
    $mdDialog.show({
      controller: 'CargoController',
      templateUrl: './view/cargo/gerenciar_cargo.tmpl.html',
    });
  };

  $rootScope.gerenciarEmpresas = function() {
    $mdDialog.show({
      controller: 'EmpresaController',
      templateUrl: './view/empresa/gerenciar_empresa.tmpl.html',
    });
  };

});