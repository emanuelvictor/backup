(function (angular) {
  'use strict';

  /**
   *
   * @param $scope
   * @param $state
   */
  angular.module('autenticador')
    .controller('UsuarioController', function ($scope, $mdSidenav, $rootScope, $state, $importService, $mdToast, $mdDialog, $log, $timeout, $q, $filter) {

      /**
       * Serviços importados do DWR
       */
      $importService("usuarioService");
      $importService("aplicativoService");


      /*-------------------------------------------------------------------
       * 		 				 	ATTRIBUTES
       *-------------------------------------------------------------------*/

      $scope.tags = [];
      $scope.search = false;
      $scope.inputFoto = '';
      $scope.historic = [];

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

        hideTitle: false,

        filters: {
          terms: [],
          key: 'ativo'
        },
        page: {//PageImpl
          content: null,
          pageable: {//PageRequest
            size: 5,
            page: 0,
            sort: {//Sort
              orders: [
                {direction: 'ASC', property: 'nomeCompleto'}
              ]
            }
          }
        }
      };

      $scope.isFilterOpen = false;

      $scope.isFormSubmit = false;
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

        if ($state.current.name != $scope.LIST_STATE)
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Usuários'}];

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

        $state.go($scope.ADD_STATE);

        console.debug("changeToAdd");

        $scope.model.entity = new Usuario();//Limpa o formulário

        $scope.model.entity.dataBloqueio = undefined;
        $scope.model.entity.dataDesbloqueio = undefined;

        $state.current.breadCrumbs.push({name: 'Novo usuário'});
      };

      $scope.toggleSearch = function(){

        $state.current.breadCrumbs = $scope.search ? [] : [{'state': $scope.LIST_STATE, 'name': 'Usuários'}];

        $scope.search = !$scope.search;

        if($scope.search) {
          setTimeout(function(){$('#search').find('input').focus()},100);
        }
      };

      $scope.addTag = function(){
        if($('#search').find('input').val() != '' && $scope.search && !!$scope.tags) {

          $scope.tags.push($('#search').find('input').val());
          $('#search').find('input').val('');
          $('#search').find('input').focus();

        }
      };

      $scope.$watch('tags', function(newlVal, oldVal){

        $scope.model.filters.terms = newlVal;

        handlerBack();

      }, true);

      $scope.$on('user-logged', function(events, args) {
        $scope.userLogged = args;
      });

      /**
       handler de back para clicar no botão voltar
       */
      $rootScope.backHandler = function () {
        if ($state.current.name == $scope.ADD_STATE || $state.current.name == $scope.DETAIL_STATE) {
          $state.go($scope.LIST_STATE);
        } else if ($state.current.name == $scope.EDIT_STATE) {
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

            $state.go($scope.EDIT_STATE, {id: id});
            
            $state.current.breadCrumbs.push({name: result.nomeCompleto});

            // date-picker não aceita objeto do tipo null
            if ($scope.model.entity.dataExpiracaoSenha == null) $scope.model.entity.dataExpiracaoSenha = undefined;
            // date-picker não aceita objeto do tipo null
            if ($scope.model.entity.dataBloqueio == null) $scope.model.entity.dataBloqueio = undefined;
            // date-picker não aceita objeto do tipo null
            if ($scope.model.entity.dataDesbloqueio == null) $scope.model.entity.dataDesbloqueio = undefined;

            // Certifica-se de que a variável aplicativo sempre esteja null quando a controller for carregada
            $scope.aplicativos = null;

           $scope.findFotoUsuarioById(id);

            
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

        $scope.listUsuariosByFilters($scope.model.filters, $scope.model.page.pageable);
      };



      /**
       * Realiza os procedimentos iniciais (prepara o estado)
       * para a tela de detalhe e após isso, muda o estado para de
       * tail.
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

            //$scope.dataExpiracaoSenha = moment($scope.model.entity.dataExpiracaoSenha).format('DD/MM/YYYY hh:mm');
            //$scope.dataBloqueio = moment($scope.model.entity.dataBloqueio).format('DD/MM/YYYY hh:mm');
            //$scope.dataDesbloqueio = moment($scope.model.entity.dataDesbloqueio).format('DD/MM/YYYY hh:mm');

            $scope.findFotoUsuarioById(id);

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
      $scope.changeToRemove = function (event, entity, index, selected) {

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente excluir este usuário?')
          .content('Será possível restaurá-lo posteriormente.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then(function (result) {
          usuarioService.excluirUsuarios([entity.id], {
            callback: function (result) {
              if(index != undefined){
                //Se esta excluindo a partir da tabela
                $scope.model.page.content[index] = result[0];
                // Handler para decrementar o contador do top-sheet
                angular.forEach(selected, function(value, key) {
                  if(entity.id==value.id){
                    selected.splice(key, 1);
                    $scope.selected.excluir = [];
                  }
                });
              }else{
                //Se esta excluindo a partir da edição de usuário
                $scope.model.entity.enabled = result[0].enabled;
                $scope.model.entity.dataExclusao = result[0].dataExclusao;
              }
              $mdToast.showSimple('Usuário excluído com sucesso.');
              $scope.$apply();
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
      $scope.insertHandler = function (form) {



        if (form.$valid) {

          delete $scope.model.entity.perfisUsuarioAplicativo;

          $scope.isFormSubmit = false;

          if ($scope.model.entity.id) {

            $scope.updateHandler();

          } else {

            console.debug("insertHandler");
            usuarioService.insertUsuario($scope.model.entity, {
              callback: function (result) {
                $mdToast.showSimple('Usuário inserido com sucesso.');

                if($scope.inputFoto) {
                  usuarioService.uploadFotoUsuario($scope.inputFoto, result.id, {
                    callback: function (result) {
                      $rootScope.$broadcast('user-logged', $scope.userLogged);
                      $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                      $mdToast.showSimple(message);
                      $scope.$apply();
                    }
                  });
                }

                $state.go($scope.DETAIL_STATE, {id: result.id});
                $scope.$apply();
              },
              errorHandler: function (message, exception) {
                $mdToast.showSimple(message);
                $scope.$apply();
              }
            });
          }
        } else {

          informacoes.open();

          $scope.isFormSubmit = true;
          $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório');
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
            $mdToast.showSimple('Usuário atualizado com sucesso.');
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
        }).then(function (result) {
        }, function () {
        });
      };

      $scope.bloquearUsuario = function (ev, usuario, selected) {

        $mdDialog.show({
          controller: 'BloquearUsuariosController',
          templateUrl: 'modules/autenticador/views/usuario/usuario-bloquear.jsp',
          targetEvent: ev,
          locals: {
            idsUsuarios: [usuario.id]
          }
        }).then(function (result) {

          if(result !== true) {

          /*  result[0].dataExpiracaoSenha = moment(result[0].dataExpiracaoSenha).format('DD/MM/YYYY hh:mm');
            result[0].dataBloqueio = moment(result[0].dataBloqueio).format('DD/MM/YYYY hh:mm');
            result[0].dataDesbloqueio = moment(result[0].dataDesbloqueio).format('DD/MM/YYYY hh:mm');*/

            $scope.model.entity = result[0];
            $mdToast.showSimple('Usuário bloqueado com sucesso.');
          }
        }, function () {
        });
      };

      $scope.desbloquearUsuario = function (usuario, index, selected) {

        usuarioService.desbloquearUsuarios([usuario.id], {
          callback: function (result) {
            if (index != undefined) {
              $scope.model.page.content[index] = result[0];
              // Handler para decrementar o contador do top-sheet
              angular.forEach(selected, function(value, key) {
                if(usuarios[0].id==value.id){
                  selected.splice(key, 1);
                }
              });
            } else {
              $scope.model.entity = result[0];
            };
            $mdToast.showSimple('Usuário desbloqueado com sucesso');
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
          }
        });
      };


      $scope.restaurarUsuario = function (usuario, index, selected) {
        usuarioService.restaurarUsuarios([usuario.id], {
          callback: function (result) {
            if(index != undefined){
              $scope.model.page.content[index] = result[0];
              // Handler para decrementar o contador do top-sheet
              angular.forEach(selected, function(value, key) {
                if(usuario.id==value.id){
                  selected.splice(key, 1);
                }
              });
            }else{
              $scope.model.entity.enabled = result[0].enabled;
              $scope.model.entity.dataExclusao = result[0].dataExclusao;
            }
            $mdToast.showSimple('Usuário desbloqueado com sucesso.');
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
          }
        });
      };

      $scope.findFotoUsuarioById = function(id){
        usuarioService.findFotoUsuarioById(id, {
          callback: function (result) {
            $scope.foto = result;
            $scope.$apply();
          }});
      };

      $scope.onUploadFotoDelete = function(event) {

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente remover a foto do usuário ' + $scope.model.entity.nomeCompleto + '?')
          .content('Não será possível recuperar este registro se for excluído.')
          .ok('Excluir')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then( function( result ) {

          usuarioService.removeFotoUsuario($scope.model.entity.id, {
            callback: function () {

              $scope.foto = null;

              $rootScope.$broadcast('user-logged', $scope.userLogged);

              $mdToast.showSimple('Foto excluída com sucesso.');
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        });
      };


      //HANDLERS ESPECÍFICOS DA TABELA
      $scope.selected = [];

      // Separa todos os usuários a bloquear, desbloquear, excluir e restaurar
      $scope.handlerUsuarios = function(usuarios){
        $scope.selected.bloquear = [];
        $scope.selected.desbloquear = [];
        $scope.selected.excluir = [];
        $scope.selected.restaurar = [];
        angular.forEach(usuarios, function(usuario, key) {
          if(usuario.accountNonLocked){
            $scope.selected.bloquear.push(usuario.id);
          }
          if(!usuario.accountNonLocked){
            $scope.selected.desbloquear.push(usuario.id);
          }
          if(!usuario.dataExclusao){
            $scope.selected.excluir.push(usuario.id);
          }
          if(usuario.dataExclusao){
            $scope.selected.restaurar.push(usuario.id);
          }
        });
        return $scope.selected;
      };

      $scope.toggleRightMenu = function () {
        $scope.isFilterOpen = !$scope.isFilterOpen;
      };

      $scope.bloquearUsuarios = function(event, idsUsuarios){
        $mdDialog.show({
          controller: 'BloquearUsuariosController',
          templateUrl: 'modules/autenticador/views/usuario/usuario-bloquear.jsp',
          targetEvent: event,
          locals: {
            idsUsuarios: idsUsuarios
          }
        }).then(function (result) {
          if(result){
            $mdToast.showSimple('Usuários bloqueados com sucesso.');
          }
        }, function () {
        });
      };


      $scope.desbloquearUsuarios = function(idsUsuarios){
        usuarioService.desbloquearUsuarios(idsUsuarios, {
          callback: function (result) {
            angular.forEach(result, function(usuario) {
              angular.forEach($scope.model.page.content, function(usuarioDaTable, key) {
                if(usuario.id == usuarioDaTable.id){
                  $scope.model.page.content[key].accountNonLocked = usuario.accountNonLocked;
                }
              });
            });
            $mdToast.showSimple('Usuário desbloqueado com sucesso');
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
          }
        });
      };

      $scope.excluirUsuarios = function(event, usuarios){

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente excluir estes usuários?')
          .content('Será possível restaurá-los posteriormente.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then(function (result) {
          usuarioService.excluirUsuarios( usuarios , {
            callback: function (result) {

              handlerBack();

              $mdToast.showSimple('Usuários excluídos com sucesso.');
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        });
      };

      function handlerBack(){
        if($scope.model.filters.key == "ativo"){
          $scope.listUsuariosByFilters($scope.model.filters , $scope.model.page.pageable );
        } else if($scope.model.filters.key == "bloqueado"){
          $scope.listUsuariosByFiltersAndBloqueados($scope.model.filters , $scope.model.page.pageable );
        } else if($scope.model.filters.key == "excluido"){
          $scope.listUsuariosByFiltersAndExcluidos($scope.model.filters , $scope.model.page.pageable );
        }
      }

      $scope.restaurarUsuarios = function(idsUsuarios){
        usuarioService.restaurarUsuarios(idsUsuarios, {
          callback: function (result) {
            handlerBack();
            $mdToast.showSimple('Usuários restaurados com sucesso');
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
          }
        });
      };

      $scope.onOrderChange = function (order) {

        if(order[0] == '-'){
          order = order.replace('-','');
          $scope.model.page.pageable.sort.orders[0].direction = 'DESC';
        }else{
          $scope.model.page.pageable.sort.orders[0].direction = 'ASC';
        }
        $scope.model.page.pageable.sort.orders[0].property = order;

        var deferred = $q.defer();

        if($scope.model.filters.key == "ativo") {
          usuarioService.listUsuariosByFilters($scope.model.filters.terms.toString(), null, null, $scope.model.page.pageable, {
            callback: function (result) {
              deferred.resolve(result.content);
              $scope.model.page.content = result.content;
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
            }
          });
        } else if($scope.model.filters.key == "bloqueado") {

          usuarioService.listUsuariosByFiltersAndBloqueados($scope.model.filters.terms.toString(), null, null, $scope.model.page.pageable, {
            callback: function (result) {
              deferred.resolve(result.content);
              $scope.model.page.content = result.content;
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });

        } else if($scope.model.filters.key == "excluido"){

          usuarioService.listUsuariosByFiltersAndExcluidos($scope.model.filters.terms.toString(), null, null, $scope.model.page.pageable, {
            callback: function (result) {
              deferred.resolve(result.content);
              $scope.model.page.content = result.content;
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });

        }

        return deferred.promise;
      };

      $scope.onPaginationChange = function (page, limit) {

        if(limit > 0) {
          $scope.model.page.pageable.page = page;
          $scope.model.page.pageable.size = limit;
          handlerBack();
        }
      };

      // testando tabela
      $scope.removePerfilUsuarioAplicativo = function (aplicativo/*, perfisUsuarioAplicativo*/) {
        usuarioService.removePerfilUsuarioAplicativoByAplicativoId($scope.model.entity.id, aplicativo, {
          callback: function () {
            $scope.aplicativos.splice($scope.aplicativos.indexOf(aplicativo), 1);
            $scope.$apply();
          }
        });
      };

      $scope.showResetPassword = function (ev, usuario) {
        $mdDialog.show({
          controller: 'AlterarSenhaController',
          templateUrl: 'modules/autenticador/views/usuario/usuario-modal-reset-password.jsp',
          targetEvent: ev,
          locals: {
            usuario: usuario
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
            aplicativos: $scope.aplicativos
          }
        }).then(function (answer) {
          $scope.onOpenEventHandler();
        }, function () {
          $scope.onOpenEventHandler();
        });
      };


      /*-------------------------------------------------------------------
       * 		 				 PRIVATE BEHAVIORS
       *-------------------------------------------------------------------*/

      /**
       * Realiza a consulta de registros, consirando filtro, paginação e sorting.
       * OBS: RETORNA APENAS OS USUAÁRIOS ATIVOS
       * @see $scope.LIST_STATE
       * @see $scope.filters
       * @see $scope.page
       */
      $scope.listUsuariosByFilters = function (filters, pageRequest) {

        $scope.selectedItems = [];
        $scope.selected = [];

        usuarioService.listUsuariosByFilters($scope.model.filters.terms.toString(), null, null, $scope.model.page.pageable, {
          callback: function (result) {
            $scope.model.page = result;
            $state.go($scope.LIST_STATE);
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      /**
       * Realiza a consulta de registros, consirando filtro, paginação e sorting.
       * OBS: RETORNA APENAS OS USUÁRIOS EXCLUÍDOS
       * @see $scope.LIST_STATE
       * @see $scope.filters
       * @see $scope.page
       */
      $scope.listUsuariosByFiltersAndExcluidos = function (filters, pageRequest) {

        $scope.selectedItems = [];
        $scope.selected = [];

        usuarioService.listUsuariosByFiltersAndExcluidos(filters.terms.toString(), null, null, pageRequest, {
          callback: function (result) {
            $scope.model.page = result;
            $state.go($scope.LIST_STATE);
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      /**
       * Realiza a consulta de registros, consirando filtro, paginação e sorting.
       * OBS: RETORNA APENAS OS USUÁRIOS BLOQUEADOS
       * @see $scope.LIST_STATE
       * @see $scope.filters
       * @see $scope.page
       */
      $scope.listUsuariosByFiltersAndBloqueados = function (filters, pageRequest) {

        $scope.selectedItems = [];
        $scope.selected = [];

        usuarioService.listUsuariosByFiltersAndBloqueados(filters.terms.toString(), null, null, pageRequest, {
          callback: function (result) {
            $scope.model.page = result;
            $state.go($scope.LIST_STATE);
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      // Exibe o histórico de aplicativos do usuário
      $scope.showHistory = function (ev, historic) {
        $mdDialog.show({
          controller: DialogShowHistoryUser,
          templateUrl: 'modules/autenticador/views/usuario/usuario-historico-aplicativo.jsp',
          targetEvent: ev,
          locals: {
            historic: historic
          }
        }).then(function () {

        }, function () {
        });
      };

      /**
       * Retorna todos os aplicativos deste usuário
       *
       * @see $scope.LIST_STATE
       * @see $scope.filters
       * @see $scope.page
       */
      $scope.onOpenEventHandler = function () {

        usuarioService.listAplicativoByUsuarioId($scope.model.entity.id, null,{
          callback: function(result){
            $scope.aplicativos = result;

            $scope.aplicativos.forEach(function(aplicativo) {
              aplicativoService.findIconeAplicativoById(aplicativo.id, {
                callback: function (result) {
                  aplicativo.icone = result;
                  $scope.$apply();
                }
              });
            });

            $scope.$apply();
          }
        });

        usuarioService.listPerfilUsuarioAplicativoHistoricoByUsuarioId($scope.model.entity.id, {
          callback: function (result) {
            $scope.historic = result;
          },
          errorHandler: function (message, exception) {
            $scope.$apply();
          }
        });

      };

      $scope.onUploadFotoError = function (msg) {
        $mdToast.showSimple(msg);
      };

      $scope.onUploadFotoSuccess = function (input) {

        if($scope.model.entity.id) {
          usuarioService.uploadFotoUsuario(input.element, $scope.model.entity.id, {
            callback: function (result) {
              $mdToast.showSimple('Foto atualizada com sucesso.');
              $scope.userLogged.foto = result;

              $rootScope.$broadcast('user-logged', $scope.userLogged);

              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        } else {
          $scope.inputFoto = input.element;
        }
      };

      $scope.hide = function () {
        $mdDialog.hide();
      };

      $scope.hideTitle = function () {
        $scope.model.hideTitle = true;
      };


      $scope.showTitle = function () {
        $scope.model.hideTitle = false;
      };

      function DialogShowHistoryUser($scope, historic, $mdDialog) {

        $scope.historic = historic;

        $scope.hide = function () {
          $mdDialog.hide();
        };

        $scope.cancel = function () {
          $mdDialog.cancel();
        };

        $scope.sendForm = function () {
          $mdDialog.hide();
        };
      };

    });

}(window.angular));

