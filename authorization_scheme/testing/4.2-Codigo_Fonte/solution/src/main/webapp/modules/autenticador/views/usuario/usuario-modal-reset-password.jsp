<md-dialog>

    <md-dialog-content>
        <h2>Redefinir senha </h2>
        <form name="changePassword">

            <div layout="row" layout-margin>

                <md-input-container md-no-float>
                    <label>Nova senha</label>
                    <input type="password" ng-model="newPassword"  name ="newPassword" required=""/>                  
                </md-input-container>

                <md-input-container md-no-float>
                    <label>Digite a nova senha novamente</label>
                    <input type="password" ng-model="confirmPassword" required=""/>
                </md-input-container>

            </div>
            <div layout="row" ng-if="newPassword!=confirmPassword">
                <div ng-if="!enviandoEmail"> As senhas devem ser iguais <span style="color:rgb(232, 165, 165);">*</span></div>
            </div>
            <div layout="row" ng-if="enviandoEmail">
                <div> Redefinindo ...  <!--ver qual é a melhor frase --></div>
            </div>
            <div layout="row">
                <md-progress-linear ng-if="enviandoEmail"  md-mode="indeterminate"></md-progress-linear>
            </div>

        </form>
       
    </md-dialog-content>
    
    <div class="md-actions" layout="row">
        <md-button ng-click="hide('Cancelado')" class="md-primary">
            Cancelar
        </md-button>
        <md-button ng-if="newPassword&&newPassword==confirmPassword" ng-click="redefinir( newPassword )" class="md-primary">
            Redefinir
        </md-button>
    </div>

</md-dialog>