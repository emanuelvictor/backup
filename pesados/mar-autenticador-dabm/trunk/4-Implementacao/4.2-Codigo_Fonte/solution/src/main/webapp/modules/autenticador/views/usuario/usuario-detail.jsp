<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout="column" layout-align="start center" class="content flex layout-fill">
    <div layout-align="center center" class="eits-content">
        <form name="model.form">
            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open class="ng-scope">
                        <div layout="row">
                            <div flex="70" layout="row" layout-align="start center" layout-wrap>
                                <div flex="25">
                                    <upload-image text="foto"
                                                  color="#e5e5e5"
                                                  min-width="120"
                                                  min-height="120"
                                                  source="foto"
                                                  max-size="1"
                                                  formats="['image/jpeg', 'image/jpg', 'image/png']"
                                                  on-success="onUploadFotoSuccess(input)"
                                                  on-error="onUploadFotoError(msg)"
                                                  on-delete="onUploadFotoDelete(event)"
                                                  is-circle="true">
                                        <md-icon class="md-icon-account-circle md-icon-5x"></md-icon>
                                    </upload-image>
                                </div>
                                <div layout-margin>
                                    <div layout="column">
                                        <div layout="row">
                                            <h1 class="md-headline name-detail">
                                                {{model.entity.nomeCompleto}}

                                            </h1>
                                            <h5 ng-style="model.entity.enabled ? {'color': '#00b0ff'} : {'color':'#cccccc'} ">
                                                {{ model.entity.enabled ? 'Ativo' : 'Inativo' }}
                                            </h5>
                                        </div>
                                        <div>
                                            <p style="margin-top: 0">{{model.entity.login}}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div flex offset="10" layout="row" layout-align="end start">

                                <md-button ng-click="$state.go( EDIT_STATE , {id: model.entity.id})"
                                           aria-label="Editar usuário" class="md-icon-button">
                                    <md-icon class="md-icon-edit md-icon-lg"></md-icon>
                                </md-button>


                                <md-menu md-position-mode="target-right target">
                                    <md-button aria-label="Opções do usuário" class="md-icon-button"
                                               ng-click="$mdOpenMenu()">
                                        <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                                    </md-button>
                                    <md-menu-content width="3">
                                        <md-menu-item>
                                            <md-button ng-click="replyProfile($event, model.entity)"
                                                       aria-label="Replicar perfis de acesso do usuário">
                                                Replicar perfil de acesso
                                            </md-button>
                                        </md-menu-item>
                                        <md-menu-item>
                                            <md-button ng-click="showResetPassword($event, model.entity)"
                                                       aria-label="Redefinir senha do usuário">
                                                Redefinir senha
                                            </md-button>
                                        </md-menu-item>
                                        <md-menu-item ng-if="model.entity.accountNonLocked">
                                            <md-button ng-click="bloquearUsuario($event, model.entity)"
                                                       aria-label="Bloquear usuário">
                                                Bloquear
                                            </md-button>
                                        </md-menu-item>
                                        <md-menu-item ng-if="!model.entity.accountNonLocked">
                                            <md-button ng-click="desbloquearUsuario(model.entity)"
                                                       aria-label="Desbloquear">
                                                Desbloquear
                                            </md-button>
                                        </md-menu-item>
                                        <md-menu-item ng-if="!model.entity.dataExclusao">
                                            <md-button ng-click="changeToRemove($event, model.entity)"
                                                       aria-label="Excluir usuário">
                                                Excluir
                                            </md-button>
                                        </md-menu-item>
                                        <md-menu-item ng-if="model.entity.dataExclusao">
                                            <md-button ng-click="restaurarUsuario(model.entity)"
                                                       aria-label="Restaurar usuário">
                                                Restaurar
                                            </md-button>
                                        </md-menu-item>
                                    </md-menu-content>
                                </md-menu>
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
                                    <p>E-mail</p>
                                    <p class="ng-binding">{{model.entity.email}}</p>
                                </li>

                                <li ng-if="model.entity.pessoa.cpf">
                                    <p>CPF</p>
                                    <p class="ng-binding">{{model.entity.pessoa.cpf}}</p>
                                </li>

                            </ul>
                            <ul class="details">

                                <md-divider style="clear:both"></md-divider>

                                <li ng-if="model.entity.dataExpiracaoSenha">
                                    <p>Expiração de senha</p>
                                    <p class="ng-binding">{{model.entity.dataExpiracaoSenha | date}}</p>
                                </li>

                                <li ng-if="model.entity.dataAlteracaoSenha">
                                    <p>Última alteração</p>
                                    <p class="ng-binding">{{model.entity.dataAlteracaoSenha| date}} </p>
                                </li>
                                <li ng-if="model.entity.dataUltimoAcesso">
                                    <p>Último acesso</p>
                                    <p class="ng-binding">{{model.entity.dataUltimoAcesso | date}}</p>
                                </li>

                                <li ng-if="model.entity.dataExclusao">
                                    <p>Data da exclusão</p>
                                    <p class="ng-binding">{{model.entity.dataExclusao | date}}</p>
                                </li>

                                <li ng-if="model.entity.dataBloqueio">
                                    <p>Bloqueio de conta</p>
                                    <p class="ng-binding">{{model.entity.dataBloqueio | date}}</p>
                                </li>

                                <li ng-if="model.entity.dataDesbloqueio">
                                    <p>Desbloqueio de conta</p>
                                    <p class="ng-binding">{{model.entity.dataDesbloqueio | date}}</p>
                                </li>
                            </ul>

                            <ul class="details" ng-if="model.entity.organizacaoMilitar.nome">
                                <md-divider style="clear:both"></md-divider>
                                <li>
                                    <p>Organização militar</p>

                                    <p class="ng-binding">{{model.entity.organizacaoMilitar.nome }}</p>
                                </li>
                                <li nf-if="model.entity.pessoa.postoGraduacao">
                                    <p>Posto de graduação</p>

                                    <p class="ng-binding">{{model.entity.pessoa.postoGraduacao}}</p>
                                </li>

                                <li nf-if="model.entity.pessoa.nomeGuerra">
                                    <p>Nome de guerra</p>

                                    <p class="ng-binding">{{model.entity.pessoa.nomeGuerra}}</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </content-opened>
            </eits-paper-sheet>
        </form>
        <eits-paper-sheet id="aplicativos" on-open="onOpenEventHandler()">
            <content-closed>
              <div layout="row" layout-align="start center" flex layout-wrap>
                <div layout="column">
                  <h4>Aplicativos</h4>
                </div>
                <span flex></span>
                <div ng-if="historic.length" flex layout-wrap layout="column" layout-align="center end">
                  <md-button aria-label="Adicionar perfil" ng-click="showHistory($event, historic)">
                    Visualizar histórico
                  </md-button>
                </div>
              </div>
                <md-divider></md-divider>
            </content-closed>
            <content-opened>
                <md-content>
                    <md-list>
                        <md-list-item class="md-2-line" ng-repeat="aplicativo in aplicativos">

                            <span ng-if="!aplicativo.icone" class="avatar-list" style="margin-right: 16px">{{ aplicativo.nome | limitTo : 1 : 0}}</span>
                            <img ng-if="aplicativo.icone"
                                 style="width: 40px; height: 40px;margin-right: 16px;margin-top: 12px"
                                 ng-src="{{aplicativo.icone}}">

                            <div class="md-list-item-text">
                                <h3>{{aplicativo.nome}}</h3>

                                <p>
                                  <span ng-repeat="perfilAcesso in aplicativo.perfisAcesso">
                                      {{$index != aplicativo.perfisAcesso.length -1 ? perfilAcesso.nome + ', ' : perfilAcesso.nome+ ''}}
                                  </span>
                                </p>
                            </div>

                            <div class="md-list-item-text">
                                <h3 ng-style="aplicativo.ativo ? {'color': '#00b0ff'} : {'color': '#cccccc'}">
                                    {{ aplicativo.ativo ? 'Ativo' : 'Inativo' }}
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
