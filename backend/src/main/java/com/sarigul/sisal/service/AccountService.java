package com.sarigul.sisal.service;

import com.sarigul.sisal.entity.Account;
import com.sarigul.sisal.entity.model.AccountDto;
import com.sarigul.sisal.entity.model.JwtResponse;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account save(Account account);
    Optional<Account> findByEmail(String email);
    Optional<Account> findById(Long id);
    void updateProfile(String jwtUserName, Account account) throws Exception;
    JwtResponse getUserWithJwtToken(String email, String token);
    AccountDto convert(Account account);
    long count();
}
