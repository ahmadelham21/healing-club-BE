package com.example.healingclub.model.entity;


import com.example.healingclub.constant.TableName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.FACILITY_HOTEL_TABLE)
public class HotelFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "facility_id")
    private Facility facility;
}