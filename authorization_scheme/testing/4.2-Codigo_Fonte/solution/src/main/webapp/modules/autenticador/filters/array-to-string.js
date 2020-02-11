(function (angular) {
    'use strict';

    /**
     * Filtro que converte um array para string
     * @param $scope
     * @param $state
     */
    angular.module('autenticador')
        .filter('arrayToString', function(){
            return function(array, id){
                 var stringFormatted = null;
                 angular.forEach(array, function(item, i) {
                    if(id == array[i].aplicativo.id){
                      if (!stringFormatted){
                        stringFormatted = array[i].nome;       
                      }else{
                        stringFormatted = stringFormatted +', '+ array[i].nome;          
                      }          
                    } 
                 });
                 return stringFormatted;
            }           
        })

}(window.angular));