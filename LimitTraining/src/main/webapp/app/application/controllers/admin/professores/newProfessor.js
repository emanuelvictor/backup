/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('NewProfessorCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast) {
        $scope.professor = {}




        $scope.save = function(){
            professorService.save($scope.professor, {
                callback : function(result) {
                    $state.go('professor');
                }, errorHandler : function(message, exception){
                    $mdToast.show($scope.toast);
                }
            });
        };
    });