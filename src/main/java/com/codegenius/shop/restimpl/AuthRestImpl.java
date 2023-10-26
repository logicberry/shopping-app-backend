package com.codegenius.shop.restimpl;

import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.rest.AuthRest;
import com.codegenius.shop.service.AuthService;
import com.codegenius.shop.utils.ShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class AuthRestImpl implements AuthRest {
    @Autowired
    AuthService authService;

    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
       try {
           return authService.register(requestMap);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
      return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return authService.login(requestMap);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
