<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">
<md-dialog md-theme="default" aria-label="Perfil de acesso" style="width: 765px;">

  <md-dialog-content style="width: 700px; max-height:350px; height: 350px;float:left">

    <div>
      <md-progress-linear md-mode="indeterminate" ng-if="!notVoid"></md-progress-linear>
      <div layout-padding layout="column" ng-if="notVoid" layout-fill flex>

          <md-autocomplete
              md-selected-item="selectedItem"
              md-search-text-change="getAplicativos(searchText)"
              md-search-text="searchText"
              md-selected-item-change="getAplicativos(aplicativo.nome)"
              md-items="aplicativo in querySearch(searchText)"
              md-item-text="aplicativo.nome"
              md-min-length="0"
              placeholder="Pesquisar aplicativos">
            <md-item-template>
              <span md-highlight-text="searchText" md-highlight-flags="^i">{{aplicativo.nome}}</span>
            </md-item-template>
            <md-not-found>
              Nenhum resultado encontrado para "{{searchText}}".
            </md-not-found>
          </md-autocomplete>
        </div>
        <div layout="column" flex layout-fill>
          <md-list>
            <md-list-item class="md-2-line" ng-repeat="aplicativo in aplicativos">

              <span ng-if="!aplicativo.icone" class="md-avatar">{{ aplicativo.nome | limitTo : 1 : 0}}</span>
              <img ng-if="aplicativo.icone" style="width: 40px; height: 40px;margin-right: 16px;margin-top: 12px" ng-src="{{aplicativo.icone}}">

              <div class="md-list-item-text">
                <h3>{{aplicativo.nome}}</h3>

                <p ng-if="aplicativo.descricao">
                  {{aplicativo.descricao}}
                </p>
              </div>

              <div class="md-list-item-text">
                <!-- utilizar o ng-style -->
                <span ng-if="aplicativo.ativo" style="color: #00b0ff; margin-left:80px">Ativo</span>
                <span ng-if="!aplicativo.ativo" style="color: #cccccc; margin-left:80px">Inativo</span>
              </div>

              <div class="md-list-item-text" style="padding-top: 8px">
                <md-input-container style="padding-bottom:0">
                  <md-select placeholder="Selecione perfis" multiple="true" ng-model="aplicativo.perfisAcessoUsuario">
                    <md-option class="perfil-aplicativo" ng-repeat="item in aplicativo.perfisAcessoAplicativo"
                               value="{{item.nome}}" ng-click="toggle(item, aplicativo.perfisAcessoUsuario)">
                      {{ item.nome }}
                    </md-option>
                  </md-select>
                </md-input-container>
              </div>
            </md-list-item>
          </md-list>

      </div>
    </div>
  </md-dialog-content>
  <span flex></span>
  <div class="md-actions" layout="row">
    <span flex></span>
    <md-button ng-click="hide()" style="margin-right: 20px">
      Fechar
    </md-button>
  </div>

</md-dialog>
</html>