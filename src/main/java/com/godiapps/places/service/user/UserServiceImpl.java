package com.godiapps.places.service.user;

import com.godiapps.places.DTO.UserRequestDTO;
import com.godiapps.places.entity.User;
import com.godiapps.places.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UsersRepository _userRepository;

    public User addNewUser(User user){
        return _userRepository.save(user);
    }


    public Optional<User> findById(Long id){
        return _userRepository.findById(id);
    }
}
