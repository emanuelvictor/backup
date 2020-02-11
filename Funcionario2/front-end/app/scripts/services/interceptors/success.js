'use strict';

/**
 * @ngdoc service
 * @name funcionario2App.successInterceptor
 * @description
 * # successInterceptor
 * Factory in the funcionario2App.
 */
angular.module('funcionario2App')
  .factory('successInterceptor',['$q',function($q) {
    return {
        response: function(response){
            console.log(response.status+' intercepted');
            return response || $q.when(response);
        }
    };
}]);