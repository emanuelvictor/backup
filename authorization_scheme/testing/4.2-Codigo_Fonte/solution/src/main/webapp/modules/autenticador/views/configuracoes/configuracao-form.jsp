<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-button class="md-fab md-fab-top-right" ng-click="updateDiasExpiracaoSenha()">
    <md-icon class="md-icon-save md-icon-lg"></md-icon>
</md-button>

<md-content layout="row" layout-padding layout-align="center start" class="content">

    <div layout-align="center center" style="max-width:800px; min-width: 800px">
        <form name="configuracao.form">
            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open>
                        <div layout="row">
                            <div flex layout="row">
                                <div flex="45" layout="row" layout-align="center center" layout-wrap>
                                    <div flex>
                                        <div class="icon">
                                            <!-- TODO ICONE-->
                                        </div>
                                    </div>
                                    <div flex="55" layout-margin>
                                        <h2>Configurações</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </content-open>
                </div>
            </div>
            <eits-paper-sheet on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">

                <div>
                    <content-closed>
                        <h4>Configurações de senha</h4>
                        <md-divider></md-divider>
                    </content-closed>

                    <content-opened>


                        <div layout layout-sm="column" layout-align="start center" layout-margin>

                            <md-input-container>
                                Tempo de expiração de senha


                            </md-input-container>


                            <md-input-container flex="10">
                                <input aria-label="Dias de expiração de senha"
                                       type="number"
                                       name="periodo"
                                       flex="20"
                                       min="1"
                                       ng-model="configuracao.diasExpiracaoSenha">


                            </md-input-container>


                            <md-input-container flex>
                                dias
                            </md-input-container>
                        </div>
                        <md-input-container style="margin-top: -50px ; margin-left: 8px" >

                            <div ng-messages="configuracao.form.periodo.$error">
                                <div ng-message="min" >

                                    Quantidade de dias deve ser maior que zero
                                </div>
                            </div>
                        </md-input-container>
                    </content-opened>
                </div>
            </eits-paper-sheet>
        </form>
    </div>

</md-content>

</html>