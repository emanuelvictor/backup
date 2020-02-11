angular.module('App')
.controller('FunctionController', function($scope, $rootScope, $mdDialog, $mdToast, Function) {

  
  $scope.edit = function(functionn){
    $scope.editFunction = angular.copy(functionn);
  };
  
  $scope.clear = function(){
    $scope.editFunction = {name:null};
  };
  
  $scope.clear();
  
  $scope.select = function(functionn){
	$mdDialog.hide(functionn);
  };

  $scope.destroy = function(functionn){
    Function.remove({id:functionn.id},function(){
    	$scope.clear();
    	$scope.init();
    });
  };

  $scope.save = function(functionn){
    Function.save(functionn, function(data){
    	$scope.editFunction = {};
        $scope.init();
    });
  };

  $scope.search = function(functionn){
    if(functionn.name){
      
    }else{
      $scope.clear();
      $scope.init();
    };
  };
    

  $scope.init = function(){
	  Function.getAll(function(data){
		  $scope.functions = data;  
	  });
  };

});
