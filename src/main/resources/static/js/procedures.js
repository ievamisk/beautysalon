app.service('proceduresService', function ($http) {
    this.procedures = [];
    this.editProcedureId = '';

    this.getEmployeeProcedures = function (employeeId, callback) {
        var request = {
            url: '/procedures/employee/' + employeeId,
            method: 'GET'
        };
        $http(request).then(function (response) {
            callback(response.data);
        }, function(error) {
            console.log('error', error);
            callback([]);
        })
    };

    this.addProcedure = function (price, duration, employeeId, subcategoryId, callback) {
        var request = {
            url: '/procedures/add',
            method: 'POST',
            params: {
                price: price,
                duration: duration,
                employee_id: employeeId,
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

    this.editProcedure = function (procedureId, price, duration, callback) {
        var request = {
            url: '/procedures/'+ procedureId,
            method: 'PUT',
            params: {
                price: price,
                duration: duration
            }

        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    }
});

app.controller('proceduresController', function ($scope, proceduresService, categoryService, employeesService) {
    $scope.procedures = [];
    $scope.categories = [];
    $scope.service = proceduresService;
    $scope.cService = categoryService;
    $scope.eService = employeesService;
    $scope.procedureId = '';
    $scope.employeeId = '';
    $scope.editProcedurePrice = '';
    $scope.editProcedureDuration = '';
    $scope.editProcedureId = '';
    $scope.procedure = '';

    $scope.$watch('service.editProcedureId', function (newVal) {
       $scope.editProcedureId = newVal;
    });

    $scope.$watch('cService.categories', function(newVal) {
        $scope.categories = newVal;
    });

    $scope.$watch('service.procedures',function (newVal) {
        $scope.procedures = newVal;
    });

    $scope.$watch('eService.procedures', function (newVal) {
        $scope.procedures = newVal;
    });

    $scope.categoriesInit = function() {
        categoryService.getListRequest();
    };

    $scope.addProcedure = function () {
        console.log($scope.procedure);
        if($scope.price !=="" && $scope.duration!=="" && $scope.employeeId!==""  && $scope.procedure!=="") {
            proceduresService.addProcedure($scope.price, $scope.duration, $scope.employeeId, $scope.procedure, function (response) {
                $scope.eService.getListRequest();
            });
        }
    };

    $scope.deleteProcedure = function (event) {
        var id = event.target.getAttribute('data-procedure');
        proceduresService.deleteProcedure(id, function (response) {
            console.log("deleted", response);
        });
    };

    $scope.setupEditProcedure = function (id) {
        $scope.editProcedureId = id;
        $scope.service.editProcedureId = id;
        console.log($scope.editProcedureId,id);
    };

    $scope.editProcedure = function () {
        console.log($scope.editProcedureId);
        proceduresService.editProcedure($scope.editProcedureId, $scope.editProcedurePrice, $scope.editProcedureDuration, function (response) {
            console.log("updated", response);
            $scope.eService.getListRequest();

        })
    };

    $scope.getEmployeeProcedures = function (employeeId) {
        $scope.employeeId = employeeId;
        proceduresService.getEmployeeProcedures(employeeId, function (data) {
            console.log(data);
            $scope.procedures = data;
        });

    }
});