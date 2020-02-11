(function (angular) {
    'use strict';

    /**
     *    Controller do caso de uso vincular aplicativos á usuários
     *
     * @param $scope
     * @param $state
     */
    angular.module('autenticador')
        .controller('ExcluirUsuariosController', function ($scope, $mdToast, $importService, $mdDialog) {

            /**
             * Serviços importados do DWR
             */
            $importService("usuarioService");

            $scope.excluir = function () {
                usuarioService.excluirUsuarios([$scope.model.entity.id], {
                    callback: function (result) {
                        $scope.remove(result[0]);
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                    }
                });
            };

            $scope.cancel = function() {
                $scope.answer();
            };

            $scope.remove = function (result) {
                $mdDialog.hide(result);
            };
        });


}(window.angular));
