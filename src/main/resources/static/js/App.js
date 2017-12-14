var app = angular.module("myApp", ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/register', {
            templateUrl : "register.html"
        })
});
//
// var app = angular.module("myApp", ['ngRoute']);
//
// app.config(function($routeProvider) {
//     $routeProvider
//         .when('/register', {
//             templateUrl : "register.html"
//         })
//     // .when('/categories', {
//     //         templateUrl : "sideNavbar.html"
//     // });
// });