angular.module('App')
.controller('CargoController', function($scope, $rootScope, $mdDialog, $mdToast) {

  
  
  $scope.edit = function(cargo){
    $scope.editCargo = angular.copy(cargo);
  };
  
  $scope.clear = function(){
    $scope.editCargo = {nome:null};
  };
  
  $scope.clear();
  
  $scope.select = function(cargo){
    $mdDialog.hide(cargo);
  };

  $scope.delete = function(cargo){
    cargoService.destroy(cargo.id, {
      callback : function() {
        $scope.clear();
        $scope.init();
      },
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao remover informações, certifique-se de que nenhum funcionário está vinculado á esse cargo');
        $mdToast.show($rootScope.toast);
      }
    });
  };

  $scope.save = function(cargo){
    cargoService.save(cargo, {
      callback : function(result) {
        $scope.editCargo = {};
        $scope.init();
      },
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao salvar informações, verifique se não há um cargo cadastrado com o mesmo nome');
        $mdToast.show($rootScope.toast);
      }
    });
  };

  $scope.search = function(cargo){
    if(cargo.nome){
      cargoService.find(cargo.nome, {
        callback : function(result) {
          $scope.cargos = result;
          $scope.$apply();
        }
      });
    }else{
      $scope.clear();
      $scope.init();
    };
  };
    

  $scope.init = function(){
    cargoService.find({
      callback : function(result) {
        $scope.cargos = result;
        $scope.$apply();
      }
      ,
      errorHandler : function(message, exception) {
        $rootScope.toast.content('Erro ao salvar informações, verifique se não há um cargo cadastrado com o mesmo nome');
        $mdToast.show($rootScope.toast);
      }
    });
  };

});
