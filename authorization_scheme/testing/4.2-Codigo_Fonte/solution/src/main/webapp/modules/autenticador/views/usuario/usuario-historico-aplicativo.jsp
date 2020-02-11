<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Histórico de aplicativos" style="width: 500px">

    <md-dialog-content>

        <h2>Histórico de aplicativos</h2>
        <md-divider></md-divider>
        <md-content>
            <md-list>
                <md-list-item class="md-3-line">

                    <div class="md-list-item-text">
                        <h3>Aplicativo 1</h3>
                    </div>
                    <div class="md-list-item-text">
                        <p>
                            <span>Perfil 1,Perfil 2</span>
                        </p>
                    </div>
                </md-list-item>
                <md-divider></md-divider>
                <md-list-item class="md-3-line">
                    <div class="md-list-item-text">
                        <h3>Aplicativo 2</h3>
                    </div>
                    <div class="md-list-item-text">
                        <p>
                            <span>Perfil a,Perfil b</span>
                        </p>
                    </div>
                </md-list-item>
                <md-divider></md-divider>
                <md-list-item class="md-3-line">
                    <div class="md-list-item-text">
                        <h3>Aplicativo 3</h3>
                    </div>
                    <div class="md-list-item-text">
                        <p>
                            <span>Perfil 5,Perfil 5</span>
                        </p>
                    </div>
                </md-list-item>
            </md-list>
        </md-content>
        <div class="md-actions" layout="row">
            <span flex></span>
            <md-button ng-click="hide('not useful')">
                Fechar
            </md-button>
        </div>
    </md-dialog-content>

</md-dialog>