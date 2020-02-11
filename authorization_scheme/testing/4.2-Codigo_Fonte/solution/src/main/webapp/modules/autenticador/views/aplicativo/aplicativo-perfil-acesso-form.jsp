<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 700px">

    <md-dialog-content>

        <h2>{{ perfilAcesso.id ? 'Editar' : 'Inserir' }} Perfil</h2>

        <div>
            <form name="perfilAcesso.form">
                <md-input-container>
                    <label>Nome</label>
                    <input type="text" name="nome" ng-model="perfilAcesso.entity.nome" md-maxlength="144" required>
                </md-input-container>

                <md-input-container>
                    <label>Descrição</label>
                    <input type="text" name="descricao" ng-model="perfilAcesso.entity.descricao">
                </md-input-container>

                <md-input-container>
                    <md-select ng-model="perfilAcesso.entity.perfilAcessoSuperior.id"
                               placeholder="Perfil de acesso superior">
                        <md-option value="1">Perfil acesso 1</md-option>
                        <md-option value="2">Perfil acesso 2</md-option>
                    </md-select>
                </md-input-container>

                <div layout layout-sm="column" layout-align="start center">
                    <md-input-container>

                        <md-checkbox ng-model="perfilAcesso.entity.expirarSenha" aria-label="Dias para expiração">
                            Senha expira em
                        </md-checkbox>
                    </md-input-container>
                    <md-input-container flex="10">

                        <input aria-label="Dias de expiração de senha"
                               type="number"
                               name="periodo"
                               flex="20"
                               ng-model="perfilAcesso.entity.diasExpiracaoSenha">

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
        <md-button ng-click="sendForm()" class="md-primary">
            Salvar
        </md-button>
    </div>

</md-dialog>