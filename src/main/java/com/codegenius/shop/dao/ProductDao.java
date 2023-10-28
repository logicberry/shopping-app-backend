package com.codegenius.shop.dao;

import com.codegenius.shop.POJO.Product;
import com.codegenius.shop.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<ProductWrapper> getAllProduct();


}
