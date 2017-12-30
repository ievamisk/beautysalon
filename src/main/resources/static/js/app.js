var app = angular.module("myApp", ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/register', {
            templateUrl : "register.html"
        })
        .when('/categories', {
            templateUrl: "categories.html"
        })
        .when('/procedures', {
            templateUrl: "procedures.html"
        })
});