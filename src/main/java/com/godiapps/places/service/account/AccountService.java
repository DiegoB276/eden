package com.godiapps.places.service.account;

import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.entity.Account;
import java.util.Optional;

public abstract class AccountService {

    public abstract AuthResponseDTO addNewAccount(AuthRequestDTO accRequest);

    public abstract int deleteAccount(String email, String token);

    public abstract Optional<Account> findAccountById(String email, String token);

    public abstract Optional<Account> findAccountByEmail(String email);

    public abstract void activateAccount(Long id);

    //public abstract void deleteAccountWithTime();

    //public abstract AuthResponseDTO loginAccount(AuthRequestDTO accRequest);

    //public abstract Role setRole(short role);
}
