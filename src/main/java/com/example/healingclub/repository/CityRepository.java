package com.example.healingclub.repository;

import com.example.healingclub.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
    Optional<City> findByNameIgnoreCase(String name);
}
