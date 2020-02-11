(function (angular) {
    'use strict';

    /**
     *
     * @param $scope
     * @param $state
     */
    angular.module('autenticador')
        .controller('BloquearUsuariosController', function ($scope, idsUsuarios, $importService, $mdToast, $mdDialog) {


            /**
             * Serviços importados do DWR
             */
            $importService("usuarioService");

            $scope.dataBloqueio = new Date();

            $scope.dataDesbloqueio = null;
            
            $scope.bloquear = function (dataBloqueio, dataDesbloqueio) {
                usuarioService.bloquearUsuarios(idsUsuarios, dataBloqueio, dataDesbloqueio, {
                    callback: function (result) {
                        $scope.block(result);
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                    }
                });
            };

            $scope.block = function (result) {
                $mdDialog.hide(result);
            };

            $scope.cancel = function () {
                $mdDialog.hide(false);
            };

        });


}(window.angular));

