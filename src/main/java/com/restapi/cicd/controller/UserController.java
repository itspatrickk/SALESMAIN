package com.restapi.cicd.controller;

import com.restapi.cicd.entity.SalesUser;
import com.restapi.cicd.payload.UserRequest;
import com.restapi.cicd.repository.SalesUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BasedController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SalesUserRepository salesUserRepository;
    private final PasswordEncoder passwordEncoder;
    public UserController(SalesUserRepository salesUserRepository, PasswordEncoder passwordEncoder) {
        this.salesUserRepository = salesUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request){
        try {
           displayAllParameter(request);
            SalesUser salesUser = new SalesUser();
            LocalDate localDate = LocalDate.now();
            salesUser.setDateCreated(java.sql.Date.valueOf(localDate));
            salesUser.setUsername(request.getUsername());
            salesUser.setPassword(passwordEncoder.encode(request.getPassword()));
            salesUserRepository.save(salesUser);

            return ResponseEntity.ok("SUCCESSFULLY SAVE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?>signIn(){
        return ResponseEntity.ok("ok");
    }
}
