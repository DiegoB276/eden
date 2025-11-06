package com.godiapps.places.controller;
import com.godiapps.places.DTO.AuthRequestDTO;
import com.godiapps.places.DTO.AuthResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.service.UserAccont.UserAccountService;
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

    @Autowired
    private UserAccountService _userAccountService;


    /*
    * OPEN TO EVERYBODY.
    * ----------------- Create a new Account ------------------
    */
    @Operation(summary = "Registrar una nueva cuenta")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> createAccount(@RequestBody AuthRequestDTO accRequest){
            return ResponseEntity.ok(_accountService.addNewAccount(accRequest));
    }


    /*
    OPEN TO EVERYBODY.
    *------------- Do login with existent account -------------------
    */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO accRequest) {
        var authResponse = _userAccountService.loginAccount(accRequest);
        if (authResponse == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales Incorrectas");
        }
        return ResponseEntity.ok(authResponse);
    }


    /*
    *OPEN TO USERS
    *----------------- Find account by ID -----------------------
    */
    @Operation(summary = "Buscar cuenta por ID")
    @GetMapping("/find")
    public ResponseEntity<Optional<Account>> findAccountById(@RequestParam String email, @RequestParam(required = true) String token){
        return ResponseEntity.ok(_accountService.findAccountById(email, token));
    }


    /*
    OPEN TO USERS
    * --------------- Delete a existent account ------------------
    */
    @Operation(summary = "Eliminar cuenta")
    @DeleteMapping("/delete")
    public void deleteAccount(@RequestParam String email, @RequestParam String token){
        if(_accountService.deleteAccount(email, token)==0){
            ResponseEntity.ok();
            return;
        }
        ResponseEntity.badRequest();
        return;
    }
}
