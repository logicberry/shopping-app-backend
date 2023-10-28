package com.codegenius.shop.wrapper;

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


    public ProductWrapper(Integer id, String name, String description, Integer price,String company, Integer categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.company = company;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }




}
