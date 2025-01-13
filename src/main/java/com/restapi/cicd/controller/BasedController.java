package com.restapi.cicd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Objects;



@Configuration
public class BasedController {
    private static final Logger logger = LoggerFactory.getLogger(BasedController.class);
    public void displayAllParameter (Object request){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(request);

            logger.info("request:{}" + jsonString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
