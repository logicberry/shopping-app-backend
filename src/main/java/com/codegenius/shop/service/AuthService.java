package com.codegenius.shop.service;

import com.codegenius.shop.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AuthService {

    ResponseEntity<String> register(Map<String, String> requestMap);

     ResponseEntity<String> login(Map<String, String> requestMap);


}
