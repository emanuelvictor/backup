'use strict';

/**
 * @ngdoc service
 * @name funcionario2App.requestError
 * @description
 * # interceptors/error
 * Factory in the funcionario2App.
 */
angular.module('funcionario2App')
  .factory('requestErrorInterceptor', ['$q','$rootScope', '$location', '$injector', function($q, $rootScope,$location, $injector) {
    return {
            responseError: function(response){
              var status = response.status;
              // var config = response.config;
              // var method = config.method;
              // var url = config.url;

              if (status === 400) {
                $injector.get('notify').showToast(response.data.statusText);
                $location.path('/security/authentication');
              } else if (status === 401) {
                $injector.get('notify').showToast(response.data.error);
                $location.path('/security/authentication');
              } else if (status === 403) {
                $injector.get('notify').showToast(response.data.error);
              } else if (status === 500) {
                $injector.get('notify').showToast(response.data.error);
              }
              return $q.reject(response);
            }
        };

    }]);
