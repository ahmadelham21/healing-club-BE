package com.example.healingclub.controller;


import com.example.healingclub.constant.ApiUrl;
import com.example.healingclub.constant.ResponseMessage;
import com.example.healingclub.dto.response.CommonResponse;
import com.example.healingclub.model.entity.Facility;
import com.example.healingclub.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.FACILITY_API)
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping
    public ResponseEntity<CommonResponse<Facility>> createNewFacility(@RequestBody Facility request){
        Facility newFacility = facilityService.create(request);
        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.CREATED_FACILITY)
                .data(newFacility)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Facility>> getFacilityById(@PathVariable Long id){
        Facility facility = facilityService.getById(id);
        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_FACILITY)
                .data(facility)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Facility>>> getAllFacility(){
        List<Facility> facilityList = facilityService.getAll();
        CommonResponse<List<Facility>> response = CommonResponse.<List<Facility>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.GET_FACILITY)
                .data(facilityList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Facility>> updateFacility(@RequestBody Facility facility){
        Facility updateFacility = facilityService.update(facility);
        CommonResponse<Facility> response = CommonResponse.<Facility>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.UPDATE_FACILITY)
                .data(updateFacility)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteFacility(@PathVariable Long id){
        facilityService.deleteById(id);

        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.DELETE_FACILITY)
                .build();

        return ResponseEntity.ok(response);
    }

}
