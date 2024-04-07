package com.codegenius.shop.serviceimpl;
import com.codegenius.shop.JWT.JwtFilter;
import com.codegenius.shop.POJO.User;
import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.dao.UserDao;
import com.codegenius.shop.service.UserService;
import com.codegenius.shop.utils.PasswordUtils;
import com.codegenius.shop.utils.ShopUtils;
import com.codegenius.shop.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Autowired
    JwtFilter jwtfilter;

    @Override
    public ResponseEntity<UserWrapper> viewProfile() {
        try {
            String currentUserEmail = jwtfilter.getCurrentUser();
            UserWrapper userWrapper = userDao.viewProfile(currentUserEmail);
                return new ResponseEntity<>(userWrapper, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtfilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
           User user = userDao.findByEmailId(jwtfilter.getCurrentUser());
           if (user != null) {
               user.setName(requestMap.getOrDefault("name", user.getName()));
               user.setPhone(requestMap.getOrDefault("phone", user.getPhone()));
               user.setAddress(requestMap.getOrDefault("address", user.getAddress()));
               user.setCountry(requestMap.getOrDefault("country", user.getCountry()));
               userDao.save(user);
               return ShopUtils.getResponseEntity("User Data Successfully updated", HttpStatus.OK);
           }
            return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            String currentUserEmail = jwtfilter.getCurrentUser();
            User user = userDao.findByEmailId(currentUserEmail);

            if (user != null) {

                if (PasswordUtils.verifyPassword(requestMap.get("oldPassword"), user.getPassword())) {
                    String newPassword = PasswordUtils.hashPassword(requestMap.get("newPassword"));
                    user.setPassword(newPassword);
                    userDao.save(user);
                    return ShopUtils.getResponseEntity("Password Updated successfully", HttpStatus.OK);
                } else {
                    return ShopUtils.getResponseEntity("Incorrect Old password", HttpStatus.BAD_REQUEST);
                }
            }
            return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
