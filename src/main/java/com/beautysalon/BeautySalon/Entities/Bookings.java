package com.beautysalon.BeautySalon.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bookings {

    public Bookings(Date startTime){
        this.startTime = startTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_procedure_id")
    private EmployeeProcedure employeeProcedure;

    @Column(name = "start_time", columnDefinition = "date", nullable = false)
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
