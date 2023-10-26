package com.codegenius.shop.rest;

import com.codegenius.shop.wrapper.UserWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/users")

public interface UserRest {

    @GetMapping(path = "/")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
}
