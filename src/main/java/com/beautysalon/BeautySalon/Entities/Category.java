package com.beautysalon.BeautySalon.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    public Category() {

    }

    public Category (String category) {
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name="category", columnDefinition="varchar(100)", nullable = false)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category")
    @JsonManagedReference
    private List<Subcategory> subcategories;
    public List<Subcategory> getSubcategories() {
        return subcategories;
    }
}
