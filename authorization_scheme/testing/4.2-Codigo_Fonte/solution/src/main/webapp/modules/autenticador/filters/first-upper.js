(function (angular) {
    'use strict';

    /**
     * Filtro responsável para tornar a primeira letra da palavra maiúscula
     * @param $scope
     * @param $state
     */
    angular.module('autenticador')
        .filter('firstUpper', function(){
            return function(input){
                return input.charAt(0).toUpperCase() + input.substring(1).toLowerCase();
            }
        })

}(window.angular));