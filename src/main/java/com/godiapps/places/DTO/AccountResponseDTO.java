package com.godiapps.places.DTO;
import com.godiapps.places.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountResponseDTO {
    private String email;
    private boolean isActive;
    private Role role;
    private LocalDateTime creationDate;
}
