package com.softuni.bettleship_exam.service.impl;

import com.softuni.bettleship_exam.model.CategoryEntity;
import com.softuni.bettleship_exam.model.ShipEntity;
import com.softuni.bettleship_exam.model.ShipTypeEnum;
import com.softuni.bettleship_exam.model.UserEntity;
import com.softuni.bettleship_exam.model.dtos.AttackerDTO;
import com.softuni.bettleship_exam.model.dtos.CreateShipDTO;
import com.softuni.bettleship_exam.repository.CategoryRepository;
import com.softuni.bettleship_exam.repository.ShipRepository;
import com.softuni.bettleship_exam.repository.UserRepository;
import com.softuni.bettleship_exam.sec.CurrentUser;
import com.softuni.bettleship_exam.service.ShipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShepServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;


    public ShepServiceImpl(ShipRepository shipRepository, CategoryRepository categoryRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;

    }

    @Override
    public boolean create(CreateShipDTO createShipDTO) {

        Optional<ShipEntity> byName = shipRepository.findByName(createShipDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        ShipEntity ship = new ShipEntity();

        ShipTypeEnum type = switch (createShipDTO.getCategory()) {

            case BATTLE -> ShipTypeEnum.BATTLE;
            case CARGO -> ShipTypeEnum.CARGO;
            case PATROL -> ShipTypeEnum.PATROL;
            default -> ShipTypeEnum.BATTLE;
        };

        CategoryEntity category = this.categoryRepository.findByName(type);

        Optional<UserEntity> user = this.userRepository.findById(this.currentUser.getId());

        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        ship.setCreated(createShipDTO.getCreated());
        ship.setCategory(category);
        ship.setUser(user.get());

        shipRepository.save(ship);

        return true;
    }

    @Override
    public List<ShipEntity> getAll() {

        List<ShipEntity> allShips = shipRepository.findAll();
        return allShips;
    }

    @Override
    public List<ShipEntity> findAllByUserId(Long id) {

      return shipRepository
              .findAll()
              .stream()
              .filter(shipEntity -> shipEntity.getUser().getId().equals(id))
              .collect(Collectors.toList());

    }

    @Override
    public List<ShipEntity> getAllWithoutCurrentUser(Long id) {
         return shipRepository
                .findAll()
                .stream()
                .filter(shipEntity -> !shipEntity.getUser().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public void reduceShipValues(AttackerDTO attackerDTO) {


        Optional<ShipEntity> attacker = shipRepository.findById(attackerDTO.getAttackerShipId());
        Optional<ShipEntity> defender = shipRepository.findById(attackerDTO.getDefenderShipId());

        long attackerPower = attacker.get().getPower();
        long defenderHealth = defender.get().getHealth();

        long result = defenderHealth - attackerPower;

        if (result <= 0  || defenderHealth <= 0) {
            shipRepository.delete(defender.get());
        } else  {
            ShipEntity ship = defender.get().setHealth(result);
            shipRepository.save(ship);
        }
    }
}
