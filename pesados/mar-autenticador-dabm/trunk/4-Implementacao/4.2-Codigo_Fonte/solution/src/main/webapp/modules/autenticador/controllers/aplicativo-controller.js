(function (angular) {
  'use strict';

  /**
   *    Controller do caso de uso Manter Aplicativos
   *
   * @param $scope
   * @param $state
   */
  angular.module('autenticador')
    .controller('AplicativoController', function ($scope, $state, $importService, $mdToast, $mdSidenav, $mdDialog, $mdUtil, $log, $rootScope, $filter) {

      /**
       * Serviços importados do DWR
       */
      $importService("aplicativoService");

      /*-------------------------------------------------------------------
       * 		 				 	ATTRIBUTES
       *-------------------------------------------------------------------*/

      $scope.tags = [];
      $scope.search = false;
      $scope.inputIcone = '';
      $scope.listAplicativos = [];

      //----STATES
      /**
       * Representa o estado de listagem de registros.
       */
      $scope.LIST_STATE = "aplicativo.list";
      /**
       * Representa o estado para a criação de registros.
       */
      $scope.ADD_STATE = "aplicativo.add";
      /**
       * Representa o estado para a edição de registros.
       */
      $scope.EDIT_STATE = "aplicativo.edit";
      /**
       * Representa o estado de detalhe de um registro.
       */
      $scope.DETAIL_STATE = "aplicativo.detail";


      //----FORM MODEL

      /**
       * Contém o estados dos filtros da tela
       * Contém estado da paginação inicial contendo sorting
       *
       */

      $scope.aplicativo = {
        form: null,
        entity: new Aplicativo(),
        permissao: new Permissao(),
        filters: {
          terms: [],
          ativo: true
        },
        page: {//PageImpl
          content: null,
          pageable: {//PageRequest
            size: 50,
            page: 0,
            sort: {//Sort
              orders: [
                {direction: 'ASC', property: 'nome'}
              ]
            }
          }
        }
      };

      $scope.isFormSubmit = false

      $scope.perfilAcessoPermissao = [];

      //-------LOADINGS
      $scope.loadingPerfilAcesso = false;

      //-------BUTTONS STATES
      $scope.showAddPerfilAcesso = false;


      //-------SIDENAV STATES
      $scope.isFilterOpen = false;

      $scope.selectedItems = [];

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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Aplicativos'}];

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

      $scope.toggleSearch = function(){

        $state.current.breadCrumbs = $scope.search ? [] : [{'state': $scope.LIST_STATE, 'name': 'Aplicativos'}];

        $scope.search = !$scope.search;

        if($scope.search) {
          setTimeout(function(){$('#search').find('input').focus()},100);
        } else {
          $scope.tags = [];
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

        $scope.aplicativo.filters.terms = newlVal;
        $scope.listAplicativosByFilter();

      }, true);

      $scope.$on('user-logged', function(events, args) {
        $scope.userLogged = args;
      });

      /**
       * Handler para que realiza os procedimentos iniciais (prepara o estado)
       * para a tela de inserção e após isso, muda o estado para $scope.ADD_STATE.
       *
       * @see $scope.ADD_STATE
       */
      $scope.changeToAdd = function () {
        console.debug("changeToAdd");

        $scope.aplicativo.entity = new Aplicativo();//Limpa o formulário

        $state.current.breadCrumbs.push({name: 'Novo aplicativo'});

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

        aplicativoService.findAplicativoById(id, {
          callback: function (result) {
            $scope.aplicativo.entity = result;

            $state.go($scope.EDIT_STATE, {id: id});

            $state.current.breadCrumbs.push({name: result.nome});

            $scope.findIconeAplicativoById(id);
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
        //console.debug("changeToList");

        if (paginate) $scope.aplicativo.page.pageable.page++;

        $scope.listAplicativosByFilter($scope.aplicativo.filters, $scope.aplicativo.page.pageable);
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

        aplicativoService.findAplicativoById(id, {
          callback: function (result) {
            $scope.aplicativo.entity = result;

            $state.current.breadCrumbs.push({name: result.nome});

            $scope.findIconeAplicativoById(id);

            $state.go($scope.DETAIL_STATE, {id: id});

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
       * Antes de excluir, o usuário é notificado para confirmação e só então o registro é excluido.
       */
      $scope.changeToRemove = function (event, entity) {

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente excluir o aplicativo ' + entity.nome + '?')
          .content('Não será possível restaurar este registro posteriormente.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then(function (result) {

          aplicativoService.removeAplicativos([entity.id], {
            callback: function (result) {
              $mdToast.showSimple('Aplicativo ' + entity.nome +  ' excluído com sucesso.');
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

        console.debug("insertHandler");

        if (form.$valid) {

          $scope.isFormSubmit = false;

          $scope.aplicativo.entity.perfisDinamicos = $scope.aplicativo.entity.perfisDinamicos == null ? false : $scope.aplicativo.entity.perfisDinamicos;
          $scope.aplicativo.entity.identificador   = retirarAcento($scope.aplicativo.entity.nome).replace(' ', '');

          aplicativoService.insertAplicativo($scope.aplicativo.entity, {
            callback: function (result) {
              $mdToast.showSimple('Aplicativo inserido com sucesso.');

              if($scope.inputIcone) {
                aplicativoService.updateIcone($scope.inputIcone, result.id, {
                  callback: function (result) {

                    $scope.$apply();
                  },
                  errorHandler: function (message, exception) {
                    $mdToast.showSimple(message);
                    $scope.$apply();
                  }
                });
              }
              $scope.isFormSubmit = true;
              $state.go($scope.DETAIL_STATE, {id: result.id});
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });

        } else {

          informacoes.open();

          $scope.isFormSubmit = true;
          $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório.');
        }
      };

      $scope.listAplicativosAtivos = function() {
        aplicativoService.listAplicativosByFilters(null, true, null, {
          callback: function (result) {

            if($scope.aplicativo.entity.id) {
              var aplicativo = $filter('filter')(result.content, {id: $scope.aplicativo.entity.id})[0];
              var index = result.content.indexOf(aplicativo);
              result.content.splice(index, 1);
            }

            $scope.listAplicativos = result.content;
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      $scope.loadAplicativoInfo = function(){
        $scope.listAplicativosAtivos();
      };

      /**
       * Realiza a inserção de um novo registro
       * e no suscesso, modifica o estado da tela para o detail.
       */
      $scope.updateHandler = function (form) {
        console.debug("updateHandler");

        if (form.$valid) {

          $scope.isFormSubmit = false;

          if(!$scope.aplicativo.entity.identificador || $scope.aplicativo.entity.identificador == null)
            $scope.aplicativo.entity.identificador = retirarAcento($scope.aplicativo.entity.nome).replace(' ', '');

          aplicativoService.updateAplicativo($scope.aplicativo.entity, {
            callback: function (result) {
              $mdToast.showSimple('Aplicativo atualizado com sucesso.');
              $state.go($scope.DETAIL_STATE, {id: result.id});
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        } else  {

          informacoes.open();

          $scope.isFormSubmit = true;
          $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório.');
        }
      };

      /*-------------------------------------------------------------------
       * 		 				 PRIVATE BEHAVIORS
       *-------------------------------------------------------------------*/
      /**
       * Realiza a consulta de registros, considerando filtro, paginação e sorting.
       *
       * @see $scope.LIST_STATE
       * @see $scope.filters
       * @see $scope.page
       */
      $scope.listAplicativosByFilter = function (filters, pageRequest) {

        $scope.selectedItems = [];

        aplicativoService.listAplicativosByFilters($scope.aplicativo.filters.terms.toString(), $scope.aplicativo.filters.ativo, pageRequest, {
          callback: function (result) {
            $scope.aplicativo.page.content = result.content;

            $scope.aplicativo.page.content.forEach(function(aplicativo){

              aplicativoService.findIconeAplicativoById(aplicativo.id, {
                callback: function (result) {
                  aplicativo.icone = result;
                  $scope.$apply();
                }
              });

            });
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
       handler de back para clicar no botão voltar
       */
      $rootScope.backHandler = function () {
        if ($state.current.name == $scope.ADD_STATE || $state.current.name == $scope.DETAIL_STATE) {
          $state.go($scope.LIST_STATE);
        } else if ($state.current.name == $scope.EDIT_STATE) {
          $state.go($scope.DETAIL_STATE, {id: $scope.aplicativo.entity.id});
        };
      };

      $scope.findIconeAplicativoById = function (id) {
        aplicativoService.findIconeAplicativoById(id, {
          callback: function (result) {
            $scope.icone = result;
            $scope.$apply();
          }
        });
      };

      $scope.toggleRightMenu = function () {
        $scope.isFilterOpen = !$scope.isFilterOpen;
      };

      $scope.clearSelectedItems = function() {
        $scope.selectedItems = [];
        $scope.aplicativo.page.content.forEach(function(aplicativo){
          aplicativo.checked = false;
          aplicativo.showCheckBox = false;
        });
      };


      $scope.enableAplicativo = function (aplicativoIds) {

        var aplicativoIds = aplicativoIds;
        
        if(!aplicativoIds) {

            aplicativoIds = [];

            $scope.selectedItems.forEach(function(aplicativo){
                aplicativoIds.push(aplicativo.id);
            });
        }

        aplicativoService.enableAplicativos(aplicativoIds, {
          callback: function (result) {
            
            $mdToast.showSimple('Aplicativo ativado com sucesso.');

            if ($state.current.name == $scope.DETAIL_STATE) {
                $scope.aplicativo.entity.ativo = true;
            } else {
                $scope.listAplicativosByFilter();
            }

            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      }

      /**
       * Realiza os procedimentos iniciais (prepara o estado)
       * para a desativação do aplicativo.
       * Antes de desativar, o usuário poderá informar uma mensagem e confirmar.
       */
      $scope.disableAplicativo = function (event, aplicativo) {

        $mdDialog.show({
          controller: DialogDisableAplicativoController,
          templateUrl: 'modules/autenticador/views/aplicativo/aplicativo-modal-disable.jsp',
          targetEvent: event,
          locals: {
            aplicativo: aplicativo
          }
        })
          .then(function (aplicativo) {

            var aplicativosIds = [];

            if (aplicativo.length > 1) {
              aplicativo.forEach(function (item) {
                aplicativosIds.push(item.id);
              });

            } else {
              aplicativosIds.push(aplicativo.id);
            }

            aplicativoService.disableAplicativos(aplicativosIds, aplicativo.mensagemDesativacao, {
              callback: function (result) {

                $mdToast.showSimple('Aplicativo desativado com sucesso.');

                aplicativo.ativo = false;


                $scope.listAplicativosByFilter();

                $scope.$apply();
              },
              errorHandler: function (message, exception) {
                $mdToast.showSimple(message);
                $scope.$apply();
              }
            });
          }, function () {

          });
      };

      /*-------------------------------------------------------------------
       * 		 				 PERFIL DE ACESSO E PERMISSÃO
       *-------------------------------------------------------------------*/

      /**
       *  Exibe um perfil de acesso
       *  @see $scope.perfilAcesso
       */
      $scope.showPerfilAcesso = function (perfilAcesso) {

        /* MOSTRAR O PRIMEIRO AO ABRIR PERFIS DE ACESSO */
        if (!perfilAcesso) {
          $scope.perfilAcesso = $scope.perfisAcesso[0];
        } else {
          $scope.perfilAcesso = perfilAcesso;
        }

        aplicativoService.listPerfilAcessoPermissoesByPerfilAcessoId($scope.perfilAcesso.id, {
          callback: function (result) {

            $scope.perfilAcessoPermissoes = result;

            //DESMARCAR TODOS OS CHECKBOXES
            angular.forEach($scope.perfilAcessoPermissao, function (perfilAcessoPermissao, key) {
              $scope.perfilAcessoPermissao[key].status = false;
            });

            //MARCAR AS PERMISSÕES DO PERFIL SELECIONADO
            angular.forEach(result, function (item) {
              $scope.perfilAcessoPermissao[item.permissao.id] = {status: true, permissaoId: item.id};
            });

            $scope.loadingPerfilAcesso = false;

            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      /**
       * Realiza os procedimentos iniciais (prepara o estado)
       * para a tela de exclusão.
       * Antes de excluir, o usuário é notificado para confirmação e só então o registro é excluido.
       */
      $scope.removePerfilAcesso = function (event, entity) {

        console.debug("removePerfilAcesso", entity);

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente excluir o perfil de acesso ' + entity.nome + '?')
          .content('Não será possível recuperar este perfil de acesso se for excluído.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then(function (result) {
          aplicativoService.removePerfilAcesso(entity.id, {
            callback: function () {

                $scope.showPerfilAcesso();

                var perfilAcesso = $filter('filter')($scope.perfisAcesso, {id: entity.id})[0];
                var index = $scope.perfisAcesso.indexOf(perfilAcesso);
                
                $scope.perfisAcesso.splice(index, 1);

                $mdToast.showSimple('Perfil de acesso  ' + entity.nome + ' excluído com sucesso.');
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        });
      };

      $scope.excluirAplicativos = function(){

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente excluir estes aplicativos?')
          .content('Não será possível restaurá-los posteriormente.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        var aplicativosIds = [];

        $scope.selectedItems.forEach(function(item){
            aplicativosIds.push(item.id);
        });

        $mdDialog.show(confirm).then(function (result) {
          aplicativoService.removeAplicativos( aplicativosIds , {
            callback: function (result) {
              $mdToast.showSimple('Aplicativos excluídos com sucesso.');

              $scope.listAplicativosByFilter();

              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        });
      };

      $scope.showPermissoes = function () {
        aplicativoService.listPermissoesByAplicativoId($scope.aplicativo.entity.id, {
          callback: function (result) {

            $scope.aplicativo.permissao = result;

            $scope.showPerfilAcesso();
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      $scope.selectItem = function(item) {

        if(item.checked) {
          $scope.selectedItems.push(item);
        } else {
          var item = $filter('filter')($scope.selectedItems, {id: item.id})[0];
          $scope.selectedItems.splice(item, 1);
        }

      };

      $scope.openPerfisAcesso = function () {

        $scope.showAddPerfilAcesso = true;
        $scope.loadingPerfilAcesso = true;

        aplicativoService.listPerfisAcessoByAplicativoId($scope.aplicativo.entity.id, {
          callback: function (result) {

            if (!!result.length) {
              $scope.perfisAcesso = result;
              $scope.showPermissoes();
            } else {
              $scope.loadingPerfilAcesso = false;
            }
            $scope.$apply();
          },
          errorHandler: function (message, exception) {
            $mdToast.showSimple(message);
            $scope.$apply();
          }
        });
      };

      $scope.onCloseEventHandler = function () {

        $scope.showAddPerfilAcesso = false;
      };

      $scope.setPermissoesInferiores = function(permissoesInferiores, status, perfilAcessoPermissoesIds){

        var perfilAcessoPermissoesIds = perfilAcessoPermissoesIds ? perfilAcessoPermissoesIds : [];

        permissoesInferiores.forEach(function(permissaoInferior){

          var permissaoInferior = permissaoInferior;
          if (status) {

            aplicativoService.insertPerfilAcessoPermissao($scope.perfilAcesso.id, permissaoInferior.id, {
              callback: function (result) {

                if (!$scope.perfilAcessoPermissao[result.permissao.id]) {
                  $scope.perfilAcessoPermissao[result.permissao.id] = {
                    id: result.permissao.id,
                    status: true,
                    permissaoId: result.id
                  };
                } else {
                  $scope.perfilAcessoPermissao[result.permissao.id].permissaoId = result.id;
                  $scope.perfilAcessoPermissao[result.permissao.id].status = true;
                }
                $scope.$apply();

                $scope.setPermissoesInferiores(permissaoInferior.permissoesInferiores, status, perfilAcessoPermissoesIds);
              },
              errorHandler: function (message, exception) {
                $mdToast.showSimple(message);
                $scope.$apply();
              }
            });

          } else {

            perfilAcessoPermissoesIds.push({id: permissaoInferior.id, permissaoId: $scope.perfilAcessoPermissao[permissaoInferior.id].permissaoId });

            $scope.setPermissoesInferiores(permissaoInferior.permissoesInferiores, status, perfilAcessoPermissoesIds);
          }
        });

        return perfilAcessoPermissoesIds;

      };

      $scope.setPermissaoSuperior = function(permissaoSuperior, status){

        if(!!$scope.perfilAcessoPermissao[permissaoSuperior.id])
          $scope.perfilAcessoPermissao[permissaoSuperior.id].indeterminate = !status;

        if(permissaoSuperior.permissaoSuperior)
          $scope.setPermissaoSuperior(permissaoSuperior.permissaoSuperior);

      };

      /*$scope.setPerfilAcessoPermissaoIndeterminate = function(){

        $scope.perfilAcessoPermissoes.forEach(function(permissao){
        });

        $scope.perfilAcessoPermissao.forEach(function(perfilAcessoPermissao){
          if($scope.perfilAcessoPermissoes[perfilAcessoPermissao.id])
            console.log($scope.perfilAcessoPermissoes[perfilAcessoPermissao.id]);
          else
            console.log(perfilAcessoPermissao);
        })
      };*/

      $scope.setPerfilAcessoPermissao = function (permissao, perfilAcessoPermissaoId, perfilAcessoPermissaoStatus) {

        var permissaoId = permissao.id;

        if (perfilAcessoPermissaoStatus) {
          aplicativoService.insertPerfilAcessoPermissao($scope.perfilAcesso.id, permissaoId, {
            callback: function (result) {
              $scope.perfilAcessoPermissao[result.permissao.id].permissaoId = result.id
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });

          $scope.setPermissoesInferiores(permissao.permissoesInferiores, true);

        } else {

          aplicativoService.removePerfilAcessoPermissoes([perfilAcessoPermissaoId], {
            callback: function (result) {
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });

          var permissoes = $scope.setPermissoesInferiores(permissao.permissoesInferiores, false);

          //console.log(permissoes);

          var permissoesIds = [];

          permissoes.forEach(function(permissao){
            permissoesIds.push(permissao.permissaoId);
            $scope.perfilAcessoPermissao[permissao.id].status = false;
          });

          if(!!permissoesIds.length) {
            aplicativoService.removePerfilAcessoPermissoes(permissoesIds, {
              callback: function () {
                $scope.$apply();
              },
              errorHandler: function (message, exception) {
                $mdToast.showSimple(message);
                $scope.$apply();
              }
            });
          }
        }

        $scope.setPermissaoSuperior(permissao.permissaoSuperior, perfilAcessoPermissaoStatus);

      };

      $scope.showFormPerfilAcesso = function (event, perfilAcesso) {
        $mdDialog.show({
          controller: DialogPerfilAcessoController,
          templateUrl: 'modules/autenticador/views/aplicativo/aplicativo-perfil-acesso-form.jsp',
          targetEvent: event,
          locals: {
            perfilAcesso: perfilAcesso
          }
        })
          .then(function (perfilAcesso) {

            perfilAcesso.entity.aplicativo = $scope.aplicativo.entity;

            if (!perfilAcesso.entity.id) {

                perfilAcesso.entity.editavel  = true;
                perfilAcesso.entity.removivel = true;

              aplicativoService.insertPerfilAcesso(perfilAcesso.entity, {
                callback: function (result) {

                  $scope.perfisAcesso.push(result);

                  $scope.showPerfilAcesso(result);

                  $mdToast.showSimple('Perfil de acesso inserido com sucesso.');
                  $scope.$apply();
                },
                errorHandler: function (message, exception) {
                  $mdToast.showSimple(message);
                  $scope.$apply();
                }
              });

            } else {
              aplicativoService.updatePerfilAcesso(perfilAcesso.entity, {
                callback: function (result) {
                  $mdToast.showSimple('Perfil de acesso atualizado com sucesso.');
                  $scope.$apply();
                },
                errorHandler: function (message, exception) {
                  $mdToast.showSimple(message);
                  $scope.$apply();
                }
              });
            }

          }, function (result) {

            if(!!result){
                var perfilAcesso = $filter('filter')($scope.perfisAcesso, {id: result.id})[0];
                var index = $scope.perfisAcesso.indexOf(perfilAcesso);

                $scope.perfisAcesso[index] = result;
                $scope.perfilAcesso = result;
            }
            //$scope.$apply();
          });
      };

      $scope.onUploadIconError = function (msg) {
        //console.log(msg);
        $mdToast.showSimple(msg);
      };

      $scope.onUploadIconSuccess = function (input) {

        if($scope.aplicativo.entity.id) {
          aplicativoService.updateIcone(input.element, $scope.aplicativo.entity.id, {
            callback: function (result) {
              $mdToast.showSimple('Ícone atualizado com sucesso.');
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        } else {
          $scope.inputIcone = input.element;
        }
      };

      $scope.onUploadIconDelete = function (event) {

        var confirm = $mdDialog.confirm()
          .title('Deseja realmente remover o ícone do aplicativo ' + $scope.aplicativo.entity.nome + '?')
          .content('Não será possível recuperar este registro se for excluído.')
          .ok('Sim')
          .cancel('Cancelar')
          .targetEvent(event);

        $mdDialog.show(confirm).then(function (result) {

          aplicativoService.removeIcone($scope.aplicativo.entity.id, {
            callback: function () {

              $scope.icone = null;

              $mdToast.showSimple('Ícone excluído com sucesso.');
              $scope.$apply();
            },
            errorHandler: function (message, exception) {
              $mdToast.showSimple(message);
              $scope.$apply();
            }
          });
        });
      }

      function DialogPerfilAcessoController($scope, perfilAcesso, $mdDialog, $mdToast) {

        $scope.perfilAcessoOld = angular.copy(perfilAcesso);

        $scope.perfilAcesso = {
          form: null,
          entity: !!perfilAcesso ? perfilAcesso : new PerfilAcesso()
        };

        $scope.perfilAcesso.entity.editavel = true;
        $scope.perfilAcesso.entity.removivel = true;

        $scope.hide = function () {
          $mdDialog.hide();
        };

        $scope.cancel = function () {
          $mdDialog.cancel($scope.perfilAcessoOld);
        };

        $scope.sendForm = function (form) {

            if(form.$valid){

                $scope.isFormSubmit = false;
                $mdDialog.hide($scope.perfilAcesso);

            }else{
                $scope.isFormSubmit = true;
                $mdToast.showSimple('Os campos em destaque são de preenchimento obrigatório.');
            }
        };
      };

      $scope.showCheckBox = function(aplicativo, status) {

        aplicativo.showCheckBox = aplicativo.checked ? true : status;
      };

      function DialogDisableAplicativoController($scope, aplicativo, $mdDialog) {

        $scope.aplicativo = aplicativo;

        $scope.aplicativoList = [];

        if (aplicativo.length > 1) {
          aplicativo.forEach(function (item) {
            $scope.aplicativoList.push(item.nome);
          });

        } else if (aplicativo.length == 1) {
          $scope.aplicativo = aplicativo[0];
        }

        if ($scope.aplicativoList.length > 1) {

          $scope.lastAplicativo = $scope.aplicativoList[$scope.aplicativoList.length - 1];
          $scope.aplicativoList.pop();

          if ($scope.aplicativoList.length > 1) {
            $scope.aplicativoListNomes = $scope.aplicativoList.join(', ', $scope.aplicativoList) + ' e ' + $scope.lastAplicativo;
          } else {
            $scope.aplicativoListNomes = $scope.aplicativoList[0] + ' e ' + $scope.lastAplicativo;
          }
        }

        $scope.hide = function () {
          $mdDialog.hide();
        };

        $scope.cancel = function () {
          $mdDialog.cancel();
        };

        $scope.sendForm = function () {
          $mdDialog.hide($scope.aplicativo);
        };
      };

      function retirarAcento(objResp) {
        var varString = new String(objResp);
        var stringAcentos = new String('àâêôûãõáéíóúçüÀÂÊÔÛÃÕÁÉÍÓÚÇÜ');
        var stringSemAcento = new String('aaeouaoaeioucuAAEOUAOAEIOUCU');

        var i = new Number();
        var j = new Number();
        var cString = new String();
        var varRes = '';

        for (i = 0; i < varString.length; i++) {
          cString = varString.substring(i, i + 1);
          for (j = 0; j < stringAcentos.length; j++) {
            if (stringAcentos.substring(j, j + 1) == cString){
              cString = stringSemAcento.substring(j, j + 1);
            }
          }
          varRes += cString;
        }
        return varRes;
      }

    });

}(window.angular));