<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

  <md-toolbar style="height: 100px;">

    <!-- TOPSHEET -->
    <div class="alternate" style="height: 100px; background-color: #e3f2fd;position: absolute;top: 0;left: 0;width: 100%" ng-style="selectedItems.length ? {'height':'100px'} : {'height':'0'} ">
      <div ng-show="selectedItems.length" layout="row" flex layout-fill>
        <div flex layout="row" layout-align="start center">
          <div>
            <md-button class="md-icon-button" aria-label="Fechar" ng-click="clearSelectedItems()">
              <md-icon class="md-icon-close md-icon-lg" style="color: #666"></md-icon>
            </md-button>
          </div>
          <div layout-padding>
            <h2 class="md-toolbar-tools md-subhead" style="color: rgb(2,119,189);">
              <span>{{ selectedItems.length }} {{ selectedItems.length == 1 ? 'item selecionado' : 'itens selecionados' }}</span>
            </h2>
          </div>
        </div>
        <span flex></span>

        <div layout-align="end center" layout="row">
          <md-menu md-position-mode="target-right target" style="color:#666">
            <md-button aria-label="Abrir menu" class="md-icon-button" ng-click="$mdOpenMenu()" >
              <md-icon class="md-icon-more-horiz md-icon-lg" style="color:#666"></md-icon>
            </md-button>
            <md-menu-content width="3">
              <md-menu-item class="md-raised" ng-if="!aplicativo.filters.ativo">
                <md-button ng-click="enableAplicativo()" aria-label="Ativar">
                  Ativar
                </md-button>
              </md-menu-item>
              <md-menu-item class="md-raised" ng-if="aplicativo.filters.ativo">
                <md-button ng-click="disableAplicativo( $event, selectedItems )" aria-label="Desativar">
                  Desativar
                </md-button>
              </md-menu-item>
              <md-menu-item class="md-raised">
                <md-button ng-click="excluirAplicativos( $event, selectItems )" aria-label="Excluir">
                  Excluir
                </md-button>
              </md-menu-item>
            </md-menu-content>
          </md-menu>
        </div>
      </div>
    </div>
    <!-- TOPSHEET-->

    <div ng-show="!selectedItems.length" class="eits-padding">

      <md-button aria-label="Novo Aplicativo"  class="md-fab" ng-click="$state.go( ADD_STATE )" style="z-index: 100; left:38px;bottom: -33px;position: absolute;">
        <md-icon class="md-icon-add md-icon-lg"></md-icon>
        <div class="md-ripple-container"></div>
      </md-button>

      <div flex layout="row" layout-fill layout-padding style="margin-top: 8px; padding-right: 0">
        <div layout="column" ng-show="!search" flex layout-align="center start">
          <h2 class="eits-header-title md-toolbar-tools md-toolbar-tools-bottom">
            <span class="md-flex">Aplicativos</span>
          </h2>
        </div>
        <span flex></span>
        <div layout="row" layout-fill layout-padding layout-align="end center" style="padding-right: 0">

          <md-button style="margin-left: 63px" ng-show="search" aria-label="Pesquisar aplicativos" class="md-icon-button" ng-click="addTag()">
            <md-icon class="md-icon-search md-icon-lg"></md-icon>
          </md-button>

          <div layout-align="start center" ng-show="!selected.length && search" layout="row" layout-fill flex>
            <md-chips secondary-placeholder="Pesquisar aplicativos" placeholder="Pesquisar aplicativos" ng-model="tags" style="width: 100%;" id="search"></md-chips>
          </div>

          <md-button ng-show="!search" aria-label="Pesquisar aplicativos" class="md-icon-button" ng-click="toggleSearch()">
            <md-icon class="md-icon-search md-icon-lg"></md-icon>
          </md-button>
          <md-button ng-show="search" aria-label="Fechar pesquisa" class="md-icon-button" ng-click="toggleSearch()">
            <md-icon class="md-icon-close md-icon-lg"></md-icon>
          </md-button>

        </div>
      </div>
    </div>
  </md-toolbar>

  <md-content layout="row" flex>
    <div ng-if="aplicativo.page.content.length" layout="column" layout-fill flex style="background-color: #fafafa">
      <div>
        <md-toolbar layout="row" class="eits-menu-header" style="background-color: #fff;z-index:0; color: #333;" >
          <h2 class="md-toolbar-tools" style="color: #666;font-size: 16px; margin-left: 100px">
            <span ng-if="aplicativo.page.content.length">Todos aplicativos {{ aplicativo.filters.ativo ? 'ativos' : 'inativos' }}</span>
            <!--<span ng-if="!aplicativo.page.content.length">Nenhum aplicativo {{ aplicativo.filters.ativo ? 'ativo' : 'inativo' }}</span>-->
          </h2>
          <span flex></span>
          <md-button style="margin-right: 20px" class="md-icon-button" aria-label="Filtros" ng-click="toggleRightMenu()">
            <md-icon class="md-icon-filter-list md-icon-lg" style="color: #666"></md-icon>
          </md-button>
        </md-toolbar>
      </div>
      <div>
        <md-divider></md-divider>
        <div style="padding: 0 38px;" layout-align="start center" flex layout="row" ng-repeat="aplicativo in aplicativo.page.content" ng-class="{'aplicativo-seleted': aplicativo.checked}" layout-padding>

          <div style="width: 50px; height: 50px; margin:0px 35px 0 10px" layout="row" layout-align="center center" ng-mouseenter="showCheckBox(aplicativo, true)" ng-mouseleave="showCheckBox(aplicativo, false)">
            <div ng-show="!aplicativo.showCheckBox">
              <span ng-if="!aplicativo.icone" class="avatar-list">{{ aplicativo.nome | limitTo : 1 : 0}}</span>
              <img ng-if="aplicativo.icone" ng-src="{{aplicativo.icone}}" style="width: 40px; height: 40px">
            </div>
            <md-checkbox style="margin-left: 16px;" ng-model="aplicativo.checked" ng-change="selectItem(aplicativo)" aria-label="aplicativo.nome" ng-show="aplicativo.showCheckBox"></md-checkbox>
          </div>

          <div flex="60" ng-click="$state.go( DETAIL_STATE, {id: aplicativo.id})" style="cursor: pointer;">
            <h3 style="font-weight: normal">{{ aplicativo.nome }}</h3>
            <p style="color: #666">{{ aplicativo.descricao }}</p>
          </div>
          <span flex></span>
          <div>
            <md-menu md-position-mode="target-right target">
              <md-button aria-label="Abrir menu" class="md-icon-button" ng-click="$mdOpenMenu()">
                <md-icon class="md-icon-more-horiz md-icon-lg"></md-icon>
              </md-button>
              <md-menu-content width="3">
                <md-menu-item class="md-raised">
                  <md-button ng-click="$state.go( EDIT_STATE,{id: aplicativo.id})" aria-label="Editar">
                    Editar
                  </md-button>
                </md-menu-item>
                <md-menu-item class="md-raised" ng-if="aplicativo.ativo">
                  <md-button ng-click="disableAplicativo( $event, aplicativo )" aria-label="Desativar">
                    Desativar
                  </md-button>
                </md-menu-item>
                <md-menu-item class="md-raised" ng-if="!aplicativo.ativo">
                  <md-button ng-click="enableAplicativo( [aplicativo.id] )" aria-label="Ativar">
                    Ativar
                  </md-button>
                </md-menu-item>
                <md-menu-item class="md-raised">
                  <md-button ng-click="changeToRemove( $event, aplicativo )" aria-label="Excluir">
                    Excluir
                  </md-button>
                </md-menu-item>
              </md-menu-content>
            </md-menu>
          </div>
        </div>
      </div>
    </div>

    <div layout="column" class="list-empty-grid flex layout" layout-align="center center" ng-if="!aplicativo.page.content.length">
      Nenhum registro de Aplicativo
    </div>

    <div layout="row" layout-fill ng-show="isFilterOpen" style="width: 304px; float: right;z-index:10">
      <md-sidenav class="md-sidenav-right md-whiteframe-z2" md-is-locked-open="isFilterOpen == true" md-component-id="right">
        <md-toolbar layout="row">
          <h1 class="md-toolbar-tools">Filtro</h1>
          <md-button ng-if="aplicativo.page.content.length" class="md-icon-button" aria-label="Configurações" ng-click="toggleRightMenu()">
            <md-icon class="md-icon-close md-icon-lg" style="color: #fff"></md-icon>
          </md-button>
        </md-toolbar>
        <md-content layout-padding>
          <form>
            <md-input-container>
              <md-switch ng-change="listAplicativosByFilter()" ng-model="aplicativo.filters.ativo" aria-label="Perfis dinamicos"> {{ aplicativo.filters.ativo ? 'Ativos' : 'Inativos'}}
              </md-switch>
            </md-input-container>
          </form>

        </md-content>
      </md-sidenav>
    </div>
    <!-- FILTRO-->

  </md-content>

</html>