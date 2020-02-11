'use strict'

angular.module('App')
.controller('NewCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {
	
  $scope.newFuncionario = {}
  $rootScope.nameButtonFuncionario = 'Funcionários';

  $rootScope.handler = function(){
    $state.go('funcionario');
  };

  //TODO
  $scope.selectCargo = function() {
    $mdDialog.show({
      controller: 'CargoController',
      templateUrl: './view/cargo/empresa.tmpl.html',
    })
    .then(function(answer) {
      $scope.newFuncionario.cargo = answer;
    });
  };
  
  $scope.save = function(){
    funcionarioService.save($scope.newFuncionario, {
      callback : function(result) {
        $state.go('funcionario');
      }, errorHandler : function(message, exception){
        $scope.toast.content('Erro ao salvar informações, verifique se o email não está cadastrado');
        $mdToast.show($scope.toast);
      }
    });
  };
                            
})
.controller('DetailCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {

  funcionarioService.find($state.params.id, {
    callback : function(result) {
      $scope.editFuncionario = result;
      
      $scope.editFuncionario.password = null;
    
      $scope.$apply();
    }
  });
  
  $rootScope.nameButtonFuncionario = 'Funcionários';

  $rootScope.handler = function(){
    $state.go('funcionario');
  };

	//TODO
  $scope.selectCargo = function() {
    
    $mdDialog.show({
      controller: 'CargoController',
      templateUrl: './view/cargo/empresa.tmpl.html'
    })
    .then(function(answer) {
      $scope.editFuncionario.cargo = answer;
    });
    
  };
  
  $scope.save = function(){
    funcionarioService.save($scope.editFuncionario, {
      callback : function(result) {
        console.log($scope.editFuncionario);
        $state.go('funcionario');
      }, errorHandler : function(message, exception){
        $scope.toast.content('Erro ao salvar informações, verifique se o email não está cadastrado');
        $mdToast.show($scope.toast);
      }
    });
  };
  
  $scope.delete = function(funcionario){
    $mdDialog.show({
      controller: 'ConfirmCtrl',
      templateUrl: './view/funcionario/confirm.tmpl.html'
    })
    .then(function(answer) {
      if(answer){
        funcionarioService.destroy(funcionario.id,{
          callback : function(result) {
            $state.go('funcionario');
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
.controller('FindCtrl', function($scope, $rootScope, $state) {
  $rootScope.nameButtonFuncionario = 'Novo funcionário';

  $scope.init = function(){
    funcionarioService.find({
      callback : function(result) {
        $scope.funcionarios = result;
        $scope.$apply();
      }, errorHandler : function( a, b  ){
        console.log(a); 
      }
    });
  };

  $rootScope.handler = function(){
    $state.go('new');
  };

  $scope.edit = function(funcionario){
    $state.go('detail',{id:funcionario.id});
  };  
	
});
