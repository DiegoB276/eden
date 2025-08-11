package com.godiapps.places.service.PlaceUser;

import com.godiapps.places.DTO.PlaceRequestDTO;
import com.godiapps.places.DTO.PlaceResponseDTO;
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

    public PlaceResponseDTO CreatePlaceWithUser(PlaceRequestDTO placeRequestDto, Long userId){
        Optional<User> user = _userService.findById(userId);
        if (user.isPresent()){
            Place placeToSave =  _placeService.addNewPlace(formatDtoToPlace(placeRequestDto, new Place()));
            if (_placeService.findPlaceById(placeToSave.getPlaceId()).isPresent()){
                placeToSave.setUser(user.get());
                _placeService.addNewPlace(placeToSave);
                return PlaceResponseDTO.builder()
                        .name(placeToSave.getName())
                        .description(placeToSave.getDescription())
                        .country(placeToSave.getCountry())
                        .city(placeToSave.getCity())
                        .creationDate(LocalDateTime.now())
                        .images(placeToSave.getImages())
                        .categories(placeToSave.getCategories())
                        .ranting(placeToSave.getRanting())
                        .timeToArrive(placeToSave.getTimeToArrive())
                        .canCarArrived(placeToSave.isCanCarArrived())
                        .latitude(placeToSave.getLatitude())
                        .longitude(placeToSave.getLongitude())
                        .placeUserName(placeToSave.getUser().getName())
                        .placeUserProfileImage(placeToSave.getUser().getProfileImage() )
                        .build();

            }
        }
        return null;
    }

    public Place formatDtoToPlace(PlaceRequestDTO placeRequestDto, Place place){
        return  Place.builder()
                .name(placeRequestDto.getName())
                .description(placeRequestDto.getDescription())
                .country(placeRequestDto.getCountry())
                .city(placeRequestDto.getCity())
                .creationDate(LocalDateTime.now())
                .images(placeRequestDto.getImages())
                .categories(placeRequestDto.getCategories())
                .ranting(placeRequestDto.getRanting())
                .canCarArrived(placeRequestDto.isCanCarArrived())
                .timeToArrive(placeRequestDto.getTimeToArrive())
                .latitude(placeRequestDto.getLatitude())
                .longitude(placeRequestDto.getLongitude())
                .build();

    }
}
