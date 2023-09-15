package com.redmath.bankWebApp.exceptions;

import org.hibernate.JDBCException;
import org.hibernate.resource.beans.container.internal.NoSuchBeanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@Controller
@ControllerAdvice
public class BankWebAppExceptionControllerAdvice {

    //    Handling the null pointer exception thrown by the jdbc
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException) {
        return new ResponseEntity<String>("Null value is not allowed", HttpStatus.BAD_REQUEST);
    }

    //    Handling the exception when no account holder found using search operation prior to update or delete
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<String>("Account holder not found!", HttpStatus.BAD_REQUEST);
    }

    //    Handling the exception when the user tries to enter the wrong input in the form fields
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return new ResponseEntity<String>("Your inputs are invalid. Please look into it!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    This error will be caught when there is a bean that needs to be injected, but it doesn't exist
    @ExceptionHandler(NoSuchBeanException.class)
    public ResponseEntity<String> handleNoSuchBeanException(NoSuchBeanException noSuchBeanException) {
        return new ResponseEntity<String>("Required bean doesn't exist", HttpStatus.BAD_REQUEST);
    }

    //    This exception is thr
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleClientErrorException(HttpClientErrorException clientErrorException) {
        return new ResponseEntity<String>("Something is wrong at your hand. Please look into your request!", HttpStatus.BAD_REQUEST);
    }

    //    When the user is not authorized to access a particular resource
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<String> handleForbiddenException() {
        return new ResponseEntity<String>("You are not authorized to access this resource", HttpStatus.FORBIDDEN);
    }

    //    Handling the exception thrown by the jdbc like unique constraint violation. In my db, username and email are unique, So if we try to enter
//    the username or email that already exists in the db, then it will throw this exception
    @ExceptionHandler(JDBCException.class)
    public ResponseEntity<String> handleJDBCException() {
        return new ResponseEntity<String>("Please enter different username or email", HttpStatus.FORBIDDEN);
    }

//    The following exception will be thrown while doing the withdrawal transaction when the user doesn't have the sufficient funds to perform
//    the withdrawal
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException() {
            return new ResponseEntity<String>("You don't have the sufficient funds to perform this transaction", HttpStatus.BAD_REQUEST);
    }

}
