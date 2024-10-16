package com.example.demousers.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("isActive")
    private boolean active;
    @JsonProperty("userName")
    private String userName;

    public User() {
    }

    public User(String userId, boolean active, String userName) {
        this.userId = userId;
        this.active = active;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
