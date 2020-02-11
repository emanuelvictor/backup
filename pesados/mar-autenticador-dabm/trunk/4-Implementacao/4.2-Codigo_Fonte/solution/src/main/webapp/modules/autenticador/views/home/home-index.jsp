<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-toolbar style="min-height: 100px;" layout-padding>
  <h2 class="eits-header-title md-toolbar-tools md-toolbar-tools-bottom" style="margin-top: 20px ">
    <span class="md-flex">Bem-vindo ao autenticador DAbM</span>
  </h2>
</md-toolbar>


<md-content layout="row" flex md-scroll-y class="eits-home" layout-margin layout-padding style="background-color: rgb(236,236,236)">

  <md-grid-list
      md-cols-sm="1" md-cols-md="2" md-cols-gt-md="4"
      md-row-height-gt-md="1:1" md-row-height="2:2">

    <div flex class="gray md-whiteframe-z1 box-list-home" ng-click="openAplicativo($event, aplicativo)" ng-repeat="aplicativo in aplicativos" ng-class="{'aplicativo-disabled': !aplicativo.ativo}">
      <div class="box-home" layout="column" layout-align="start center">
        <div class="icon-box-home" layout-align="center center" layout="row">
          <span ng-if="!aplicativo.icone" class="avatar-list">{{ aplicativo.nome | limitTo : 1 : 0}}</span>
          <img ng-if="aplicativo.icone" ng-src="{{aplicativo.icone}}" style="width: 80px; height: 80px">
        </div>
        <div class="md-caption" style="font-weight: bold ; margin-top: 40px">{{ aplicativo.nome | limitTo: 30}}{{ aplicativo.nome.length > 30 ? '...' : ''}}</div>
        <p class="md-caption">
          {{ aplicativo.descricao | limitTo: 60 }}{{ aplicativo.descricao.length > 60 ? '...' : ''}}
        </p>
      </div>
    </div>

  </md-grid-list>

</md-content>

</html>

