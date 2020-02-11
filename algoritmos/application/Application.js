var app = angular.module('InteligenceArtificialStudyApp',['ngMaterial', 'ngMessages','ngMdIcons', 'ngRoute' /*,  'material.svgAssetsCache'*/])
  .config(function($mdIconProvider, $routeProvider) {
    $routeProvider
        .when('/genetic', {
            controller : 'GeneticCtrl',
            templateUrl : './algorithms/genetic/application/genetic.html'
        }).otherwise({
            redirectTo : '/'
        });

    $mdIconProvider
      .defaultIconSet('img/icons/sets/core-icons.svg', 24);
  })            //TODO
  .controller('DemoBasicCtrl', function DemoCtrl($mdDialog, $mdSidenav, $log) {

    this.toggleRight = function(navID){
       $mdSidenav('left')
         .toggle()
         .then(function () {
           $log.debug("toggle " + navID + " is done");
         });
    }
                //TODO
  }).controller('RightCtrl', function RightCtrl() {


  });