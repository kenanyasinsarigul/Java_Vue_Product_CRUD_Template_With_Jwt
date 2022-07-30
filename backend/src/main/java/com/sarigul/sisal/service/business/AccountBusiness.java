package com.sarigul.sisal.service.business;

import com.sarigul.sisal.entity.Account;
import com.sarigul.sisal.entity.model.AccountDto;
import com.sarigul.sisal.entity.model.AuthDto;
import com.sarigul.sisal.entity.model.JwtResponse;
import com.sarigul.sisal.repository.AccountJpaDao;
import com.sarigul.sisal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountBusiness implements AccountService {

    private final AccountJpaDao dao;

    public AccountBusiness(AccountJpaDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public Account save(Account account) {
        return this.dao.save(account);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return this.dao.findById(id);
    }

    @Transactional
    @Override
    public void updateProfile(String jwtUserName, Account account) throws Exception {
        Account record = dao.findByEmail(jwtUserName)
                .orElseThrow(() -> new Exception("Böyle bir kayıt yok"));

        if (jwtUserName.equals(record.getAuth().getEmail())) {
            record.setAddress(account.getAddress());
            record.setBirthday(account.getBirthday());
            record.setPhone(account.getPhone());
            record.setName(account.getName());
            dao.save(record);
        } else {
            System.out.println("Bu işlem için yetkiniz bulunmuyor");
        }
    }

    @Override
    public JwtResponse getUserWithJwtToken(String email, String token) {
        Optional<Account> control = findByEmail(email);
        if (control.isPresent()) {
            Account account = control.get();
            AccountDto dto = this.convert(account);
            return new JwtResponse(token, dto);
        }
        return null;
    }

    @Override
    public AccountDto convert(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAddress(account.getAddress());
        accountDto.setName(account.getName());
        accountDto.setSurname(account.getSurname());
        accountDto.setPhone(account.getPhone());
        accountDto.setAddress(account.getAddress());
        accountDto.setBirthday(account.getBirthday());

        AuthDto authDto = new AuthDto();
        authDto.setEmail(account.getAuth().getEmail());
        authDto.setId(account.getAuth().getId());
        accountDto.setAuth(authDto);
        return accountDto;
    }


    @Override
    public long count() {
        return this.dao.count();
    }
}
