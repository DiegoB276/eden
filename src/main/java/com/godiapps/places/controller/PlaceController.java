package com.godiapps.places.controller;

import com.godiapps.places.DTO.PlaceDTO;
import com.godiapps.places.entity.Place;
import com.godiapps.places.service.PlaceUser.PlaceUser;
import com.godiapps.places.service.place.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void CreatePlaceWithUser(@RequestBody PlaceDTO placeDTO, @PathVariable Long userId){
        if (_placeUser.CreatePlaceWithUser(placeDTO, userId)){
            System.out.println("Lugar creado y asociado a un usuario");
            return;
        }
        System.out.println("Fallo al agregar el lugar!!");
        return;
    }

    @Operation(summary = "Buscar luar por ID")
    @GetMapping("/{placeId}")
    public ResponseEntity<Optional<Place>> findPlaceById(@PathVariable Long placeId){
        return ResponseEntity.ok(_placeService.findPlaceById(placeId));
    }
}
