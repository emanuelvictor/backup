<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout="column" layout-align="start center" class="content flex layout-fill">

  <div layout-align="center center" class="eits-content">

    <md-button aria-label="Adicionar Usuário"
               class="md-fab md-fab-top-right save-button"
               ng-if="$state.current.name == ADD_STATE" ng-click="insertHandler(modelForm)">
      <md-icon class="md-icon-save md-icon-lg"></md-icon>
    </md-button>

    <md-button aria-label="Editar Usuário"
               class="md-fab md-fab-top-right save-button"
               ng-if="$state.current.name == EDIT_STATE" ng-click="insertHandler(modelForm)">
      <md-icon class="md-icon-save md-icon-lg"></md-icon>
    </md-button>

    <form name="modelForm" ng-submit="insertHandler(modelForm)" novalidate>

      <div class="eits-paper-sheet-item">
        <div class="paper-sheet-closed">
          <content-open class="ng-scope">
            <div layout="row" layout-align="center center">
              <div flex="20">
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

              <div flex="80">
                <div layout="row">
                  <md-input-container class="md-default-theme" flex="60" layout-margin>
                    <label for="nomeCompleto">Nome do usuário</label>
                    <input type="text" ng-model="model.entity.nomeCompleto"
                           md-maxlength="144" name="nomeCompleto"
                           id="nomeCompleto">

                    <div ng-if="modelForm.nomeCompleto.$touched || isFormSubmit" ng-messages="modelForm.nomeCompleto.$error">
                      <div ng-message="required">Campo nome é obrigatório</div>
                    </div>

                  </md-input-container>
                  <md-input-container class="md-default-theme" flex layout-margin>

                    <label for="login">NIP</label>
                    <input  ng-model="model.entity.login" required name="login" type="text" id="login"
                            maxlength="50">

                    <div ng-if="modelForm.login.$touched || isFormSubmit" ng-messages="modelForm.login.$error">
                      <div ng-message="required">Campo NIP é obrigatório</div>
                      <div ng-message="maxlength">Campo NIP com no máximo 50 caracteres</div>
                    </div>
                  </md-input-container>
                </div>
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
          <div layout="column" layout-margin>
            <md-input-container style="max-width: 30%">
              <label for="email">Email</label>
              <input ng-model="model.entity.email" type="email" name="email"
                     md-maxlength="100" id="email">

              <div ng-if="modelForm.email.$touched || isFormSubmit" ng-messages="modelForm.email.$error">
                <div ng-message="email">E-mail inválido</div>
              </div>
            </md-input-container>
            <md-input-container ng-if="$state.current.name == EDIT_STATE && model.entity.pessoa.cpf">
              <label for="cpf">CPF</label>
              <input ng-model="model.entity.pessoa.cpf" disabled id="cpf">
            </md-input-container>

            <md-input-container ng-if="$state.current.name == EDIT_STATE">
              <label for="nomeGuerra">Nome de guerra</label>
              <input ng-model="model.entity.pessoa.nomeGuerra" disabled id="nomeGuerra">
            </md-input-container>

            <div layout="row" layout-sm="column">
              <label layout-margin>Expiração de senha</label>
            </div>
            <div layout="row" layout-sm="column">
              <md-datepicker ng-model="model.entity.dataExpiracaoSenha" placeholder="Expiração de senha"></md-datepicker>
            </div>

            <div layout="row" layout-sm="column">
              <label layout-margin>Período de bloqueio de conta</label>
            </div>
            <div layout="row" layout-sm="column">

              <md-datepicker ng-model="model.entity.dataBloqueio" placeholder="Início"></md-datepicker>
              <span style="margin:16px 0 0 22px">até</span>
              <md-datepicker ng-model="model.entity.dataDesbloqueio" md-min-date="model.entity.dataBloqueio" placeholder="Fim"></md-datepicker>

            </div>
          </div>

        </content-opened>
      </eits-paper-sheet>
    </form>
    <eits-paper-sheet ng-if="$state.current.name == EDIT_STATE" id="aplicativos" on-open="onOpenEventHandler()">
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
              <img ng-if="aplicativo.icone" style="width: 40px; height: 40px;margin-right: 16px;margin-top: 12px" ng-src="{{aplicativo.icone}}">

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
              <div flex layout="row" layout-align="end start">
                <div class="menu-demo-container" layout-align="center center" layout="column">
                  <md-menu md-position-mode="target-right target">
                    <md-button aria-label="Opções do aplicativo" class="md-icon-button" ng-click="$mdOpenMenu()">
                      <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                    </md-button>
                    <md-menu-content width="3">
                      <md-menu-item>
                        <md-button ng-click="showAddApp($event, model.entity, aplicativo)" aria-label="Editar aplicativo">
                          Editar
                        </md-button>
                      </md-menu-item>
                      <md-menu-item>
                        <md-button ng-click="removePerfilUsuarioAplicativo(aplicativo, perfisUsuarioAplicativo)" aria-label="Remover aplicativo">
                          Remover
                        </md-button>
                      </md-menu-item>
                    </md-menu-content>
                  </md-menu>
                </div>
              </div>

            </md-list-item>
          </md-list>
        </md-content>

        <div style="cursor:pointer;" ng-click="showAddApp($event, model.entity)">

          <md-button aria-label="Adicionar aplicativo" class="md-fab md-hue-2" style="background-color: #ececec;box-shadow: none; margin-left: 0;">
            <md-icon class="md-icon-add md-icon-lg" style="color: #999;"></md-icon>
          </md-button>

          <span>Adicionar aplicativo</span>
        </div>
      </content-opened>
    </eits-paper-sheet>
  </div>
</md-content>
</html>