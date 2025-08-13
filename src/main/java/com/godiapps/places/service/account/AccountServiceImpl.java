package com.godiapps.places.service.account;
import com.godiapps.places.DTO.AccountRequestDTO;
import com.godiapps.places.DTO.AccountResponseDTO;
import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.config.JwtService;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import com.godiapps.places.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AccountServiceImpl extends AccountService {

    @Autowired
    private AccountRepository _accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    public AuthResponseDTO addNewAccount(AuthRequestDTO accRequest){
        Account account = Account.builder()
                .email(accRequest.getEmail())
                .password(passwordEncoder.encode(accRequest.getPassword()))
                .creationDate(LocalDateTime.now())
                .isActive(false)
                .role(Set.of(Role.VISITOR))
                .build();
        _accountRepository.save(account);
        String token = jwtService.generateToken(account.getEmail(), Map.of("roles", account.getRole()));
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO loginAccount(AuthRequestDTO accRequest){
        try{
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accRequest.getEmail(), accRequest.getPassword())
            );
            var userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            System.out.println(userDetails.getUsername());
            String token = jwtService.generateToken(userDetails.getUsername(), Map.of("roles", userDetails.getAuthorities()));
            System.out.println(token);
            return new AuthResponseDTO(token);
        }catch (BadCredentialsException e){
            return null;
        }
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
