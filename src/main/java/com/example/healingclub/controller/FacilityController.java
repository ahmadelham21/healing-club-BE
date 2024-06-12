package com.example.healingclub.controller;


import com.example.healingclub.constant.ApiUrl;
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
    public ResponseEntity<Facility> createNewFacility(@RequestBody Facility request){
        Facility newFacility = facilityService.create(request);
        return ResponseEntity.ok(newFacility);
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id){
        Facility facility = facilityService.getById(id);
        return ResponseEntity.ok(facility);
    }

    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacility(){
        List<Facility> facilityList = facilityService.getAll();
        return ResponseEntity.ok(facilityList);
    }

    @PutMapping
    public ResponseEntity<Facility> updateFacility(@RequestBody Facility facility){
        Facility updateFacility = facilityService.update(facility);
        return ResponseEntity.ok(updateFacility);
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<String> deleteFacility(@PathVariable Long id){
        facilityService.deleteById(id);
        return ResponseEntity.ok("Successfully delete data");
    }

}
