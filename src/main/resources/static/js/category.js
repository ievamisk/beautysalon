app.service('categoryService', function ($http) {
    this.categories = [];

    this.getListRequest = function () {
        var self = this;
        var request = {
            url: '/category/',
            method: 'GET'
        };
        $http(request).then(function (response) {
            self.categories = response.data;
        }, function(error) {
            console.log('error', error);
        })
    };

    this.addNewCategory = function (category, callback) {
        var request = {
            url: '/category/add',
            method: 'POST',
            params: {
                category: category
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.deleteCategory = function (category, callback) {
        console.log(category);
        var request = {
            url: '/category/'+ category,
            method: 'DELETE'
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.editCategory = function (id, category, callback) {
        var request = {
            url: '/category/' + id,
            method: 'PUT',
            params: {
                category: category
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };
});

app.controller('categoryController', function ($scope, categoryService) {
    $scope.categories = [];
    $scope.service = categoryService;

    $scope.editCategoryId = '';
    $scope.editCategoryName = '';

    $scope.$watch('service.categories',function (newVal) {
        $scope.categories = newVal;
    });

    $scope.onInit = function () {
        categoryService.getListRequest();
    };

    $scope.addNewCategory = function () {
        if($scope.category !== ""){
            categoryService.addNewCategory($scope.category, function (response) {
                console.log('from category controller', response);
                $scope.service.getListRequest();
            });
        }
    };

    $scope.deleteCategory = function (event) {
        var id = event.target.getAttribute('data-category');
        console.log(event.target);
        categoryService.deleteCategory(id, function (response) {
            console.log("deleted", response);
            categoryService.getListRequest();
        });
    };

    $scope.setupEditCategory = function (id) {
        $scope.editCategoryId = id;
    };

    $scope.editCategory = function () {
        categoryService.editCategory($scope.editCategoryId, $scope.editCategoryName, function (response) {
            console.log("updated", response);
            categoryService.getListRequest();
        })
    }
});
