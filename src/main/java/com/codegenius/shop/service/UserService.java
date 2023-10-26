package com.codegenius.shop.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> register(Map<String, String> requestMap);

     ResponseEntity<String> login(Map<String, String> requestMap);

}
