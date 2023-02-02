package com.nikitalipatov.houseapp.repos;

import com.nikitalipatov.houseapp.models.Houses;
import com.nikitalipatov.houseapp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HousesRepo extends JpaRepository<Houses, Integer> {
}
