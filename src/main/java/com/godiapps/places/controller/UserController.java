package com.godiapps.places.controller;

import com.godiapps.places.entity.User;
import com.godiapps.places.service.UserAccont.UserAccountService;
import com.godiapps.places.service.account.AccountService;
import com.godiapps.places.service.account.AccountServiceImpl;
import com.godiapps.places.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuarios", description = "Acciones para la gestion de los usuarios.")
public class UserController {

    @Autowired
    private UserService _userService;

    @Autowired
    private UserAccountService _userAccService;

    //OPEN TO USER.
    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping("/create/{email}")
    public ResponseEntity<?> createUser(@PathVariable String email, @RequestBody User user){
        if(_userAccService.createUser(email, user)){
            return ResponseEntity.ok().body("Ususario Creado y activado");
        };
        return ResponseEntity.badRequest().body("Error al crear el usuario");
    }

    //OPEN TO USERS.
    @Operation(summary = "Buscar un usuario por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        if(_userService.findById(id).isPresent()){
            return ResponseEntity.ok(_userService.findById(id));
        }
        return ResponseEntity.badRequest().body("User Not found");
    }

}
