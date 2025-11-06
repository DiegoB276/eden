package com.godiapps.places.service.place;

import com.godiapps.places.DTO.PlaceResponseDTO;
import com.godiapps.places.entity.Place;
import com.godiapps.places.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PlaceServiceImpl extends PlaceService {

    @Autowired
    private PlaceRepository _placeRepository;

    public Place addNewPlace(Place place){

        return _placeRepository.save(place);
    }

    public List<PlaceResponseDTO> getPlaces(){
        return  _placeRepository.findAll().stream().map((Place place)->{
            return PlaceResponseDTO.builder()
                    .name(place.getName())
                    .description(place.getDescription())
                    .country(place.getCountry())
                    .city(place.getCity())
                    .creationDate(place.getCreationDate())
                    .latitude(place.getLatitude())
                    .longitude(place.getLongitude())
                    .images(place.getImages())
                    .categories(place.getCategories())
                    .canCarArrived(place.isCanCarArrived())
                    .ranting(place.getRanting())
                    .timeToArrive(place.getTimeToArrive())
                    .placeUserName(place.getUser().getName())
                    .placeUserProfileImage(place.getUser().getProfileImage())
                    .build();
        }).toList();
    }

    public Optional<Place> findPlaceById(Long id){
        return _placeRepository.findById(id);
    }

    public void deletePlace(Long id){
        _placeRepository.deleteById(id);
    }
}
