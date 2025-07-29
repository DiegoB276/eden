package com.godiapps.places.service.account;
import com.godiapps.places.DTO.AccountRequestDTO;
import com.godiapps.places.DTO.AccountResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import com.godiapps.places.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccountServiceImpl extends AccountService {

    @Autowired
    private AccountRepository _accountRepository;

    public AccountResponseDTO addNewAccount(AccountRequestDTO accountRequestDto){
        Account account = new Account();
        account.setEmail(accountRequestDto.getEmail());
        account.setPassword(accountRequestDto.getPassword());
        account.setCreationDate(LocalDateTime.now());
        account.setRole(Role.VISITOR);
        //System.out.println("Email:" + account.getEmail() + " Password: " + account.getPassword() + " Date: " + account.getCreationDate() + " Rol: " + account.getRole());
        _accountRepository.save(account);
        return new AccountResponseDTO(account.getEmail(), account.getIsActive(), account.getRole(), account.getCreationDate());
    }




    public int deleteAccount(Long id){
        _accountRepository.deleteById(id);
        if(_accountRepository.findById(id).isPresent()){
            return 0;
        }
        return 1;
    }

    public Optional<Account> findAccountById(Long id){
        return _accountRepository.findById(id);
    }

    public Optional<Account> findAccountByEmail(String email){
        return _accountRepository.findAccountByEmail(email);
    }

    @Transactional
    @Override
    public void deleteAccountWithTime(){
    }

    public void activateAccount(Long id){
        Optional<Account> account = _accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setIsActive(true);
            _accountRepository.save(account.get());
            return;
        }
    }

    /*
    TODO
    public Role setRole(short role){
        return role == 1? Role.USER : Role.VISITOR;
    }
    */
}
