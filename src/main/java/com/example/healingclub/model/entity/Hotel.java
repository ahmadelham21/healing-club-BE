package com.example.healingclub.model.entity;


import com.example.healingclub.constant.TableName;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.HOTEL_TABLE)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "address",length = 100)
    private String address;

    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference
    private List<Picture> pictures;

    @OneToMany(mappedBy = "hotel")
    @JsonManagedReference
    private List<HotelFacility> hotelFacilities;


}
