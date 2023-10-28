package com.codegenius.shop.dao;

import com.codegenius.shop.POJO.User;
import com.codegenius.shop.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
   User findByEmailId(@Param("email") String email);

   UserWrapper viewProfile();

   List<UserWrapper> getAllUser();

   User findByEmail(String email);

}
