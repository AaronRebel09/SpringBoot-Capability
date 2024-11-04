package com.example.genusers;

import com.example.genusers.client.ApiClient;
import com.example.genusers.client.EncodingUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2024-11-04T10:28:00.691-06:00")
public interface UsersApi extends ApiClient.Api {


  /**
   * Get list of active users
   * 
   */
  @RequestLine("GET /api/users/active")
  @Headers({
    "Accept: application/json",
  })
  void getActiveUsers();
}
