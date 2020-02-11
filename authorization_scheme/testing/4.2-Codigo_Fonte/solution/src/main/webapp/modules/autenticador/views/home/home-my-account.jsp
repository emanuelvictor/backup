<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<md-toolbar>
    <h2 class="md-toolbar-tools md-toolbar-tools-bottom" style="margin-left: 85px">
        <span class="md-flex">Configurações da conta</span>
    </h2>
</md-toolbar>

<md-content layout-padding>
    <div ng-init="getLoggedUser()" layout-align="center center"
         style="max-width: 800px; margin: 0 auto; z-index: 10; position: relative;">
        <md-whiteframe class="md-whiteframe-z2" layout layout-align="center center"
                       style=" margin: 20px ; padding: 0px 20px 20px 20px ;">

            <ul class="details">
                <h4>Informações pessoais</h4>
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

        <md-whiteframe class="md-whiteframe-z2" layout layout-align="center center"
                       style=" margin: 20px;  padding: 0px 20px 20px 20px ;">


            <ul class="details">
                <h4>Atividades recentes</h4>
                <li ng-click="showAlterarSenha(userLogged)" style="cursor : pointer;">
                    <p>Senha</p>

                    <p>Última alteração {{ userLogged.dataAlteracaoSenhaFormatted }}</p>
                </li>
                <li>
                    <p>Alteração de senha</p>

                    <p>Próxima alteração {{ (userLogged.dataExpiracaoSenhaFormatted) }}</p>
                </li>

            </ul>

        </md-whiteframe>
    </div>
</md-content>

