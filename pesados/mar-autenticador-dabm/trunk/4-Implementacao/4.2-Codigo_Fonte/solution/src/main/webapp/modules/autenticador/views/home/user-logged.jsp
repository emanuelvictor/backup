<div>
  <div style="min-width: 380px" layout-align="start start" layout="column">
    <div layout="row" layout-padding>
      <div layout-padding>
        <upload-image text="foto"
                      color="#e5e5e5"
                      min-width="120"
                      min-height="120"
                      source="userLogged.foto"
                      max-size="1"
                      formats="['image/jpeg', 'image/jpg', 'image/png']"
                      on-success="onUploadFotoSuccess(input)"
                      on-error="onUploadFotoError(msg)"
                      on-delete="onUploadFotoDelete(event)"
                      is-circle="true">
          <md-icon class="md-icon-account-circle md-icon-5x"></md-icon>
        </upload-image>
      </div>
      <div layout-padding>
        <p><b>{{userLogged.nomeCompleto}}</b></p>
        <p>{{userLogged.email}}</p>
        <md-button style="margin: 0; box-shadow: none" class="md-raised md-primary" ng-click="$state.go('inicio.account')">Minha conta</md-button>
      </div>
    </div>

    <div layout="row" layout-padding layout-fill layout-align="end end" style="border-top: 1px solid #e5e5e5">

      <md-button ng-href="./logout" style="background-color: rgba(158,158,158,0.2)">Sair</md-button>
    </div>
  </div>
</div>