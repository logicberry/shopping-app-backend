package com.codegenius.shop.rest;

import com.codegenius.shop.wrapper.ProductWrapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@RequestMapping(path = "/product")
public interface ProductRest {

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addNewProduct(@RequestParam("imageFile") MultipartFile imageFile, @RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PostMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping(path = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);

    @GetMapping(path = "/getByCategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable Integer id);

}
