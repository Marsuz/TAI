// app.js
var app = angular.module('app', ['ui.router', 'ngResource', 'ngCookies']);

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
            templateUrl: 'app/dashboard/dashboard.html',
            controller: 'DashboardCtrl',
            onEnter: function($rootScope){
                $rootScope.$broadcast('tabChange', 1);
            }
        })

        .state('home.myrecipes', {
            url: 'my-recipes',
            templateUrl: 'app/recipes/my-recipes.html',
            controller: 'MyRecipesCtrl',
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

app.run(['$rootScope', '$state', function($rootScope, $state) {

    $rootScope.$on('$stateChangeStart', function(evt, to, params) {
        if (to.redirectTo) {
            evt.preventDefault();
            $state.go(to.redirectTo, params, {location: 'replace'})
        }
    });
}]);



app.factory('TokenStorage', function() {
    var storageKey = 'auth_token';
    return {
        store : function(token) {
            return localStorage.setItem(storageKey, token);
        },
        retrieve : function() {
            return localStorage.getItem(storageKey);
        },
        clear : function() {
            return localStorage.removeItem(storageKey);
        }
    };
}).factory('TokenAuthInterceptor', function($q, $rootScope, TokenStorage) {
    return {
        request: function(config) {
            var authToken = TokenStorage.retrieve();
            if (authToken) {
                config.headers['X-AUTH-TOKEN'] = authToken;
            }
            return config;
        },
        responseError: function(error) {
            if (error.status === 401 || error.status === 403) {
                TokenStorage.clear();
                $rootScope.authenticated = false;
            }
            return $q.reject(error);
        }
    };
}).config(function($httpProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');
});

app.controller('AuthCtrl', function ($scope, $rootScope, $http, $cookies, TokenStorage) {
    $rootScope.authenticated = false;
    $scope.token; // For display purposes only

    $scope.init = function () {
        var authCookie = $cookies['AUTH-TOKEN'];
        if (authCookie) {
            TokenStorage.store(authCookie);
            delete $cookies['AUTH-TOKEN'];
        }
        $http.get('/api/user/current').success(function (user) {
            if (user.username) {
                $rootScope.authenticated = true;
                $scope.username = user.username;

                // For display purposes only
                $scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
            }
        });
    };

    $scope.logout = function () {
        // Just clear the local storage
        TokenStorage.clear();
        $rootScope.authenticated = false;
    };

    $scope.getSocialDetails = function() {
        $http.get('/api/facebook/details').success(function (socialDetails) {
            $scope.socialDetails = socialDetails;
        });
    };
});


