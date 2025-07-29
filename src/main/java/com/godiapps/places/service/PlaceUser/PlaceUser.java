package com.godiapps.places.service.PlaceUser;

import com.godiapps.places.DTO.PlaceDTO;
import com.godiapps.places.entity.Place;
import com.godiapps.places.entity.User;
import com.godiapps.places.service.place.PlaceService;
import com.godiapps.places.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PlaceUser {

    @Autowired
    private UserService _userService;

    @Autowired
    private PlaceService _placeService;

    public Boolean CreatePlaceWithUser(PlaceDTO placeDto, Long userId){
        Optional<User> user = _userService.findById(userId);
        if (user.isPresent()){
            Place placeToSave =  _placeService.addNewPlace(formatPlaceDTO(placeDto, new Place()));
            System.out.println("Lugar antes de guardarse: " + placeToSave.getName());
            if (_placeService.findPlaceById(placeToSave.getPlaceId()).isPresent()){
                placeToSave.setUser(user.get());
                _placeService.addNewPlace(placeToSave);
                System.out.println("Lugar antes de guardarse: " + placeToSave.getName());
                return true;
            }
        }
        return false;
    }

    public Place formatPlaceDTO(PlaceDTO placeDto, Place place){
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setCity(placeDto.getCity());
        place.setCreationDate(LocalDateTime.now());
        place.setCountry(placeDto.getCountry());
        place.setImages(placeDto.getImages());
        place.setLatitude(placeDto.getLatitude());
        place.setLongitude(placeDto.getLongitude());
        return place;
    }
}
