package com.codegenius.shop.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String country;


    public UserWrapper(Integer id, String name, String email, String phone, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }
}
