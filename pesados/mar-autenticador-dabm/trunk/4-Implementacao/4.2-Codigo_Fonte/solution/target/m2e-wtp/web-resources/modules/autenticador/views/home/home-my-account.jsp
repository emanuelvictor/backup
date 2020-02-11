<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<div layout="column" flex layout-fill style="overflow: hidden">

  <md-toolbar style="min-height: 100px;" layout-padding>
    <span flex></span>
    <h2 class="md-toolbar-tools md-toolbar-tools-bottom" style="margin-left: 85px">
      <span class="md-flex md-headline">Configurações da conta</span>
    </h2>
  </md-toolbar>

  <md-content layout="column" layout-align="start center" class="content flex layout-fill">
    <div ng-init="getUserLogged()" layout-align="center center" class="eits-content">
      <md-whiteframe class="md-whiteframe-z2 whiteframe-myAccont" layout layout-align="center center">

        <ul class="details">
          <li style="font-weight: 900;margin:10px 0px">Informações pessoais</li>
          <li>
            <p>NIP</p>

            <p>{{ userLogged.login }}</p>
          </li>

          <li>
            <p>Email</p>
            <p>{{ userLogged.email }}</p>
          </li>

          <li>
            <p>Nome completo</p>
            <p>{{ userLogged.nomeCompleto }}</p>
          </li>

        </ul>
      </md-whiteframe>

      <md-whiteframe class="md-whiteframe-z2 whiteframe-myAccont" layout layout-align="center center">

        <ul class="details">
          <li style="font-weight: 900;margin:10px 0px">Atividades recentes</li>
          <li ng-click="showAlterarSenha(userLogged)" style="cursor : pointer;">
            <p>Senha</p>

            <p ng-if="userLogged.dataAlteracaoSenha" >Última alteração {{ userLogged.dataAlteracaoSenha | date  }}</p>
          </li>
          <li>
            <p>Próxima alteração </p>
            <p>{{ userLogged.dataExpiracaoSenha | date }}</p>
          </li>
          <li>
            <p>Último acesso</p>

            <p>{{ userLogged.dataUltimoAcesso | date }}</p>
          </li>

        </ul>

      </md-whiteframe>
    </div>
  </md-content>
</div>

</html>

