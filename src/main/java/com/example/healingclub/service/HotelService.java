package com.example.healingclub.service;

import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(HotelRequest request);
    Hotel getById(String id);
    List<Hotel> getAll();
    void deleteById(String id);

}
