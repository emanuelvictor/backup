<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<md-dialog style="width: 400px">
  <md-dialog-content style="padding: 24px;float:left">
    <h2>Redefinir senha</h2>

    <form name="changePassword">

      <md-input-container>
        <label>Digite sua senha</label>
        <input type="password" ng-model="password" name="password" required/>

        <div ng-messages="changePassword.form.password.$error">
          <div ng-message="required">Digite sua senha atual</div>
        </div>
      </md-input-container>

      <md-input-container md-no-float>
        <label>Nova senha</label>
        <input type="password" ng-model="newPassword" name="newPassword" required minlength="6"/>

        <div ng-if="changePassword.newPassword.$touched || isFormSubmit"
             ng-messages="changePassword.newPassword.$error">
          <div ng-message="required">Campo senha é obrigatório</div>
          <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>

        </div>

      </md-input-container>

      <md-input-container md-no-float>
        <label>Confirmar nova senha</label>
        <input type="password" ng-model="confirmPassword" name="confirmPassword" required minlength="6"
               same-as="newPassword"/>

        <div ng-if="changePassword.confirmPassword.$touched || isFormSubmit"
             ng-messages="changePassword.confirmPassword.$error">
          <div ng-message="required">Campo confirmar senha é obrigatório</div>
          <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>
          <div class="error" ng-show="!changePassword.confirmPassword.$valid &&
                                                  !changePassword.confirmPassword.$error.minlength &&
                                                  !changePassword.confirmPassword.$error.required">Senhas não conferem
          </div>
        </div>
      </md-input-container>

      <div layout="row">
        <md-progress-linear ng-if="enviandoEmail" md-mode="indeterminate"></md-progress-linear>
      </div>
    </form>
    <!-- FORM-->

  </md-dialog-content>
  <!-- DIALOG-CONTENT -->
  <div class="md-actions" layout="row">
    <md-button ng-click="cancel()">
      Cancelar
    </md-button>
    <md-button ng-click="redefine(changePassword)" class="md-primary">
      Redefinir
    </md-button>
  </div>


</md-dialog>