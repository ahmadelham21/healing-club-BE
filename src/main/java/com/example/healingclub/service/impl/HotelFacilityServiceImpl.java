package com.example.healingclub.service.impl;

import com.example.healingclub.model.entity.HotelFacility;
import com.example.healingclub.repository.HotelFacilityRepository;
import com.example.healingclub.service.HotelFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelFacilityServiceImpl implements HotelFacilityService {
    private final HotelFacilityRepository hotelFacilityRepository;

    @Override
    public List<HotelFacility> createBulk(List<HotelFacility> request) {
        return hotelFacilityRepository.saveAllAndFlush(request);
    }

    @Override
    public HotelFacility create(HotelFacility request) {
        return hotelFacilityRepository.saveAndFlush(request);
    }

}
