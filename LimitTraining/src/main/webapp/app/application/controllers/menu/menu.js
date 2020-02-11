/**
 * Created by Emanuel Victor on 16/06/2016.
 */
/**
 * Created by Emanuel Victor on 16/06/2016.
 */
'use strict'

angular.module('App')
    .controller('MenuCtrl', function($log, $scope, $rootScope, $mdDialog, $state, $mdToast, $mdSidenav) {

        $scope.toogleDrawer = function () {
            console.log('asdfasdf');
            $mdSidenav("left")
                .toggle()
                .then(function () {
                    $log.debug("toggle is done");
                });
        };


        $scope.openMenu = function ($mdOpenMenu, ev) {
            $mdOpenMenu(ev);
        }



    });