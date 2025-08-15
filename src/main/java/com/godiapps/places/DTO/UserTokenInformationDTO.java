package com.godiapps.places.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
