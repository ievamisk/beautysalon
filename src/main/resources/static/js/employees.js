app.service('employeesService', function ($http) {
    this.employees = [];

    this.getListRequest = function () {
        var self = this;
        var request = {
            url: '/employee/',
            method: 'GET'
        };
        $http(request).then(function (response) {
            self.employees = response.data;
            console.log(response.data);

        }, function(error) {
            console.log('error', error);
        })
    };

    this.addNewEmployee = function (name,lastName,description,callback) {
        var request = {
            url: '/employee/add',
            method : 'POST',
            params : {
                name : name,
                last_name : lastName,
                description : description
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function (error) {
            callback(error);
        })
    };
    this.deleteEmployee = function (employee, callback) {
        console.log(employee);
        var request = {
            url : '/employee/'+ employee,
            method: 'DELETE'
        };
        $http(request).then(function (response) {
            callback(response);
        },function (error) {
            callback(error);
        })
    };

    this.editEmployee = function (id, name, lastName, description, callback) {
        var request = {
            url : '/employee/'+id,
            method: 'PUT',
            params :{
                name: name,
                last_name: lastName,
                description: description
            }
        };
        $http(request).then(function (response) {
            callback(response);
        },function (error) {
            callback(error);
        })
    };
});

app.controller('employeesController',function ($scope,employeesService) {
    $scope.employees = [];
    $scope.service = employeesService;

    // variables for edit function
    $scope.editEmployeeId="";
    $scope.editEmployeeName="LOPAS";
    $scope.editEmployeeLastName="";
    $scope.editEmployeeDescription="";


    $scope.$watch('service.employees',function (newVal) {
        $scope.employees = newVal;
    });

    $scope.onInit = function () {
        employeesService.getListRequest();
    };

    $scope.addNewEmployee = function () {
        if($scope.name !=="" && $scope.lastName!=="" && $scope.description!==""){
            employeesService.addNewEmployee($scope.name, $scope.lastName, $scope.description, function (response) {
                console.log("Response from employee controller", response);
                $scope.service.getListRequest();
            })
        }
    };

    $scope.deleteEmployee = function (event) {
        var id= event.target.getAttribute('data-category');
        employeesService.deleteEmployee(id, function (response) {
            console.log("delete", response);
            employeesService.getListRequest();
        });
    };

    $scope.setupEditEmployee = function (id, firstName,lastName, description) {
        $scope.editEmployeeId=id;
        $scope.editEmployeeName=firstName;
        $scope.editEmployeeLastName=lastName;
        $scope.editEmployeeDescription=description;
    };
    
    $scope.editEmployee = function () {
        employeesService.editEmployee($scope.editEmployeeId, $scope.editEmployeeName,$scope.editEmployeeLastName, $scope.editEmployeeDescription,
            function (response) {
            employeesService.getListRequest();
        })
    }



});