package com.softuni.bettleship_exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "health")
    private long health;

    @Column(name = "power")
    private long power;

    @Column(name = "created")
    private LocalDate created;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity user;

    public ShipEntity() {
    }

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipEntity setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipEntity setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
