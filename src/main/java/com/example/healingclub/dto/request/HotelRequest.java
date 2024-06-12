package com.example.healingclub.dto.request;

import com.example.healingclub.model.entity.HotelFacility;
import com.example.healingclub.model.entity.Picture;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {
    private String name;

    private Double rating;

    private String address;

    private PictureRequest pictures;

    private List<Long> facilityId;
}
