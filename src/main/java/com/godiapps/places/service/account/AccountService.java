package com.godiapps.places.service.account;

import com.godiapps.places.DTO.AccountRequestDTO;
import com.godiapps.places.DTO.AccountResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;

import java.util.Optional;
import java.util.Set;

public abstract class AccountService {

    public abstract AccountResponseDTO addNewAccount(AccountRequestDTO accountRequestDto);

    public abstract int deleteAccount(Long id);

    public abstract Optional<Account> findAccountById(Long id);

    public abstract Optional<Account> findAccountByEmail(String email);

    public abstract void deleteAccountWithTime();

    public abstract void activateAccount(Long id);

    //public abstract Role setRole(short role);
}
