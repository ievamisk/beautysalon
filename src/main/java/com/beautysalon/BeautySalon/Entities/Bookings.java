package com.beautysalon.BeautySalon.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Bookings {

    public Bookings(){

    }

    public Bookings(String startTime, BeautyProcedure procedureId, User userId){

        this.startTime = startTime;
        this.beautyProcedure = procedureId;
        this.user = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public User getUser() {
        return user;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beauty_procedure_id")
    @JsonBackReference
    private BeautyProcedure beautyProcedure;

    public BeautyProcedure getBeautyProcedure() {
        return beautyProcedure;
    }

    @Column(name = "start_time", columnDefinition = "varchar(20)", nullable = false)
    private String startTime;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
