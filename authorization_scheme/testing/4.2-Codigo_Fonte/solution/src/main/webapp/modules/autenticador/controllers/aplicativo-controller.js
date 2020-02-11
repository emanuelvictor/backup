(function ( angular ) {
    'use strict';

/**
 *	Controller do caso de uso Manter Aplicativos 
 * 
 * @param $scope
 * @param $state
 */
angular.module('autenticador')
	   .controller('AplicativoController', function( $scope, $state, $importService, $mdToast, $mdDialog, $log, $rootScope ) {
		   
    /**
     * Serviços importados do DWR
     */
	$importService("aplicativoService");

    /*-------------------------------------------------------------------
     * 		 				 	ATTRIBUTES
     *-------------------------------------------------------------------*/
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
	    		size: 5,
	    		page: 0,
	        	sort: {//Sort
	        		orders:[
	        		   { direction: 'ASC', property: 'nome' }
	        		]
	        	}
	    	}
	    }
	};

    $scope.perfilAcessoPermissao = [];

    //-------LOADINGS
    $scope.loadingPerfilAcesso = false;

    //-------BUTTONS STATES
    $scope.showAddPerfilAcesso = false;
	
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
    $scope.$on('$stateChangeSuccess', function( event, toState, toParams, fromState, fromParams ) {

        if($state.current.name != $scope.LIST_STATE)
            $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Aplicativos'}];

    	switch ( $state.current.name ) {
			case $scope.LIST_STATE: {
				$scope.changeToList( false );
				break;
			}
			case $scope.DETAIL_STATE: {
			    $scope.changeToDetail( $state.params.id );
				break;
			}
	        case $scope.ADD_STATE: {
	        	$scope.changeToAdd();
	        	break;
	        }
	        case $scope.EDIT_STATE: {
        	    $scope.changeToEdit( $state.params.id );
	        	break;
	        }
	        default : {
	        	$state.go( $scope.LIST_STATE );
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
	$scope.changeToAdd = function() {
		console.debug("changeToAdd");
		
		$scope.aplicativo.entity = new Aplicativo();//Limpa o formulário

        $state.current.breadCrumbs.push({name: 'Novo aplicativo'});

	};

	/**
	 handler de back para clicar no botão voltar
	*/
    $rootScope.backHandler = function(){
	  if($state.current.name == $scope.ADD_STATE || $state.current.name == $scope.DETAIL_STATE){
		$state.go($scope.LIST_STATE);
	  } else if($state.current.name == $scope.EDIT_STATE){
		$state.go($scope.DETAIL_STATE, {id: $scope.aplicativo.entity.id});
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
    $scope.changeToEdit = function( id ) {
        console.debug("changeToEdit", id);

        aplicativoService.findAplicativoById( id, {
            callback : function(result) {
            	$scope.aplicativo.entity = result;

                $state.current.breadCrumbs.push({name: result.nome});

                $state.go($scope.EDIT_STATE, {id:id});
                $scope.$apply();
            },
            errorHandler : function(message, exception) {
            	$mdToast.showSimple( message );
            	$state.go( $scope.LIST_STATE );
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
    $scope.changeToList = function( paginate ) {
        console.debug("changeToList");

        if ( paginate ) $scope.aplicativo.page.pageable.page++;
        
        $scope.listAplicativosByFilter( $scope.aplicativo.filters, $scope.aplicativo.page.pageable );
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
    $scope.changeToDetail = function( id ) {
        console.debug("changeToDetail", id);

        aplicativoService.findAplicativoById( id, {
            callback : function(result) {
            	$scope.aplicativo.entity = result;

                $state.current.breadCrumbs.push({name: result.nome});

                $state.go($scope.DETAIL_STATE, {id:id});
                $scope.$apply();
            },
            errorHandler : function(message, exception) {
            	$mdToast.showSimple( message );
            	$state.go( $scope.LIST_STATE );
                $scope.$apply();
            }
        });
    };
    
    /**
     * Realiza os procedimentos iniciais (prepara o estado)
     * para a tela de exclusão.
     * Antes de excluir, o usuário é notificado para confirmação e só então o registro é excluido.
     */
    $scope.changeToRemove = function( event, entity ) {
        console.debug("changeToRemove", entity);

        var confirm = $mdDialog.confirm()
        	.title('Tem certeza que deseja excluir este registro?')
        	.content('Não será possível recuperar este registro se for excluído.')
        	.ok('Sim')
        	.cancel('Cancelar')
        	.targetEvent(event);
       
        $mdDialog.show(confirm).then( function( result ) {
        	console.log( result );
        	
        	aplicativoService.removeAplicativos( [entity.id], {
                callback : function(result) {
                    $mdToast.showSimple( "O registro foi excluído com sucesso!" );
                },
                errorHandler : function(message, exception) {
                	$mdToast.showSimple( message );
                    $scope.$apply();
                }
            });
        });
    };
    
    /**
     * Realiza a inserção de um novo registro
     * e no suscesso, modifica o estado da tela para o detail.
     */
    $scope.insertHandler = function() {
    	console.debug("insertHandler");
    	
   	if ( $scope.aplicativo.form.$invalid ) {
    		$mdToast.showSimple( "Fomulário inválido" );
    		return;
    	}
    	
		aplicativoService.insertAplicativo( $scope.aplicativo.entity, {
			callback : function( result ) {
				$mdToast.showSimple( "Registro salvo com sucesso!" );
				$state.go($scope.DETAIL_STATE, {id:result.id});
				$scope.$apply();
			},
			errorHandler : function(message, exception) {
				$mdToast.showSimple( message );
                $scope.$apply();
            }
		});
    };
    
    /**
     * Realiza a inserção de um novo registro
     * e no suscesso, modifica o estado da tela para o detail.
     */
    $scope.updateHandler = function() {
    	console.debug("updateHandler");
    	
    	aplicativoService.updateAplicativo( $scope.aplicativo.entity, {
            callback : function(result) {
            	$mdToast.showSimple( "Registro atualizado com sucesso!" );
                $state.go($scope.DETAIL_STATE, {id:result.id});
                $scope.$apply();
            },
            errorHandler : function(message, exception) {
            	$mdToast.showSimple( message );
                $scope.$apply();
            }
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
	$scope.listAplicativosByFilter = function( filters, pageRequest ) {
		
		aplicativoService.listAplicativosByFilters( filters.terms.toString(), filters.ativo, pageRequest, {
			callback : function( result ) {
				$scope.aplicativo.page.content = $scope.aplicativo.page.content ? $scope.aplicativo.page.content.concat( result.content ) : $scope.aplicativo.page = result;

				$state.go( $scope.LIST_STATE );
				$scope.$apply();
			},
			errorHandler : function(message, exception) {
				$mdToast.showSimple( message );
                $scope.$apply();
            }
		});
	}

    $scope.enableAplicativo = function(){

        aplicativoService.enableAplicativos( [$scope.aplicativo.entity.id], {
            callback: function (result) {
                $mdToast.showSimple("Aplicativo ativado com sucesso!");

                $scope.aplicativo.entity.ativo = true;

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
    $scope.disableAplicativo = function( event, aplicativo ) {

        console.debug("disableAplicativo", aplicativo);

        $mdDialog.show({
            controller: DialogDisableAplicativoController,
            templateUrl: 'modules/autenticador/views/aplicativo/aplicativo-modal-disable.jsp',
            targetEvent: event,
            locals: {
                aplicativo: aplicativo
            }
        })
        .then(function( aplicativo ) {

            aplicativoService.disableAplicativos( [aplicativo.entity.id], aplicativo.entity.mensagem_desativacao, {
                callback: function (result) {
                    $mdToast.showSimple("Aplicativo desativado com sucesso!");

                    aplicativo.entity.ativo = false;

                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message);
                    $scope.$apply();
                }
            });
        }, function() {

        });
    };


    /*-------------------------------------------------------------------
     * 		 				 PERFIL DE ACESSO E PERMISSÃO
     *-------------------------------------------------------------------*/

    /**
     *  Exibe um perfil de acesso
     *  @see $scope.perfilAcesso
     */
    $scope.showPerfilAcesso = function( perfilAcesso ) {

        /* MOSTRAR O PRIMEIRO AO ABRIR PERFIS DE ACESSO */
        if(!perfilAcesso) {
            $scope.perfilAcesso = $scope.perfisAcesso[0];
        } else {
            $scope.perfilAcesso = perfilAcesso;
        }

        aplicativoService.listPerfilAcessoPermissoesByPerfilAcessoId( $scope.perfilAcesso.id, {
            callback : function(result) {

                //console.log(result);

                $scope.perfilAcessoPermissoes = result;

                /* DESMARCAR TODOS OS CHECKBOXES */
                angular.forEach($scope.perfilAcessoPermissao, function(perfilAcessoPermissao, key){
                    $scope.perfilAcessoPermissao[key].status = false;
                })

                /* MARCAR AS PERMISSÕES DO PERFIL SELECIONADO */
                angular.forEach(result, function(item){
                    $scope.perfilAcessoPermissao[item.permissao.id] = {'status': true, 'permissaoId': item.id};
                })

                //console.log($scope.perfilAcessoPermissao);

                $scope.loadingPerfilAcesso = false;

                $scope.$apply();
            },
            errorHandler : function(message, exception) {
                $mdToast.showSimple( message );
                $scope.$apply();
            }
        });
    }

    /**
     * Realiza os procedimentos iniciais (prepara o estado)
     * para a tela de exclusão.
     * Antes de excluir, o usuário é notificado para confirmação e só então o registro é excluido.
     */
    $scope.removePerfilAcesso = function( event, entity ) {

        console.debug("removePerfilAcesso", entity);

        var confirm = $mdDialog.confirm()
            .title('Tem certeza que deseja excluir este perfil de acesso?')
            .content('Não será possível recuperar este perfil de acesso se for excluído.')
            .ok('Sim')
            .cancel('Cancelar')
            .targetEvent(event);

        $mdDialog.show(confirm).then( function( result ) {
            console.log( result );

            aplicativoService.removePerfilAcesso( entity.id, {
                callback : function(result) {
                    $mdToast.showSimple( "O perfil de acesso foi excluído com sucesso!" );
                },
                errorHandler : function(message, exception) {
                    $mdToast.showSimple( message );
                    $scope.$apply();
                }
            });
        });
    };

    $scope.showPermissoes = function() {
        aplicativoService.listPermissoesByAplicativoId( $scope.aplicativo.entity.id, {
            callback : function(result) {
                $scope.aplicativo.permissao = result;
                //console.log($scope.aplicativo.permissao);
                //$scope.permissoes = $scope.aplicativo.permissao[0];

                $scope.showPerfilAcesso();
                $scope.$apply();
            },
            errorHandler : function(message, exception) {
                $mdToast.showSimple( message );
                $scope.$apply();
            }
        });
    }

	$scope.onOpenEventHandler = function () {

        $scope.showAddPerfilAcesso = true;
        $scope.loadingPerfilAcesso = true;

		$log.log("abriu");

        aplicativoService.listPerfisAcessoByAplicativoId( $scope.aplicativo.entity.id, {
            callback : function(result) {

                if(!!result.length) {
                    $scope.perfisAcesso = result;
                    $scope.showPermissoes();
                } else {
                    $scope.loadingPerfilAcesso = false;
                }
                $scope.$apply();
            },
            errorHandler : function(message, exception) {
                $mdToast.showSimple( message );
                $scope.$apply();
            }
        });
	}

	$scope.onCloseEventHandler = function () {

        $scope.showAddPerfilAcesso = false;
		$log.log("fechou");
	}

    $scope.setPerfilAcessoPermissao = function(permissaoId, perfilAcessoPermissaoId, perfilAcessoPermissaoStatus) {

        /*console.log(perfilAcessoPermissaoId);
        console.log(perfilAcessoPermissaoStatus);*/

        if(perfilAcessoPermissaoStatus) {
            aplicativoService.insertPerfilAcessoPermissao( $scope.perfilAcesso.id, permissaoId, {
                callback: function (result) {
                    $mdToast.showSimple("Permissão inserida com sucesso!");
                    $scope.perfilAcessoPermissao[result.permissao.id].permissaoId = result.id
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message);
                    $scope.$apply();
                }
            });
        } else {

            aplicativoService.removePerfilAcessoPermissao(perfilAcessoPermissaoId, {
                callback: function (result) {
                    $mdToast.showSimple("Permissão removida com sucesso!");
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message);
                    $scope.$apply();
                }
            });
        }
    }

    $scope.showFormPerfilAcesso = function(event, perfilAcesso) {
        $mdDialog.show({
            controller: DialogPerfilAcessoController,
            templateUrl: 'modules/autenticador/views/aplicativo/aplicativo-perfil-acesso-form.jsp',
            targetEvent: event,
            locals: {
                perfilAcesso: perfilAcesso
            }
        })
        .then(function( perfilAcesso ) {

            perfilAcesso.entity.aplicativo = $scope.aplicativo.entity;

            if(!perfilAcesso.entity.id) {

                aplicativoService.insertPerfilAcesso( perfilAcesso.entity, {
                    callback: function (result) {
                        $mdToast.showSimple("Perfil de acesso inserido com sucesso!");
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.$apply();
                    }
                });

            } else {
                aplicativoService.updatePerfilAcesso( perfilAcesso.entity, {
                    callback: function (result) {
                        $mdToast.showSimple("Perfil de acesso atualizado com sucesso!");
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.$apply();
                    }
                });
            }

        }, function() {

        });
    };

    function DialogPerfilAcessoController($scope, perfilAcesso, $mdDialog) {

        $scope.perfilAcesso = {
            form: null,
            entity: !!perfilAcesso ? perfilAcesso : new PerfilAcesso()
        };

        $scope.perfilAcesso.entity.editavel  = true;
        $scope.perfilAcesso.entity.removivel = true;


        $scope.hide = function() {
            $mdDialog.hide();
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

        $scope.sendForm = function() {
            $mdDialog.hide($scope.perfilAcesso);
        };
    }

    function DialogDisableAplicativoController($scope, aplicativo, $mdDialog) {

        $scope.aplicativo = aplicativo;

        $scope.hide = function() {
            $mdDialog.hide();
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

        $scope.sendForm = function() {
            $mdDialog.hide($scope.aplicativo);
        };
    }


});

}(window.angular));