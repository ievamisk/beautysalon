var app = angular.module("myApp", ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : "/"
        })
        .when('/register', {
            templateUrl : "/registerView"
        });

    $routeProvider.html5Mode(true);
});