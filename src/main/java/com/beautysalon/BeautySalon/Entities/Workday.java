package com.beautysalon.BeautySalon.Entities;
import javax.persistence.*;
import java.util.Date;


@Entity
public class Workday {

    public Workday(Date workdayStart, Date workHours, Date lunchBreak){
        this.workdayStart = workdayStart;
        this.workHours = workHours;
        this.lunchBreak = lunchBreak;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "workday_start",columnDefinition = "date",nullable = false)
    private Date workdayStart;

    public Date getWorkdayStart() {
        return workdayStart;
    }

    public void setWorkdayStart(Date workdayStart) {
        this.workdayStart = workdayStart;
    }

    @Column(name = "work_hours", columnDefinition = "date", nullable = false)
    private Date workHours;

    public Date getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Date workHours) {
        this.workHours = workHours;
    }

    @Column(name = "lunch_break", columnDefinition = "date", nullable = false)
    private Date lunchBreak;

    public Date getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(Date lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



}
