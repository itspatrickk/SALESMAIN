package com.restapi.cicd.controller;

import com.restapi.cicd.payload.UserRequest;
import com.restapi.cicd.repository.SalesUserRepository;
import com.restapi.cicd.response.CustomApiResponse;
import com.restapi.cicd.services.SqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BasedController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesUserRepository salesUserRepository;
    private final PasswordEncoder passwordEncoder;

    private  final SqlService sqlService;

    public UserController(SalesUserRepository salesUserRepository, PasswordEncoder passwordEncoder, SqlService sqlService) {
        this.salesUserRepository = salesUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.sqlService = sqlService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<CustomApiResponse<UserRequest>> registerUser(@RequestBody UserRequest request){
        try {
            displayAllParameter(request);

            boolean usernameExists = salesUserRepository.existsByUsername(request.getUsername());
            if (usernameExists == true) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new CustomApiResponse<>("error" , request , "username already exist.") );
            }
            sqlService.saveUser(request);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomApiResponse<>("success" , request , "successfully save"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<UserRequest>>signIn(@RequestBody UserRequest userRequest){
        try {
            sqlService.logIn(userRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
