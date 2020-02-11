<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Desativar aplicativo" style="width: 700px">

    <md-dialog-content style="padding: 24px;float:left">

        <h2>Desativação de aplicativo</h2>

        <div>
            <p ng-if="aplicativo.nome">Deseja realmente desativar o aplicativo {{ aplicativo.nome }}?</p>
            <p ng-if="aplicativo.length">Deseja realmente desativar os aplicativos {{ aplicativoListNomes }}?</p>
            <p>Um aplicativo desativado não poderá ser acessado por nenhum usuário.</p>
            <form>
                <md-input-container>
                    <label>Mensagem</label>
                    <textarea name="mensagem" ng-model="aplicativo.mensagemDesativacao"></textarea>
                </md-input-container>
            </form>
        </div>
    </md-dialog-content>
    <div class="md-actions" layout="row">
        <span flex></span>
        <md-button ng-click="cancel()">
            Cancelar
        </md-button>
        <md-button ng-click="sendForm()" class="md-primary">
            Desativar
        </md-button>
    </div>

</md-dialog>