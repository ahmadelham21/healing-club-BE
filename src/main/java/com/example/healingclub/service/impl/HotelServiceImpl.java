package com.example.healingclub.service.impl;

import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.model.entity.Facility;
import com.example.healingclub.model.entity.Hotel;
import com.example.healingclub.model.entity.HotelFacility;
import com.example.healingclub.model.entity.Picture;
import com.example.healingclub.repository.HotelRepository;
import com.example.healingclub.service.FacilityService;
import com.example.healingclub.service.HotelFacilityService;
import com.example.healingclub.service.HotelService;
import com.example.healingclub.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final PictureService pictureService;
    private final HotelFacilityService hotelFacilityService;
    private final FacilityService facilityService;

    @Override
    public Hotel getById(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Hotel create(HotelRequest request) {

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .rating(request.getRating())
                .address(request.getAddress())
                .build();
        Hotel saveHotel = hotelRepository.saveAndFlush(hotel);

        List<HotelFacility> hotelFacilities = request.getFacilityId().stream()
                .map(id -> {
                    Facility facility = facilityService.getById(id);
                    return HotelFacility.builder()
                            .hotel(hotel)
                            .facility(facility)
                            .build();
                }).toList();
        hotelFacilityService.createBulk(hotelFacilities);
        hotel.setHotelFacilities(hotelFacilities);

        PictureRequest pictureRequest = request.getPictures();
        pictureRequest.setHotel(saveHotel);
        Picture picture = pictureService.create(pictureRequest);

        hotel.setPictures(List.of(picture));


        return hotel;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
        hotelRepository.delete(hotel);
    }
}
