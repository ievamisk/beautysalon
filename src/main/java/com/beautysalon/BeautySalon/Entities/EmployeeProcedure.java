//package com.beautysalon.BeautySalon.Entities;
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class EmployeeProcedure {
//
//    public EmployeeProcedure(){
//
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="beauty_procedure_id")
//    private BeautyProcedure beautyProcedure;
//
//    public BeautyProcedure getBeautyProcedure() {
//        return beautyProcedure;
//    }
//
//    public void setBeautyProcedure(BeautyProcedure beautyProcedure) {
//        this.beautyProcedure = beautyProcedure;
//    }
//
////    @ManyToOne(cascade = CascadeType.ALL)
////    @JoinColumn(name="employee_id")
////    private Employee employee;
////
////    public Employee getEmployee() {
////        return employee;
////    }
////
////    public void setEmployee(Employee employee) {
////        this.employee = employee;
////    }
//
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeProcedure")
////    private List<Bookings> bookings;
////
////    public List<Bookings> getBookings() {
////        return bookings;
////    }
//}
