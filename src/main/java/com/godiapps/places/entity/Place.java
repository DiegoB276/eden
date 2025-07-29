package com.godiapps.places.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="tbl_places")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}
