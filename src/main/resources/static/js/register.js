app.service('registerService', function($http) {
    this.sendRequest = function(firstName, lastName, email, phoneNumber, password, confirmationToken, callback) {
        var request = {
            url: '/register/',
            method: 'POST',
            params: {
                first_name: firstName,
                last_name: lastName,
                email: email,
                phone_number: phoneNumber,
                password: password,
                role_id: 1, // default role: user
                confirmation_token: confirmationToken
            }

        };

        $http(request).then(function (response) {
            console.log('success', response);
            callback(response);
        }, function(error) {
            console.log('error', error);
            callback(error);
        })
    }
});

app.controller('registerController', function ($scope, registerService) {
    $scope.firstName = '';
    $scope.lastName = '';
    $scope.email = '';
    $scope.phone = '';
    $scope.password = '';
    $scope.confirmPassword = '';

    $scope.filled = false;

    $scope.onChange = function (e) {
        $scope.filled = $scope.firstName !== '' && $scope.lastName !== '' && $scope.email !== '' && $scope.phone !== '' && $scope.password !== '' && $scope.confirmPassword !== '';
    };

    $scope.register = function () {
        if($scope.password === $scope.confirmPassword){
            registerService.sendRequest($scope.firstName, $scope.lastName, $scope.email, $scope.phone, $scope.password, function (response) {
                console.log('from register controller', response);
            })
        }
    };
});