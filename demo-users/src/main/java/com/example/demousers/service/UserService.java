package com.example.demousers.service;

import com.example.demousers.client.UserClient;
import com.example.demousers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserClient userClient;

    public List<User> getActiveUsers() {
        if ( userClient.getUsers() == null ) {
            return new ArrayList<User>();
        }

        return  userClient.getUsers().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

}
