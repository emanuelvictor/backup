(function (angular) {
    'use strict';

    /**
     *
     */
    angular.module('autenticador')
        .controller('HomeController', function ($scope, $rootScope, $state, $importService, $mdDialog, $mdSidenav, $window ,$mdToast, $location) {


            /**
             * Serviços importados do DWR
             */
            $importService("configuracaoService");
            $importService("usuarioService");
            $importService("aplicativoService");


            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            $scope.APLICATIVOS_STATE = "aplicativo.list";
            $scope.USUARIOS_STATE = "usuario.list";
            $scope.INICIO_STATE = "inicio.admin";
            $scope.userLogged = {};

            $scope.$on('user-logged', function(events, args) {
              $scope.userLogged = args;

              $scope.userLogged.perfis = [];

              $scope.getUserLoggedPerfis();

            });

            /*-------------------------------------------------------------------
             * 		 				 	  BEHAVIORS
             *-------------------------------------------------------------------*/

            $scope.getUserLoggedPerfis = function() {

              $scope.userLogged.perfisUsuarioAplicativo.forEach(function(perfilUsuario){

                if(perfilUsuario.perfilAcesso.aplicativo.clientId == 'Autenticador') {

                  $scope.userLogged.perfis.push(perfilUsuario.perfilAcesso.nome);
                }

              });

            };

            $scope.findAplicativoByEndereco = function() {
              aplicativoService.findAplicativoByEndereco({
                callback: function (result) {
                  $scope.aplicativo = result;
                  aplicativoService.findIconeAplicativoById($scope.aplicativo.id, {
                    callback: function (result) {
                      $scope.aplicativo.icone = result;
                      $scope.$apply();
                    }
                  });

                  $scope.$apply();
                }, errorHandler: function (message, exception) {
                  $mdToast.showSimple(message);
                  $scope.$apply();
                }
              });
            };

            $scope.onOpenConfiguracoesEventHandler = function () {
            	configuracaoService.getDiasExpiracaoSenha({

                    callback: function (result) {
                        $scope.configuracao = result;
                        $scope.$apply();
                    }, errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.$apply();
                    }
                })
            };

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
                    templateUrl: 'modules/autenticador/views/home/home-modal-change-password.jsp',
                    targetEvent: ev,
                    locals: {
                        userLogged: $scope.userLogged
                    }
                }).then(function (result) {

                }, function () {
                    $scope.alert = '';
                });
            };
            
            function DialogController($scope, $mdDialog, userLogged, $mdToast) {

                $scope.userLogged = userLogged;
                $scope.redefine = function (form) {
                    if ($scope.enviandoEmail) {
                        return;
                    }
                    if (form.$valid) {

                        $scope.isFormSubmit = false;
                        $scope.enviandoEmail = true;

                        usuarioService.changeSenhaUsuario(userLogged.id, $scope.password, $scope.newPassword, {
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
                    } else {
                      $scope.enviandoEmail = false;
                      $scope.isFormSubmit = true;
                      $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
                    }
                };
                
                $scope.hide = function () {
                    $mdDialog.hide();
                };
                
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
            }

            $scope.getUserLogged = function () {
                usuarioService.getUserLogged({
                    callback: function (result) {
                        $scope.userLogged = result;

                        $scope.getUserLoggedPerfis();

                        $scope.$apply();
                    }
                })
            };

            $scope.openAplicativo = function(ev, aplicativo) {

              if(aplicativo.ativo) {
                var win = window.open(aplicativo.endereco, '_blank');
                win.focus();
              } else {
                $mdDialog.show(
                  $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#aplicativo-desativado')))
                    .clickOutsideToClose(true)
                    .title('Aplicativo desativado')
                    .content(aplicativo.mensagemDesativacao)
                    .ok('Ok')
                    .targetEvent(ev)
                );
                //$mdToast.showSimple(aplicativo.mensagemDesativacao);
              }

            };

            $scope.getAplicativos = function () {
                usuarioService.listAplicativosByUserLogged(
                    {
                        callback: function (result) {
                            $scope.aplicativos = result;

                            $scope.aplicativos.forEach(function(aplicativo) {
                              aplicativoService.findIconeAplicativoById(aplicativo.id, {
                                callback: function (result) {
                                  aplicativo.icone = result;
                                  $scope.$apply();
                                }
                              });
                            });

                            $scope.$apply();
                        }
                    })
            };

            $scope.getAplicativos();

            $scope.findAplicativoByEndereco();

            $scope.closeMenu = function () {
                $mdSidenav('left').close();
            };

            $scope.logout = function () {
                $window.location.href = "./logout";
            };
        });

}(window.angular));

