<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<md-dialog style="min-width: 320px">

    <md-dialog-content style="padding: 24px;float:left">
        <h2>Redefinir senha </h2>
        <form name="changePassword" novalidate>

            <div layout-margin>

                <md-input-container md-no-float>
                    <label>Nova senha</label>
                    <input type="password" ng-model="newPassword" name="newPassword" required minlength="6"/>

                    <div ng-if="changePassword.newPassword.$touched || isFormSubmit" ng-messages="changePassword.newPassword.$error">
                      <div ng-message="required">Campo senha é obrigatório</div>
                      <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>
                    </div>

                </md-input-container>

                <md-input-container md-no-float>
                    <label>Confirmar nova senha</label>
                    <input type="password" ng-model="confirmPassword" name="confirmPassword" required minlength="6"
                           same-as="newPassword"/>

                    <div ng-if="changePassword.confirmPassword.$touched || isFormSubmit" ng-messages="changePassword.confirmPassword.$error">
                      <div ng-message="required">Campo confirmar senha é obrigatório</div>
                      <div ng-message="minlength">A senha deve ter no minimo 6 caracteres</div>
                      <div class="error" ng-show="!changePassword.confirmPassword.$valid &&
                                                  !changePassword.confirmPassword.$error.minlength &&
                                                  !changePassword.confirmPassword.$error.required">Senhas não conferem</div>
                    </div>
                </md-input-container>

            </div>

            <!-- TODO As senhas devem ser iguais -->
            <!--<div layout="row" ng-if="newPassword!=confirmPassword">-->
                <!--<div ng-if="!enviandoEmail"> As senhas devem ser iguais <span style="color:rgb(232, 165, 165);">*</span></div>-->
            <!--</div>-->
            <!--<div layout="row" ng-if="enviandoEmail">-->
                <!--<div> Redefinindo ...  &lt;!&ndash; &ndash;&gt;</div>-->
            <!--</div>-->
            <div layout="row">
                <md-progress-linear ng-if="enviandoEmail"  md-mode="indeterminate"></md-progress-linear>
            </div>
        </form>
    </md-dialog-content>
    
    <div class="md-actions" layout="row">
        <md-button ng-click="hide('Cancelado')">
            Cancelar
        </md-button>
        <md-button ng-click="redefinir(changePassword)" class="md-primary">
            Redefinir
        </md-button>
    </div>

</md-dialog>
</html>