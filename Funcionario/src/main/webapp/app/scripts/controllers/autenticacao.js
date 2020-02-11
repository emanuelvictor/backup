angular.module('autenticacao', ['ngMaterial']).controller('AutenticacaoCtrl', function($scope, $http, $mdToast) {

  
  
  $scope.user = {};

  $scope.autenticar = function() {


    var config = {
      headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
    };


    $http.post( "/Funcionario/j_spring_security_check", $.param({email:$scope.user.email, password:$scope.user.password}), config).
        success(function(data, status, headers, config) {
          window.location = '../../index.html';
        }).error(function(data, status, headers, config) {
          var toast = $mdToast.simple()
              .content(data)
              .action('OK')
              .highlightAction(false)
              .position('top right');
          $mdToast.show(toast);
        });
  };
	
}).config(function($mdThemingProvider) {
  
  $mdThemingProvider.theme('default')
  .primaryPalette('pink')
  .accentPalette('blue');
  
});