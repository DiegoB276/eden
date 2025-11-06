package com.godiapps.places.service.account;
import com.godiapps.places.DTO.*;
import com.godiapps.places.config.JwtService;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import com.godiapps.places.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        String token = jwtService.generateToken(account.getEmail(), Map.of("roles", account.getRole().stream().map(Enum::name).toList()));
        return new AuthResponseDTO(token);
    }


    public int deleteAccount(String email, String token){
        try{
            Optional<Account> acc = _accountRepository.findAccountByEmail(email);
            if(jwtService.validateTokenUsername(acc.get().getEmail(), token)){
                _accountRepository.deleteById(acc.get().getAccountId());
                if(_accountRepository.findById(acc.get().getAccountId()).isPresent()){
                    return 0;
                }
            }
            return 1;
        }catch (Exception e){
            return 1;
        }
    }

    /*
    * ---------- This method only used by internal API features. ----------------
    */
    public Optional<Account> findAccountById(String email, String token){
        Optional<Account> acc = _accountRepository.findAccountByEmail(email);
        if(jwtService.validateTokenUsername(acc.get().getEmail(), jwtService.extractUsername(token))){
            return _accountRepository.findById(acc.get().getAccountId());
        }
        return Optional.empty();
    }

    public Optional<Account> findAccountByEmail(String email){
        Optional<Account> acc = _accountRepository.findAccountByEmail(email);
        return _accountRepository.findAccountByEmail(email);

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
