package com.upgrad.bookmyconsultation.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.upgrad.bookmyconsultation.entity.User;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.UserService;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final UserService userService;

    public MyApplicationRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws InvalidInputException {
    	userService.register(User.builder().firstName("ravira").lastName("raviravi").password("test").mobile("9456456352").emailId("ravi@ravi.com").userId("u3879hcd-768d-4d8e-81f6-2c4e4d6c1e6b").salt("test").build());
    }
}