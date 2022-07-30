package com.sarigul.sisal.entity.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 4773313784731829583L;
    private String email;
    private String password;

    public JwtRequest() {}
    public JwtRequest(String username, String password) {
        this.setEmail(username);
        this.setPassword(password);
    }
}