(function ( angular ) {
    'use strict';

/**
 *	Controller do caso de uso vincular aplicativos á usuários
 * 
 * @param $scope
 * @param $state
 */
 angular.module('autenticador')
	   .controller('AdicionarAplicativosController', function( $importService, $scope, $mdDialog, usuario, aplicativoSelecionado, aplicativos, $q ) {

  /**
   * Serviços importados do DWR
  */
  $importService("usuarioService");

  /*-------------------------------------------------------------------
  * 		 				 	ATTRIBUTES
  *-------------------------------------------------------------------*/

  $scope.aplicativosSelecionados = [];

  //Se esta editando o objeto realiza a busca no mesmo
  aplicativoSelecionado ? $scope.searchText = aplicativoSelecionado.nome : $scope.searchText;

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

    // Pega todos os aplicativos
  $scope.getAplicativos = function(filters){
    aplicativoService.listAplicativosByFilters( filters, true, null, {
      callback: function (result) {
        $scope.aplicativos = result.content;
        $scope.notVoid = true;
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
        // TODO substituir por forEach
        for (var i = 0; i < result.content.length; i++) {
          $scope.perfisUsuario.push(result.content[i].perfilAcesso);    
        }
      }
    });              
  };

  //Pegando todos os aplicativos
  $scope.getPerfisUsuario();

  // Pega todos os perfis vinculados ao aplicativo
  $scope.getPerfisAplicativo = function(aplicativoId){
    $scope.perfisAplicativo = new Array();
    aplicativoService.listPerfisAcessoByAplicativoId( aplicativoId, {
      callback: function (result) {
        $scope.perfisAplicativo = result;
      }
    });         
  };

  $scope.selected = $scope.perfisUsuario;

  //Função de alternação entre os checkbox
  $scope.toggle = function (item, list) {
    var idx = $scope.exists(item, list)
    if(idx>-1){
      list.splice(idx, 1);
      usuarioService.removePerfilUsuarioAplicativo(usuario.id, item.id);
    }else{
      list.push(item);
      usuarioService.insertPerfilUsuarioAplicativo(usuario.id, item.id);
    }
  };

  //Função que verifica se o item exite na lista
  //Se existir retorna o index onde o item esta, se não retorna -1
  // TODO utilizar forEach e angular filter
  $scope.exists = function (item, list) {
    for (var i = 0; i < list.length; i++) {
      if(item.id == list[i].id){
        return i;
      }
    }       
    return -1;       
  };

  $scope.hide = function () {
    $mdDialog.hide();
  };

  $scope.cancel = function () {
    $mdDialog.cancel();
  };

  $scope.answer = function (answer) {
    $mdDialog.hide(answer);
  };

});

}(window.angular));