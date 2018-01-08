package com.beautysalon.BeautySalon.Controllers;

import com.beautysalon.BeautySalon.Entities.BeautyProcedure;
import com.beautysalon.BeautySalon.Entities.Employee;
import com.beautysalon.BeautySalon.Entities.Workday;
import com.beautysalon.BeautySalon.Repositories.EmployeeRepository;
import com.beautysalon.BeautySalon.Repositories.WorkDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping (value = "/schedule")
public class workDayController {

    @Autowired
    WorkDayRepository workDayRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    Workday newWorkday(
            @RequestParam (value = "employee_id") long employeeId,
            @RequestParam (value = "work_day_start") String startOfTheBusinessDay,
            @RequestParam (value = "work_hours") String businessHours,
            @RequestParam (value = "lunch_break") String lunchBreak
    ){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = simpleDateFormat.parse(startOfTheBusinessDay);
            String jStartofTheBusinessWeek = simpleDateFormat.format(date);
            simpleDateFormat = new SimpleDateFormat("HH:mm");
            date = simpleDateFormat.parse(businessHours);
            String jBusinessHours=simpleDateFormat.format(date);
            date = simpleDateFormat.parse(lunchBreak);
            String jLunchBreak = simpleDateFormat.format(date);
            Workday newWorkday = new Workday(employeeRepository.findById(employeeId),jStartofTheBusinessWeek,jBusinessHours,jLunchBreak);
            workDayRepository.save(newWorkday);
            return newWorkday;
        } catch (ParseException e){
            Workday newWorkday = newWorkday(-1,"","","");
            e.printStackTrace();
            return newWorkday;
        }
    }

    @RequestMapping(value = "/employee/{employee_id}", method = RequestMethod.GET)
    public @ResponseBody List<Workday> getWorkdays(@PathVariable(value = "employee_id") long employeeId){
        return workDayRepository.findAllByEmployeeId(employeeId);
    }
}
