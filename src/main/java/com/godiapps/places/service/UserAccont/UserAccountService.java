package com.godiapps.places.service.UserAccont;
import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.DTO.UserTokenInformationDTO;
import com.godiapps.places.config.JwtService;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import com.godiapps.places.entity.User;
import com.godiapps.places.service.account.AccountService;
import com.godiapps.places.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAccountService {

    @Autowired
    private AccountService _accountService;

    @Autowired
    private UserService _userService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public boolean createUser(String email, User user, String token){
        if(!jwtService.validateTokenUsername(email, jwtService.extractUsername(token))){
            return false;
        }
        Optional<Account> acc = _accountService.findAccountByEmail(email);
        if (acc.isPresent() && !acc.get().getIsActive()){
            User us = _userService.addNewUser(user);
            activateAccount(us, acc.get());
            return true;
        }
        return false;
    }

    protected void activateAccount(User user, Account account){
        if (_userService.findById(user.getUserId()).isPresent()){
            account.setIsActive(true);
            account.setRole(Set.of(Role.USER));
            //_accountService.addNewAccount(account);
            user.setAccount(account);
            _userService.addNewUser(user);
        }
    }

    private Map<String, Object> getUserData(UserDetails userDetails) {
        Account account = _accountService.findAccountByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        User user = _userService.findById(account.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserTokenInformationDTO dto = UserTokenInformationDTO.builder()
                .name(user.getName())
                .age(user.getAge())
                .genre(user.getGenre())
                .profileImage(user.getProfileImage())
                .creationDate(account.getCreationDate())
                .build();

        return Map.of(
                "name", dto.getName(),
                "age", dto.getAge(),
                "genre", dto.getGenre(),
                "profileImage", dto.getProfileImage(),
                "creationDate", dto.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    public AuthResponseDTO loginAccount(AuthRequestDTO accRequest){
        try{
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(accRequest.getEmail(), accRequest.getPassword()));
            var userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            if(isUser(userDetails)){
                String token = jwtService.generateLoginToken(userDetails.getUsername(), getUserData(userDetails));
                return new AuthResponseDTO(token);
            }
            return null;
        }catch (BadCredentialsException e){
            return null;
        }
    }

    private boolean isUser(org.springframework.security.core.userdetails.User userDetails){
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet())
                .equals(Set.of(Role.USER.name()));
    }

}