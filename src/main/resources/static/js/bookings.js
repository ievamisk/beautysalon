app.service('bookingsDataService', function ($http) {
    this.bookings = [];
    this.employees = [];

    this.getBookings = function () {
        var self = this;
        var request = {
            url: '/bookings/',
            method: 'GET'
        };
        $http(request).then(function (response) {
            self.categories = response.data;
        }, function(error) {
            console.log('error', error);
        })
    };

    this.addNewBooking = function (startTime, procedureId, userId, callback) {
        var request = {
            url: '/bookings/add',
            method: 'POST',
            params: {
                start_time: startTime,
                beauty_procedure_id: procedureId,
                user_id: userId
            }
        };

        console.log(request);

        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.deleteBooking = function (booking, callback) {
        var request = {
            url: '/bookings/'+ booking,
            method: 'DELETE'
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.editCategory = function (booking, startTime, procedureId, userId, callback) {
        var request = {
            url: '/bookings/' + booking,
            method: 'PUT',
            params: {
                start_time: startTime,
                beauty_procedure_id: procedureId,
                user_id: userId
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function(error) {
            callback(error);
        })
    };

    this.getEmployeesByProcedure = function(id, callback) {
        if (id !== '') {
            var self = this;

            var request = {
                url: '/procedures/employee/procedure/' + id,
                method: 'GET'
            };
            $http(request).then(function (response) {
                callback(response.data);
            }, function (error) {
                console.log('error', error);
            })
        }
    }
});

app.controller('bookingsController', function ($scope, subcategoryService, bookingService, bookingsDataService) {
    $scope.bookings = [];
    $scope.employees = [];
    $scope.service = bookingsDataService;
    $scope.subService = bookingService;

    $scope.subCategoryName = '';
    $scope.subCategoryService = subcategoryService;

    $scope.subcategoryId = '';

    $scope.startTime = '10:00';
    $scope.endTime = '18:00';
    $scope.date = '';

    $scope.times = [];
    $scope.employee = '';

    $scope.time = '';
    $scope.fullDate = '';

    $scope.$watch('subService.procedureId', function(newVal, oldVal) {
        $scope.subcategoryId = newVal;
        bookingsDataService.getEmployeesByProcedure(newVal, function(data) {
           $scope.employees = data;
        });
        subcategoryService.getOneSubcategory(newVal, function(data) {
            $scope.subCategoryName = data.subcategory;
        });
    });

    $scope.$watch('service.employees', function(newVal, oldVal) {
        $scope.employees = newVal;
    });

    $scope.onInit = function () {
        bookingsDataService.getBookings();
    };

    $scope.dataChanged = function() {
        console.log($scope.employee, $scope.date);
        console.log($scope.employee != '' && $scope.date != '');

        if ($scope.employee != '' && $scope.date != '') {
            var certainEmployee = $scope.employees.filter(function(elem, index) {
                console.log(elem.id, $scope.employee);
                return elem.id == $scope.employee;
            });

            console.log('pooop', certainEmployee);

            certainEmployee = certainEmployee[0];

            var certainEmployeeProcedure = certainEmployee.beautyProcedures.filter(function(elem, index) {
                return elem.id == $scope.subcategoryId;
            });

            console.log('pooop2', certainEmployeeProcedure);

            certainEmployeeProcedure = certainEmployeeProcedure[0];

            console.log('ce', certainEmployee);
            console.log('cep', certainEmployeeProcedure);

            var time = Date.parse($scope.date + ' ' + $scope.startTime);
            var endTime = Date.parse($scope.date + ' ' + $scope.endTime);

            while(time < endTime) {
                var date = new Date(time);
                $scope.times.push(date.getHours() + ':' + date.getMinutes());
                time += certainEmployeeProcedure.duration * 60 * 1000;
            }
        }
    };

    $scope.selectTime = function(time) {
        $scope.time = time;
        $scope.fullDate = $scope.date + " " + time;
        console.log($scope.fullDate);
    };

    $scope.addNewBooking = function () {
        if($scope.category !== ""){
            bookingsDataService.addNewBooking($scope.fullDate, $scope.subcategoryId, $scope.employee, function (response) {
                console.log('from bookings controller', response);
                $scope.service.getBookings();
            });
        }
    };

});