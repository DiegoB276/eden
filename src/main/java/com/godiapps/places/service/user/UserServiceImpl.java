package com.godiapps.places.service.user;
import com.godiapps.places.config.JwtService;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.Place;
import com.godiapps.places.entity.User;
import com.godiapps.places.repository.AccountRepository;
import com.godiapps.places.repository.PlaceRepository;
import com.godiapps.places.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    private UsersRepository _userRepository;

    @Autowired
    private AccountRepository _accountRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PlaceRepository _placeRepository;

    public User addNewUser(User user){
        return _userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return _userRepository.findById(id);
    }

    public boolean addPlaceToFavorites(Long userID, Long placeID, String token){
       User user = _userRepository.findById(userID).get();
       if(!jwtService.validateTokenUsername(user.getAccount().getEmail(), token)){
           return false;
       }
       if(_placeRepository.findById(placeID).isEmpty()){
           return false;
       }
       Place place = _placeRepository.findById(placeID).get();
       place.setUser(user);
       _placeRepository.save(place);
        System.out.println(_placeRepository.findById(placeID).get().getUser().getName());
        return true;
    }
}
