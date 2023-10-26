package com.codegenius.shop.POJO;


import com.codegenius.shop.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")

@NamedQuery(name = "User.getAllUser" ,query = "select new com.codegenius.shop.wrapper.UserWrapper(u.id,u.name,u.email) from User u where u.role='user'")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "address")
    private String address;

    @PrePersist
    private void prePersist() {
        // Set default values for role and address
        if (role == null) {
            role = UserRole.USER;
        }
        if (address == null) {
            address = "";
        }
    }
}
