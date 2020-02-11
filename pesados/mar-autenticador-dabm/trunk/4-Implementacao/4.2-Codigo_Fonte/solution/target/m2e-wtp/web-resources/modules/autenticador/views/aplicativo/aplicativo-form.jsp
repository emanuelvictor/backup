<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">


<md-content layout="column" layout-align="start center" class="content flex layout-wrap layout-fill">

    <div layout-align="center center" class="eits-content">

        <md-button aria-label="Editar Aplicativo" class="md-fab md-fab-top-right save-button" ng-click="updateHandler(aplicativoForm)"
                   ng-if="$state.current.name == EDIT_STATE">
            <md-icon class="md-icon-save md-icon-lg"></md-icon>
        </md-button>

        <md-button aria-label="Adicionar Aplicativo" class="md-fab md-fab-top-right save-button" ng-click="insertHandler(aplicativoForm)"
                   ng-if="$state.current.name == ADD_STATE">
            <md-icon class="md-icon-save md-icon-lg"></md-icon>
        </md-button>

        <form name="aplicativoForm" novalidate>

            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open class="ng-scope">
                        <div layout="row" layout-align="center center">
                            <div flex="20">

                                <upload-image text="ícone"
                                              color="#e5e5e5"
                                              min-width="120"
                                              min-height="120"
                                              source="icone"
                                              max-size="1"
                                              formats="['image/jpeg', 'image/jpg', 'image/png']"
                                              on-success="onUploadIconSuccess(input)"
                                              on-delete="onUploadFotoDelete(event)"
                                              on-error="onUploadIconError(msg)">
                                    <md-icon class="md-icon-apps md-icon-5x"></md-icon>
                                </upload-image>

                            </div>
                            <div flex="80">
                                <md-input-container class="md-default-theme">
                                    <label>Nome do aplicativo</label>
                                    <input type="text" name="nome" ng-model="aplicativo.entity.nome" md-maxlength="144"
                                           required>

                                    <div ng-if="aplicativoForm.nome.$touched || isFormSubmit" ng-messages="aplicativoForm.nome.$error">
                                        <div ng-message="required">Campo nome é obrigatório</div>
                                    </div>
                                </md-input-container>
                            </div>

                        </div>
                    </content-open>
                </div>
            </div>

            <eits-paper-sheet id="informacoes" on-close="onCloseEventHandler()" on-open="loadAplicativoInfo()">
                <div>
                    <content-closed>
                        <h4>Informações</h4>
                        <md-divider></md-divider>
                    </content-closed>

                    <content-opened>
                        <md-input-container>
                            <md-switch ng-model="aplicativo.entity.perfisDinamicos" aria-label="Perfis dinamicos">Perfis
                                Dinâmicos
                            </md-switch>
                        </md-input-container>

                        <md-input-container>
                            <label>Endereço</label>
                            <input type="url" name="endereco" ng-model="aplicativo.entity.endereco" md-maxlength="255" required>

                            <div ng-if="aplicativoForm.endereco.$touched || isFormSubmit" ng-messages="aplicativoForm.endereco.$error">
                                <div ng-message="required">Campo endereço é obrigatório</div>
                                <div ng-message="url">Endereço inválido</div>
                            </div>

                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Identificador</label>
                            <input type="text" name="codigo" ng-model="aplicativo.entity.identificador">
                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Chave de acesso</label>
                            <input type="text" name="refreshToken" ng-model="aplicativo.entity.refreshToken">
                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Versão</label>
                            <input type="text" name="versao" ng-model="aplicativo.entity.versao" md-maxlength="144">
                        </md-input-container>

                        <md-input-container>
                            <label>Descrição</label>
                            <input type="text" name="descricao" ng-model="aplicativo.entity.descricao" md-maxlength="144">
                        </md-input-container>

                        <md-input-container style="max-width: 50%">
                            <md-select ng-model="aplicativo.entity.versaoEstavel.id" placeholder="Versão estável">
                                <md-option ng-value="aplicativoAtivo.id" ng-repeat="aplicativoAtivo in listAplicativos">{{aplicativoAtivo.nome}}</md-option>
                            </md-select>
                        </md-input-container>

                    </content-opened>
                </div>
            </eits-paper-sheet>
        </form>

        <eits-paper-sheet id="aplicativos" on-close="onCloseEventHandler()" on-open="openPerfisAcesso()"
                          ng-if="$state.current.name == EDIT_STATE">
            <content-closed>
                <div layout="row" layout-align="start center" flex layout-wrap>
                    <div layout="column">
                        <h4>Perfis de Acesso</h4>
                    </div>
                    <span flex></span>
                    <div flex layout-wrap layout="column" layout-align="center end" ng-if="showAddPerfilAcesso">
                        <md-button aria-label="Adicionar perfil" ng-click="showFormPerfilAcesso($event)"
                                   ng-if="aplicativo.entity.perfisDinamicos">
                            <md-icon class="md-icon-add md-icon-lg"></md-icon>
                            Adicionar perfil
                        </md-button>
                    </div>
                </div>
                <md-progress-linear ng-if="loadingPerfilAcesso" md-mode="indeterminate"></md-progress-linear>
                <md-divider></md-divider>
            </content-closed>

            <content-opened>
                <md-content>
                    <div layout="row" layout-wrap ng-if="perfisAcesso.length > 0">
                        <div flex="25" style="margin-right: 15px">

                            <md-list-item ng-click="showPerfilAcesso(perfilAcesso)"
                                          ng-repeat="perfilAcesso in perfisAcesso">
                                <p>{{ perfilAcesso.nome }}</p>
                            </md-list-item>

                        </div>
                        <div flex="70">


                            <div layout="row" layout-="start end" style="height: 50px">
                                <div flex>
                                    <h2>{{ perfilAcesso.nome }}</h2>
                                </div>
                                <div layout="row" layout-align="end start"
                                     ng-show="perfilAcesso.editavel || perfilAcesso.removivel">

                                    <md-menu md-position-mode="target-right target">
                                        <md-button aria-label="Abrir menu" class="md-icon-button"
                                                   ng-click="$mdOpenMenu()">
                                            <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                                        </md-button>
                                        <md-menu-content width="3">
                                            <md-menu-item class="md-raised" ng-show="perfilAcesso.editavel">
                                                <md-button aria-label="Editar perfil de acesso"
                                                           ng-click="showFormPerfilAcesso($event, perfilAcesso)">Editar
                                                </md-button>
                                            </md-menu-item>
                                            <md-menu-item class="md-raised" ng-show="perfilAcesso.removivel">
                                                <md-button aria-label="Remover perfil de acesso"
                                                           ng-click="removePerfilAcesso($event, perfilAcesso)">Excluir
                                                </md-button>
                                            </md-menu-item>

                                        </md-menu-content>
                                    </md-menu>
                                </div>
                            </div>
                            <p>{{ perfilAcesso.descricao }}</p>
                            <md-divider></md-divider>

                            <script type="text/ng-template" id="permissoes_tree.html">
                                <a ng-if="permissao.permissoesInferiores.length > 0" style="float: left" ng-click="toggle(this)">
                                    <md-icon class="md-icon-keyboard-arrow-down md-icon-lg"
                                             ng-class="{'md-icon-keyboard-arrow-right': !collapsed, 'md-icon-keyboard-arrow-down': collapsed}"></md-icon>
                                </a>
                                <div ui-tree-handle style="margin-left: 30px;">
                                    <md-checkbox ng-model="perfilAcessoPermissao[permissao.id].status"
                                                 ng-change="setPerfilAcessoPermissao(permissao, perfilAcessoPermissao[permissao.id].permissaoId, perfilAcessoPermissao[permissao.id].status)"
                                                 aria-label="permissao.identificador">{{permissao.identificador}}
                                    </md-checkbox>
                                </div>
                                <ul ui-tree-nodes="" ng-model="permissao.permissoesInferiores" ng-show="collapsed">
                                    <li ng-repeat="permissao in permissao.permissoesInferiores" ui-tree-node
                                        ng-include="'permissoes_tree.html'">
                                    </li>
                                </ul>
                            </script>

                            <div ui-tree data-drag-enabled="false" ng-if="aplicativo.permissao.length > 0">
                                <ul ui-tree-nodes="" ng-model="aplicativo.permissao" id="services-tree-root">
                                    <li ng-repeat="permissao in aplicativo.permissao" ui-tree-node
                                        ng-include="'permissoes_tree.html'"></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div layout="row" layout-wrap layout-margin ng-if="!perfisAcesso.length">
                        <p>Nenhum perfil de acesso cadastrado para este aplicativo.</p>
                    </div>

                </md-content>


                <md-button aria-label="Adicionar Aplicativo" class="md-fab" ng-click="insertHandler()"
                           ng-if="$state.current.name == ADD_STATE">
                    <md-icon class="md-icon-save md-icon-lg"></md-icon>
                </md-button>

            </content-opened>
        </eits-paper-sheet>

    </div>
</md-content>
</html>