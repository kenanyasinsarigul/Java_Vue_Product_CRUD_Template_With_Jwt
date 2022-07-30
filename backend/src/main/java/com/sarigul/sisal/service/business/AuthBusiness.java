package com.sarigul.sisal.service.business;

import com.sarigul.sisal.entity.Account;
import com.sarigul.sisal.entity.Auth;
import com.sarigul.sisal.entity.model.AuthDto;
import com.sarigul.sisal.entity.model.RegisterRequest;
import com.sarigul.sisal.repository.AuthJpaRepository;
import com.sarigul.sisal.service.AccountService;
import com.sarigul.sisal.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthBusiness implements AuthService {

    private final AuthJpaRepository authJpaRepository;

    private final AccountService accountService;

    public AuthBusiness(AuthJpaRepository authJpaRepository, AccountService accountService) {
        this.authJpaRepository = authJpaRepository;
        this.accountService = accountService;
    }

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public AuthDto register(RegisterRequest request) throws Exception {

        Optional<Auth> control = authJpaRepository.findByEmail(request.getEmail());

        if (control.isPresent()) {
            throw new Exception("Email address is already in use");
        }

        Auth user = new Auth();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        Auth dbAuth = authJpaRepository.save(user);

        Account account = new Account();
        account.setAddress(request.getAddress());
        account.setName(request.getName());
        account.setSurname(request.getSurname());
        account.setPhone(request.getPhone());
        account.setAuth(dbAuth);
        account.setBirthday(request.getBirthday());
        accountService.save(account);

        AuthDto dto = new AuthDto(user.getId(), user.getEmail());

        return dto;
    }
    @Override
    public Optional<Auth> findByEmail(String email) {
        return authJpaRepository.findByEmail(email);
    }
}
