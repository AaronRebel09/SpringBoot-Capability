package com.example.demousers.client;

import com.example.demousers.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "users", url = "http://demo4712563.mockable.io")
public interface UserClient {
    @GetMapping("/api/users/active")
    public List<User> getUsers();
}
