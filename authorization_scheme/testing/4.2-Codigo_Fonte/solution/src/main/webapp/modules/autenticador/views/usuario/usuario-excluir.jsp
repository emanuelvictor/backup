<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 500px">

    <md-dialog-content>

        <h2>Excluir usuário</h2>
        <md-divider></md-divider>

        <md-content>
            <p>Deseja realmente excluir o usuário {{usuario.nome}}?</p>
        </md-content>
        <div class="md-actions" layout="row">
            <span flex></span>
            <md-button ng-click="cancel()">
                Não
            </md-button>
            <md-button ng-click="excluir()">
                Sim
            </md-button>
        </div>
    </md-dialog-content>

</md-dialog>