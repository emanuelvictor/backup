/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('DetailAlunoCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {

        alunoService.find($state.params.id, {
            callback : function(result) {
                $scope.editAluno = result;

                $scope.editAluno.password = null;

                $scope.$apply();
            }
        });

        $rootScope.nameButtonFuncionario = 'Funcionários';

        $rootScope.handler = function(){
            $state.go('aluno');
        };


        $scope.save = function(){
            alunoService.save($scope.editAluno, {
                callback : function(result) {
                    console.log($scope.editAluno);
                    $state.go('aluno');
                }, errorHandler : function(message, exception){
                    $scope.toast.content('Erro ao salvar informações, verifique se o email não está cadastrado');
                    $mdToast.show($scope.toast);
                }
            });
        };

        $scope.delete = function(aluno){
            $mdDialog.show({
                controller: 'ConfirmDeleteAlunoCtrl',
                templateUrl: './view/admin/confirm.tmpl.html'
            })
                .then(function(answer) {
                    if(answer){
                        alunoService.destroy(aluno.id,{
                            callback : function(result) {
                                $state.go('aluno');
                            }
                        });
                    }
                });

        };

    }).controller('ConfirmDeleteAlunoCtrl', function($scope,$mdDialog) {

        $scope.sim = function(){
            $mdDialog.hide('sim');
        };

        $scope.nao = function(){
            $mdDialog.hide();
        };

    });