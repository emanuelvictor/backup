(function (angular) {
    'use strict';

    /**
     *
     * @param $scope
     * @param $state
     */
angular.module('autenticador')
        .controller('BloquearUsuariosController', function ($scope, $importService, $mdToast, $mdDialog) {


  /**
   * Serviços importados do DWR
   */
  $importService("usuarioService");

  $scope.dataBloqueio = new Date();

  $scope.bloquear = function (dataBloqueio,dataDesbloqueio) {
    usuarioService.bloquearUsuarios(usuarios, dataBloqueio, dataDesbloqueio, {
      callback: function (result) {
        $scope.answer(result);
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
      }
    });
  };

  $scope.answer = function (answer) {
    $mdDialog.hide(answer);
  };
    
});


}(window.angular));

