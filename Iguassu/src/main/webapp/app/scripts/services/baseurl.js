'use strict';

/**
 * @ngdoc service
 * @name iguassuApp.BaseUrl
 * @description
 * # BaseUrl
 * Factory in the iguassuApp.
 */
angular.module('iguassuApp').factory('BaseUrl', function($location) {
	return  $location.protocol() + '://' + $location.host() + ':' + $location.port();
});