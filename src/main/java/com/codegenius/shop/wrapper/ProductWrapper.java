package com.codegenius.shop.wrapper;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWrapper {
    Integer id;
    String name;
    String description;
    Integer price;
    String company;
    Integer categoryId;
    String categoryName;
    String imageUrl;






    public ProductWrapper(Integer id, String name, String description, Integer price,String company,String imageUrl, Integer categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.company = company;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }




}
