package com.example.healingclub.dto.request;

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
