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
    <md-content layout="row" class="md-whiteframe-z1">

      <form name="authenticationForm" layout="column" layout-align="start center" class="login-form">

          <h4>RECUPERAR SENHA</h4>
          <p> Instruções de troca de senha enviadas para </p>
          <p><b>{{email}}</b></p>
          <md-button class="md-raised md-primary login-button" ng-click="$state.go(LOGIN_STATE)">
              Continuar
          </md-button>

      </form>
      </md-content>
    </div>
</div>
</html>