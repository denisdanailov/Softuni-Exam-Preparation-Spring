package com.softuni.bettleship_exam.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private ShipTypeEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(ShipTypeEnum name) {
        this.name = name;
    }

    public ShipTypeEnum getName() {
        return name;
    }

    public CategoryEntity setName(ShipTypeEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
