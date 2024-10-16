package com.example.demousers.controller;

import com.example.demousers.model.User;
import com.example.demousers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/active")
    public List<User> getActiveUsers(){
        return userService.getActiveUsers();
    }

}
