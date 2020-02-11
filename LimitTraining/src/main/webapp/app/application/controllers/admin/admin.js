'use strict'

angular.module('App')
.controller('NewUsuarioCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {
	
  $scope.newUsuario = {}
  $rootScope.nameButtonFuncionario = 'Usuários';

  $rootScope.handler = function(){
    $state.go('usuario');
  };

  
  $scope.save = function(){
    usuarioService.save($scope.newUsuario, {
      callback : function(result) {
        $state.go('usuario');
      }, errorHandler : function(message, exception){
        $mdToast.show($scope.toast);
      }
    });
  };
                            
})
.controller('DetailUsuarioCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {

  usuarioService.find($state.params.id, {
    callback : function(result) {
      $scope.editUsuario = result;
      
      $scope.editUsuario.password = null;
    
      $scope.$apply();
    }
  });
  
  $rootScope.nameButtonFuncionario = 'Funcionários';

  $rootScope.handler = function(){
    $state.go('usuario');
  };

  
  $scope.save = function(){
    usuarioService.save($scope.editUsuario, {
      callback : function(result) {
        console.log($scope.editUsuario);
        $state.go('usuario');
      }, errorHandler : function(message, exception){
        $scope.toast.content('Erro ao salvar informações, verifique se o email não está cadastrado');
        $mdToast.show($scope.toast);
      }
    });
  };
  
  $scope.delete = function(usuario){
    $mdDialog.show({
      controller: 'ConfirmCtrl',
      templateUrl: '../../presentation/view/admin/confirm.tmpl.html'
    })
    .then(function(answer) {
      if(answer){
        usuarioService.destroy(usuario.id,{
          callback : function(result) {
            $state.go('usuario');
          }
        });    
      }
    });
    
  };
	
})
.controller('ConfirmCtrl', function($scope,$mdDialog) {

  $scope.sim = function(){
    $mdDialog.hide('sim');
  };
  
  $scope.nao = function(){
    $mdDialog.hide();
  };

})
.controller('FindUsuarioCtrl', function($scope, $rootScope, $state) {
  $rootScope.nameButtonFuncionario = 'Novo professor(a)';

  $scope.init = function(){
    usuarioService.find({
      callback : function(result) {
        $scope.usuarios = result;
        $scope.$apply();
      }, errorHandler : function( a, b  ){
        console.log(a); 
      }
    });
  };

  $rootScope.handler = function(){
    $state.go('new');
  };

  $scope.edit = function(usuario){
    $state.go('detail',{id:usuario.id});
  };  
	
});
