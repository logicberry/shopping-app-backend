package com.codegenius.shop.restimpl;

import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.rest.UserRest;
import com.codegenius.shop.service.UserService;
import com.codegenius.shop.utils.ShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
       try {
           return userService.register(requestMap);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
