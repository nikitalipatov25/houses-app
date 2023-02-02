package com.nikitalipatov.houseapp.controllers;

import com.nikitalipatov.houseapp.models.Users;
import com.nikitalipatov.houseapp.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/users")
public class UsersController {

    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok(usersService.getUsers());
    }

    record User(String name, int age) {}

    @PostMapping("/add")
    public ResponseEntity<Users> addUsers(@RequestBody User user) {
        return ResponseEntity.ok(usersService.addUser(user.name(), user.age()));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Users> editUser(@PathVariable(name = "id")int id, @RequestBody User user) {
        Optional<Users> result = usersService.editUser(id, user.name(), user.age());
        return result
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
