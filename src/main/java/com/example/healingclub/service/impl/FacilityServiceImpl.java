package com.example.healingclub.service.impl;

import com.example.healingclub.entity.Facility;
import com.example.healingclub.repository.FacilityRepository;
import com.example.healingclub.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Facility create(Facility facility) {
        return facilityRepository.saveAndFlush(facility);
    }

    @Transactional(readOnly = true)
    @Override
    public Facility getById(Long id) {
        return facilityRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Facility update(Facility facility) {
        facilityRepository.findById(facility.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
        return facilityRepository.saveAndFlush(facility);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        Facility facility = facilityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
        facilityRepository.delete(facility);
    }
}
