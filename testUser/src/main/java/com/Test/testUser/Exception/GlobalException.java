package com.Test.testUser.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

// @ControllerAdvice
// public class GlobalException {

//     // @ExceptionHandler(NoResourceFoundException.class)
//     // public String NoDataException(){
//     //     return "Id Nhi Mila . Sahi Id Enter Karein";
//     // }
    
//     // Enhanced and Updated Version Of Upper code of Exception Handling
//     @ExceptionHandler(NoResourceFoundException.class)
//     public ResponseEntity<String> NoDataException(){

//         String message = "Id Nhi Mila . Sahi Id Enter Karein";
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//     }


//     @ExceptionHandler(NoSuchElementException.class)
//     public ResponseEntity<String> IdNotFoundException(){
//         String msg = "Database me Id Nhi Mila . Sahi Id Enter Karein";
//         // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
//         return ResponseEntity.status(HttpStatusCode.valueOf(100)).body(msg);
//     }
    

    
    
// }
//---------------------------👇👇👇👇👇👇👇👇👇👇👇👇👇👇--------------

// NOTE => Valid And Enhanced Version

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> IdNotFoundException(NoSuchElementException ex){

        Map<String, Object> error = new HashMap<>();
        error.put("error", "ID Nhi Mili");
        error.put("Message", ex.getMessage());
        error.put("Status Code", HttpStatus.NOT_FOUND.value());  
        error.put("TimeStamp", LocalDateTime.now());   
        
        return new ResponseEntity<Map<String,Object>>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Map<String, Object>> IdNotFoundHandler(IdNotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("Error", "ID Nhi Mili database Me");
        error.put("Message", ex.getMessage());
        error.put("Status", HttpStatus.NOT_FOUND.value());                         
        error.put("TimeStamp", LocalDateTime.now());
        error.put("Admin", "Kon hai be");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}

// These all are Status with code 

// error.put("Status", HttpStatus.ALREADY_REPORTED .value());                      // code => 208
// error.put("Status", HttpStatus.BAD_REQUEST .value());                           // code => 400
// error.put("Status", HttpStatus.BAD_GATEWAY .value());                           // code => 502
// error.put("Status", HttpStatus.ACCEPTED .value());                              // code => 202
// error.put("Status", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED .value());              // code => 509    
// error.put("Status", HttpStatus.NOT_ACCEPTABLE .value());                        // code => 406    
// error.put("Status", HttpStatus.NOT_IMPLEMENTED .value());                       // code => 501    
// error.put("Status", HttpStatus.NO_CONTENT .value());                            // code => 204    
// error.put("Status", HttpStatus.METHOD_NOT_ALLOWED .value());                    // code => 405   
// error.put("Status", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED .value());       // code => 511 
// error.put("Status", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());                 // code => 415 