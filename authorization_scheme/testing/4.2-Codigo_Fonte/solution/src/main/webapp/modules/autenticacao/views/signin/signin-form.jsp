<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt" >

	<form ng-controller="SigninController" name="authenticationForm"
		layout="column" layout-align="start center"  class="md-whiteframe-z1"
		style="padding: 40px 90px 0px 90px; width: 480px; height: 480px ; background-color: #FFFFFF">

		<h4>LOGIN DE ACESSO</h4>
      		
		<md-input-container style="width: 300px ; margin-top: 45px">
          	<label>NIP:</label>
          	<input type="text" ng-model="usuario.login" required>
       	</md-input-container>
	  	<md-input-container style="width: 300px">
          	<label>Senha:</label>
          	<input type="password" ng-model="usuario.senha" required>
       	</md-input-container>
       	
       	<md-button class="md-raised md-warn" ng-click="signin(usuario)" style="width: 100% ; height: 60px; margin-top: 45px" >
			Acessar
		</md-button>

	</form>
	<a ng-href="#/recuperar"
	   style="text-decoration:none ; width: 100% ; margin-top: 20px " layout="row" layout-align="center center" >
		Esqueceu sua senha?
	</a>
</html>