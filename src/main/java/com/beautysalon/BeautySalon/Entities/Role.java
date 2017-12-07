package com.beautysalon.BeautySalon.Entities;


import javax.persistence.*;

@Entity
public class Role {
    public Role(){

    }
    public Role(String name){
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
