package com.example.genusers;

import com.example.genusers.client.ApiClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UsersApi
 */
public class UsersApiTest {

    private UsersApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(UsersApi.class);
    }

    
    /**
     * Get list of active users
     *
     * 
     */
    @Test
    public void getActiveUsersTest() {
        // api.getActiveUsers();

        // TODO: test validations
    }

    
}
