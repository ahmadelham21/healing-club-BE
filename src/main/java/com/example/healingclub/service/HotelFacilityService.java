package com.example.healingclub.service;

import com.example.healingclub.model.entity.HotelFacility;

import java.util.List;

public interface HotelFacilityService {
    HotelFacility create(HotelFacility request);
    List<HotelFacility> createBulk(List<HotelFacility> request);
}
