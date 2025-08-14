package com.godiapps.places.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaceRequestDTO {
    private String name;
    private String description;
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
}
