package com.sarigul.sisal.entity.model;

import lombok.Data;

@Data
public class AccountDto {
    private long id;
    private String name;
    private String surname;
    private String phone ;
    private String address;
    private String birthday;
    private AuthDto auth;
}