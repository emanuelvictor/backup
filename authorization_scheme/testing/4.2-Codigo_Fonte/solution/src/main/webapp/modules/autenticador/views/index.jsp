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

    
    <!-- Main -->
    <script type="text/javascript" src="./modules/autenticador/autenticador-module.js?v=${version}"></script>

    <!-- Controllers -->
    <script type="text/javascript" src="./modules/autenticador/controllers/home-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/aplicativo-controller.js?v=${version}"></script>
    
    <!-- Usuario -->
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/usuario-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/adicionar-aplicativos-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/alterar-senha-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/excluir-usuarios-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/bloquear-usuarios-controller.js?v=${version}"></script>
    <script type="text/javascript" src="./modules/autenticador/controllers/usuario/replicar-perfis-controller.js?v=${version}"></script>


    <!-- Filters -->
    <script type="text/javascript" src="./modules/autenticador/filters/array-to-string.js?v=${version}"></script>


</head>

<body layout="row" ng-controller="HomeController">
<div class="color-bg">
    <div class="color-bg-blue"></div>
    <div class="color-bg-grey"></div>
</div>
<md-sidenav md-component-id="left"  class="md-sidenav-left md-whiteframe-z2" layout="column">

    <md-toolbar class="md-tall">
        <span flex></span>
        <h4 class="md-toolbar-tools">
            <span>Autenticador</span>
        </h4>
    </md-toolbar>

    <md-toolbar>
        <p class="md-toolbar-tools"></p>
    </md-toolbar> 

    <div flex="50" layout-align="start start">
        <md-list>

            <md-list-item ng-click="$state.go('inicio.admin');closeMenu()">
                <p>Inicio</p>
            </md-list-item>
            <md-list-item ng-click="$state.go('usuario.list');closeMenu()">
                <p>Usuários</p>
            </md-list-item>
            <md-list-item ng-click="$state.go(APLICATIVOS_STATE);closeMenu()">
                <p>Aplicativos</p>
            </md-list-item>
        </md-list>

    </div>
    <div flex="10">
        <md-list>
            <md-divider></md-divider>
            <!-- LINE-->
            <div layout="column" flex style="font-size: 12px; color: #999" layout-align="end start">
                <md-list-item ng-click="$state.go('inicio.config');closeMenu()">
                    <p>Configurações</p>
                </md-list-item>
                <md-list-item>
                    <p>Ajuda e feedback</p>
                </md-list-item>
                <md-list-item ng-click="logout()">
                    <p >Sair</p>
                </md-list-item>

            </div>


        </md-list>
    </div>

    <md-list>
    </md-list>

</md-sidenav>
<div layout="column" class="relative" layout-fill role="main" id="main">
    <md-toolbar>
        <div class="md-toolbar-tools">
            <md-button ng-click="handlerMenuButton()" class="md-icon-button" aria-label="Menu Principal">
                <md-icon ng-class="$state.current.name.indexOf('list') > -1 || $state.current.name.indexOf('inicio') > -1 ? 'md-icon-menu md-icon-lg' : 'md-icon-keyboard-backspace md-icon-lg'" style="color: #fff"></md-icon>
            </md-button>
            <div layout="row" flex class="fill-height" layout-align="start center" style="line-height: 38px">
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
            <md-button class="md-icon-button" aria-label="Configurações">
                <md-icon class="md-icon-apps md-icon-lg" style="color: #fff"></md-icon>
            </md-button>
            <md-button class="md-icon-button" aria-label="Minha conta">
                <md-icon class="md-icon-person md-icon-lg" style="color: #fff"></md-icon>
            </md-button>
        </div>
    </md-toolbar>
    <md-content flex md-scroll-y class="content">
        <div layout="column" layout-fill ui-view></div>
    </md-content>
</div>
</body>
</html>