package com.codegenius.shop.serviceimpl;

import com.codegenius.shop.JWT.CustomerUserDetailsService;
import com.codegenius.shop.JWT.JwtUtil;
import com.codegenius.shop.POJO.User;
import com.codegenius.shop.constants.ShopConstants;
import com.codegenius.shop.dao.UserDao;
import com.codegenius.shop.enums.UserRole;
import com.codegenius.shop.service.AuthService;
import com.codegenius.shop.utils.PasswordUtils;
import com.codegenius.shop.utils.ShopUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service

public class AuthServiceImpl implements AuthService {


    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;



    @Autowired
    CustomerUserDetailsService customerUserDetailsService;


    @Override
    public ResponseEntity<String> register(Map<String, String> requestMap) {
        if (!validateCredentials(requestMap)) {
            return ShopUtils.getResponseEntity(ShopConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
        }

        User user = userDao.findByEmailId(requestMap.get("email"));
        if (user != null) {
            return ShopUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = getUSerFromMap(requestMap);
        userDao.save(newUser);

        return ShopUtils.getResponseEntity("Registration Successful", HttpStatus.OK);
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

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {

        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            if (auth.isAuthenticated()) {
                    return new ResponseEntity<>("{\"token\":\"" + jwtUtil.generateToken(
                            customerUserDetailsService.getUserDetails().getEmail(), String.valueOf(customerUserDetailsService.getUserDetails().getRole())) + "\"}",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"" + "Account not verified." + "\"}",
                            HttpStatus.BAD_REQUEST);
                }

        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return new ResponseEntity<>("{\"message\":\"" + "Bad Credentials." + "\"}",
                HttpStatus.BAD_REQUEST);
    }


}