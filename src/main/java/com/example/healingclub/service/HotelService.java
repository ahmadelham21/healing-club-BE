package com.example.healingclub.service;

import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.response.HotelResponse;
import com.example.healingclub.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelResponse create(HotelRequest request);
    HotelResponse getById(String id);
    List<HotelResponse> getAll();
    void deleteById(String id);

}
