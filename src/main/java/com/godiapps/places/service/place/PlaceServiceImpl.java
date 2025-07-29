package com.godiapps.places.service.place;

import com.godiapps.places.entity.Place;
import com.godiapps.places.repository.PlaceRepository;
import com.godiapps.places.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PlaceServiceImpl extends PlaceService {

    @Autowired
    private PlaceRepository _placeRepository;

    public Place addNewPlace(Place place){

        return _placeRepository.save(place);
    }

    public Optional<Place> findPlaceById(Long id){
        return _placeRepository.findById(id);
    }

    public void deletePlace(Long id){
        _placeRepository.deleteById(id);
    }
}
