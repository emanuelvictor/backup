<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<div layout="column" style="position: relative;">
  <div>
    <md-content layout="column" class="md-whiteframe-z1">
      <div layout="row" ng-if="recuperandoSenha" style="width: 480px; ">
          <md-progress-linear md-mode="indeterminate"></md-progress-linear>
      </div>
      <form name="recoverForm" ng-submit="recoverPassword(recoverForm, login)"
            layout="column" layout-align="start center" class="login-form" novalidate>

          <h4>RECUPERAR SENHA</h4>
          <md-input-container style="width: 300px ; margin-top: 45px">
              <h5> Para redefinir uma nova senha, informe seu NIP</h5>
              <label>NIP</label>
              <input type="text" ng-model="login" name="login" required>
              <div ng-if="recoverForm.login.$touched || isFormSubmit" ng-messages="recoverForm.login.$error">
                <div ng-message="required">Campo NIP obrigatório</div>
              </div>

          </md-input-container>

          <md-button class="md-raised md-primary login-button" type="submit">
              Enviar nova senha
          </md-button>

      </form>
      </md-content>
    </div>
  <div>
      <a ng-href="#/login" class="login-link" layout="row" layout-align="center center">
          <md-icon class="md-icon-keyboard-backspace md-icon-lg"></md-icon>
          Voltar
      </a>
  </div>
</div>
</html>