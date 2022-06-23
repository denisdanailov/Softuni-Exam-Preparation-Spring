package com.softuni.bettleship_exam.repository;

import com.softuni.bettleship_exam.model.CategoryEntity;
import com.softuni.bettleship_exam.model.ShipTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    CategoryEntity findByName(ShipTypeEnum name);
}
