angular.module('App')
.controller('EmpresaController', function($scope, $rootScope, $mdDialog, $mdToast) {

  
  
  $scope.edit = function(empresa){
    $scope.editEmpresa = angular.copy(empresa);
  };
  
  $scope.clear = function(){
    $scope.editEmpresa = {cnpj:null};
  };
  
  $scope.clear();
  
  $scope.select = function(empresa){
    $mdDialog.hide(empresa);
  };

  $scope.delete = function(empresa){
    empresaService.destroy(empresa.id, {
      callback : function() {
        $scope.clear();
        $scope.init();
      },
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao remover informações, certifique-se de que nenhum funcionário está vinculado á esse empresa');
        $mdToast.show($rootScope.toast);
      }
    });
  };

  $scope.save = function(empresa){
    empresaService.save(empresa, {
      callback : function(result) {
        $scope.editEmpresa = {};
        $scope.init();
      },
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao salvar informações, verifique se não há um empresa cadastrado com o mesmo cnpj');
        $mdToast.show($rootScope.toast);
      }
    });
  };

  $scope.search = function(empresa){
    if(empresa.cnpj){
      empresaService.find(empresa.cnpj, {
        callback : function(result) {
          $scope.empresas = result;
          $scope.$apply();
        }
      });
    }else{
      $scope.clear();
      $scope.init();
    }
  };
    

  $scope.init = function(){
    empresaService.find({
      callback : function(result) {
        $scope.empresas = result;
        $scope.$apply();
      }
      ,
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao salvar informações, verifique se não há um empresa cadastrado com o mesmo cnpj');
        $mdToast.show($rootScope.toast);
      }
    });
  };

});
