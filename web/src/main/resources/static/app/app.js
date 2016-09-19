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


app.run(function ($rootScope, Authentication, $state, $cookies, TokenStorage, $http) {
    $rootScope.username = null;
    // var authCookie = $cookies['AUTH-TOKEN'];
    // if (authCookie) {
    //     TokenStorage.store(authCookie);
    //     delete $cookies['AUTH-TOKEN'];
    // }
    // console.log($cookies.get('AUTH-TOKEN'));
    console.log("getting cookieeee");
    $http({
        method: 'GET', url: '/api/user/current', headers: {
            'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')
        }
    }).success(function (user) {
        console.log("success");
        console.log(user);
        if (user.username) {
            $rootScope.authenticated = true;
            $rootScope.username = user.username;
            console.log("if");
            $state.go('home');
            // $rootScope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
        } else {
            // console.log("else");
            console.log("no cuccesss cookie");
            $rootScope.authenticated = false;
            $state.go('login');
        }
    });
});

app.controller('LogOutCtrl', function ($rootScope, $cookies, TokenStorage) {
    $rootScope.logout = function () {
        // console.log("Logout");
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



