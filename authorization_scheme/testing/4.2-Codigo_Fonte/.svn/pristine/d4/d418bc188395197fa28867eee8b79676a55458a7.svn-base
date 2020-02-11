<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 500px">

    <md-dialog-content>

        <h2>Replicar perfis de acesso de {{usuario.nomeCompleto}} </h2>
        <md-divider></md-divider>

        <md-content>
            <p>Informe os usuários a serem alterados. Ao clicar em replicar os perfís dos aplicativos serão
                substituidos</p>
            <md-divider></md-divider>

            <md-chips ng-model="usuarioSelecionado" md-autocomplete-snap md-require-match>
                <md-autocomplete
                        md-selected-item="selectedItem"
                        md-search-text="searchText"
                        md-items="usuario in querySearch(searchText)"
                        md-item-text="usuario.nomeCompleto"
                        placeholder="Pesquisar por um usuário">
                    <span md-highlight-text="searchText">{{usuario.nomeCompleto}} :: {{usuario.login}}</span>
                </md-autocomplete>
                <md-chip-template>
                <span>
                  <strong>{{$chip.nomeCompleto}}</strong>
                  <em>({{$chip.login}})</em>
                </span>
                </md-chip-template>
            </md-chips>

            <md-divider></md-divider>
        </md-content>
        <div class="md-actions" layout="row">
            <span flex></span>
            <md-button ng-click="answer('not useful')">
                Cancelar
            </md-button>
            <md-button class="md-primary" ng-if="usuarioSelecionado.length>0" ng-click="reply()">
                Replicar
            </md-button>
        </div>
    </md-dialog-content>

</md-dialog>