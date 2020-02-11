'use strict';

/**
 * @ngdoc service
 * @name funcionario2App.Employee
 * @description
 * # Employee
 * Service in the funcionario2App.
 */
angular.module('funcionario2App')
  .service('Employee',['$resource', 'BaseURL',
      function($resource, BaseURL){
      return $resource(BaseURL+'/employees/:id', {}, {
        getAll: {method: 'GET', url: BaseURL+ '/employees', isArray: true}
      });
    }]);
