package com.example.healingclub.model.entity;


import com.example.healingclub.constant.TableName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TableName.PICTURE_TABLE)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "picture_seq")
    @SequenceGenerator(name = "picture_seq",sequenceName = "picture_seq",allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @Column(name = "url")
    private String url;
}
