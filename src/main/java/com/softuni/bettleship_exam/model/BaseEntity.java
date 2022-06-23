package com.softuni.bettleship_exam.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Long getId() {
        return Id;
    }

    public BaseEntity setId(Long id) {
        Id = id;
        return this;
    }
}
