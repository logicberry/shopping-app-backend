package com.codegenius.shop.wrapper;

import com.codegenius.shop.POJO.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWrapper {
    Integer id;
    String name;
    String description;
    Double price;
    Double initialPrice;
    String company;
    Category category;
    String imageUrl;






    public ProductWrapper(Integer id, String name, String description, Double price, Double initialPrice, String company,String imageUrl, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.initialPrice = initialPrice;
        this.company = company;
        this.imageUrl = imageUrl;
        this.category = category;
    }




}


