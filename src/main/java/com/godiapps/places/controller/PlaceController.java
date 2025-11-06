package com.godiapps.places.controller;
import com.godiapps.places.DTO.PlaceRequestDTO;
import com.godiapps.places.DTO.PlaceResponseDTO;
import com.godiapps.places.entity.Place;
import com.godiapps.places.service.PlaceUser.PlaceUser;
import com.godiapps.places.service.place.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/places")
@Tag(name = "Lugares", description = "Acciones relacionadas a Lugares.")
public class PlaceController {

    @Autowired
    private PlaceUser _placeUser;

    @Autowired
    private PlaceService _placeService;

    /*
     *OPEN TO USERS
     *----------------- Create new place with user -----------------------
     */
    @Operation(summary = "Registrar un lugar nuevo")
    @PostMapping("/create")
    public ResponseEntity<?> CreatePlaceWithUser(@RequestBody PlaceRequestDTO placeRequestDTO, @RequestParam Long userID, @RequestParam String token){
        if(_placeUser.CreatePlaceWithUser(placeRequestDTO, userID, token) == null){
            return ResponseEntity.badRequest().body("Error al agregar lugar");
        }
        return ResponseEntity.ok().body("Lugar agregado con éxito");
    }

    /*
     *OPEN TO EVERYONE
     *----------------- Find all places -----------------------
     */
    @Operation(summary = "Obtener todos los lugares")
    @GetMapping("/all")
    public List<PlaceResponseDTO> getAllPlaces(){
        return _placeService.getPlaces();
    }


    /*
     *OPEN TO USERS
     *----------------- Find place by ID -----------------------
     */
    @Operation(summary = "Buscar luar por ID")
    @GetMapping("/find")
    public ResponseEntity<Optional<Place>> findPlaceById(@PathVariable Long placeID){
        return ResponseEntity.ok(_placeService.findPlaceById(placeID));
    }


}
