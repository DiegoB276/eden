package com.godiapps.places.service.place;


import com.godiapps.places.DTO.PlaceResponseDTO;
import com.godiapps.places.entity.Place;
import java.util.List;
import java.util.Optional;

public abstract class PlaceService {

    //TODO: Add new place by an user. Cann't create a place wout user.
    public abstract Place addNewPlace(Place place);

    //TODO: Find all places
    public abstract List<PlaceResponseDTO> getPlaces();

    //TODO: find a place by id
    public abstract Optional<Place> findPlaceById(Long id);

    //TODO: Delete a place by Id
    public abstract void deletePlace(Long id);

}
