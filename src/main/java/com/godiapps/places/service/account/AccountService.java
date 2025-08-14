package com.godiapps.places.service.account;

import com.godiapps.places.DTO.AccountRequestDTO;
import com.godiapps.places.DTO.AccountResponseDTO;
import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public abstract class AccountService {

    public abstract AuthResponseDTO addNewAccount(AuthRequestDTO accRequest);

    public abstract AuthResponseDTO loginAccount(AuthRequestDTO accRequest);

    public abstract int deleteAccount(Long id);

    public abstract Optional<Account> findAccountById(Long id);

    public abstract Optional<Account> findAccountByEmail(String email);

    public abstract void deleteAccountWithTime();

    public abstract void activateAccount(Long id);

    //public abstract Role setRole(short role);
}
