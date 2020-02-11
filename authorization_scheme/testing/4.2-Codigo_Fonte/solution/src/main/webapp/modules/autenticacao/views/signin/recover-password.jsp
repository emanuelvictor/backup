<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<form name="authenticationForm"
      layout="column" layout-align="start center"  class="md-whiteframe-z1"
      style="padding: 40px 90px 0px 90px;  background-color: #FFFFFF">

    <h4>RECUPERAR SENHA</h4>

    <md-input-container style="width: 300px ; margin-top: 45px">
        <h5> Para redefinir uma nova senha, informe seu NIP</h5>
        <label>NIP:</label>
        <input type="text" ng-model="usuario.login" required>
    </md-input-container>


    <md-button class="md-raised md-warn" ng-click="recoverPassword()"
               style="width: 100% ; height: 60px; margin-top: 45px; margin-bottom: 60px" >
        Enviar nova senha
    </md-button>

</form>

</html>