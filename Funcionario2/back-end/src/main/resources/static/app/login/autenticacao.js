angular.module('autenticacao', ['ngMaterial', 'ngCookies']).controller('AutenticacaoCtrl', function($scope, $http, $mdToast, $cookieStore) {
  
  $scope.user = {};

  $scope.autenticar = function() {


    var config = {
      headers: {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8', 'Accept': 'application/json', 'Authorization':'Basic ZnVuY2lvbmFyaW8yOjEyMzQ1Ng=='}
    };

    $http.post("http://funcionario2:123456@localhost:8080/oauth/token?grant_type=password&scope=read%20write&client_secret=123456&client_id=funcionario2",
    		'&username='+$scope.user.email+'&password='+$scope.user.password , config).
        success(function(data, status, headers, config) {
        	
          $cookieStore.put('token', data);
          console.log(data);
        	
          // window.location = 'app/index.html';
        }).error(function(data, status, headers, config) {
          var toast = $mdToast.simple()
              .content(data.error_description)
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