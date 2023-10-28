package com.codegenius.shop.serviceimpl;

import com.codegenius.shop.JWT.CustomerUserDetailsService;
import com.codegenius.shop.JWT.JwtFilter;
import com.codegenius.shop.POJO.Category;
import com.codegenius.shop.POJO.Product;
import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.dao.ProductDao;
import com.codegenius.shop.service.ProductService;
import com.codegenius.shop.utils.ShopUtils;
import com.codegenius.shop.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    JwtFilter jwtFilter;
    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {

        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productDao.save(getProductFromMap(requestMap));
                    return ShopUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
                }
            } else {
                return ShopUtils.getResponseEntity(ShopConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }



    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap) {
        Product product = new Product();
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));

        return product;
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
