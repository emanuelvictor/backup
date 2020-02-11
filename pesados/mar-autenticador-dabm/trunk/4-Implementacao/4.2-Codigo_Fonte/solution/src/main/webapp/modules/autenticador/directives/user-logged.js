(function (angular) {
    'use strict';

    angular.module('autenticador')
        .directive('userLogged', ['$rootScope', '$mdToast', '$mdDialog', '$importService', function($rootScope, $mdToast, $mdDialog, $importService) {
            return {
                restrict: "E",
                replace: true,
                templateUrl: 'modules/autenticador/views/home/user-logged.jsp',
                link: function (scope, element) {

                    scope.$on('user-logged', function (events, args) {
                        scope.userLogged = args;
                        scope.findFotoUsuarioById(scope.userLogged.id);
                    });

                    scope.onUploadFotoError = function (msg) {
                        $mdToast.showSimple(msg);
                    };

                    scope.findFotoUsuarioById = function (id) {
                        usuarioService.findFotoUsuarioById(id, {
                            callback: function (result) {
                                scope.userLogged.foto = result;

                                if (result != null) {

                                    $('#account').find('md-icon').hide();
                                    $('#account').find('button').css('height', '40px');
                                    $('#account').find('button').css('line-height', '40px');
                                    $('#account').find('button').html('<img class="md-avatar" src="' + result + '" style="width: 40px; height: 40px">');
                                } else {
                                    $('#account').find('md-icon').show();
                                    $('#account').find('button').html('<md-icon class="md-icon-person md-icon-lg"></md-icon>');
                                }
                            }
                        });
                    };

                    scope.onUploadFotoSuccess = function (input) {
                        usuarioService.uploadFotoUsuario(input.element, scope.userLogged.id, {
                            callback: function (result) {
                                $mdToast.showSimple("Foto alterada com sucesso");

                                scope.findFotoUsuarioById(scope.userLogged.id);
                            },
                            errorHandler: function (message, exception) {
                                $mdToast.showSimple(message);
                            }
                        });
                    };

                    scope.onUploadFotoDelete = function (event) {

                        var confirm = $mdDialog.confirm()
                            .title('Deseja realmente remover a foto do usuário ' + scope.userLogged.nomeCompleto + '?')
                            .content('Não será possível recuperar este registro se for excluído.')
                            .ok('Excluir')
                            .cancel('Cancelar')
                            .targetEvent(event);

                        $mdDialog.show(confirm).then(function (result) {

                            usuarioService.removeFotoUsuario(scope.userLogged.id, {
                                callback: function () {

                                    scope.userLogged.foto = null;

                                    $('#account').find('md-icon').show();
                                    $('#account').find('button').html('<md-icon class="md-icon-person md-icon-lg"></md-icon>');

                                    $mdToast.showSimple("Foto removida com sucesso");
                                },
                                errorHandler: function (message, exception) {
                                    $mdToast.showSimple(message);
                                }
                            });
                        });
                    };

                    $rootScope.userLogged = $rootScope.userLogged ? $rootScope.userLogged : '';

                    if (!$rootScope.userLogged) {

                        usuarioService.getUserLogged({
                            callback: function (response) {
                                $rootScope.userLogged = response;

                                $rootScope.$broadcast('user-logged', response);
                            }
                        });

                    } else {
                        $rootScope.userLogged = $rootScope.userLogged;
                        $rootScope.$broadcast('user-logged', $rootScope.userLogged);
                    }
                }
            };
        }]);

}(window.angular));
