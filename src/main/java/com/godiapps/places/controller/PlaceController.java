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
@RequestMapping("/places")
@Tag(name = "Lugares", description = "Acciones relacionadas a Lugares.")
public class PlaceController {

    @Autowired
    private PlaceUser _placeUser;

    @Autowired
    private PlaceService _placeService;

    @Operation(summary = "Registrar un lugar nuevo")
    @PostMapping("/create/{userId}")
    public ResponseEntity<PlaceResponseDTO> CreatePlaceWithUser(@RequestBody PlaceRequestDTO placeRequestDTO, @PathVariable Long userId){
        return ResponseEntity.ok(_placeUser.CreatePlaceWithUser(placeRequestDTO, userId));
    }

    @Operation(summary = "Obtener todos los lugares")
    @GetMapping
    public List<PlaceResponseDTO> getAllPlaces(){
        return _placeService.getPlaces();
    }

    @Operation(summary = "Buscar luar por ID")
    @GetMapping("/{placeId}")
    public ResponseEntity<Optional<Place>> findPlaceById(@PathVariable Long placeId){
        return ResponseEntity.ok(_placeService.findPlaceById(placeId));
    }


}
