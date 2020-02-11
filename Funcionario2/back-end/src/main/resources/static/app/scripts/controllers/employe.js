'use strict'

angular.module('App')
.controller('NewCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast, Employe) {
	
  $scope.newEmploye = {}
  $rootScope.nameButtonEmploye = 'Funcionários';

  $rootScope.handler = function(){
    $state.go('employe');
  };

  //TODO
  $scope.selectFunction = function() {
    $mdDialog.show({
      controller: 'FunctionController',
      templateUrl: 'app/view/function/function.tmpl.html',
    })
    .then(function(answer) {
      $scope.newEmploye.function = answer;
    });
  };
  
  $scope.save = function(){
   Employe.save($scope.newEmploye, function(data){
	   $state.go('employe');
   });
  };
                            
})
.controller('DetailCtrl', function($scope, $rootScope, $mdDialog, $state, $mdToast, Employe) {

  //Pega o funcionário pelo id
  Employe.get({id: $state.params.id}, function(data) {
	$scope.editEmploye = data;
	$scope.editEmploye.password = null;
  });
  
  $rootScope.nameButtonEmploye = 'Funcionários';

  $rootScope.handler = function(){
    $state.go('employe');
  };

//TODO
  $scope.selectFunction = function() {
    $mdDialog.show({
      controller: 'FunctionController',
      templateUrl: 'app/view/function/function.tmpl.html',
    })
    .then(function(answer) {
      $scope.editEmploye.function = answer;
    });
  };
  
  $scope.save = function(){
   Employe.save($scope.editEmploye, function(data){
	   $state.go('employe');
   });
  };
  
  $scope.destroy = function(employe){
    $mdDialog.show({
      controller: 'ConfirmCtrl',
      templateUrl: 'app/view/employe/confirm.tmpl.html'
    })
    .then(function(answer) {
      if(answer){
         Employe.remove({id: $state.params.id}, function(){
        	 $state.go('employe');
         });
      }
    });
  };
	
})
.controller('ConfirmCtrl', function($scope,$mdDialog) {

  $scope.yes = function(){
    $mdDialog.hide('yes');
  };
  
  $scope.no = function(){
    $mdDialog.hide();
  };

})
.controller('FindCtrl', function($scope, $rootScope, $state, Employe) {
  $rootScope.nameButtonEmploye = 'Novo funcionário';

  $scope.init = function(){
    $scope.employes = Employe.getAll();
	  
  };

  $rootScope.handler = function(){
    $state.go('new');
  };

  $scope.edit = function(employe){
    $state.go('detail',{id:employe.id});
  };  
	
});
