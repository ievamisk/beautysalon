package com.beautysalon.BeautySalon.Entities;

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
    private int id;

    @Column(name="category", columnDefinition="varchar(100)", nullable = false)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Subcategory> subcategories;

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }
}
