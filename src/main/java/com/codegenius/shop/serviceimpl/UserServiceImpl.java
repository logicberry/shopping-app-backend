package com.codegenius.shop.serviceimpl;

import com.codegenius.shop.POJO.User;
import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.dao.UserDao;
import com.codegenius.shop.enums.UserRole;
import com.codegenius.shop.service.UserService;
import com.codegenius.shop.utils.PasswordUtils;
import com.codegenius.shop.utils.ShopUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Objects;

@Slf4j

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
        try {
            if(validateCredentials(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user)) {
                    userDao.save(getUSerFromMap(requestMap));
                    return ShopUtils.getResponseEntity("Registration Successful", HttpStatus.OK);
                } else {
                    return ShopUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return ShopUtils.getResponseEntity(ShopConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCredentials(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private User getUSerFromMap(Map<String, String> requestMap) {
    User user = new User();
    user.setName(requestMap.get("name"));
    user.setEmail(requestMap.get("email"));
    String hashedPassword = PasswordUtils.hashPassword(requestMap.get("password"));
    user.setPassword(hashedPassword);
    if (requestMap.containsKey("role")) {
        user.setRole(UserRole.valueOf(requestMap.get("role").trim().toUpperCase()));
    }
    return user;
}
}
