(function (angular) {
    'use strict';


    /**
     *
     * @param $scope
     * @param $state
     */
    angular.module('authentication')
        .controller('SigninController', function ($scope, $cookies, $state, $http, $mdToast, $window , $importService) {

            /**
             *
             */

            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            //----STATES

            $scope.RECOVER_STATE = "recover"

            /*-------------------------------------------------------------------
             * 		 				 	  BEHAVIORS
             *-------------------------------------------------------------------*/

            $scope.signin = function (usuario) {

                if ($scope.authenticationForm.$invalid) {
                    $mdToast.showSimple("Fomulário inválido");
                } else {
                	
                	
                    var config = {
                        headers: {'Content-Type': 'application/x-www-form-urlencoded;', 'X-CSRF-TOKEN': $cookies.get('X-CSRF-TOKEN')}
                    };

                    $http.post("./authentication", $.param(usuario), config)
                        .success(function (data, status, headers, config) {
                            $window .location.href = "./";
                        })
                        .error(function (data, status, headers, config) {
                            $mdToast.showSimple(data);
                        });
                }
            }
            $scope.recoverPassword = function () {
            //TODO

                usuarioService.sendRecuperarSenha ( $scope.usuario.login , {

                        callback: function (result) {
                            $state.go($scope.RECOVER_STATE);
                            $state.apply();
                        }
                    }
                )
                ;
            }

        });

}
(window.angular)
)
;