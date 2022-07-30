package com.sarigul.sisal.restApi;

import com.sarigul.sisal.entity.Account;
import com.sarigul.sisal.entity.model.AccountDto;
import com.sarigul.sisal.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity myAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User a = (User) auth.getPrincipal();
        String jwtUserName = a.getUsername();

        Optional<Account> op = service.findByEmail(jwtUserName);
        if (op.isPresent()) {
            AccountDto account = service.convert(op.get());
            return ResponseEntity.ok(account);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity getAccount(@PathVariable("id") Long id) {
        Optional<Account> op = service.findById(id);
        if (op.isPresent()) {
            AccountDto account = service.convert(op.get());
            return ResponseEntity.ok(account);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Account account) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User a = (User) auth.getPrincipal();
        String jwtUserName = a.getUsername();

        try {
            service.updateProfile(jwtUserName, account);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}}
