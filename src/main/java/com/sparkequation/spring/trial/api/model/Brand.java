package com.sparkequation.spring.trial.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BRAND_SEQ")
    @SequenceGenerator(name = "BRAND_SEQ", sequenceName = "BRAND_SEQ", allocationSize = 1)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private int id;
    private String name;
    private String country;

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
