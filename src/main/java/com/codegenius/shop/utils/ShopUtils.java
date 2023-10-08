package com.codegenius.shop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ShopUtils {
    private ShopUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String resMes, HttpStatus status) {
        return  new ResponseEntity<>("{\"message\":\"" + resMes + "\"}", status);
    }
}
