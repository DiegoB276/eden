package com.godiapps.places.DTO;

import com.godiapps.places.entity.Place;
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
public class UserTokenInformationDTO {
    private String name;
    private int age;
    private char genre;
    private String country;
    private String profileImage;
    private LocalDateTime creationDate;
}
