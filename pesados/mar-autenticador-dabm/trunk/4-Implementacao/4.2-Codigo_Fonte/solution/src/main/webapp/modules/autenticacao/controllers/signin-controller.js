(function (angular) {
    'use strict';


    /**
     *
     * @param $scope
     * @param $state
     */
    angular.module('authentication')
        .controller('SigninController', function ($scope, $state, $http, $mdToast, $window, $location, $importService) {

            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/


            //----STATES
            $scope.LOGIN_STATE = "signin.login";
            $scope.RECOVER_STATE = "signin.recover";
            $scope.RECOVER_SUCESS_STATE = "signin.recover-success";
            $scope.REDEFINE_PASSWORD = "signin.redefine";

            $scope.recuperandoSenha = false;
            $scope.isFormSubmit = false;

            $scope.login = '';

            $scope.params = $location.search();
            /*-------------------------------------------------------------------
             * 		 				 	  BEHAVIORS
             *-------------------------------------------------------------------*/
            

            $scope.signin = function (form, login, senha) {

              var data = {login: login, senha: senha};

              if (form.$valid) {
                $scope.isFormSubmit = false;

                var config = {
                  headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                    'X-CSRF-TOKEN': readCookie('X-CSRF-TOKEN')
                  }
                };
                $http.post("./authenticate", $.param(data), config)
                  .success(function (data, status, headers, config) {
                    $window.location.href = "./";
                  })
                  .error(function (data, status, headers, config) {
                    $mdToast.showSimple(data);
                    if (status == 406) {
                      $scope.login = login;
                      $state.go($scope.REDEFINE_PASSWORD);
                    }
                  });
              }else {
                $scope.isFormSubmit = true;
                $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
              }
            };


            $scope.recoverPassword = function (form, login) {
                if (!$scope.recuperandoSenha) {

                    $scope.recuperandoSenha = true;

                    if (form.$valid) {
                      $scope.isFormSubmit = false;
                      $http({
                        method: 'POST',
                        url: './api/usuarios/recuperarSenha?login=' + login,
                        headers: {'Content-Type': 'application/json; charset=UTF-8'}/*,
                         data: {
                         login: login
                         }*/
                      }).success(function (data, status, headers) {
                        //$mdToast.showSimple("Senha alterada com sucesso.");
                        $scope.recuperandoSenha = false;
                        $scope.email = data.email;
                        $state.go($scope.RECOVER_SUCESS_STATE);
                      }).error(function (data, status, headers, config) {
                        $mdToast.showSimple(data.message);
                        $scope.recuperandoSenha = false;
                      });
                    } else {
                      $scope.recuperandoSenha = false;
                      $scope.isFormSubmit = true;
                      $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
                    }

                }
            };

            $scope.redefinePassword = function (form, novaSenha, confirmacao) {

                if (!$scope.recuperandoSenha) {

                  $scope.recuperandoSenha = true;

                  if (form.$valid) {
                    $scope.isFormSubmit = false;

                    $http({
                      method: 'POST',
                      url: './api/usuarios/redefinirSenha',
                      headers: {'Content-Type': 'application/json; charset=UTF-8'},
                      data: {
                        login: $scope.login,
                        novaSenha: novaSenha,
                        confirmacao: confirmacao
                      }
                    }).success(function (data, status, headers) {
                      $scope.senha = null;
                      $mdToast.showSimple("Senha alterada com sucesso.");
                      $scope.recuperandoSenha = false;
                      $state.go($scope.LOGIN_STATE);
                    }).error(function (data, status, headers, config) {
                      $mdToast.showSimple(data.message);
                      $scope.recuperandoSenha = false;
                    });
                  } else {
                    $scope.recuperandoSenha = false;
                    $scope.isFormSubmit = true;
                    $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
                  }
                }
            };


            // Se está no no REDEFINE_PASSWORD mas naõ tem o usuário no scope, então deve retornar para a tela de login.
            if ($state.current.name == $scope.REDEFINE_PASSWORD && !$scope.login) {
                if($scope.params.nip){
                    $scope.login = $scope.params.nip;
                }else {
                    $state.go($scope.LOGIN_STATE);
                }
            };


            if($scope.params.loginousenhaincorretos)
            {
              $mdToast.showSimple("Login ou senha incorretos");
            } 
            else if($scope.params.usuariodesabilitado)
            {
              $mdToast.showSimple("Usuário desabilitado"); // TODO melhorar mensagem?
            } 
            else if($scope.params.contabloqueada)
            {
              $mdToast.showSimple("Conta bloqueada");
            }

        });

}(window.angular));