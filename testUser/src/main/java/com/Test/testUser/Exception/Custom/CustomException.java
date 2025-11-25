package com.Test.testUser.Exception.Custom;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomException extends ResponseEntityExceptionHandler {
    
    protected ResponseEntity<Object> handleMethodArgumentNotValid(

        MethodArgumentNotValidException ex,
        HttpHeaders header,
        HttpStatusCode status,
        WebRequest req
    ){
        Map<String, Object> error = new HashMap<>();
        error.put("Status", status.value());
        error.put("Error", ex.getBindingResult().getFieldError());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
