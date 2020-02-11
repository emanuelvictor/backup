/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('FindProfessorCtrl', function($scope, $rootScope, $state) {
        $scope.scroll = 0;
        $scope.showAddButton = function () {
            if ($scope.scroll < 25 ){
                $scope.showLabelProfessores = true;
                return true;
            }else{
                $scope.showLabelProfessores = false;
                return document.getElementById('toolbarProfessores').style.transform == "translate3d(0px, 0px, 0px)"
            }
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