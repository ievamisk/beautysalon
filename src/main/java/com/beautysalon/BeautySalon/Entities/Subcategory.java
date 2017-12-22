package com.beautysalon.BeautySalon.Entities;
import com.beautysalon.BeautySalon.Repositories.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Subcategory {

    public Subcategory(){

    }

    public Subcategory(String subcategory, Category category){
        this.subcategory = subcategory;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name="subcategory", columnDefinition = "varchar(100)", nullable = false)
    private String subcategory;

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonBackReference
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
