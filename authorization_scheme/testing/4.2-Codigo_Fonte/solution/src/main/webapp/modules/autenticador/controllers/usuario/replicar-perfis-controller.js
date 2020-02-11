(function ( angular ) {
    'use strict';

/**
 *	Controller do caso de uso vincular aplicativos á usuários
 * 
 * @param $scope
 * @param $state
*/
angular.module('autenticador')
	   .controller('ReplicarPerfisController', function( $scope, $importService, $mdToast, $mdDialog, $q, usuario) {

  /**
   * Serviços importados do DWR
  */
  $importService("usuarioService");

  $scope.usuario = usuario;

  $scope.selectedItem = null;
  $scope.searchText = null;
  $scope.querySearch = querySearch;
  $scope.usuarioSelecionado = [];

  function querySearch(query) {
    var deferred = $q.defer();

    usuarioService.listUsuariosByFilters(query, null, {
      callback: function (result) {
        deferred.resolve(result.content);
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
      }
    });

    return deferred.promise;
  }

  $scope.reply = function () {
    usuarioService.replicarPerfisAcesso(usuario, $scope.usuarioSelecionado, {
      callback: function (result) {
        $mdToast.showSimple('Replicado com sucesso');
        $scope.usuarioSelecionado = [];
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
      }
    });
  };

  $scope.answer = function (answer) {
    $mdDialog.hide(answer);
  };
  
});


}(window.angular));