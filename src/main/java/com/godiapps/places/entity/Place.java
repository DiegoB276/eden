package com.godiapps.places.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    private String city;
    private String country;
    private double ranting;
    private boolean canCarArrived;
    private int timeToArrive;
    private double latitude;
    private double longitude;
    private List<String> images;
    private List<String> categories;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;
}
