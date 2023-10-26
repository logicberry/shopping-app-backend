package com.codegenius.shop.service;

import com.codegenius.shop.wrapper.UserWrapper;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> register(Map<String, String> requestMap);

     ResponseEntity<String> login(Map<String, String> requestMap);

     ResponseEntity<List<UserWrapper>> getAllUser();
}
