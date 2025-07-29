package com.godiapps.places.controller;
import com.godiapps.places.DTO.AccountRequestDTO;
import com.godiapps.places.DTO.AccountResponseDTO;
import com.godiapps.places.entity.Account;
import com.godiapps.places.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@Controller
@RestController
@RequestMapping("/account")
@Tag(name = "Cuentas de Usuario", description = "Endpoints relacionados con cuentas de usuario.")
public class AccountController {

    @Autowired
    private AccountService _accountService;

    @Operation(summary = "Registrar una nueva cuenta")
    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO account){
            return ResponseEntity.ok(_accountService.addNewAccount(account));
    }

    @Operation(summary = "Buscar cuenta por ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Account>> findById(@PathVariable Long id){
        return ResponseEntity.ok(_accountService.findAccountById(id));
    }

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


    @DeleteMapping("deleteAfter")
    public void deleteAfterTime(){
        _accountService.deleteAccountWithTime();
    }
}
