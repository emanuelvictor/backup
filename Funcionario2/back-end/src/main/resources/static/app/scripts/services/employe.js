angular.module('App')
  .service('Employe',['$resource', 
    function($resource){
      return $resource('/employees/:id', {}, {
        getAll: {method: 'GET', url: '/employees', isArray: true}
      });
    }]);