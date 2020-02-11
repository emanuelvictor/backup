(function (angular) {
    'use strict';

    /**
     *
     */
    angular.module('autenticador')
        .controller('HomeController', function ($scope, $rootScope, $state, $importService, $mdDialog, $mdSidenav, $mdToast, $location) {

            $scope.perfil = "ADMINISTRADOR";

            $scope.APLICATIVOS_STATE = "aplicativo.list";
            $scope.USUARIOS_STATE = "usuarios.list";

            $scope.userLogged = {};

            /**
             * Serviços importados do DWR
             */
            $importService("configuracaoService");
            $importService("usuarioService");
            $importService("aplicativoService");


            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            /*-------------------------------------------------------------------
             * 		 				 	  BEHAVIORS
             *-------------------------------------------------------------------*/


            configuracaoService.getDiasExpiracaoSenha({
                callback: function (result) {
                    $scope.configuracao = result;
                    $scope.$apply();
                }
            });


            $scope.updateDiasExpiracaoSenha = function () {

                configuracaoService.updateDiasExpiracaoSenha($scope.configuracao, {

                    callback: function (result) {
                        $mdToast.showSimple("Configuração atualizada com sucesso!");
                        $scope.configuracao = result;
                        $scope.$apply();
                    }, errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.$apply();
                    }
                })
            };
            $scope.showAlterarSenha = function (ev) {
                $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'modules/autenticador/views/usuario/usuario-modal-change-password.jsp',
                    targetEvent: ev,
                    locals: {
                        userLogged: $scope.userLogged
                    }
                }).then(function (result) {
                    $scope.userLogged.dataAlteracaoSenhaFormatted = moment(result.dataAlteracaoSenha).format('DD/MM/YYYY hh:mm');
                }, function () {
                   $scope.alert = 'You cancelled the dialog.';
                });

            };
            function DialogController($scope, $mdDialog, userLogged, $mdToast) {


                $scope.redefine = function () {
                    $scope.enviandoEmail = true;
                    usuarioService.changeSenhaUsuario(userLogged.id, $scope.usuario.password, $scope.usuario.newPassword, {
                        callback: function (result) {
                            
                            $mdToast.showSimple("Senha alterada com sucesso!");
                            $mdDialog.hide(result);
                        },
                        errorHandler: function (message, exception) {
                            $mdToast.showSimple(message);
                            $scope.$apply();
                            $scope.enviandoEmail = false;
                        }
                    });
                };
                $scope.hide = function () {
                    $mdDialog.hide();
                };
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
                $scope.answer = function () {
                    $scope.redefine();
                };
            };

            $scope.openAplicativo = function (link) {
                window.open(link, '_blank');
            };

            $scope.getLoggedUser = function () {
                usuarioService.getLoggedUser({
                    callback: function (result) {
                        $scope.userLogged = result;
                        if ($scope.userLogged.dataAlteracaoSenha != null) {
                            $scope.userLogged.dataAlteracaoSenhaFormatted = moment($scope.userLogged.dataAlteracaoSenha).format('DD/MM/YYYY HH:mm');
                        } else {
                            $scope.userLogged.dataAlteracaoSenhaFormatted = " ";
                        }
                        if ($scope.userLogged.dataExpiracaoSenhaFormatted != null) {
                            $scope.userLogged.dataExpiracaoSenhaFormatted = moment($scope.userLogged.dataExpiracaoSenha).format('DD/MM/YYYY HH:mm');
                        } else {
                            $scope.userLogged.dataExpiracaoSenhaFormatted = " ";
                        }
                        $scope.$apply();

                    }
                })
            };
            $scope.getLoggedUser();

            $scope.getAplicativos = function () { 
              usuarioService.listAplicativoByUserLogged(
                {
                  callback: function (result) {
                    $scope.aplicativos = result;
                    $scope.$apply();
                  }
                })
            };

            

            $scope.closeMenu = function () {
                $mdSidenav('left').close();
            };

            $scope.logout = function () {
                $location.absUrl('http://localhost:8080/logout');
                console.log($location);
            };

        });


    // /xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

    //.controller('MyController', function($scope, $mdSidenav) {
    //    $scope.openLeftMenu = function() {
    //        $mdSidenav('left').toggle();
    //    };
    //});
}(window.angular));

