'use strict';

/**
 * @ngdoc service
 * @name funcionario2App.position
 * @description
 * # position
 * Service in the funcionario2App.
 */
angular.module('funcionario2App')
  .service('Position',['$resource', 'BaseURL',
      function($resource, BaseURL){
      return $resource(BaseURL+'/positions/:id', {}, {
        getAll: {method: 'GET', url: BaseURL+ '/positions', isArray: true}
      });
    }]);
