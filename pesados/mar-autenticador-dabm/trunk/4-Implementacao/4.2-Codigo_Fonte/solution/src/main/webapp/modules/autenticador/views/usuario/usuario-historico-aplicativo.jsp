<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Histórico de aplicativos" style="width: 500px">
  <md-dialog-content style="padding: 24px;float:left">
    <h2>Histórico de aplicativos</h2>
    <md-divider></md-divider>
    <md-content>
      <p ng-if="!historic.length">Nenhum histórico encontrado</p>
      <md-list ng-if="historic.length">
        <md-list-item class="md-3-line" ng-repeat="aplicativo in historic">
          <div class="md-list-item-text">
            <p>{{ aplicativo.nome }}</p>
          </div>
          <div class="md-list-item-text">
            <p ng-repeat="perfil in aplicativo.perfisAcesso">
              <span>{{ perfil.nome }}</span>
            </p>
          </div>
          <md-divider></md-divider>
        </md-list-item>
      </md-list>
    </md-content>
    <div class="md-actions" layout="row">
      <span flex></span>
      <md-button ng-click="hide()">
        Fechar
      </md-button>
    </div>
  </md-dialog-content>

</md-dialog>