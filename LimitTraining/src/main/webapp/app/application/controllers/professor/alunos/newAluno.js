/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('NewAlunoCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {
        console.log('aluno');
        $scope.newAluno = {}
        $rootScope.nameButtonFuncionario = 'Usuários';

        $rootScope.handler = function(){
            $state.go('aluno');
        };


        $scope.save = function(){
            alunoService.save($scope.newAluno, {
                callback : function(result) {
                    $state.go('aluno');
                }, errorHandler : function(message, exception){
                    $mdToast.show($scope.toast);
                }
            });
        };
    });