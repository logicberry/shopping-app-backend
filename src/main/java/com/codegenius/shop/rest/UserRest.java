package com.codegenius.shop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/auth")

public interface UserRest {
    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody(required = true) Map<String, String> requestMap);
}
