<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-toolbar>
    <h2 class="md-toolbar-tools md-toolbar-tools-bottom" style="margin-left: 85px">
        <span class="md-flex">Aplicativos</span>
    </h2>
</md-toolbar>

<md-button aria-label="Novo Aplicativo" class="md-fab md-fab-top-left" ng-click="$state.go( ADD_STATE )" style="left:10px">
    <md-icon class="md-icon-add md-icon-lg"></md-icon>
    <div class="md-ripple-container"></div>
</md-button>

<md-content layout="column" flex class="md-default list">

    <eits-table id="table" content="aplicativo.page.content" multi-selection="true"
                on-item-click="$state.go( DETAIL_STATE, {id:item.id} )">
        <columns>
            <table-column width="40" sortable="false">
                <column-template>
                    icone
                </column-template>
            </table-column>
            <table-column header="Nome" field="nome" sortable="true"/>
            <table-column header="Status" field="ativo" sortable="false"/>
            <table-column width="40" sortable="false">
                <column-template>
                    dropdown
                </column-template>
            </table-column>
        </columns>

        <pager>
            <infinity-scroll-pager on-scroll-end="changeToList(true)"></infinity-scroll-pager>
        </pager>

            <filter>
                <side-filter filters="filters" title="Filtros" on-filter="changeToList(false)">

                    <filter-container label="Aplicativos Ativos">
                        <md-switch ng-model="aplicativo.filters.ativo" aria-label="Filtrar por aplicativos ativos"></md-switch>
                    </filter-container>
                </side-filter>
            </filter>

        <!--<filter-container label="Aplicativos Ativos">
            <md-switch ng-model="aplicativo.filters.ativo" aria-label="Filtrar por aplicativos ativos"></md-switch>
        </filter-container>
        </side-filter>
        </filter>-->

    </eits-table>


</md-content>

</html>