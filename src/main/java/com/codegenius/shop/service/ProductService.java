package com.codegenius.shop.service;

import com.codegenius.shop.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap, MultipartFile imageFile);

    ResponseEntity<List<ProductWrapper>> getAllProduct();

    ResponseEntity<String> delete(Integer id);

    ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    ResponseEntity<ProductWrapper> getProductById(Integer id);


}
