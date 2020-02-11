<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

	
		<md-toolbar>
			<h2 class="md-toolbar-tools md-toolbar-tools-bottom" style="margin-left: 85px">
				<span class="md-flex">Usuários</span>
			</h2>
		</md-toolbar>
		
		<md-button aria-label="Novo Usuário" class="md-fab md-fab-top-left" ng-click="$state.go( ADD_STATE )" style="left:10px">
			<md-icon class="md-icon-add md-icon-lg"></md-icon>
			<div class="md-ripple-container"></div>
		</md-button>

        <md-data-table
                table-card="{visible: true, title: 'Usuários'}"
                selectable-rows="true"
                alternate-headers="'asdfasda'">
		
            <md-data-table-header-row>
<!--             	<md-data-table-column align-rule="left">
            		<md-checkbox ng-model="test" aria-label="Checkbox 1"></md-checkbox>
        		</md-data-table-column> -->
                <md-data-table-column align-rule="left">Nome</md-data-table-column>
                <md-data-table-column align-rule="right">NIP</md-data-table-column>
                <md-data-table-column align-rule="right">Status</md-data-table-column>
                <md-data-table-column align-rule="right">Ações</md-data-table-column>
            </md-data-table-header-row>

            <md-data-table-row ng-repeat="usuario in model.page.content">
<!--             	<md-data-table-column align-rule="left">
            		<md-checkbox ng-model="test" aria-label="Checkbox 1"></md-checkbox>
        		</md-data-table-column> -->
                <md-data-table-cell>{{usuario.nomeCompleto}}</md-data-table-cell>
                <md-data-table-cell>{{usuario.login}}</md-data-table-cell>
                <md-data-table-cell>{{usuario.enabled}}</md-data-table-cell>
				<md-data-table-cell>
				  <div flex layout="row" layout-align="end">
					<eits-dropdown icon-menu="md-icon-more-horiz md-icon-lg" class="ng-isolate-scope">
					  <md-list-item ng-click="test1()">
						<p>Redefinir senha</p>
					  </md-list-item>
					  <md-list-item ng-click="showResetPassword($event)">
						<p>Redefinir senha</p>
					  </md-list-item>
					  <md-list-item ng-if="model.entity.accountNonLocked" ng-click="bloquearUsuario($event, model.entity)">
						<p>Bloquear</p>
					  </md-list-item>
					  <md-list-item ng-if="!model.entity.accountNonLocked" ng-click="desbloquearUsuario(model.entity)">
						<p>Desbloquear</p>
					  </md-list-item>
					  <md-list-item ng-if="!model.entity.dataExclusao" ng-click="excluirUsuario($event)">
						<p>Excluir</p>
					  </md-list-item>
					  <md-list-item ng-if="model.entity.dataExclusao" ng-click="restaurarUsuario(model.entity)">
						<p>Restaurar</p>
					  </md-list-item>
					</eits-dropdown>
				  </div>	
				</md-data-table-cell>
            </md-data-table-row>
        </md-data-table>


<!-- 		<md-content layout="column" flex class="md-default list">
		<eits-table id="table" content="model.page.content" multi-selection="true" ng-click="replyProfile($event, model.page.content[1])">
			<columns>
				<table-column width="40" sortable="false">
					<column-template>
						foto
					</column-template>
				</table-column>
				<table-column header="Nome" field="nomeCompleto" sortable="true"/>
				<table-column header="NIP" field="login" sortable="true"/>
				<table-column header="Status" field="enabled" sortable="false"/>
				<table-column width="40" sortable="false">
					<column-template >
						dropdown
					</column-template>
				</table-column>
			</columns> -->

<!-- 			<pager>
				<infinity-scroll-pager on-scroll-end="changeToList(true)"></infinity-scroll-pager>
			</pager> -->
			
<!--  			<filter>
				<side-filter filters="filters" title="Filtros" on-filter="changeToList(false)">
					<filter-container label="Usuarios Ativos">
						<md-switch ng-model="model.filters.ativo" aria-label="Filtrar por usuarios ativos"></md-switch>
					</filter-container>
				</side-filter>
			</filter>
			
 		</eits-table>
		</md-content> -->
	
	</html>