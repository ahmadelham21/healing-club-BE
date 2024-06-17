package com.example.healingclub.controller;


import com.example.healingclub.constant.ApiUrl;
import com.example.healingclub.constant.ErrorMassage;
import com.example.healingclub.constant.ResponseMessage;
import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.dto.request.SearchHotelRequest;
import com.example.healingclub.dto.response.*;
import com.example.healingclub.entity.Hotel;
import com.example.healingclub.service.HotelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.HOTEL_API)
public class HotelController {
    private final HotelService hotelService;

    private final ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<BaseResponse> create(
            @RequestPart(name = "file")List<MultipartFile> image,
            @RequestPart(name = "hotel_request")String jsonHotelrequest
            ){
        CommonResponse.CommonResponseBuilder<HotelResponse> hotelBuilder = CommonResponse.builder();

        try {
            HotelRequest hotelRequest = objectMapper.readValue(jsonHotelrequest, new TypeReference<>(){
            });
            PictureRequest pictureRequest = PictureRequest.builder()
                    .image(image)
                    .build();



            pictureRequest.setImage(image);
            hotelRequest.setPictures(pictureRequest);

            HotelResponse hotel = hotelService.create(hotelRequest);
            hotelBuilder.message(ResponseMessage.CREATED_HOTEL);
            hotelBuilder.statusCode(HttpStatus.OK.value());
            hotelBuilder.data(hotel);
            return ResponseEntity.status(HttpStatus.OK).body(hotelBuilder.build());




        }catch (Exception e){
            hotelBuilder.message(ErrorMassage.INTERNAL_SERVER_ERROR);
            hotelBuilder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelBuilder.build());

        }

    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(@PathVariable String id){
        HotelResponse hotelResponse = hotelService.getById(id);
        CommonResponse<HotelResponse> response = CommonResponse.<HotelResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_HOTEL)
                .data(hotelResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city

    ){
        SearchHotelRequest searchHotelRequest = SearchHotelRequest.builder()
                .direction(direction)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .name(name)
                .cityName(city)
                .build();

        Page<Hotel> hotelList = hotelService.getAll(searchHotelRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(hotelList.getTotalPages())
                .totalElements(hotelList.getTotalElements())
                .page(hotelList.getPageable().getPageNumber() + 1)
                .size(hotelList.getPageable().getPageSize())
                .hasNext(hotelList.hasNext())
                .hasPrevious(hotelList.hasPrevious())
                .build();

        List<HotelResponse> hotelResponseList = hotelList.getContent().stream().map(
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


                    return HotelResponse.builder()
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

        BaseResponse response = CommonResponseWithPage.<List<HotelResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_HOTEL)
                .data(hotelResponseList)
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> deleteById(@PathVariable String id){
        hotelService.deleteById(id);
        BaseResponse response = CommonResponseWithoutData.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.DELETE_HOTEL)
                .build();

        return ResponseEntity.ok(response);
    }
}
