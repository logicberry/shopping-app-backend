package com.codegenius.shop.wrapper;

import com.codegenius.shop.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String country;

    private UserRole role;


    public UserWrapper(Integer id, String name, String email, String phone,String address, String country, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.country = country;
        this.role = role;
    }
}
