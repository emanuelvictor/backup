<md-dialog>

    <md-dialog-content>
        <h2>Redefinir senha </h2>
        <form name="changePassword">

            <md-input-container>
                <label>Digite sua senha</label>
                <input type="password" ng-model="usuario.password" required=""/>
            </md-input-container>

            <div layout="row">

                <md-input-container md-no-float>
                    <label>Nova senha</label>
                    <input type="password" ng-model="usuario.newPassword"  name ="newPassword" required=""/>
                    <div ng-messages="changePassword.usuario.newPassword.$error">
                        <div ng-message> As senhas devem ser iguais
                        </div>
                    </div>
                </md-input-container>

                <md-input-container md-no-float>
                    <label>Digite a nova senha novamente</label>
                    <input type="password" ng-model="usuario.newPassword2"  required=""/>
                </md-input-container>

            </div>
            <div layout="row">
                <md-progress-linear ng-if="enviandoEmail"  md-mode="indeterminate"></md-progress-linear>
            </div>
        </form>
        <!-- FORM-->
    </md-dialog-content>
    <!-- DIALOG-CONTENT -->
    <div class="md-actions" layout="row">
        <md-button ng-click="cancel()" class="md-primary">
            Cancelar
        </md-button>
        <md-button ng-click="answer()" class="md-primary">
            Redefinir
        </md-button>
    </div>
    <!-- .MD-ACTIONS -->


</md-dialog>