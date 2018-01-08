package com.beautysalon.BeautySalon.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Workday {

    public Workday(){
    }
    public Workday(Employee employee, String workdayStart, String workHours, String  lunchBreak){
        this.employee = employee;
        this.workdayStart = workdayStart;
        this.workHours = workHours;
        this.lunchBreak = lunchBreak;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "workday_start",columnDefinition = "varchar(20)",nullable = false)
    private String workdayStart;

    public String getWorkdayStart() {
        return workdayStart;
    }

    public void setWorkdayStart(String workdayStart) {
        this.workdayStart = workdayStart;
    }

    @Column(name = "work_hours", columnDefinition = "varchar(10)", nullable = false)
    private String workHours;

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    @Column(name = "lunch_break", columnDefinition = "varchar(10)", nullable = false)
    private String lunchBreak;

    public String getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(String lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    @JsonBackReference
    public Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}
