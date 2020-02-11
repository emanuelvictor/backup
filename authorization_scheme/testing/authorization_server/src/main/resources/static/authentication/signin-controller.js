angular.module('Authentication', ['ngMaterial','ngCookies'])
    .controller('SigninController', function ($scope, $cookies, $http, $mdToast, $window) {

        $scope.signin = function (userAccount) {
            var config = {
                headers: {'Content-Type': 'application/x-www-form-urlencoded;', 'X-CSRF-TOKEN': $cookies['X-CSRF-TOKEN']}
            };

            $http.post("./login", $.param(userAccount), config)
            .success(function (data, status, headers, config) {
                $window .location.href = "./";
            })
            .error(function (data, status, headers, config) {
                $mdToast.showSimple(data);
            });
        }
        $scope.saia = function () {
        $http.post('./logout', {}).success(function() {
            self.authenticated = false;
            $location.path("/");
          }).error(function(data) {
            console.log("Logout failed")
            self.authenticated = false;
          });}

    });