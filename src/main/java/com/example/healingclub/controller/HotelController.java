package com.example.healingclub.controller;


import com.example.healingclub.constant.ApiUrl;
import com.example.healingclub.dto.request.HotelRequest;
import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.dto.response.BaseResponse;
import com.example.healingclub.dto.response.CommonResponse;
import com.example.healingclub.dto.response.HotelResponse;
import com.example.healingclub.entity.Hotel;
import com.example.healingclub.service.HotelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            hotelBuilder.message("Successfully create data");
            hotelBuilder.statusCode(HttpStatus.OK.value());
            hotelBuilder.data(hotel);
            return ResponseEntity.status(HttpStatus.OK).body(hotelBuilder.build());




        }catch (Exception e){
            hotelBuilder.message("internal server error");
            hotelBuilder.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(hotelBuilder.build());

        }

    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getById(@PathVariable String id){
        HotelResponse hotelResponse = hotelService.getById(id);
        CommonResponse<HotelResponse> response = CommonResponse.<HotelResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get data")
                .data(hotelResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll(){
        List<HotelResponse> hotelList = hotelService.getAll();
        BaseResponse response = CommonResponse.<List<HotelResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get data")
                .data(hotelList)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> deleteById(@PathVariable String id){
        hotelService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully delete data")
                .build();

        return ResponseEntity.ok(response);
    }
}
