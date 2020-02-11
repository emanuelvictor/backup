<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 700px">

    <md-dialog-content style="padding: 24px;float:left">

        <h2>{{ perfilAcesso.entity.id ? 'Editar' : 'Inserir' }} Perfil</h2>
        <div>
            <form name="perfilAcessoForm" novalidate>
                <md-input-container>
                    <label>Nome do perfil</label>
                    <input type="text" name="nome" ng-model="perfilAcesso.entity.nome" md-maxlength="144" required>
                    <div ng-if="perfilAcessoForm.nome.$touched || isFormSubmit" ng-messages="perfilAcessoForm.nome.$error">
                        <div ng-message="required">Campo nome do perfil é obrigatório</div>
                    </div>
                </md-input-container>

                <md-input-container>
                    <label>Descrição</label>
                    <input type="text" name="descricao" ng-model="perfilAcesso.entity.descricao">
                </md-input-container>

                <div ng-class="{'disabled' : !perfilAcesso.entity.expirarSenha }" layout layout-sm="column" layout-align="start center" >
                    <md-input-container>

                        <md-checkbox ng-model="perfilAcesso.entity.expirarSenha" aria-label="Dias para expiração">
                            Perfil expira em
                        </md-checkbox>
                    </md-input-container>
                    <md-input-container flex="10">

                        <input aria-label="Dias de expiração de senha"
                               type="number"
                               min="0"
                               name="periodo"
                               flex="20"
                               ng-model="perfilAcesso.entity.diasExpiracaoSenha"
                               ng-disabled="!perfilAcesso.entity.expirarSenha" >

                    </md-input-container>
                    <md-input-container flex>dias</md-input-container>
                </div>
            </form>
        </div>
    </md-dialog-content>
    <div class="md-actions" layout="row">
        <span flex></span>
        <md-button ng-click="cancel()">
            Cancelar
        </md-button>
        <md-button ng-click="sendForm(perfilAcessoForm)" class="md-primary">
            Salvar
        </md-button>
    </div>

</md-dialog>