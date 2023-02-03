package com.nikitalipatov.houseapp.services;

import com.nikitalipatov.houseapp.models.Houses;
import com.nikitalipatov.houseapp.models.Users;
import com.nikitalipatov.houseapp.repos.HousesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HousesService {

    HousesRepo housesRepo;
    UsersService usersService;

    public HousesService(HousesRepo housesRepo, UsersService usersService) {
        this.housesRepo = housesRepo;
        this.usersService = usersService;
    }

    public Optional<Houses> getHouse(int id) {
        return housesRepo.findById(id);
    }

    public List<Houses> getHouses() {
        return housesRepo.findAll();
    }

    public Houses addHouse(String adress) {
        Houses house = new Houses();
        house.setAdress(adress);
        return housesRepo.save(house);
    }

    public Optional<Houses> editHouse(int id, String adress) {
        Optional<Houses> house = getHouse(id);
        return house
                .map(e -> {
                    e.setAdress(adress);
                    return housesRepo.save(e);
                });
    }

    public Optional<Boolean> deleteHouse(int id) {
        Optional<Houses> house = housesRepo.findById(id);
        return house
                .map(e -> {
                    housesRepo.deleteById(id);
                    return true;
                });
    }

    public Optional<Houses> setOwner(int userId, int houseId) {
        Optional<Users> owner = usersService.getUser(userId);
        usersService.setUserAsOwner(owner.get());
        Optional<Houses> house = housesRepo.findById(houseId);
        return house
                .map(e -> {
                    e.setOwner(owner.get());
                    return housesRepo.save(e);
                });
    }

    public Optional<Houses> deleteOwner(int id) {
        Optional<Houses> house = housesRepo.findById(id);
        usersService.deleteOwner(house.get().getOwner());
        return house
                .map(e -> {
                    e.setOwner(null);
                    return housesRepo.save(e);
                });
    }

    public Optional<Houses> setResident(int userId, int houseId) {
        Optional<Users> resident = usersService.getUser(userId);
        Optional<Houses> house = housesRepo.findById(houseId);
        Set<Users> residents = house.get().getResidents();
        residents.add(resident.get());
        return house
                .map(e -> {
                    e.setResidents(residents);
                    return housesRepo.save(e);
                });
    }

    public Optional<Houses> deleteResident(int userId, int houseId) {
        Optional<Users> resident = usersService.getUser(userId);
        Optional<Houses> house = housesRepo.findById(houseId);
        Set<Users> residents = house.get().getResidents();
        residents.remove(resident.get());
        return house
                .map(e -> {
                    e.setResidents(residents);
                    return housesRepo.save(e);
                });
    }
}
