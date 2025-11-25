package com.Test.testUser.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id Not Found in Database")
public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message){
        super(message);
    }
}

