<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt" >
<div layout="column" style="position: relative;">
  <div>
    <md-content layout="row" class="md-whiteframe-z1">
      <form name="authenticationForm" method="POST" action="./authenticate" class="login-form" layout="column" layout-align="start center" novalidate>

        <h4 style="color:rgb(21,101,192);">LOGIN DE ACESSO</h4>

        <md-input-container style="width: 300px ; margin-top: 45px">
          <label>NIP</label>
          <input type="text" ng-model="login" name="login" required>


          <div ng-if="authenticationForm.login.$touched || isFormSubmit" ng-messages="authenticationForm.login.$error">
            <div ng-message="required">Campo NIP obrigatório</div>
          </div>

        </md-input-container>
        <md-input-container style="width: 300px">
          <label>Senha</label>
          <input type="password" ng-model="senha" name="senha" required>

          <div ng-if="authenticationForm.senha.$touched || isFormSubmit" ng-messages="authenticationForm.senha.$error">
            <div ng-message="required">Campo senha obrigatório</div>
          </div>

        </md-input-container>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <md-button class="md-raised md-primary login-button" type="submit">
          Acessar
        </md-button>


      </form>
    </md-content>
  </div>
  <div>
    <a ng-href="#/signin/recuperar"  layout="row" layout-align="center center">
      Esqueceu sua senha?
    </a>
  </div>
</div>
</html>