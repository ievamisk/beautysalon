package com.beautysalon.BeautySalon.Entities;
import javax.persistence.*;

@Entity
public class Subcategory {

    public Subcategory(){

    }

    public Subcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="subcategory", columnDefinition = "varchar(100)", nullable = false)
    private String subcategory;

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
