app.service('subcategoryService', function ($http) {
    this.subcategories = [];
    this.categoryId = '';
    this.subcategoryId = '';

    this.getAllSubcategories = function () {
        var self = this;
        var request = {
            url: '/subcategory/',
            method: 'GET'
        };
        $http(request).then(function (response) {
            self.subcategories = response.data;
        }, function(error) {
            console.log('error', error);
        });
    };

    this.addNewSubcategory = function (subcategory, categoryId, callback) {
        var request = {
            url: '/subcategory/add',
            method: 'POST',
            params: {
                subcategory: subcategory,
                category_id: categoryId
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.deleteSubcategory = function (subcategory, callback) {
        console.log(subcategory);
        var request = {
            url: '/subcategory/'+ subcategory,
            method: 'DELETE'
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.editSubcategory = function (subcategoryId, subcategoryName, callback) {
        var request = {
            url: '/subcategory/'+ subcategoryId,
            method: 'PUT',
            params: {
                subcategory: subcategoryName
            }

        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    }
});

app.controller('subcategoryController', function ($scope, subcategoryService, categoryService) {
    $scope.subcategories = [];
    $scope.service = subcategoryService;
    $scope.categoryId = '';
    $scope.subcategoryId = '';
    $scope.subcategoryName = '';

    $scope.setupSubcategory = function (categoryId) {
        subcategoryService.categoryId = categoryId;
        $scope.categoryId = categoryId;
    };

    $scope.setupEditSubcategory = function (subcategoryId) {
        subcategoryService.subcategoryId = subcategoryId;
        $scope.subcategoryId = subcategoryId;
    };

    $scope.$watch('service.subcategories',function (newVal) {
        $scope.subcategories = newVal;
    });

    $scope.$watch('service.categoryId', function (newVal) {
        $scope.categoryId = newVal;
    });

    $scope.$watch('service.subcategoryId', function (newVal) {
        $scope.subcategoryId = newVal;
    });

    $scope.addNewSubcategory = function () {
        console.log($scope.categoryId, $scope.subcategoryName);

        subcategoryService.addNewSubcategory($scope.subcategoryName, $scope.categoryId, function (response) {
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