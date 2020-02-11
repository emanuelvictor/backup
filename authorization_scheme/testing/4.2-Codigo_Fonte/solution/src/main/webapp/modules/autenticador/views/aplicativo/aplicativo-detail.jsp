<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout="row" layout-padding layout-align="center start" class="content">

    <div layout-align="center center" style="max-width:800px; min-width: 800px">
        <form name="aplicativo.form">
            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open>
                        <div layout="row">

                            <div flex layout="row">
                                <div flex="70" layout="row" layout-align="start center" layout-wrap>
                                    <div flex="25">
                                        <div class="icon">
                                            <span class="md-caption">Adicionar<br>ícone</span>
                                        </div>
                                    </div>
                                    <div layout-margin>
                                        <h2>{{aplicativo.entity.nome}}</h2>
                                    </div>
                                    <div>
                                        <h5 style="color: #00b0ff ">{{ aplicativo.entity.ativo ? 'Ativo' : 'Inativo' }}</h5>
                                    </div>
                                </div>
                                <div flex offset="10" layout="row" layout-align="end start">
                                    <eits-dropdown icon-menu="md-icon-more-horiz md-icon-lg">
                                        <md-list-item ng-click="$state.go( EDIT_STATE, {id: aplicativo.entity.id} )">
                                            <p>Editar</p>
                                        </md-list-item>
                                        <md-list-item ng-if="!aplicativo.entity.ativo" class="md-raised" aria-label="Ativar" ng-click="enableAplicativo()">
                                            <p>Ativar</p>
                                        </md-list-item>
                                        <md-list-item ng-if="aplicativo.entity.ativo" class="md-raised" aria-label="Desativar" ng-click="disableAplicativo( $event, aplicativo )">
                                            <p>Desativar</p>
                                        </md-list-item>
                                    </eits-dropdown>

                                    <!--<md-button aria-label="Editar" ng-click="$state.go( EDIT_STATE, {id: aplicativo.entity.id} )">
                                        <md-icon class="md-icon-edit md-icon-lg"></md-icon>
                                    </md-button>

                                    <md-button ng-if="!aplicativo.entity.ativo" class="md-raised" aria-label="Ativar" ng-click="enableAplicativo()">
                                        Ativar
                                    </md-button>

                                    <md-button ng-if="aplicativo.entity.ativo" class="md-raised" aria-label="Desativar" ng-click="disableAplicativo( $event, aplicativo )">
                                        Desativar
                                    </md-button>-->
                                </div>
                            </div>
                        </div>

                        <!--<div layout="row" layout-margin layout-align="center center">
                            <div flex="20">
                                <div class="icon">
                                    <span class="md-caption">Alterar<br>ícone</span>
                                </div>
                            </div>
                            <div flex="55" layout-margin>
                                <h2>{{aplicativo.entity.nome}}</h2>
                            </div>
                            <div flex="20">
                                <h5 style="color: #00b0ff ">{{ aplicativo.entity.ativo ? 'Ativo' : 'Inativo' }}</h5>
                            </div>
                            <div flex offset="20" layout="row" layout-align="end start">
                                <md-button aria-label="Mais" ng-click="changeToEdit( aplicativo.entity.id )">
                                    <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                                </md-button>
                            </div>
                        </div>-->

                    </content-open>
                </div>
            </div>

            <eits-paper-sheet id="informacoes" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
                <div>
                    <content-closed>
                        <h4>Informações</h4>
                        <md-divider></md-divider>
                    </content-closed>

                    <content-opened>

                        <ul class="details">

                            <li>
                                <p>Descrição</p>

                                <p>{{ aplicativo.entity.descricao }}</p>
                            </li>

                            <li>
                                <p>URL</p>

                                <p>{{ aplicativo.entity.endereco }}</p>
                            </li>

                            <li>
                                <p>Versão</p>

                                <p>{{ aplicativo.entity.versao }}</p>
                            </li>

                            <li>
                                <p>Versão estável</p>

                                <p>{{ aplicativo.entity.versaoEstavel }}</p>
                            </li>

                            <li>
                                <p>Status</p>

                                <p>{{ aplicativo.entity.ativo ? 'Ativo' : 'Inativo' }}</p>
                            </li>


                            <li>
                                <p>Tipo de perfil</p>

                                <p>{{ aplicativo.entity.perfisDinamicos ? 'Dinâmico' : 'Estático' }}</p>
                            </li>

                            <li>
                                <p>Código</p>

                                <p>{{ aplicativo.entity.codigo }}</p>
                            </li>

                            <li>
                                <p>Chave de acesso</p>

                                <p>{{ aplicativo.entity.refreshToken }}</p>
                            </li>

                        </ul>

                    </content-opened>
                </div>
            </eits-paper-sheet>
        </form>

        <eits-paper-sheet id="aplicativos" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
            <content-closed>
                <div layout="row" layout-align="center center">
                    <div>
                        <h4>Perfis de Acesso</h4>
                    </div>
                    <span flex></span>
                    <div ng-if="showAddPerfilAcesso">
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

                            <!--<script type="text/ng-template" id="nodes_renderer.html">

                                <a ng-if="node.items.length > 0" style="float: left" ng-click="toggle(this)">
                                    <md-icon class="md-icon-keyboard-arrow-down md-icon-lg"
                                             ng-class="{'md-icon-keyboard-arrow-right': collapsed, 'md-icon-keyboard-arrow-down': !collapsed}"></md-icon>
                                </a>

                                <div ui-tree-handle>
                                    <p ng-class="{'selected': node.id == perfilAcesso.id, 'children': node.items.length == 0 }" ng-click="showPerfilAcesso(node)">{{node.nome}}</p>
                                </div>
                                <ul ui-tree-nodes="" data-drop-enabled="false" ng-model="node.items" ng-show="!collapsed">
                                    <li ng-repeat="node in node.items" ui-tree-node ng-include="'nodes_renderer.html'">
                                    </li>
                                </ul>
                            </script>

                            <div ui-tree data-drag-enabled="false">
                                <ul ui-tree-nodes="" ng-model="perfis" id="tree-root">
                                    <li ng-repeat="node in perfis" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                                </ul>
                            </div>-->

                            <md-list-item ng-click="showPerfilAcesso(perfilAcesso)" ng-repeat="perfilAcesso in perfisAcesso">
                                <p>{{ perfilAcesso.nome }}</p>
                            </md-list-item>

                        </div>
                        <div flex="70">


                            <div layout="row">
                                <div flex>
                                    <h2>{{ perfilAcesso.nome }}</h2>
                                </div>
                                <div layout="row" layout-align="end start">
                                    <!--<eits-dropdown icon-menu="md-icon-more-horiz">
                                        <ul>
                                            <li>Teste</li>
                                        </ul>
                                    </eits-dropdown>-->

                                    <md-button ng-if="perfilAcesso.removivel" aria-label="Remover perfil de acesso" ng-click="removePerfilAcesso($event, perfilAcesso)">
                                        <md-icon class="md-icon-delete md-icon-lg"></md-icon>
                                    </md-button>

                                    <md-button ng-if="perfilAcesso.editavel" aria-label="Editar perfil de acesso" ng-click="showFormPerfilAcesso($event, perfilAcesso)">
                                        <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                                    </md-button>
                                </div>
                            </div>
                            <p>{{ perfilAcesso.descricao }}</p>
                            <md-divider></md-divider>

                            <script type="text/ng-template" id="permissoes_tree.html">
                                <a ng-if="permissao.permissao.length > 0" style="float: left" ng-click="toggle(this)">
                                    <md-icon class="md-icon-keyboard-arrow-down md-icon-lg"
                                             ng-class="{'md-icon-keyboard-arrow-right': collapsed, 'md-icon-keyboard-arrow-down': !collapsed}"></md-icon>
                                </a>
                                <div ui-tree-handle style="margin-left: 30px;">
                                    <md-checkbox ng-model="perfilAcessoPermissao[permissao.id].status"
                                                 ng-change="setPerfilAcessoPermissao(permissao.id, perfilAcessoPermissao[permissao.id].permissaoId, perfilAcessoPermissao[permissao.id].status)"
                                                 aria-label="permissao.identificador">{{permissao.identificador}}</md-checkbox>
                                </div>
                                <ul ui-tree-nodes="" ng-model="permissao.permissao" ng-show="!collapsed">
                                    <li ng-repeat="permissao in permissao.permissao" ui-tree-node ng-include="'permissoes_tree.html'">
                                    </li>
                                </ul>
                            </script>

                            <div ui-tree data-drag-enabled="false" ng-if="aplicativo.permissao.length > 0">
                                <ul ui-tree-nodes="" ng-model="aplicativo.permissao" id="services-tree-root">
                                    <li ng-repeat="permissao in aplicativo.permissao" ui-tree-node ng-include="'permissoes_tree.html'"></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div layout="row" layout-wrap layout-margin ng-if="!perfisAcesso.length">
                        <p>Nenhum perfil de acesso cadastrado para este aplicativo.</p>
                    </div>

                </md-content>

            </content-opened>
        </eits-paper-sheet>

    </div>
</md-content>
</html>