package com.codegenius.shop.POJO;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;


@NamedQuery(name = "Product.getAllProduct", query = "select new com.codegenius.shop.wrapper.ProductWrapper(p.id,p.name,p.description,p.price,p.company,p.imageUrl,p.category.id,p.category.name) from Product p")
@NamedQuery(name = "Product.getProductById", query = "select new com.codegenius.shop.wrapper.ProductWrapper(p.id,p.name,p.description,p.price,p.company,p.imageUrl,p.category.id,p.category.name) from Product p where p.id=:id")

@NamedQuery(name = "Product.getByCategory", query = "select new com.codegenius.shop.wrapper.ProductWrapper(p.id,p.name,p.description,p.price,p.company,p.imageUrl,p.category.id,p.category.name) from Product p where p.category.id=:id")


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "company")
    private String company;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_public_id")
    private String imagePublicId;




}
