package com.example.healingclub.service;

import com.example.healingclub.entity.Facility;

import java.util.List;

public interface FacilityService {
    Facility create(Facility facility);
    Facility getById(Long id);
    List<Facility> getAll();
    Facility update(Facility facility);
    void deleteById(Long id);


}
