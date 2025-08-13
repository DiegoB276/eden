package com.godiapps.places.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tbl_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isActive = false;
    private Set<Role> role;
    private LocalDateTime creationDate;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;
}
