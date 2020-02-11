(function ( angular ) {
  'use strict';

  /**
   *  Controller do caso de uso vincular aplicativos á usuários
   *
   * @param $scope
   * @param $state
   */
  angular.module('autenticador')
    .controller('AdicionarAplicativosController', function( $importService, $scope, $mdDialog, usuario, aplicativoSelecionado, aplicativos, $filter, $log, $q ) {

      /**
       * Serviços importados do DWR
       */
      $importService("usuarioService");

      /*-------------------------------------------------------------------
       *                             ATTRIBUTES
       *-------------------------------------------------------------------*/

      $scope.aplicativosSelecionados = [];

      //Se esta editando o objeto realiza a busca no mesmo
      aplicativoSelecionado ? $scope.searchText = aplicativoSelecionado.nome : $scope.searchText;

      $scope.usuarioAplicativos = aplicativos;

      // Variável no escopo que armazena os aplicativos
      $scope.aplicativos = [];

      // Variável no escopo que armazena os perfis vinculados à aplicativos
      $scope.perfisAplicativo = [];

      // Variável no escopo que armazena os perfis vinculados à usuários
      $scope.perfisUsuario = [];

      // Serviço asyncrono para buscar os dados
      $scope.querySearch = function(query) {
        var deferred = $q.defer();

        aplicativoService.listAplicativosByFilters(query, true, null, {
          callback: function (result) {
            deferred.resolve(result.content);
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
          }
        });

        return deferred.promise;
      };

      $scope.searchTextChange = function(text) {
        $log.info('Text changed to ' + text);
      };

      $scope.selectedItemChange = function(item) {
        //$scope.aplicativos = item;

        $log.info('Item changed to ' + JSON.stringify(item));

      };

      // Pega todos os aplicativos
      $scope.getAplicativos = function(filters){
        aplicativoService.listAplicativosByFilters( filters, true, null, {
          callback: function (result) {
            $scope.aplicativos = result.content;

            $scope.aplicativos.forEach(function(aplicativo) {
              aplicativoService.findIconeAplicativoById(aplicativo.id, {
                callback: function (result) {
                  aplicativo.icone = result;
                  $scope.$apply();
                }
              });
            });

            $scope.usuarioAplicativos.forEach(function(aplicativos){

              var aplicativo = $filter('filter')($scope.aplicativos, {id: aplicativos.id})[0];

              if(!!aplicativo) {
                var perfis = [];
                aplicativos.perfisAcesso.forEach(function (perfil) {
                  perfis.push(perfil.nome);
                });

                aplicativo.perfisAcessoUsuario = perfis;
              }
            });

            $scope.aplicativos.forEach(function(aplicativo){
              $scope.getPerfisAplicativo(aplicativo);
            });

            $scope.notVoid = true;
            $scope.$apply();
          }
        });
      };

      //Pegando todos os aplicativos
      aplicativoSelecionado ? $scope.getAplicativos(aplicativoSelecionado.endereco) : $scope.getAplicativos(undefined);

      // Pega todos os perfis vinculados ao usuário
      $scope.getPerfisUsuario = function(){
        $scope.perfisUsuario = new Array();
        usuarioService.listPerfilUsuarioAplicativoByUsuarioId(usuario.id, null, {
          callback: function (result) {
            for (var i = 0; i < result.content.length; i++) {
              $scope.perfisUsuario.push(result.content[i].perfilAcesso);
            }
            $scope.$apply();
          }
        });
      };

      // Pega todos os perfis vinculados ao aplicativo
      $scope.getPerfisAplicativo = function(aplicativo){
        $scope.perfisAplicativo = new Array();
        aplicativoService.listPerfisAcessoByAplicativoId( aplicativo.id, {
          callback: function (result) {

            aplicativo.perfisAcessoAplicativo = result;

            $scope.$apply();
          }
        });
      };

      $scope.selected = $scope.perfisUsuario;

      //Função de alternação entre os checkbox
      $scope.toggle = function (item, list) {

        list = !list ? [] : list;

        if(!list.indexOf(item.nome)){
          usuarioService.removePerfilUsuarioAplicativo(usuario.id, item.id);
        }else{
          usuarioService.insertPerfilUsuarioAplicativo(usuario.id, item.id);
        }
      };

      $scope.hide = function () {
        $mdDialog.hide();
      };

      $scope.cancel = function () {
        $mdDialog.cancel();
      };


    });

}(window.angular));