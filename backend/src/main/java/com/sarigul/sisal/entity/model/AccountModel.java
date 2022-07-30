package com.sarigul.sisal.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    private long id;
    private String name;
    private String surname;
    private String mail;
}
