package com.beautysalon.BeautySalon.Entities;
import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    public Employee(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @Column(name = "last_name", columnDefinition = "varchar(100)", nullable = false)
    private String lastName;

    @Column(name = "description", columnDefinition = "varchar(300)", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<EmployeeProcedure> employeeProcedures;

    public List<EmployeeProcedure> getEmployeeProcedures() {
        return employeeProcedures;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Bookings> bookings;

    public List<Bookings> getBookings() {
        return bookings;
    }
}
