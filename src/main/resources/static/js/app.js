var app = angular.module("myApp", ['ngRoute','ngCookies']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/register', {
            templateUrl : "register.html"
        })
        .when('/login', {
            templateUrl : "loginUser.html"
            // controller: "LoginController"
        })
        .when('/home',{
            templateUrl : "home.html"
        })
        .when('/categories', {
            templateUrl: "categories.html"
        })
        .when('/employee',{
            templateUrl: "employees.html"
        })
});
