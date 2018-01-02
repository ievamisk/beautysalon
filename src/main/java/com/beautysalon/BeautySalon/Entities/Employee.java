package com.beautysalon.BeautySalon.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    public Employee(){
    }

    public Employee(String firstName, String lastName, String description){
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", columnDefinition = "varchar(100)", nullable = false)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "varchar(100)", nullable = false)
    private String lastName;

    @Column(name = "description", columnDefinition = "varchar(300)", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @JsonManagedReference
    private List<BeautyProcedure> beautyProcedures;
    public List<BeautyProcedure> getBeautyProcedures() {
        return beautyProcedures;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bookings> bookings;

    public List<Bookings> getBookings() {
        return bookings;
    }
}
