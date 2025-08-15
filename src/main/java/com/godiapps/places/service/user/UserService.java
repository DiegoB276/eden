package com.godiapps.places.service.user;

import com.godiapps.places.entity.User;

import java.util.Optional;

public abstract class UserService {

    public abstract User addNewUser(User user);

    public abstract Optional<User> findById(Long id);

}
