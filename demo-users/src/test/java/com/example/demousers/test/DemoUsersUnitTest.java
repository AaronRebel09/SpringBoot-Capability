package com.example.demousers.test;

import com.example.demousers.controller.UserController;
import com.example.demousers.exception.ApiError;
import com.example.demousers.exception.CustomException;
import com.example.demousers.exception.GlobalExceptionHandler;
import com.example.demousers.model.User;
import com.example.demousers.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
public class DemoUsersUnitTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetActiveUsers() throws Exception {
        User user1 = new User("1", true, "John Doe");
        User user2 = new User("2", true, "Jane Doe");

        List<User> activeUsers = Arrays.asList(user1, user2);

        when(userService.getActiveUsers()).thenReturn(activeUsers);

        mockMvc.perform(get("/api/users/active")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'userId':'1','isActive':true,'userName':'John Doe'},{'userId':'2','isActive':true,'userName':'Jane Doe'}]"));
    }

    @Test
    public void testHandleCustomException() {
        CustomException ex = new CustomException("Custom error");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleCustomException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Custom error occurred", ((ApiError) response.getBody()).getMessage());
    }

    @Test
    public void testHandleConstraintViolation() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleConstraintViolation(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testHandleHttpRequestMethodNotSupported() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("POST", Collections.singleton("GET"));
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleHttpRequestMethodNotSupported(ex, headers, status, request);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    public void testHandleHttpMediaTypeNotSupported() {
        HttpMediaTypeNotSupportedException ex = new HttpMediaTypeNotSupportedException("application/xml", Collections.<MediaType>singletonList(MediaType.valueOf("application/json")));
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleHttpMediaTypeNotSupported(ex, headers, status, request);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    public void testHandleHttpMessageNotReadable() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Malformed JSON request", new Throwable());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleHttpMessageNotReadable(ex, headers, status, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Malformed JSON request", ((ApiError) response.getBody()).getMessage());
    }

    @Test
    public void testHandleGlobalException() {
        Exception ex = new Exception("An unexpected error occurred");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity response = globalExceptionHandler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred", ((ApiError) response.getBody()).getMessage());
    }

}
