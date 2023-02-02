package com.nikitalipatov.houseapp.repos;

import com.nikitalipatov.houseapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {
}
