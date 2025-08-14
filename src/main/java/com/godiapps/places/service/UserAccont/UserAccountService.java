package com.godiapps.places.service.UserAccont;

import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Role;
import com.godiapps.places.entity.User;
import com.godiapps.places.repository.AccountRepository;
import com.godiapps.places.repository.UsersRepository;
import com.godiapps.places.service.account.AccountService;
import com.godiapps.places.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserAccountService {

    @Autowired
    private AccountService _accountService;

    @Autowired
    private UserService _userService;

    public boolean createUser(String email, User user){
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
}