package com.codegenius.shop.POJO;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;


@NamedQuery(name = "Product.getAllProduct", query = "select new com.codegenius.shop.wrapper.ProductWrapper(p.id,p.name,p.description,p.price,p.category.id,p.category.name) from Product p")
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




}
