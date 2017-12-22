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
            templateUrl : "home.html",
        })
        // .when('/categories', {
        //         templateUrl : "sideNavbar.html"
        // });
});
// app.run(['$rootScope','$cookieStore','$http', function ($rootScope,$cookieStore,$http) {
//
//     var token = $rootScope.user.token;
//     if (token){
//         $http.defaults.headers.common['Authorization'] = token;
//     }
//
// }]);

