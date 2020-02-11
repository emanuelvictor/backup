<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<md-content layout-padding>

    <div layout-align="center center" style="min-width: 800px; margin: 0 auto;">

        <eits-paper-sheet id="aplicativo" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
            <content-closed>

            </content-closed>

            <content-opened>
                <h3></h3>
            </content-opened>
        </eits-paper-sheet>

        <eits-paper-sheet id="info" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
            <content-closed>
                <span>Informações</span>
            </content-closed>

            <content-opened height="150">
                <div layout layout-sm="column">
                    <md-input-container flex>
                        <label>Descrição</label>
                        <input ng-model="aplicativo.nome">
                    </md-input-container>
                </div>
                <div layout layout-sm="column">
                    <md-select placeholder="Versão estável" ng-model="aplicativo.versaoEstavel">
                        <md-option value="1">1.0.1</md-option>
                        <md-option value="2">1.0.2</md-option>
                    </md-select>
                </div>
            </content-opened>
        </eits-paper-sheet>

        <eits-paper-sheet id="perfis-acesso" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
            <content-closed>
                <span>Título preview</span>
            </content-closed>

            <content-opened height="150">
                <h3>Conteúdo</h3>
                <span>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam tortor diam, semper in magna in, ultrices gravida ligula. Integer malesuada lacus id auctor aliquet. Sed fringilla euismod risus sit amet consequat. Praesent sed diam hendrerit enim aliquet lacinia non a ipsum. Etiam ac consectetur erat.</span>
            </content-opened>
        </eits-paper-sheet>

    </div>

</md-content>
</html>