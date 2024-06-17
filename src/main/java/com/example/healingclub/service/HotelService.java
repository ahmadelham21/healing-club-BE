package com.example.healingclub.service;

import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.request.SearchHotelRequest;
import com.example.healingclub.dto.response.HotelResponse;
import com.example.healingclub.entity.Hotel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HotelService {

    HotelResponse create(HotelRequest request);
    HotelResponse getById(String id);
    Page<Hotel> getAll(SearchHotelRequest request);
    void deleteById(String id);

}
