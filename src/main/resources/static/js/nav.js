app.controller('NavController',['$http','$scope','AuthService','$location','$rootScope',function ($http, $scope, AuthService, $location, $rootScope) {
    $scope.$on("LoginSuccessful",function () {
        $scope.user =AuthService.user;
    });
    $scope.$on("LogoutSuccessful",function () {
        $scope.user=null;
    });
    $scope.logout = function () {
        AuthService.user = null;
        $rootScope.$broadcast('LogoutSuccessful');
        $location.path('/login');
    }
}]);