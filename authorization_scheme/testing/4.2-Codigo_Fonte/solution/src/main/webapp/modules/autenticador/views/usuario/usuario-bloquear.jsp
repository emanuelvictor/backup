<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 500px">

    <md-dialog-content>

        <h2>Bloquear usuário(s)</h2>
        <md-divider></md-divider>

        <md-content>
            <p>Realmente deseja excluir o(s) usuario(s)?</p>
            <div layout-margin layout="row">
                <md-checkbox ng-model="definirPeriodo" aria-label="Definir data de bloqueio">
                    Definir período debloqueio
                </md-checkbox>
            </div>
            <div layout="row" ng-show="definirPeriodo">
                <date-picker layout-margin aria-label="Data de bloqueio" model="dataBloqueio"
                             label="Início"
                             locale="pt-br"
                             date-format="L"></date-picker>
                <date-picker layout-margin aria-label="Data de desbloqueio" model="dataDesbloqueio"
                             label="Fim"
                             locale="pt-br"
                             date-format="L"></date-picker>
            </div>
        </md-content>
        <div class="md-actions" layout="row">
            <span flex></span>
            <md-button ng-click="cancel()">
                Cancelar
            </md-button>
            <md-button ng-click="bloquear(dataBloqueio, dataDesbloqueio)">
                Salvar
            </md-button>
        </div>
    </md-dialog-content>

</md-dialog>