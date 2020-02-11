/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('FindAlunoCtrl', function($scope, $rootScope, $state) {
        $rootScope.nameButtonFuncionario = 'Novo aluno(a)';
        
        $scope.init = function(){
            alunoService.find({
                callback : function(result) {
                    $scope.alunos = result;
                    $scope.$apply();
                }, errorHandler : function( a, b  ){
                    console.log(a);
                }
            });
        };
        
        $scope.scroll = 0;
        $scope.showAddButton = function () {
            if ($scope.scroll < 25 ){
                $scope.showLabelAlunos = true;
                return true;
            }else{
                $scope.showLabelAlunos = false;
                return document.getElementById('toolbarAlunos').style.transform == "translate3d(0px, 0px, 0px)"
            }
        };

        $rootScope.handler = function(){
            $state.go('new');
        };

        $scope.edit = function(aluno){
            $state.go('detail',{id:aluno.id});
        };

        var imagePath = 'regina.jpg';
        $scope.todos = [];
        for (var i = 0; i < 200; i++) {
            $scope.todos.push({
                face: imagePath,
                what: "Brunch this weekend?" + i,
                who: "Min Li Chan"+ i,
                notes: "I'll be in your neighborhood doing errands." + i
            });
        }

    });