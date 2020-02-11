(function (angular) {
    'use strict';

    /**
     *
     * @param $scope
     * @param $state
     */
angular.module('autenticador')
        .controller('UsuarioController', function ($scope, $rootScope, $state, $importService, $mdToast, $mdDialog, $log, $timeout, $q, $filter) {


  /**
   * Serviços importados do DWR
   */
  $importService("usuarioService");
  $importService("aplicativoService");



  /*-------------------------------------------------------------------
   * 		 				 	ATTRIBUTES
   *-------------------------------------------------------------------*/
  //----STATES
  /**
   * Representa o estado de listagem de registros.
   */
  $scope.LIST_STATE = "usuario.list";
  /**
   * Representa o estado para a criação de registros.
   */
  $scope.ADD_STATE = "usuario.add";
  /**
   * Representa o estado para a edição de registros.
   */
  $scope.EDIT_STATE = "usuario.edit";
  /**
   * Representa o estado de detalhe de um registro.
   */
  $scope.DETAIL_STATE = "usuario.detail";

  //----FORM MODEL

  /**
   * Contém o estados dos filtros da tela
   * Contém estado da paginação inicial contendo sorting
   *
   */
  $scope.model = {
    form: null,
    entity: new Usuario(),

    filters: {
      terms: [],
      ativo: true
    },
    page: {//PageImpl
      content: null,
      pageable: {//PageRequest
        size: 30,
        page: 0,
        sort: {//Sort
          orders: [
            {direction: 'ASC', property: 'nomeCompleto'}
          ]
        }
      }
    }
  };

  /*-------------------------------------------------------------------
   * 		 				  POST CONSTRUCT
   *-------------------------------------------------------------------*/
  /**
   * Handler que escuta as mudanças de URLs pertecentes ao estado da tela.
   * Ex.: list, add, detail, edit
   *
   * Toda vez que ocorre uma mudança de URL se via botão, troca de URL manual, ou ainda
   * ao vançar e voltar do browser, este evento é chamado.
   *
   */
  $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {

    if($state.current.name != $scope.LIST_STATE)
      $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Usuarios'}];

    switch ($state.current.name) {
      case $scope.LIST_STATE:
      {
        $scope.changeToList(false);
        break;
      }
      case $scope.DETAIL_STATE:
      {
        $scope.changeToDetail($state.params.id);
        break;
      }
      case $scope.ADD_STATE:
      {
        $scope.changeToAdd();
        break;
      }
      case $scope.EDIT_STATE:
      {
        $scope.changeToEdit($state.params.id);
        break;
      }
      default :
      {
        $state.go($scope.LIST_STATE);
      }
    }
  });

  /*-------------------------------------------------------------------
   * 		 				 	  HANDLERS
   *-------------------------------------------------------------------*/
  /**
   * Handler para que realiza os procedimentos iniciais (prepara o estado)
   * para a tela de inserção e após isso, muda o estado para $scope.ADD_STATE.
   *
   * @see $scope.ADD_STATE
   */
  $scope.changeToAdd = function () {
    console.debug("changeToAdd");

    $scope.model.entity = new Usuario();//Limpa o formulário

    $scope.model.entity.dataBloqueio = undefined;
    $scope.model.entity.dataDesbloqueio = undefined;

    $state.current.breadCrumbs.push({name: 'Novo usuário'});
  };

  /**
   handler de back para clicar no botão voltar
  */
  $rootScope.backHandler = function(){
    if($state.current.name == $scope.ADD_STATE || $state.current.name == $scope.DETAIL_STATE){
      $state.go($scope.LIST_STATE);
    } else if($state.current.name == $scope.EDIT_STATE){
      $state.go($scope.DETAIL_STATE, {id: $scope.model.entity.id});
    };
  };

  /**
   * Realiza os procedimentos iniciais (prepara o estado)
   * para a tela de edição e após isso, muda o estado para update.
   *
   * Para mudar para este estado, deve-se primeiro obter via id
   * o registro pelo serviço de consulta e só então mudar o estado da tela.
   *
   * @see $scope.EDIT_STATE
   */
  $scope.changeToEdit = function (id) {
    console.debug("changeToEdit", id);

    usuarioService.findUsuarioById(id, {
      callback: function (result) {
        $scope.model.entity = result;

        $state.current.breadCrumbs.push({name: result.nomeCompleto});

        // date-picker não aceita objeto do tipo null
        if($scope.model.entity.dataExpiracaoSenha == null) $scope.model.entity.dataExpiracaoSenha = undefined;
        // date-picker não aceita objeto do tipo null
        if($scope.model.entity.dataBloqueio == null) $scope.model.entity.dataBloqueio = undefined;
        // date-picker não aceita objeto do tipo null
        if($scope.model.entity.dataDesbloqueio == null) $scope.model.entity.dataDesbloqueio = undefined;

        // Certifica-se de que a variável aplicativo sempre esteja null quando a controller for carregada
        $scope.aplicativos = null;
        $scope.$apply();
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
        $state.go($scope.LIST_STATE);
        $scope.$apply();
      }
    });
  };




  /**
   * Realiza os procedimentos iniciais (prepara o estado)
   * para a tela de consulta e após isso, muda o estado para list.
   * Para mudar para este estado, deve-se primeiro carregar os dados da consulta.
   *
   * @see $scope.LIST_STATE
   *
   * @param paginate boolean para saber se precisa paginar
   */
  $scope.changeToList = function (paginate) {
    console.debug("changeToList");

    if (paginate) $scope.model.page.pageable.page++;

    $scope.listUsuariosByFilter($scope.model.filters, $scope.model.page.pageable);
  };

   /**
   * Realiza os procedimentos iniciais (prepara o estado)
   * para a tela de detalhe e após isso, muda o estado para detail.
   *
   * Para mudar para este estado, deve-se primeiro obter via id
   * o registro atualizado pelo serviço de consulta e só então mudar o estado da tela.
   *
   * @see $scope.DETAIL_STATE
   */
  $scope.changeToDetail = function (id) {
    console.debug("changeToDetail", id);

    usuarioService.findUsuarioById(id, {
      callback: function (result) {
        $scope.model.entity = result;
        $state.go($scope.DETAIL_STATE, {id: id});

        $state.current.breadCrumbs.push({name: result.nomeCompleto});

        $scope.dataExpiracaoSenha = moment($scope.model.entity.dataExpiracaoSenha).format('DD/MM/YYYY hh:mm');
        $scope.dataBloqueio = moment($scope.model.entity.dataBloqueio).format('DD/MM/YYYY hh:mm');
        $scope.dataDesbloqueio = moment($scope.model.entity.dataDesbloqueio).format('DD/MM/YYYY hh:mm');
        $scope.$apply();
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
        $state.go($scope.LIST_STATE);
        $scope.$apply();
      }
    });
  };

  /**
   * Realiza os procedimentos iniciais (prepara o estado)
   * para a tela de exclusão.
   * Antes de excluir, o usuário notificado para confirmação e só então o registro é excluido.
   */
  $scope.changeToRemove = function (event, entity) {
    console.debug("changeToRemove", entity);

    var confirm = $mdDialog.confirm()
        .title('Tem certeza que deseja excluir este registro?')
        .content('Não será possível recuperar este registro se for excluído.')
        .ok('Sim')
        .cancel('Cancelar')
        .targetEvent(event);

    $mdDialog.show(confirm).then(function (result) {
      console.log(result);

      usuarioService.removeUsuarios([entity.id], {
        callback: function (result) {
          $mdToast.showSimple("O registro foi excluído com sucesso!");
        },
        errorHandler: function (message, exception) {
          $mdToast.showSimple(message);
          $scope.$apply();
        }
      });
    });
  };

  /**
   * Realiza a inserção de um novo registro
   * e no suscesso, modifica o estado da tela para o detail.
  */
  $scope.insertHandler = function () {

    if($scope.model.entity.id){
      $scope.updateHandler();
    }else{
      console.debug("insertHandler");
      usuarioService.insertUsuario($scope.model.entity, {
        callback: function (result) {
          $mdToast.showSimple("Registro salvo com sucesso!");
          $state.go($scope.DETAIL_STATE, {id: result.id});
          $scope.$apply();
        },
        errorHandler: function (message, exception) {
          $mdToast.showSimple(message);
          $scope.$apply();
        }
      });
    }
  };

  /**
   * Realiza a inserção de um novo registro
   * e no suscesso, modifica o estado da tela para o detail.
   */
  $scope.updateHandler = function () {
    console.debug("updateHandler");

    usuarioService.updateUsuario($scope.model.entity, {
      callback: function (result) {
        $mdToast.showSimple("Registro atualizado com sucesso!");
        $state.go($scope.DETAIL_STATE, {id: result.id});
        $scope.$apply();
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
        $scope.$apply();
      }
    });
  };

  $scope.replyProfile = function (event, usuario) {

    $mdDialog.show({
      controller: 'ReplicarPerfisController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-replicar-perfil.jsp',
      targetEvent: event,
      locals: {
        usuario: usuario
      }
    }).then(function (answer) {
      $scope.alert = 'You said the information was "' + answer + '".';
    }, function () {
      $scope.alert = 'You cancelled the dialog.';
    });
  };

  $scope.bloquearUsuarios = function (ev, usuarios) {
    if($state.current.name != $scope.LIST_STATE){
      usuarios = new Array(usuarios);
    };
    $mdDialog.show({
      controller: 'BloquearUsuariosController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-bloquear.jsp',
        targetEvent: ev,
        locals: {
          usuarios: usuarios
        } 
    }).then(function (answer) {
       if($state.current.name != $scope.LIST_STATE){
         $scope.model.entity = answer[0];
         $scope.dataBloqueio = moment($scope.model.entity.dataBloqueio).format('DD/MM/YYYY hh:mm');
         $scope.dataDesbloqueio = moment($scope.model.entity.dataDesbloqueio).format('DD/MM/YYYY hh:mm');
       }else{
         $scope.model.entity = answer;            
       }
    }, function () {
      $scope.alert = 'You cancelled the dialog.';
    });
  };

  $scope.desbloquearUsuarios = function (usuarios) {
    if($state.current.name != $scope.LIST_STATE){
      usuarios = new Array(usuarios);
    };
    usuarioService.desbloquearUsuarios(usuarios, {
      callback: function (result) {
        if($state.current.name != $scope.LIST_STATE){
          $scope.model.entity  = result[0];
          $mdToast.showSimple( 'Usuário desbloqueado com sucesso' );
        }else{
          $scope.model.entity  = result;
          $mdToast.showSimple( 'Usuários desbloqueados com sucesso' );
        };
      },
      errorHandler : function(message, exception) {
        $mdToast.showSimple( message );
      }
    });
  };

             
  $scope.excluirUsuario = function (ev) {
    $mdDialog.show({
      controller: 'ExcluirUsuariosController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-excluir.jsp',
      targetEvent: ev,
      scope : $scope.$new(),
    }).then(function (answer) {
      $scope.model.entity = answer;
    });
  };

  $scope.restaurarUsuario = function () {
    usuarioService.restaurarUsuarios([$scope.model.entity.id], {
      callback: function (result) {
        $scope.model.entity  = result[0];
        $mdToast.showSimple( 'Usuário restaurado com sucesso' );
      },
      errorHandler : function(message, exception) {
        $mdToast.showSimple( message );
      }
    });
  };

            
  $scope.removePerfilUsuarioAplicativo = function (aplicativo, perfisUsuarioAplicativo) {
    usuarioService.removePerfilUsuarioAplicativo($filter('filter')(perfisUsuarioAplicativo, aplicativo.endereco), {
      callback: function () {
        $scope.aplicativos.splice( $scope.aplicativos.indexOf(aplicativo), 1 );
        $scope.$apply();
      }
    });
  };
  
  $scope.showResetPassword = function (ev) {
    $mdDialog.show({
      controller: 'AlterarSenhaController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-modal-reset-password.jsp',
      targetEvent: ev,
      locals: {
        model: $scope.model
      }
    })
  };


  // Abre dialog para vincular aplicativos á um usuário          
  $scope.showAddApp = function (ev, usuario, aplicativo) {
    $mdDialog.show({
      controller: 'AdicionarAplicativosController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-add-aplicativo.jsp',
      targetEvent: ev,
      locals: {
        usuario: usuario,
        aplicativoSelecionado: aplicativo,
        aplicativos: $scope.aplicativos,
      }
    }).then(function (answer) {
      $scope.onOpenEventHandler();
    }, function () {
      $scope.alert = 'You cancelled the dialog.';
    });
  };


  /*-------------------------------------------------------------------
   * 		 				 PRIVATE BEHAVIORS
   *-------------------------------------------------------------------*/

  /**
   * Realiza a consulta de registros, consirando filtro, paginação e sorting.
   *
   * @see $scope.LIST_STATE
   * @see $scope.filters
   * @see $scope.page
   */
  $scope.listUsuariosByFilter = function (filters, pageRequest) {
    usuarioService.listUsuariosByFilters(filters.terms.toString(), pageRequest, {
      callback: function (result) {
        $scope.model.page.content = $scope.model.page.content ? $scope.model.page.content.concat(result.content) : $scope.model.page = result;
        $state.go($scope.LIST_STATE);
        $scope.$apply();
      },
      errorHandler: function (message, exception) {
        $mdToast.showSimple(message);
        $scope.$apply();
      }
    });
  };


  $scope.listHistoricoUsuario = function(){
    usuarioService.listPerfilUsuarioAplicativoHistoricoByUsuarioId($scope.model.entity.id,{
      callback: function(result){
        console.log(result) ;
      },
      errorHandler: function (message, exception) {
        $scope.$apply();
      }
    });         
  };        

  $scope.hide = function (answer) {
    $mdDialog.hide(answer);
  };            

  // Exibe o histórico de aplicativos do usuário
  $scope.showHistory = function (ev) {
    $mdDialog.show({
      controller: 'UsuarioController',
      templateUrl: 'modules/autenticador/views/usuario/usuario-historico-aplicativo.jsp',
      targetEvent: ev
    });
  };

  $scope.onCloseEventHandler = function(){
    $scope.aplicativos = null; 
    $scope.$apply();      
  };
            
  /**
   * Retorna todos os aplicativos deste usuário
   *
   * @see $scope.LIST_STATE
   * @see $scope.filters
   * @see $scope.page
  */
  $scope.onOpenEventHandler = function () {
    $scope.aplicativos = new Array();
    usuarioService.listPerfilUsuarioAplicativoByUsuarioId($scope.model.entity.id, null, {
      callback: function (result) {
        $scope.perfisUsuarioAplicativo = result.content;
        angular.forEach(result.content, function(perfilUsuarioAplicativo, i){
      
          var index = $scope.aplicativos.indexOf($filter('filter')($scope.aplicativos, {id: perfilUsuarioAplicativo.perfilAcesso.aplicativo.id})[0]);
      
          if(index<0){
            $scope.aplicativos.push(perfilUsuarioAplicativo.perfilAcesso.aplicativo);                                     
          }

        });
        $scope.$apply();
      }
    });
  };


  $scope.test1 = function(){
    console.log('test 1');
  };

  $scope.test2 = function(model){
    console.log(model);
  };

  $scope.test3 = function(){
    console.log('test 3');
  };

});


}(window.angular));

