package com.nikitalipatov.houseapp.repos;

import com.nikitalipatov.houseapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
