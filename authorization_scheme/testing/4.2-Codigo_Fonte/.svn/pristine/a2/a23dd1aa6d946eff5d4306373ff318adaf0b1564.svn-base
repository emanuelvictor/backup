<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns:ng="http://angularjs.org" lang="pt">


<md-content layout="row" layout-padding layout-align="center start" class="content">
 

    <div layout-align="center center" style="max-width:800px; min-width: 800px">
        <form name="model.form">
            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open class="ng-scope">

                        <div layout="row" layout-align="center center">
                            <div flex="20">
                                <div class="icon">
                                    <span class="md-caption">{{ $state.current.name == 'ADD_STATE' ? 'Adicionar' : 'Alterar' }}<br>foto</span>
                                </div>
                            </div>

                            <div flex="60">
                                <div layout="column">
                                    <div layout="row">
                                        <h1 style="margin: 2px 10px 0 0">{{model.entity.nomeCompleto}}</h1>
                                        <h5 ng-style="model.entity.enabled ? {'color': '#00b0ff'} : {'color':'#cccccc'} ">{{ model.entity.enabled ? 'ativo' : 'inativo' }}</h5>
                                    </div>
                                    <div>
                                        <p style="margin-top: 0">{{model.entity.email}}</p>
                                    </div>
                                </div>
                            </div>
                            
                            <div flex layout="row" layout-align="end start">
                                <md-button class="md-icon-button" ng-click="$state.go( EDIT_STATE , {id: model.entity.id})" aria-label="Detalhes">
                                  <md-icon class="md-icon-edit md-icon-lg"></md-icon>
                                </md-button>
                                <eits-dropdown icon-menu="md-icon-more-horiz md-icon-lg" class="ng-isolate-scope">
                                  <md-list-item ng-click="showResetPassword($event)">
                                    <p>Redefinir senha</p>
                                  </md-list-item>
                                  <md-list-item ng-if="model.entity.accountNonLocked" ng-click="bloquearUsuario($event, model.entity)">
                                    <p>Bloquear</p>
                                  </md-list-item>
                                  <md-list-item ng-if="!model.entity.accountNonLocked" ng-click="desbloquearUsuario(model.entity)">
                                    <p>Desbloquear</p>
                                  </md-list-item>
                                  <md-list-item ng-if="!model.entity.dataExclusao" ng-click="excluirUsuario($event)">
                                    <p>Excluir</p>
                                  </md-list-item>
                                  <md-list-item ng-if="model.entity.dataExclusao" ng-click="restaurarUsuario(model.entity)">
                                    <p>Restaurar</p>
                                  </md-list-item>
                                </eits-dropdown>
                            </div>
                        </div>
                    </content-open>
                </div>
            </div>
            <eits-paper-sheet id="informacoes">

                <content-closed>
                    <h4>Informações da conta</h4>
                    <md-divider></md-divider>
                </content-closed>

                <content-opened>
                    <div layout="row">
                        <div flex>
                            <ul class="details">
                                <li>
                                    <p>NIP</p>

                                    <p class="ng-binding">{{model.entity.login}}</p>
                                </li>

                                <li ng-if="model.entity.dataExpiracaoSenha">
                                    <p>Expiração de senha</p>

                                    <p class="ng-binding">{{dataExpiracaoSenha}}</p>
                                </li>
                            </ul>
                            <md-divider ng-if="model.entity.dataBloqueio" style="clear:both"></md-divider>
                            <ul ng-if="model.entity.dataBloqueio" class="details">
                                <li>
                                    <p>Bloqueio em </p>

                                    <p class="ng-binding">{{dataBloqueio}}</p>
                                </li>
                                <li ng-if="model.entity.dataDesbloqueio">
                                    <p>Desbloqueio em </p>

                                    <p class="ng-binding">{{dataDesbloqueio}}</p>
                                </li>
                                <li>
                                    <p>Organização militar</p>

                                    <p class="ng-binding">{{model.entity.organizacaoMilitar}}</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </content-opened>

            </eits-paper-sheet>
        </form>
        <eits-paper-sheet id="aplicativos" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
            <content-closed>
                <div layout="row" layout-align="center center">
                    <h4>Aplicativos</h4>
                    <span flex></span>

                </div>
                <md-divider></md-divider>
            </content-closed>
            <content-opened>
                <md-content>
                    <md-list>
                        <md-list-item class="md-2-line" ng-repeat="aplicativo in aplicativos">
                            
                            <img ng-src="{{todos[0].face}}?25" class="md-avatar" alt="{{todos[0].who}}"/>

                            <div class="md-list-item-text">
                                <h3>{{aplicativo.nome}}</h3>
                                <p>
                                    <span style="margin-top: -5px;"
                                       ng-repeat="perfilAcesso in aplicativo.perfisAcesso">
                                        {{perfilAcesso.nome}}
                                    </span>
                                </p>
                            </div>

                            <div class="md-list-item-text">
                                <h3 ng-style="aplicativo.ativo ? {'color': '#00b0ff'} : {'color': '#cccccc'}">
                                    {{ aplicativo.ativo ? 'ativo' : 'inativo' }}
                                </h3>
                            </div>

                         
                        </md-list-item>
                    </md-list>
                </md-content>
            </content-opened>
            
        </eits-paper-sheet>
    </div>
</md-content>
</html>
