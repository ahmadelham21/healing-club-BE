package com.example.healingclub.service.impl;

import com.example.healingclub.constant.ErrorMassage;
import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.dto.response.HotelResponse;
import com.example.healingclub.dto.response.PictureResponse;
import com.example.healingclub.entity.*;
import com.example.healingclub.repository.HotelRepository;
import com.example.healingclub.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final CityService cityService;

    @Override
    public HotelResponse getById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMassage.NOT_FOUND));


        List<PictureResponse> urlList = hotel.getPictures().stream().map(
                picture -> {
                    return PictureResponse.builder()
                            .url(picture.getUrl())
                            .thumbnailUrl(picture.getThumbnailUrl())
                            .build();
                }
        ).toList();

        List<String> Facilitylist = hotel.getHotelFacilities().stream().map(
                hotelFacility -> {
                    return hotelFacility.getFacility().getName();
                }
        ).toList();

       return HotelResponse.builder()
                    .id(hotel.getId())
                    .name(hotel.getName())
                    .rating(hotel.getRating())
                    .address(hotel.getAddress())
                    .city(hotel.getCity().getName())
                    .pictures(urlList)
                    .facility(Facilitylist)
                    .build();


    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public HotelResponse create(HotelRequest request) {
        City city = cityService.getById(request.getCityId());

        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .rating(request.getRating())
                .address(request.getAddress())
                .city(city)
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
        List<Picture> picture = pictureService.create(pictureRequest);

        hotel.setPictures(picture);

        List<PictureResponse> urlList = hotel.getPictures().stream().map(
                image -> {
                    return PictureResponse.builder()
                            .url(image.getUrl())
                            .thumbnailUrl(image.getThumbnailUrl())
                            .build();
                }
        ).toList();

        List<String> Facilitylist = hotel.getHotelFacilities().stream().map(
                hotelFacility -> {
                    return hotelFacility.getFacility().getName();
                }
        ).toList();



        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .rating(hotel.getRating())
                .city(hotel.getCity().getName())
                .address(hotel.getAddress())
                .pictures(urlList)
                .facility(Facilitylist)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<HotelResponse> getAll() {

       return hotelRepository.findAll().stream().map(
                hotel -> {
                    List<String> facilities = hotel.getHotelFacilities().stream().map(
                            facility -> {
                                return facility.getFacility().getName();
                            }
                    ).toList();

                    List<PictureResponse> pictureResponses = hotel.getPictures().stream().map(
                            picture -> {
                                return PictureResponse.builder()
                                        .url(picture.getUrl())
                                        .thumbnailUrl(picture.getThumbnailUrl())
                                        .build();
                            }
                    ).toList();


                    return  HotelResponse.builder()
                            .id(hotel.getId())
                            .name(hotel.getName())
                            .rating(hotel.getRating())
                            .address(hotel.getAddress())
                            .city(hotel.getCity().getName())
                            .pictures(pictureResponses)
                            .facility(facilities)
                            .build();
                }
        ).toList();

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMassage.NOT_FOUND));
        hotelRepository.delete(hotel);
        hotel.getPictures().forEach(
                picture -> {
                    pictureService.deletePictureFromImageKit(picture.getFileId());
                }
        );
    }
}
