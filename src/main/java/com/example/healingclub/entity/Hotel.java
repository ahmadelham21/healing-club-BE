package com.example.healingclub.entity;


import com.example.healingclub.constant.TableName;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Picture> pictures;

    @OneToMany(mappedBy = "hotel" ,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<HotelFacility> hotelFacilities;


}
