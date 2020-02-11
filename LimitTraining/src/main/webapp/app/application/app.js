angular
    .module('App', ['ngMaterial', /*'ngCookies',*/ /*'ngRoute',*/ 'ui.router'])
    .config(function ($mdThemingProvider, $stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('inicio'); //todo

        $stateProvider.state('inicio', {
            url: '/inicio',
            templateUrl: './presentation/view/inicio.html',
            controller: 'InicioCtrl'
        })

            .state('professores', {
                url: '/professores',
                templateUrl: './presentation/view/admin/professores/find.html',
                controller: 'FindProfessorCtrl'
            }).state('professoresnew', {
            url: '/professores/new',
            templateUrl: './presentation/view/admin/professores/new.html',
            controller: 'NewProfessorCtrl'
        }).state('professoresdetail', {
            url: '/professores/:id',
            templateUrl: './presentation/view/admin/professores/detail.html',
            controller: 'DetailProfessorCtrl'
        })

            .state('alunos', {
                url: '/alunos',
                templateUrl: './presentation/view/professor/alunos/find.html',
                controller: 'FindAlunoCtrl'
            }).state('alunosnew', {
            url: '/alunos/new',
            templateUrl: './presentation/view/professor/alunos/new.html',
            controller: 'NewAlunoCtrl'
        }).state('alunosdetail', {
            url: '/alunos/:id',
            templateUrl: './presentation/view/professor/alunos/detail.html',
            controller: 'DetailAlunoCtrl'

        }).state('autenticacao', {
            url: '/autenticacao',
            templateUrl: './presentation/view/autenticacao/login.html',
            controller: 'AutenticacaoCtrl'
        });

        $mdThemingProvider.theme('default')
            .primaryPalette('indigo')
            .accentPalette('indigo');

    }).run(function ($rootScope, $mdDialog, $location, /*$cookieStore,*/ $state, $stateParams, $http, $mdToast) {

    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    $rootScope.toast = $mdToast.simple()
        .content('Erro ao realizar tranzação')
        .action('OK')
        .highlightAction(false)
        .position('top right');

    usuarioService.getCurrentUser({
        callback: function (result) {
            $rootScope.usuario = result;
        },
        errorHandler: function (message, exception) {
            $scope.message = {type: "error", text: message};
        }
    })


})


    .directive('scrollPosition', function () {
        return {
            scope: {
                scroll: '=scrollPosition'
            },
            link: function (scope, element, attrs) {
                var elemento = element;
                var handler = function () {
                    scope.scroll = elemento.scrollTop();
                };
                elemento.on('scroll', scope.$apply.bind(scope, handler));
                handler();
            }
        };
    });