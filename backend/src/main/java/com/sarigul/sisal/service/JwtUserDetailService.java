package com.sarigul.sisal.service;

import com.sarigul.sisal.entity.Auth;
import com.sarigul.sisal.service.business.AuthBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final AuthBusiness authBusiness;

    public JwtUserDetailService(AuthBusiness authBusiness) {
        this.authBusiness = authBusiness;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Auth> user = authBusiness.findByEmail(email);
        if (user.isPresent()) {
            if (!user.get().isActive()) {
                throw new UsernameNotFoundException("User is not actived");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
    }
    }
