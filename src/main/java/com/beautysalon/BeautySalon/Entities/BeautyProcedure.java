package com.beautysalon.BeautySalon.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="beauty_procedure")
public class BeautyProcedure {

    public BeautyProcedure(){}

    public BeautyProcedure(double price, int duration, Subcategory subcategory, Employee employee) {
        this.price = price;
        this.duration = duration;
        this.subcategory = subcategory;
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "price", columnDefinition = "double", nullable = false)
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "duration", columnDefinition = "int", nullable = false)
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    @JsonBackReference
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beautyProcedure")
    private List<Bookings> bookings;

    public List<Bookings> getBookings() {
        return bookings;
    }
}


