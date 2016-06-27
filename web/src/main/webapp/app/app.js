// app.js
var app = angular.module('app', ['ui.router', 'ngResource']);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider

    // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/',
            templateUrl: 'app/home/home.html'
        })

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('login', {
            url: '/login',
            templateUrl: 'app/authorization/login.html'
        })

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('myrecipes', {
            url: '/my-recipes',
            templateUrl: 'app/recipes/my-recipes.html'
        })

        .state('addrecipe', {
            url: '/add-recipe',
            templateUrl: 'app/recipes/add-recipe.html',
            controller: 'AddRecipeController'
        })

        .state('search', {
            url: '/search',
            templateUrl: 'app/search/search.html'
        });





});

app.controller('TabController', function () {
    this.tab = 1;

    this.setTab = function (tabId) {
        this.tab = tabId;
    };

    this.isSet = function (tabId) {
        return this.tab === tabId;
    };
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



