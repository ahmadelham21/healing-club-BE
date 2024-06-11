package com.example.healingclub.repository;

import com.example.healingclub.model.entity.HotelFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacility,String> {
}
