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
@Table(name = TableName.FACILITY_TABLE)
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "facility_seq")
    @SequenceGenerator(name = "facility_seq",sequenceName = "facility_seq",allocationSize = 1)
    private Long id;

    @Column(name = "name",length = 50)
    private String name;

    @OneToMany(mappedBy = "facility")
    @JsonManagedReference
    private List<HotelFacility> hotelFacilities;

}
