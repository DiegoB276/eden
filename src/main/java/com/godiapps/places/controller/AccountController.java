package com.godiapps.places.controller;
import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@Controller
@RestController
@RequestMapping("/api/account")
@Tag(name = "Cuentas de Usuario", description = "Endpoints relacionados con cuentas de usuario.")
public class AccountController {

    @Autowired
    private AccountService _accountService;
    //OPEN TO EVERYBODY.
    @Operation(summary = "Registrar una nueva cuenta")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> createAccount(@RequestBody AuthRequestDTO accRequest){
            return ResponseEntity.ok(_accountService.addNewAccount(accRequest));
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO accRequest) {
        var authResponse = _accountService.loginAccount(accRequest);
        if (authResponse == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales Incorrectas");
        }
        return ResponseEntity.ok(authResponse);
    }

    //OPEN TO USERS
    @Operation(summary = "Buscar cuenta por ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Account>> findById(@PathVariable Long id){
        return ResponseEntity.ok(_accountService.findAccountById(id));
    }

    //OPEN TO USERS
    @Operation(summary = "Eliminar cuenta")
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Long id){
        if(_accountService.deleteAccount(id)==0){
            ResponseEntity.ok();
            return;
        }
        ResponseEntity.badRequest();
        return;
    }

}
