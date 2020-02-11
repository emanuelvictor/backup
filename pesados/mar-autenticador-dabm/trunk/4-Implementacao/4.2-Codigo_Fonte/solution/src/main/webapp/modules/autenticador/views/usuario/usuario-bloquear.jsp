<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<md-dialog md-theme="default" aria-label="Bloquear usuário" style="width: 500px">

  <md-dialog-content style="padding: 24px;float:left">

    <h2>Bloquear usuário(s)</h2>
    <md-divider></md-divider>

    <p>Definir período debloqueio?</p>
    <div layout="row">
      <form name="blockForm">

        <div layout="row" layout-sm="column">

          <md-datepicker name="dataBloqueio" ng-model="dataBloqueio" placeholder="Início" required></md-datepicker>
          <div class="validation-messages" ng-messages="blockForm.dataBloqueio.$error">
            <div ng-message="required">Campo obrigatório</div>
          </div>
          <span style="margin:16px 0 0 22px">até</span>
          <md-datepicker md-min-date="dataBloqueio" ng-model="dataDesbloqueio" placeholder="Fim"></md-datepicker>
        </div>
      </form>
    </div>

  </md-dialog-content>
  <div class="md-actions" layout="row">
    <md-button ng-click="cancel()">
      Cancelar
    </md-button>
    <md-button class="md-primary" ng-click="bloquear(dataBloqueio, dataDesbloqueio)">
      Bloquear
    </md-button>
  </div>

</md-dialog>
</html>