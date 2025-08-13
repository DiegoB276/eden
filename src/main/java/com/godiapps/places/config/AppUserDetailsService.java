package com.godiapps.places.config;

import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.User;
import com.godiapps.places.repository.AccountRepository;
import com.godiapps.places.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository _accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = _accountRepository.findAccountByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Account Not Found"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .authorities(account.getRole().stream()
                        .map(Enum::name)
                        .toList().toArray(new String[0]))
                .build();
    }
}
