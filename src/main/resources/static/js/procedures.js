app.service('proceduresService', function ($http) {
    this.procedures = [];

    this.getAllProcedures = function () {
        var self = this;
        var request = {
            url: '/procedures/',
            method: 'GET'
        };
        $http(request).then(function (response) {
            self.procedures = response.data;
        }, function(error) {
            console.log('error', error);
        })
    };

    this.addProcedure = function (price, duration, subcategoryId, callback) {
        var request = {
            url: '/procedures/add',
            method: 'POST',
            params: {
                price: price,
                duration: duration,
                subcategory_id: subcategoryId
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.deleteProcedure = function (procedureId, callback) {
        console.log(procedure);
        var request = {
            url: '/procedures/'+ procedureId,
            method: 'DELETE'
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.editProcedure = function (procedureId, price, duration, subcategoryId, callback) {
        var request = {
            url: '/procedures/'+ subcategoryId,
            method: 'PUT',
            params: {
                price: price,
                duration: duration,
                subcategory_id: subcategoryId
            }

        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    }
});

app.controller('proceduresController', function ($scope, proceduresService, subcategoryService) {
    $scope.subcategories = [];
    $scope.service = subcategoryService;
    $scope.categoryId = '';
    $scope.subcategoryId = '';
    $scope.subcategoryName = '';

    $scope.addNewProcedure = function () {
        console.log($scope.categoryId, $scope.subcategoryName);

        subcategoryService.addNewProcedure($scope.subcategoryName, $scope.categoryId, function (response) {
            console.log('from subcategory controller', response);
            categoryService.getListRequest();
        });
    };

    $scope.deleteSubcategory = function (event) {
        var id = event.target.getAttribute('data-subcategory');
        subcategoryService.deleteSubcategory(id, function (response) {
            console.log("deleted", response);
            categoryService.getListRequest();
        });
    };

    $scope.editSubcategory = function () {
        console.log($scope.subcategoryId, $scope.subcategoryName);
        subcategoryService.editSubcategory($scope.subcategoryId, $scope.subcategoryName, function (response) {
            console.log($scope.subcategoryName, $scope.subcategoryId);
            console.log("updated", response);
            categoryService.getListRequest();
        })
    }
});