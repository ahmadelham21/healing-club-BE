package com.example.healingclub.repository;

import com.example.healingclub.model.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility,Long> {
}
