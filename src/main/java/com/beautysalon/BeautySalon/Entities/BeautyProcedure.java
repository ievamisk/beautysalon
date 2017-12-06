package com.beautysalon.BeautySalon.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BeautyProcedure {

    public BeautyProcedure(String name, Date duration) {
        this.name = name;
        this.duration = duration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "duration", columnDefinition = "date", nullable = false)
    private Date duration;

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
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
}


