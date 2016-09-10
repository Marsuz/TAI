// app.js
var app = angular.module('app', ['ui.router', 'ngResource']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider

        .state('home', {
            url: '/',
            templateUrl: 'app/home/home.html',
            controller: 'HomeCtrl',
            redirectTo: 'home.dashboard'
        })

        .state('login', {
            url: '/login',
            templateUrl: 'app/authorization/login.html'
        })

        .state('home.dashboard', {
            url: 'dashboard',
            templateUrl: 'app/home/home2.html',
            onEnter: function($rootScope){
                $rootScope.$broadcast('tabChange', 1);
            }
        })

        .state('home.myrecipes', {
            url: 'my-recipes',
            templateUrl: 'app/recipes/my-recipes.html',
            onEnter: function($rootScope){
                    $rootScope.$broadcast('tabChange', 2);
            }
        })

        .state('home.addrecipe', {
            url: 'add-recipe',
            templateUrl: 'app/recipes/add-recipe.html',
            controller: 'AddRecipeController',
            reload: true,
            cache: false,
            onEnter: function($rootScope){

                $rootScope.$broadcast('tabChange', 3);
            }
        })

        .state('home.search', {
            url: 'search',
            templateUrl: 'app/search/search.html',
            onEnter: function($rootScope){
                $rootScope.$broadcast('tabChange', 4);
            }
        });





});

var data;
var ingredients;

$.getJSON("app/data.json", function(json) {
    console.log(json); // this will show the info it in firebug console
    data = json.posts;

});

$.getJSON("app/ingredients.json", function(json) {
    ingredients = json.ingredients;

});

app.controller('postsController', function($scope) {
    $scope.records = data;
});


app.run(['$rootScope', '$state', function($rootScope, $state) {

    $rootScope.$on('$stateChangeStart', function(evt, to, params) {
        if (to.redirectTo) {
            evt.preventDefault();
            $state.go(to.redirectTo, params, {location: 'replace'})
        }
    });
}]);


