(function (angular) {
    'use strict';

    /**
     * Controller do caso de uso de alterar e resetar senha

     * @param $scope
     * @param $state
     */
    angular.module('autenticador')
        .controller('AlterarSenhaController', function ($scope, $state, usuario, $importService, $mdToast, $mdDialog) {


            /**
             * Serviços importados do DWR
             */
            $importService("usuarioService");


            $scope.isFormSubmit = false;

            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.redefinir = function (form) {

              if (form.$valid) {

                $scope.isFormSubmit = false;
                $scope.enviandoEmail = true;

                if(usuario.length) {
                  usuario.forEach(function (usuario) {

                    usuarioService.resetSenhaUsuario(usuario.id, $scope.newPassword, {
                      callback: function () {
                        $mdDialog.hide();
                        $mdToast.showSimple('Senha alterada com sucesso');
                      },
                      errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.enviandoEmail = false;
                      }
                    });

                  });
                } else {

                  usuarioService.resetSenhaUsuario(usuario.id, $scope.newPassword, {
                    callback: function () {
                      $mdDialog.hide();
                      $mdToast.showSimple('Senha alterada com sucesso');
                    },
                    errorHandler: function (message, exception) {
                      $mdToast.showSimple(message);
                      $scope.enviandoEmail = false;
                    }
                  });
                }

              } else {

                $scope.isFormSubmit = true;
                $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
              }

            };


        });


}(window.angular));

