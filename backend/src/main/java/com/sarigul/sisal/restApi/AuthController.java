package com.sarigul.sisal.restApi;


import com.sarigul.sisal.entity.model.AuthDto;
import com.sarigul.sisal.entity.model.JwtRequest;
import com.sarigul.sisal.entity.model.JwtResponse;
import com.sarigul.sisal.entity.model.RegisterRequest;
import com.sarigul.sisal.service.AccountService;
import com.sarigul.sisal.service.AuthService;
import com.sarigul.sisal.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final AuthService authService;

    private final AccountService accountService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService jwtUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, AccountService accountService, JwtTokenUtil jwtTokenUtil, UserDetailsService jwtUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        JwtResponse jwt = accountService.getUserWithJwtToken(userDetails.getUsername(), token);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public AuthDto add(@RequestBody RegisterRequest request) throws Exception {
        return this.authService.register(request);
    }

    private void authenticate(String email, String password) throws Exception {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User is not active", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }
    }
}