package com.example.healingclub.controller;


import com.example.healingclub.constant.ApiUrl;
import com.example.healingclub.constant.ResponseMessage;
import com.example.healingclub.dto.response.BaseResponse;
import com.example.healingclub.dto.response.CommonResponse;
import com.example.healingclub.dto.response.CommonResponseWithPage;
import com.example.healingclub.dto.response.CommonResponseWithoutData;
import com.example.healingclub.entity.Facility;
import com.example.healingclub.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.FACILITY_API)
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<BaseResponse> createNewFacility(@RequestBody Facility request){
        Facility newFacility = facilityService.create(request);
        BaseResponse response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.CREATED_FACILITY)
                .data(newFacility)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> getFacilityById(@PathVariable Long id){
        Facility facility = facilityService.getById(id);
        BaseResponse response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_FACILITY)
                .data(facility)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllFacility(){
        List<Facility> facilityList = facilityService.getAll();
        BaseResponse response = CommonResponse.<List<Facility>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_FACILITY)
                .data(facilityList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateFacility(@RequestBody Facility facility){
        Facility updateFacility = facilityService.update(facility);
        BaseResponse response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.UPDATE_FACILITY)
                .data(updateFacility)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<BaseResponse> deleteFacility(@PathVariable Long id){
        facilityService.deleteById(id);

        BaseResponse response = CommonResponseWithoutData.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.DELETE_FACILITY)
                .build();

        return ResponseEntity.ok(response);
    }

}
