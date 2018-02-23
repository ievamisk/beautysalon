app.service('scheduleService', function ($http) {
    this.addNewBusinessDay = function (employeeId,workDayStarts,workHours,lunchBreak, callback) {
        this.schedules=[];
        var request = {
            url: '/schedule/add',
            method:'POST',
            params:{
                employee_id: employeeId,
                work_day_start: workDayStarts,
                work_hours: workHours,
                lunch_break: lunchBreak
            }
        };
        $http(request).then(function (response) {
            callback(response);
        }, function (error) {
            callback(error);
        })
    };
    this.getEmployeesBusinessDays = function (employeeId,callback) {
        var self = this;
        var request = {
            url : '/schedule/employee/' + employeeId,
            method : 'GET'
        };
        $http(request).then(function (response) {
            callback(response.data);
        }, function (error) {
            console.log("error",error);
        })
    };

});

app.controller('scheduleController', function ($scope, scheduleService) {
    $scope.schedules=[]; // Array for employee by workdays info
    $scope.scheduleService = scheduleService;
    $scope.date = new Date();// today's date
    $scope.year=new Date().getFullYear(); // This year
    $scope.month=new Date().getMonth()+1; // This month
    $scope.monthDate =[]; // Array for split month data
    $scope.monthOfTheYearName="";// Month full name
    $scope.workDay=1;
    $scope.businessDay=1;
    $scope.initialWorkingHour=10;
    $scope.initialWorkingMinutes=0;
    $scope.lunchHour=10;
    $scope.lunchMinutes=0;
    $scope.businessHours=4;
    $scope.businessMinutes=0;
    $scope.scheduleData=[];// Array for employee by id split data
    $scope.employeeId="";
    $scope.editEmployeeId="";
    $scope.employeeBusinessDayId="";
    $scope.businessDay="";

    $scope.$watch('scheduleService.schedules',function (newVal) {
        $scope.schedules= newVal;
    });

    $scope.getEmployeeById = function (employeeId) {
        $scope.employeeId=employeeId;
        scheduleService.getEmployeesBusinessDays(employeeId,function (data) {
            $scope.schedules=data;
            console.log($scope.schedules);
            $scope.employeeScheduleData(employeeId);

        });

    };

    $scope.employeeScheduleData=function (employeid) {
        for(var i=0; i< $scope.schedules.length; i++)
        {
            var year = new Date($scope.schedules[i].workdayStart).getFullYear();
            var month =new Date($scope.schedules[i].workdayStart).getMonth()+1;
            var day = new Date($scope.schedules[i].workdayStart).getDate();
            var fullDate = year+"-"+month+"-"+day;
            var startHours = new Date($scope.schedules[i].workdayStart).getHours();
            var startMinutes = new Date($scope.schedules[i].workdayStart).getMinutes();
            var tempDate = fullDate+" "+ $scope.schedules[i].workHours;
            var tempEndOfTheDay = new Date(tempDate).getHours();
            var endHours = startHours+tempEndOfTheDay+1;
            tempEndOfTheDay = new Date(tempDate).getMinutes();
            var endMinutes = startMinutes+tempEndOfTheDay;
            if(endMinutes==60){
                endHours=endHours+1;
                endMinutes=0;
            }
            if(endMinutes==0){
                endMinutes='00';
            }
            if(startMinutes==0){
                startMinutes='00';
            }
            var lunchBreak= $scope.schedules[i].lunchBreak;
            var data = {
                id:  $scope.schedules[i].id,
                employeeId: employeid,
                year: year,
                month: month,
                day: day,
                fullDate: fullDate,
                startHour: startHours,
                startMinutes: startMinutes,
                endHours: endHours,
                endMinutes: endMinutes,
                lunchBreak: lunchBreak
            };
            $scope.scheduleData.push(data);
        }
    };


    $scope.calendar = function () {
        $scope.monthDate.length=0;
        var day;
        var yyyymmdd;
        for(var i=0; i<new Date($scope.year,$scope.month,0).getDate(); i++){
            day=i+1;
            yyyymmdd=$scope.year+'-'+$scope.month+'-'+day;
            var dayInfo = {
                "dayOfTheMonth" : i+1,
                "dayOfTheWeek" : $scope.dayOfTheWeek(new Date(yyyymmdd).getDay()),
                "weekDay" : new Date(yyyymmdd).getDay(),
                "fullDate" : yyyymmdd
            };
            $scope.monthDate.push(dayInfo);
        }
        $scope.monthOfTheYearName=$scope.monthOfTheYear(new Date(yyyymmdd).getMonth());
    };

    $scope.daysInTheMonth = function () {
        return new Date($scope.year,$scope.month,0).getDate();
    };

    $scope.dayOfTheWeek=function (day) {
        var weekDay = new Array(7);
        weekDay[0] = "S";
        weekDay[1] = "M";
        weekDay[2] = "T";
        weekDay[3] = "W";
        weekDay[4] = "T";
        weekDay[5] = "F";
        weekDay[6] = "S";
        return weekDay[day];
    };

    $scope.monthOfTheYear=function (month) {
        var monthNames = [ 'January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December' ];
        return monthNames[month];
    };

    $scope.addNewBusinessDay = function () {
       var businessDayDate = $scope.year+'-'+$scope.month+'-'+$scope.businessDay+' '+$scope.initialWorkingHour+":"+$scope.initialWorkingMinutes;
       var lunchHour = $scope.lunchHour+":"+$scope.lunchMinutes;
       var businessHours = $scope.businessHours+":"+$scope.businessMinutes;
       console.log($scope.employeeId);
       scheduleService.addNewBusinessDay($scope.employeeId,businessDayDate,businessHours,lunchHour, function (response){
           console.log("Response from scheduleController", response);
           $scope.scheduleService.getEmployeesBusinessDays($scope.employeeId,function (data) {
               $scope.schedules=data;
           });
       })
    };

    // $scope.editBusinessDay = function () {
    //     console.log(console.log($scope.businessDay))
    // };

});