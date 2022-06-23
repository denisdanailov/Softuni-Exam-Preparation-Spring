package com.softuni.bettleship_exam.service;

import com.softuni.bettleship_exam.model.ShipEntity;
import com.softuni.bettleship_exam.model.dtos.AttackerDTO;
import com.softuni.bettleship_exam.model.dtos.CreateShipDTO;

import java.util.List;

public interface ShipService {

    boolean create(CreateShipDTO createShipDTO);

    List<ShipEntity> getAll();
    List<ShipEntity> findAllByUserId(Long id);
    List<ShipEntity> getAllWithoutCurrentUser(Long id);
    void reduceShipValues(AttackerDTO attackerDTO);
}
