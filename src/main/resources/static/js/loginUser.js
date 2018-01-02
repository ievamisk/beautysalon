app.service("LoginService",function ($http) {
    this.sendRequest= function (email,password,callback) {
        var request = {
            url : "/login/",
            method : "POST",
            params : {
                email : email,
                password : password
            }
        };
        $http(request).then(function (response) {
            console.log("succes", response);
            callback(response);
        },function (error) {
            console.log("error", error);
        })

    }
});
app.controller('LoginController', ['$http','$scope','LoginService','AuthService','$rootScope', '$location' ,function($http, $scope, LoginService, AuthService, $rootScope, $location) {
    // method for login
    $scope.email='';
    $scope.password='';
    $scope.filled =false;

    $scope.onChange = function (e) {
        $scope.filled = $scope.email !== '' && $scope.password !== '';
    };

    $scope.login = function () {
        LoginService.sendRequest($scope.email, $scope.password, function (response) {
            $scope.password=null;
            if(response.data.token){
                $scope.token = response.data.token;
                $scope.message ='';
                $http.defaults.headers.common['Authorization'] = 'Bearer' + response.data.token;
                AuthService.user = response.data.user;
                $rootScope.$broadcast('LoginSuccessful');
                $location.path('/home');

            }else {
                $scope.message='Authetication Failed !';
            }
        })
    };
}]);
