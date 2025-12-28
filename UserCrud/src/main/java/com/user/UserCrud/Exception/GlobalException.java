package com.user.UserCrud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalException {

    // @ExceptionHandler(NoResourceFoundException.class)
    // public String NoDataException(){
    //     return "Id Nhi Mila . Sahi Id Enter Karein";
    // }
    
    // Enhanced and Updated Version Of Upper code of Exception Handling
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> NoDataException(){

        String message = "Id Nhi Mila . Sahi Id Enter Karein";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<String> IdNotFoundException(Integer Id){
        String msg = "Database me Id Nhi Mila . Sahi Id Enter Karein";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
    // @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    // public String TypesMissmatchException(){
    //     return "Type Missmatch ho gya data ";
    // }
}
