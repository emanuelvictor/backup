angular.module('App')
  .service('Function',['$resource', 
    function($resource){
      return $resource('/functions/:id', {}, {
        getAll: {method: 'GET', url: '/functions/', isArray: true}
      });
    }]);