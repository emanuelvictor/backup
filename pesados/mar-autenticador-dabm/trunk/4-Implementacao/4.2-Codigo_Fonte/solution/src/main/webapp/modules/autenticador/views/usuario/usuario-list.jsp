<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">


  <md-toolbar style="height: 100px;">

    <!-- TOPSHEET -->
    <md-data-table-toolbar class="alternate" style="height: 100px; background-color: #e3f2fd;position: absolute;top: 0;left: 0;width: 100%"
                           ng-style="selected.length ? {'height':'100px'} : {'height':'0'} ">

      <div ng-show="selected.length" layout="row" flex layout-fill>
        <div flex layout="row" layout-align="start center">
          <div>
            <md-button class="md-icon-button" aria-label="Fechar" ng-click="listUsuariosByFilters()">
              <md-icon class="md-icon-close md-icon-lg" style="color: #666"></md-icon>
            </md-button>
          </div>
          <div>
            <h2 class="md-toolbar-tools md-subhead" style="color: rgb(2,119,189);">
              <span>{{selected.length}} {{selected.length > 1 ? 'itens selecionados' : 'item selecionado'}}</span>
            </h2>
          </div>
        </div>
        <span flex></span>

        <div layout-align="end center" layout="row">
          <md-menu md-position-mode="target-right target">
            <md-button aria-label="Opções dos usuários" class="md-icon-button" ng-click="$mdOpenMenu();">
              <md-icon class="md-icon-more-horiz md-icon-lg" style="color : #666"></md-icon>
            </md-button>
            <md-menu-content width="3">
              <md-menu-item>
                <md-button ng-click="showResetPassword($event, selected)"
                           aria-label="Redefinir senha dos usuários">
                  Redefinir senha
                </md-button>
              </md-menu-item>
              <md-menu-item ng-if="handlerUsuarios(selected).bloquear.length>0">
                <md-button ng-click="bloquearUsuarios($event, handlerUsuarios(selected).bloquear)"
                           aria-label="Bloquear usuários">
                  Bloquear
                </md-button>
              </md-menu-item>
              <md-menu-item ng-if="handlerUsuarios(selected).desbloquear.length>0">
                <md-button ng-click="desbloquearUsuarios(handlerUsuarios(selected).desbloquear)"
                           aria-label="Desbloquear usuários">
                  Desbloquear
                </md-button>
              </md-menu-item>
              <md-menu-item ng-if="handlerUsuarios(selected).excluir.length>0">
                <md-button ng-click="excluirUsuarios($event, handlerUsuarios(selected).excluir)"
                           aria-label="Excluir usuários">
                  Excluir
                </md-button>
              </md-menu-item>
              <md-menu-item ng-if="handlerUsuarios(selected).restaurar.length>0">
                <md-button ng-click="restaurarUsuarios(handlerUsuarios(selected).restaurar)"
                           aria-label="Restaurar usuários">
                  Restaurar
                </md-button>
              </md-menu-item>
            </md-menu-content>
          </md-menu>
        </div>
      </div>

    </md-data-table-toolbar>
    <!-- TOPSHEET-->

    <div ng-show="!selected.length && !model.hideTitle" class="eits-padding">

      <md-button aria-label="Novo Usuário" class="md-fab" ng-click="$state.go( ADD_STATE )"
                 style="z-index: 100; left:38px;bottom: -33px;position: absolute;">
        <md-icon class="md-icon-add md-icon-lg"></md-icon>
        <div class="md-ripple-container"></div>
      </md-button>

      <div flex layout="row" layout-fill layout-padding style="margin-top: 8px;padding-right: 0">
        <div layout="column" ng-show="!search" flex layout-align="center start">
          <h2 class="eits-header-title md-toolbar-tools md-toolbar-tools-bottom">
            <span class="md-flex" ng-if="model.filters.key=='ativo'">Todos usuários ativos</span>
            <span class="md-flex" ng-if="model.filters.key=='bloqueado'">Todos usuários bloqueados</span>
            <span class="md-flex" ng-if="model.filters.key=='excluido'">Todos usuários excluidos</span>
          </h2>
        </div>
        <span flex></span>
        <div layout="row" layout-padding layout-fill layout-align="end center" style="padding-right: 0">

          <md-button style="margin-left: 63px" ng-show="search" aria-label="Pesquisar usuários" class="md-icon-button" ng-click="addTag()">
            <md-icon class="md-icon-search md-icon-lg"></md-icon>
          </md-button>

          <div layout-align="start center" ng-show="!selected.length && search" layout="row" layout-fill flex>
            <md-chips secondary-placeholder="Pesquisar usuários" placeholder="Pesquisar usuários" ng-focus="search" style="width: 100%;" ng-model="tags" id="search"></md-chips>
          </div>

          <md-button ng-show="!search" aria-label="Pesquisar usuários" class="md-icon-button" ng-click="toggleSearch()">
            <md-icon class="md-icon-search md-icon-lg"></md-icon>
          </md-button>
          <md-button ng-show="search" aria-label="Fechar pesquisa" class="md-icon-button" ng-click="toggleSearch()">
            <md-icon class="md-icon-close md-icon-lg"></md-icon>
          </md-button>

        </div>
      </div>
    </div>
  </md-toolbar>

  <md-content layout="row" flex layout-wrap>

    <md-content layout="column" layout-fill flex ng-if="model.page.content.length">
      <md-data-table-container>
        <table md-data-table md-row-select="selected" md-progress="deferred">
          <thead md-order="model.page.pageable.sort.orders.property" md-trigger="onOrderChange">
          <tr>
            <th></th>
            <th order-by="nomeCompleto" name="Nome">Nome</th>
            <th order-by="login" name="NIP">NIP</th>
            <th order-by="dataUltimoAcesso" name="Data do ultimo acesso">Data do último acesso</th>
            <th>
              <div layout-align="end center" layout="row" style="font-size: 1.5em">
                <md-button class="md-icon-button" aria-label="Filtros" ng-click="toggleRightMenu()">
                  <md-icon class="md-icon-filter-list md-icon-lg" style="color: #666;"></md-icon>
                </md-button>
              </div>
            </th>
          </tr>

          </thead>

          <tbody>
          <tr ng-repeat="usuario in model.page.content" style="cursor:pointer">
            <td ng-click="$state.go( DETAIL_STATE,{id: usuario.id})">{{ usuario.nomeCompleto }}</td>
            <td ng-click="$state.go( DETAIL_STATE,{id: usuario.id})">{{usuario.login}}</td>
            <td ng-click="$state.go( DETAIL_STATE,{id: usuario.id})">{{ usuario.dataUltimoAcesso | date }}</td>
            <td></td>
          </tr>
          </tbody>
        </table>
      </md-data-table-container>
      <span flex></span>
      <footer class="eits-paginator" layout="column" layout-align="end center" layout-wrap>
        <div layout="row" layout-align="center center">
          <!--<md-input-container style="margin-right: 20px; padding: 0">-->
          <!--<label>Registros por página</label>-->
          <!--<input type="number" ng-model="model.page.size" min="1"-->
          <!--ng-change="onPaginationChange(model.page.pageable.page, model.page.size);">-->
          <!--</md-input-container>-->
          <label>Página: {{model.page.pageable.page + 1}}</label>
          <button class="md-icon-button md-button md-default-theme"
                  ng-disabled="!model.page.pageable.page"
                  ng-click="onPaginationChange(model.page.pageable.page - 1, model.page.size)" tabindex="0"
                  aria-disabled="true" disabled="disabled">
            <i class="md-icon md-icon-keyboard-arrow-left md-icon-lg ng-scope"></i>
          </button>
          <button class="md-icon-button md-button md-default-theme"
                  ng-disabled="model.page.totalPages <= model.page.pageable.page + 1"
                  ng-click="onPaginationChange(model.page.pageable.page + 1, model.page.size)" tabindex="0"
                  aria-disabled="false">
            <i class="md-icon md-icon-keyboard-arrow-right md-icon-lg ng-scope"></i>
          </button>
        </div>
      </footer>
    </md-content>

    <div layout="column" class="list-empty-grid flex layout" layout-align="center center" ng-if="!model.page.content.length">
      Nenhum registro de Usuário
    </div>

    <!-- FILTRO -->
    <div layout="row" layout-fill ng-show="isFilterOpen" style="width: 304px; float: right;z-index:10">
      <md-sidenav class="md-sidenav-right md-whiteframe-z2" md-is-locked-open="isFilterOpen == true" md-component-id="right">

        <md-toolbar layout="row">
          <h1 class="md-toolbar-tools">Filtro</h1>

          <md-button ng-if="model.page.content.length" ng-click="toggleRightMenu()" class="md-icon-button"
                     aria-label="Abrir filtros">
            <md-icon class="md-icon-close md-icon-lg"></md-icon>
          </md-button>
        </md-toolbar>
        <md-content layout-padding>
          <md-input-container>
            <label>Selecione o filtro</label>
            <md-select name="filter" ng-model="model.filters.key" aria-label="Filtros">
              <md-option value="ativo"
                         ng-click="model.page.pageable.page = 0; listUsuariosByFilters(model.filters, model.page.pageable)">
                Ativos
              </md-option>
              <md-option value="bloqueado"
                         ng-click="model.page.pageable.page = 0;listUsuariosByFiltersAndBloqueados(model.filters, model.page.pageable)">
                Bloqueados
              </md-option>
              <md-option value="excluido"
                         ng-click="model.page.pageable.page = 0;listUsuariosByFiltersAndExcluidos(model.filters, model.page.pageable)">
                Excluidos
              </md-option>
            </md-select>
          </md-input-container>
        </md-content>
      </md-sidenav>
    </div>
    <!-- FILTRO-->




  </md-content>


</html>