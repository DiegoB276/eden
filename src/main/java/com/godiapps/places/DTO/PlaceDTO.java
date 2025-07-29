package com.godiapps.places.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaceDTO {
    private String name;
    private String description;
    private String city;
    private String country;
    private double latitude;
    private double longitude;
}
