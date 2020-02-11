<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">


<md-toolbar ng-init="getAplicativos()">
    <h2 class="md-toolbar-tools md-toolbar-tools-bottom" style="margin-left: 85px">
        <span class="md-flex">Bem-vindo ao autenticador DAbM</span>
    </h2>
</md-toolbar>

<md-content layout="row" layout-padding layout-fill
            style="padding: 60px; height: 400px; background-color: rgb(236,236,236)">

    <div class="gray md-whiteframe-z1 box-list-home" ng-click="$state.go( 'usuario.list' )">
        <div layout="column" layout-align="start center" style="padding: 35px 10px 30px 10px">
            <div style="width: 80px; height: 80px; background-color : #d3d3d3; border-radius: 50% ">

            </div>
            <div class="md-caption" style="font-weight: bold ; margin-top: 40px">Aplicativos</div>
            <p class="md-caption" style="height: 100%">
                Adicionar, editar e gerenciar usuários
            </p>
        </div>
    </div>

    <div class="gray md-whiteframe-z1 box-list-home"
         ng-click="$state.go( USUARIOS_STATE )">
        <div layout="column" layout-align="start center" style="padding: 35px 10px 30px 10px">
            <div
                    style="width: 80px; height: 80px; background-color : #d3d3d3; border-radius: 50% "
                    layout-align="center center" layout="row">
                <md-icon class="md-icon-apps md-icon-lg"></md-icon>
            </div>
            <div class="md-caption" style="font-weight: bold ; margin-top: 40px">Usuários</div>
            <p class="md-caption">
                Adicionar, editar e gerenciar usuários
            </p>
        </div>
    </div>

    <!---->

    <!--<md-grid-list md-gutter="38px" md-gutter-gt-sm="50px">-->


    <!--&lt;!&ndash;USUARIO&ndash;&gt;-->

    <!--<md-grid-tile class="gray"-->

    <!--ng-repeat="aplicativo in aplicativos"-->
    <!--ng-if=" perfil == 'USUARIO'"-->
    <!--ng-click="openAplicativo(aplicativo.endereco) ">-->

    <!--&lt;!&ndash; aplicativos&ndash;&gt;-->
    <!--<div layout="column" layout-align="start center" style="padding: 35px 10px 30px 10px">-->
    <!--<div style="width: 80px; height: 80px; background-color : #d3d3d3; border-radius: 50% ">-->

    <!--</div>-->

    <!--<div class="md-caption" style="font-weight: bold ; margin-top: 40px">{{aplicativo.nome}}</div>-->
    <!--<div class="md-caption" layout-align="center center" style="margin-top: 20px; text-align: center">-->
    <!--{{aplicativo.descricao}}-->
    <!--</div>-->
    <!--</div>-->
    <!--</md-grid-tile>-->

    <!--<md-grid-tile class="gray"-->
    <!--style="background-color : white ; height: 250px; width: 250px"-->
    <!--ng-click="$state.go( USUARIOS_STATE )">-->
    <!--&lt;!&ndash; usuarios&ndash;&gt;-->
    <!--<div layout="column" layout-align="start center" style="padding: 35px 10px 30px 10px">-->
    <!--<div style="width: 80px; height: 80px; background-color : #d3d3d3; border-radius: 50% ">-->

    <!--</div>-->
    <!--<div class="md-caption" style="font-weight: bold ; margin-top: 40px">Usuários</div>-->
    <!--<div class="md-caption" layout-align="center center" style="margin-top: 20px; text-align: center">-->
    <!--Adicionar, editar e gerenciar usuários-->
    <!--</div>-->
    <!--</div>-->
    <!--</md-grid-tile>-->

    <!--<md-grid-tile class="gray"-->
    <!--style="background-color : white ; height: 250px; width: 250px"-->
    <!--ng-click="$state.go( APLICATIVOS_STATE )">-->
    <!--&lt;!&ndash;aplicativos&ndash;&gt;-->
    <!--<div layout="column" layout-align="start center" style="padding: 35px 10px 30px 10px">-->
    <!--<div style="width: 80px; height: 80px; background-color : #d3d3d3; border-radius: 50% ">-->
    <!--<img src="https://s3.amazonaws.com/www.eits.com.br/logo-email.png"/>-->
    <!--</div>-->
    <!--<div class="md-caption" style="font-weight: bold ; margin-top: 40px">Aplicativos</div>-->
    <!--<div class="md-caption" layout-align="center center" style="margin-top: 20px; text-align: center">-->
    <!--Adicionar, editar e gerenciar aplicativos-->
    <!--</div>-->
    <!--</div>-->

    <!--</md-grid-tile>-->


    <!--</md-grid-list>-->

</md-content>
</html>