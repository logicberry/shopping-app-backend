package com.codegenius.shop.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {
    private Integer id;

    private String name;

    private String email;


    public UserWrapper(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
