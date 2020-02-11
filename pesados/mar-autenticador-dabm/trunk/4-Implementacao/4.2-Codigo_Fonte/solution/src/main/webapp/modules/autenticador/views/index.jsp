<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="en" xmlns:jsp="http://www.w3.org/1999/XSL/Transform">

<head ng-app="autenticador">
  <title>Autenticador</title>

  <!-- Styles -->
  <jsp:include page="/modules/default-styles.jsp"/>
  <link rel="stylesheet" href="./static/css/theme.css">
  <link rel="stylesheet" href="./static/css/angular-ui-tree.min.css">

  <!-- Scripts -->
  <jsp:include page="/modules/default-scripts.jsp"/>
  <script type="text/javascript" src="./static/js/angular-ui-tree.min.js"></script>

  <script type="text/javascript" src="./static/js/beta-components/upload-image/uploadImage.js"></script>

  <!-- Main -->
  <script type="text/javascript" src="./modules/autenticador/autenticador-module.js?v=${version}"></script>

  <!-- Controllers -->
  <script type="text/javascript" src="./modules/autenticador/controllers/home-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/aplicativo-controller.js?v=${version}"></script>

  <!-- Usuario -->
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/usuario-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/adicionar-aplicativos-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/alterar-senha-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/excluir-usuarios-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/bloquear-usuarios-controller.js?v=${version}"></script>
  <script type="text/javascript"
          src="./modules/autenticador/controllers/usuario/replicar-perfis-controller.js?v=${version}"></script>


  <!-- Filters -->
  <script type="text/javascript" src="./modules/autenticador/filters/array-to-string.js?v=${version}"></script>
  <script type="text/javascript" src="./modules/autenticador/filters/format-date.js?v=${version}"></script>

  <!-- Directives -->
  <script type="text/javascript" src="./modules/autenticador/directives/same-as.js?v=${version}"></script>
  <script type="text/javascript" src="./modules/autenticador/directives/user-logged.js?v=${version}"></script>

</head>

<body layout="row" ng-controller="HomeController" style="overflow:visible;min-width: 1000px">
  <div class="color-bg">
    <div class="color-bg-blue"></div>
    <div class="color-bg-grey"></div>
  </div>
  <md-sidenav md-component-id="left" class="md-sidenav-left md-whiteframe-z2" layout="column">

    <md-toolbar layout-fill layout-wrap style="height: 160px; min-height: 160px; background: url(./static/images/MENU-BG-BLURRED.png) no-repeat" layout="column"
                layout-align="center center">
      <div layout="row" style="margin-left:40px; min-width: 315px; width: 315px">
        <div ng-if="aplicativo.icone" layout="column" style="width: 80px; background-size: cover;height: 80px;display: block;"
             ng-style="{'background-image':'url(' + aplicativo.icone + ')'}">
        </div>

        <div ng-if="!aplicativo.icone" layout="column" style="width: 80px; background-size: cover;height: 80px;display: block;background-image: url(./static/images/AUTENTICACAO-ICON.png)">
        </div>

        <div flex layout-padding layout-align="center center">
          <h4 style="margin: 15px 0 0 0;font-size: 18px">{{ aplicativo.nome ? aplicativo.nome : 'Autenticador DAbM'}}</h4>
          <p style="margin-top: 4px;font-size: 11px;color: #e5e5e5">Versão {{ aplicativo.versao ? aplicativo.versao : '1.0.0-SNAPSHOT' }}</p>
        </div>
      </div>
    </md-toolbar>

    <div layout="column">
      <md-list>
        <div flex>

          <md-list-item ng-click="$state.go('inicio.admin');closeMenu()">

            <md-button class="md-icon-button" aria-label="Início">
              <md-icon class="md-icon-home md-icon-lg"></md-icon>
            </md-button>
            <p>Início</p>

          </md-list-item>
          <md-list-item ng-click="$state.go(USUARIOS_STATE);closeMenu()">
            <md-button class="md-icon-button" aria-label="Usuários">
              <md-icon class="md-icon-account-circle md-icon-lg"></md-icon>
            </md-button>
            <p>Usuários</p>
          </md-list-item>
          <md-list-item ng-click="$state.go(APLICATIVOS_STATE);closeMenu()">
            <md-button class="md-icon-button" aria-label="Aplicativos">
              <md-icon class="md-icon-apps md-icon-lg"></md-icon>
            </md-button>
            <p>Aplicativos</p>
          </md-list-item>
        </div>
      </md-list>
    </div>
    <span flex></span>
    <div layout="column" flex layout-align="end start">
      <md-list class="bottom" style="width: 100%">
        <md-divider></md-divider>
        <!-- LINE-->
        <div flex style="font-size: 12px; color: #999;">
          <md-list-item ng-click="$state.go('inicio.config');closeMenu()">
            <p style="padding-left: 24px">Configurações</p>
          </md-list-item>
          <!--<md-list-item ng-click="$state.go('inicio.config');closeMenu()">
            <p style="padding-left: 24px" >Ajuda e feedback</p>
          </md-list-item>-->
          <md-list-item ng-click="logout()">
            <p style="padding-left: 24px">Sair</p>
          </md-list-item>
        </div>
      </md-list>
    </div>
  </md-sidenav>
  <div layout="column" flex id="main" role="main">
    <md-toolbar layout="column" class="eits-menu-header">
      <div class="md-toolbar-tools">

        <md-button ng-if="(userLogged.perfis | filter : 'ADMINISTRADOR').length" ng-click="handlerMenuButton()" class="md-icon-button" aria-label="Menu Principal">
          <md-icon
              ng-class="$state.current.name.indexOf('list') > -1 || $state.current.name.indexOf('inicio') > -1 ? 'md-icon-menu md-icon-lg' : 'md-icon-keyboard-backspace md-icon-lg'"
              style="color: #fff"></md-icon>
        </md-button>
        <!--<h2 ng-if="$state.current.name == 'inicio.admin'">Início</h2>-->
        <div layout="row" flex>
          <div class="md-toolbar-item md-breadcrumb">
            <breadcrumb ng-repeat="item in $state.current.breadCrumbs">
              <a ng-if="!$last" ui-sref="{{ item.state }}">
                                          <span class="md-breadcrumb-page">
                                              {{ item.name }}
                                          </span>
                <md-icon class="md-icon-navigate-next md-icon-lg" style="color: #fff"></md-icon>
              </a>
                                      <span ng-if="$last" class="md-breadcrumb-page">
                                          {{ item.name }}
                                      </span>
            </breadcrumb>
          </div>
        </div>
        <span flex></span>
        <eits-dropdown id="account" icon-menu="md-icon-person md-icon-lg" menu-orientation="left-top">
          <user-logged></user-logged>
        </eits-dropdown>
      </div>
    </md-toolbar>

    <div layout="column" flex class="content">
      <md-content id="ui-view" layout="column" flex ui-view></md-content>
    </div>

    <div id="aplicativo-desativado"></div>
  </div>
</body>
</html>