package com.godiapps.places.controller;
import com.godiapps.places.entity.User;
import com.godiapps.places.service.UserAccont.UserAccountService;
import com.godiapps.places.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuarios", description = "Acciones para la gestion de los usuarios.")
public class UserController {

    @Autowired
    private UserService _userService;

    @Autowired
    private UserAccountService _userAccService;

    /*
     *OPEN TO USERS
     *----------------- Create new User -----------------------
     */
    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestParam String email, @RequestBody User user, @RequestParam String token){
        if(_userAccService.createUser(email, user, token)){
            return ResponseEntity.ok().body("Ususario Creado y activado");
        };
        return ResponseEntity.badRequest().body("Error al crear el usuario");
    }

    /*
     *OPEN TO USERS
     *----------------- Find user by ID -----------------------
     */
    @Operation(summary = "Buscar un usuario por ID")
    @GetMapping("/find")
    public ResponseEntity<?> findUserById(@RequestParam Long userID){
        if(_userService.findById(userID).isPresent()){
            return ResponseEntity.ok(_userService.findById(userID));
        }
        return ResponseEntity.badRequest().body("User Not found");
    }

    @Operation(summary = "Agregar a Favoritos")
    @PostMapping("/addFavorites")
    public ResponseEntity<?> addToFavorites(@RequestParam Long userID, @RequestParam Long placeID, @RequestParam String token){
        try{
            return ResponseEntity.ok(_userService.addPlaceToFavorites(userID, placeID, token));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
