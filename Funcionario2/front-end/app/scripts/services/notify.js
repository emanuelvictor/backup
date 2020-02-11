'use strict';

/**
 * @ngdoc service
 * @name funcionario2App.notify
 * @description
 * # notify
 * Factory in the funcionario2App.
 */
angular.module('funcionario2App')
  .factory('notify', ['$mdToast', function($mdToast) {
    return {
        showToast: function(msg) {
            var toast = $mdToast.simple()
                .content(msg)
                .action('OK')
                .highlightAction(false)
                .position('top right');

            $mdToast.show(toast).then(function() {
              
            });
        }
    }
  }]);
