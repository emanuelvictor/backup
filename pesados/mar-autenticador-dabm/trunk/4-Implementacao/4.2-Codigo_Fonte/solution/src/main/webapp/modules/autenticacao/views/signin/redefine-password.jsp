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
      <form name="redefineForm" layout="column" layout-align="start center" class="login-form"
            style="width: 480px" ng-submit="redefinePassword(redefineForm, newPassword, confirmPassword)" novalidate>

          <h4>REDEFINIR SENHA</h4>

          <p class="md-caption">Sua senha expirou ou foi alterada recentemente pelo administrador,
              altere sua senha para poder acessar o sistema.</p>

          <md-input-container style="width: 300px ; margin-top: 25px">
              <label>Nova senha</label>
              <input type="password" ng-model="newPassword" name="newPassword" minlength="6" required/>

              <div ng-if="redefineForm.newPassword.$touched || isFormSubmit" ng-messages="redefineForm.newPassword.$error">
                <div ng-message="required">Campo senha é obrigatório</div>
                <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>
              </div>

          </md-input-container>

          <md-input-container style="width: 300px">
              <label>Confirmar nova senha</label>
            <input type="password" ng-model="confirmPassword" name="confirmPassword" required minlength="6"
                   same-as="newPassword"/>
              <div ng-if="redefineForm.confirmPassword.$touched || isFormSubmit" ng-messages="redefineForm.confirmPassword.$error">
                <div ng-message="required">Campo confirmar senha é obrigatório</div>
                <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>
                <div class="error" ng-show="!redefineForm.confirmPassword.$valid &&
                                                    !redefineForm.confirmPassword.$error.minlength &&
                                                    !redefineForm.confirmPassword.$error.required">Senhas não conferem</div>
              </div>

          </md-input-container>


          <md-button class="md-raised md-primary login-button" type="submit">
              REDEFINIR SENHA
          </md-button>
      </form>
      </md-content>
    </div>
</div>
</html>