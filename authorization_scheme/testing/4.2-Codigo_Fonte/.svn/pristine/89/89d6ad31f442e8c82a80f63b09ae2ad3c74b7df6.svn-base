(function (angular) {
    'use strict';

/**
 * Controller do caso de uso de alterar e resetar senha
 
 * @param $scope
 * @param $state
 */
angular.module('autenticador')
        .controller('AlterarSenhaController', function ($scope, $state, model, $importService, $mdToast, $mdDialog) {


  /**
  * Serviços importados do DWR
  */
  $importService("usuarioService");

    
  $scope.model = model;
            
  $scope.hide = function (answer) {
    $mdDialog.hide(answer);
  };            

  $scope.redefinir = function (newPassword) {
    $scope.enviandoEmail = true;
    usuarioService.changeSenhaUsuario($scope.model.entity.id, newPassword, {
      callback: function () {
        $mdDialog.hide();
        $mdToast.showSimple( 'Senha alterada com sucesso' );
      },
      errorHandler : function(message, exception) {
        $mdToast.showSimple( message );
        $scope.enviandoEmail = false;
      }
    });
  };
            


});


}(window.angular));

