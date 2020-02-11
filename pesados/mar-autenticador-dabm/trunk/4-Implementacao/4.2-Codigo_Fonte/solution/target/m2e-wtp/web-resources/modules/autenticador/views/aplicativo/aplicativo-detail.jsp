<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout="column" layout-align="start center" class="content" flex layout-wrap layout-fill>
<div layout-align="center center" class="eits-content">

<form name="aplicativoForm">
  <div class="eits-paper-sheet-item">
    <div class="paper-sheet-closed">
      <content-open>
        <div layout="row">
          <div flex layout="row">
            <div flex="70" layout="row" layout-align="start center" layout-wrap>
              <div flex="25">
                <upload-image text="ícone"
                              color="#e5e5e5"
                              min-width="120"
                              min-height="120"
                              source="icone"
                              max-size="1"
                              formats="['image/jpg', 'image/jpeg', 'image/png']"
                              on-delete="onUploadIconDelete(event)"
                              on-success="onUploadIconSuccess(input)"
                              on-error="onUploadIconError(msg)">
                  <md-icon class="md-icon-apps md-icon-5x"></md-icon>
                </upload-image>
              </div>
              <div layout-margin>
                <div layout="row">
                  <h2 class="md-headline name-detail">
                    {{aplicativo.entity.nome}}
                  </h2>
                  <h5 ng-style="aplicativo.entity.ativo ? {'color': '#00b0ff'} : {'color':'#cccccc'} ">
                    {{ aplicativo.entity.ativo ? 'Ativo' : 'Inativo'}}
                  </h5>
                </div>
              </div>
            </div>
            <div flex offset="10" layout="column" layout-align="start end">
              <div class="menu-demo-container" layout-align="center center" layout="column">
                <md-menu md-position-mode="target-right target">

                  <md-button aria-label="Abrir menu" class="md-icon-button"
                             ng-click="$mdOpenMenu()">
                    <md-icon class="md-icon-more-vert md-icon-lg"></md-icon>
                  </md-button>
                  <md-menu-content width="3">
                    <md-menu-item>
                      <md-button
                          ng-click="$state.go( EDIT_STATE,{id: aplicativo.entity.id})"
                          aria-label="Editar">
                        Editar
                      </md-button>
                    </md-menu-item>
                    <md-menu-item ng-if="!aplicativo.entity.ativo" class="md-raised">
                      <md-button ng-click="enableAplicativo( [aplicativo.entity.id] )" aria-label="Ativar">
                        Ativar
                      </md-button>
                    </md-menu-item>
                    <md-menu-item ng-if="aplicativo.entity.ativo" class="md-raised">
                      <md-button ng-click="disableAplicativo( $event, aplicativo.entity )"
                                 aria-label="Desativar">
                        Desativar
                      </md-button>
                    </md-menu-item>
                    <md-menu-item class="md-raised">
                      <md-button ng-click="changeToRemove( $event, aplicativo.entity )" aria-label="Excluir">
                        Excluir
                      </md-button>
                    </md-menu-item>
                  </md-menu-content>
                </md-menu>
              </div>
            </div>
          </div>
        </div>
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

            <p ng-if="aplicativo.entity.versaoEstavel.nome">{{ aplicativo.entity.versaoEstavel.nome }}<span ng-if="aplicativo.entity.versaoEstavel.versao">: {{ aplicativo.entity.versaoEstavel.versao }}</span></p>
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
            <p>Identificador</p>

            <p>{{ aplicativo.entity.identificador }}</p>
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

<eits-paper-sheet id="aplicativos" on-close="onCloseEventHandler()" on-open="openPerfisAcesso()">
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
      <div layout="row" layout-wrap ng-if="perfisAcesso.length > 0" style="overflow:hidden">
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

  </content-opened>
</eits-paper-sheet>

</div>
</md-content>
</html>