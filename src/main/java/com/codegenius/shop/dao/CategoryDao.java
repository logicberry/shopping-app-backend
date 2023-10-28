package com.codegenius.shop.dao;

import com.codegenius.shop.POJO.Category;
import com.codegenius.shop.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    List<Category> getAllCategory();

}
