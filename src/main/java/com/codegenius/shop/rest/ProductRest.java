package com.codegenius.shop.rest;

import com.codegenius.shop.wrapper.ProductWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
@RequestMapping(path = "/product")
public interface ProductRest {

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();
}
