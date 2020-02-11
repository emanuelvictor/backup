<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">


<md-button aria-label="Adicionar Aplicativo" class="md-fab md-fab-top-right" ng-click="insertHandler()"
           ng-if="$state.current.name == ADD_STATE">
    <md-icon class="md-icon-save md-icon-lg"></md-icon>
</md-button>

<md-button aria-label="Editar Aplicativo" class="md-fab md-fab-top-right" ng-click="updateHandler()"
           ng-if="$state.current.name == EDIT_STATE">
    <md-icon class="md-icon-save md-icon-lg"></md-icon>
</md-button>

<md-content layout="row" layout-padding layout-align="center start" class="content">

    <div layout-align="center center" style="max-width:800px; min-width: 800px">

        <form name="aplicativo.form">
            <div class="eits-paper-sheet-item">
                <div class="paper-sheet-closed">
                    <content-open class="ng-scope">


                        <div layout="row" layout-margin layout-align="center center">
                            <div flex="20">
                                <div class="icon">
                                    <span class="md-caption">{{ $state.current.name == 'ADD_STATE' ? 'Adicionar' : 'Alterar' }}<br>ícone</span>
                                </div>
                            </div>
                            <div flex="80">
                                <md-input-container class="md-default-theme">
                                    <label>Nome do aplicativo</label>
                                    <input type="text" name="nome" ng-model="aplicativo.entity.nome" md-maxlength="144"
                                           required>

                                    <div ng-messages="aplicativo.form.nome.$error">
                                        <div ng-message="required">Campo nome é obrigatório</div>
                                    </div>
                                </md-input-container>
                            </div>

                        </div>
                    </content-open>
                </div>
            </div>

            <eits-paper-sheet id="informacoes" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()">
                <div>
                    <content-closed>
                        <h4>Informações</h4>
                        <md-divider></md-divider>
                    </content-closed>

                    <content-opened>
                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <md-switch ng-model="aplicativo.entity.perfisDinamicos" aria-label="Perfis dinamicos">Perfis
                                Dinâmicos
                            </md-switch>
                        </md-input-container>

                        <md-input-container>
                            <label>Endereço</label>
                            <input type="url" name="endereco" ng-model="aplicativo.entity.endereco" required>
                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Código</label>
                            <input type="text" name="codigo" ng-model="aplicativo.entity.codigo">
                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Refresh token</label>
                            <input type="text" name="refreshToken" ng-model="aplicativo.entity.refreshToken">
                        </md-input-container>

                        <md-input-container ng-if="$state.current.name == EDIT_STATE">
                            <label>Versão</label>
                            <input type="text" name="versao" ng-model="aplicativo.entity.versao" md-maxlength="144"
                                   required>
                        </md-input-container>

                        <md-input-container>
                            <label>Descrição</label>
                            <input type="text" name="descricao" ng-model="aplicativo.entity.descricao">
                        </md-input-container>

                        <md-input-container>
                            <md-select ng-model="aplicativo.entity.versaoEstavel" placeholder="Versão estável">
                                <!--<md-option value="">Versão estável</md-option>-->
                                <md-option value="1">Versão 1</md-option>
                                <md-option value="2">Versão 2</md-option>
                            </md-select>
                        </md-input-container>

                    </content-opened>
                </div>
            </eits-paper-sheet>
        </form>

        <eits-paper-sheet id="aplicativos" on-close="onCloseEventHandler()" on-open="onOpenEventHandler()"
                          ng-if="$state.current.name == EDIT_STATE">
            <content-closed>
                <h4>Perfis de Acesso</h4>
            </content-closed>

            <content-opened>
                <md-content>


                </md-content>


                <md-button aria-label="Adicionar Aplicativo" class="md-fab" ng-click="insertHandler()"
                           ng-if="$state.current.name == ADD_STATE">
                    <md-icon class="md-icon-save md-icon-lg"></md-icon>
                </md-button>

            </content-opened>
        </eits-paper-sheet>

    </div>
</md-content>
</html>