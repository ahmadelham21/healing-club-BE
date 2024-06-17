package com.example.healingclub.dto.response;
import com.example.healingclub.entity.Picture;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * this response for HotelResponse
 * you can use @JsonProperty("") to change property name into json in response
 * */
public class HotelResponse {

    private String id;
    private String name;
    private Double rating;
    private String address;
    private String city;
    private List<PictureResponse> pictures;
    private List<String> facility;



}
