// app.js
var app = angular.module('app', ['ui.router', 'ngResource', 'ngCookies']);

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
}).config(function($httpProvider, $stateProvider, $urlRouterProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');

    $urlRouterProvider.otherwise('/dashboard');

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
            controller: 'SearchCtrl',
            templateUrl: 'app/search/search.html',
            onEnter: function($rootScope){
                $rootScope.$broadcast('tabChange', 4);
            }
        });

});


app.run(function ($rootScope, Authentication, $state, $cookies, TokenStorage, $http) {
    $rootScope.username = null;

    $http({
        method: 'GET', url: '/api/user/current', headers: {
            'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')
        }
    }).success(function (user) {

        if (user.username) {
            $rootScope.authenticated = true;
            $rootScope.username = user.username;
            
            $state.go('home.dashboard');
        } else {
            $rootScope.authenticated = false;
            $state.go('login');
        }
    });
});

app.controller('LogOutCtrl', function ($rootScope, $cookies, TokenStorage) {
    $rootScope.logout = function () {
        // Just clear the local storage
        TokenStorage.clear();
        $rootScope.authenticated = false;
        $rootScope.username = null;
        $cookies.remove('AUTH-TOKEN');
    };
});

app.factory('Authentication', function ($resource) {
    return $resource('/api', {}, {
        getSocialDetails: {
            method: 'GET',
            url: '/api/facebook/details'
        },

        getCurrent:{
            method: 'GET',
            url: '/api/user/current'
        }
    })
});



