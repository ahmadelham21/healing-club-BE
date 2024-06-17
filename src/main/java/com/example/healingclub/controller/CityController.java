package com.example.healingclub.controller;

import com.example.healingclub.constant.ApiUrl;
import com.example.healingclub.constant.ResponseMessage;
import com.example.healingclub.dto.response.BaseResponse;
import com.example.healingclub.dto.response.CommonResponse;
import com.example.healingclub.dto.response.CommonResponseWithPage;
import com.example.healingclub.dto.response.CommonResponseWithoutData;
import com.example.healingclub.entity.City;
import com.example.healingclub.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.CITY_API)
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<BaseResponse> createCity(@RequestBody City city){
        City newCity = cityService.create(city);
        BaseResponse response = CommonResponse.<City>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.CREATED_CITY)
                .data(newCity)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getCityById(@PathVariable String id){
        BaseResponse response = CommonResponse.<City>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_CITY)
                .data(cityService.getById(id))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllCity(){
        BaseResponse response = CommonResponseWithPage.<List<City>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_CITY)
                .data(cityService.getAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateCity(@RequestBody City city){
        BaseResponse response = CommonResponse.<City>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.UPDATE_CITY)
                .data(cityService.update(city))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> deleteCity(@PathVariable String id){
        cityService.deleteById(id);
        BaseResponse response = CommonResponseWithoutData.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.DELETE_CITY)
                .build();
        return ResponseEntity.ok(response);
    }

}
