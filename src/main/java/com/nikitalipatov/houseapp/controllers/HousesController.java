package com.nikitalipatov.houseapp.controllers;

import com.nikitalipatov.houseapp.models.Houses;
import com.nikitalipatov.houseapp.models.Users;
import com.nikitalipatov.houseapp.services.HousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/houses")
public class HousesController {

    @Autowired
    HousesService housesService;

    @GetMapping("/get")
    public ResponseEntity<List<Houses>> getHouses() {
        return ResponseEntity.ok(housesService.getHouses());
    }

    record House(String adress) {}

    @PostMapping("/add")
    public ResponseEntity<Houses> addHouse(@RequestBody House house) {
        return ResponseEntity.ok(housesService.addHouse(house.adress()));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Houses> editHouse(@PathVariable(name = "id") int id, @RequestBody House house) {
        Optional<Houses> result = housesService.editHouse(id, house.adress());
        return result
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable(name = "id") int id) {
        Optional<Boolean> result = housesService.deleteHouse(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    record Owner(int userId, int houseId) {}

    @PostMapping("/setOwner")
    public ResponseEntity<Optional<Houses>> setOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(housesService.setOwner(owner.userId(), owner.houseId()));
    }

    @DeleteMapping("/deleteOwner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable(name = "id") int id) {
        Optional<Houses> result = housesService.deleteOwner(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    record Resident(int userId, int houseId) {}

    @PostMapping("/setResidents")
    public ResponseEntity<Optional<Houses>> setResident(@RequestBody Resident resident) {
        return ResponseEntity.ok(housesService.setResident(resident.userId(), resident.houseId()));
    }

    @DeleteMapping("/deleteResident")
    public ResponseEntity<?> deleteResident(@RequestBody Resident resident) {
        Optional<Houses> result = housesService.deleteResident(resident.userId(), resident.houseId());
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
