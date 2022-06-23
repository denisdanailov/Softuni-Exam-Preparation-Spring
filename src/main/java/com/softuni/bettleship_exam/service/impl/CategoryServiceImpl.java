package com.softuni.bettleship_exam.service.impl;

import com.softuni.bettleship_exam.model.CategoryEntity;
import com.softuni.bettleship_exam.model.ShipTypeEnum;
import com.softuni.bettleship_exam.model.UserEntity;
import com.softuni.bettleship_exam.model.dtos.UserRegistrationDTO;
import com.softuni.bettleship_exam.repository.CategoryRepository;
import com.softuni.bettleship_exam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {

        if (this.categoryRepository.count() == 0) {

            List<CategoryEntity> categories = Arrays.stream(ShipTypeEnum.values())
                    .map(CategoryEntity::new).collect(Collectors.toList());

            categories.forEach(categoryEntity -> {

                switch (categoryEntity.getName()) {
                    case BATTLE -> categoryEntity.setDescription("BATTLE");
                    case CARGO -> categoryEntity.setDescription("CARGO");
                    case PATROL -> categoryEntity.setDescription("PATROL");
                }
            });

            categoryRepository.saveAll(categories);
        }
    }

}



























