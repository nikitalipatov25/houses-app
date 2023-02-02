package com.nikitalipatov.houseapp.services;

import com.nikitalipatov.houseapp.models.Houses;
import com.nikitalipatov.houseapp.models.Users;
import com.nikitalipatov.houseapp.repos.HousesRepo;
import com.nikitalipatov.houseapp.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    UsersRepo usersRepo;

    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Optional<Users> getUser(int id) {
        return usersRepo.findById(id);
    }

    public List<Users> getUsers() {
        return usersRepo.findAll();
    }

    public Users addUser(String name, int age) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        return usersRepo.save(user);
    }

    public Optional<Users> editUser(int id, String name, int age) {
        Optional<Users> user = getUser(id);
        return user
                .map(e -> {
                    e.setName(name);
                    e.setAge(age);
                    return usersRepo.save(e);
                });
    }

}
