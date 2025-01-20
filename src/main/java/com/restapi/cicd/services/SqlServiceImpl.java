package com.restapi.cicd.services;


import com.restapi.cicd.entity.SalesUser;
import com.restapi.cicd.payload.UserRequest;
import com.restapi.cicd.repository.SalesUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

@Service
@Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
public class SqlServiceImpl implements SqlService{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    private final SalesUserRepository salesUserRepository;
    private final PasswordEncoder passwordEncoder;

    private  Connection connection;
    public SqlServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate, SalesUserRepository salesUserRepository, PasswordEncoder passwordEncoder) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.salesUserRepository = salesUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(UserRequest request) {

        SalesUser salesUser = null;
        try {
            salesUser = new SalesUser();

            salesUser.setUsername(request.getUsername());
            salesUser.setPassword(passwordEncoder.encode(request.getPassword()));
            salesUserRepository.save(salesUser);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logIn(UserRequest request) {


        try {
            
            String sql = "SELECT role FROM sales_user_mst WHERE username = ? AND password = ?";


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
