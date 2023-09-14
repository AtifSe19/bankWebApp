package com.redmath.bankWebApp.exceptions;

import jakarta.persistence.ElementCollection;
import org.hibernate.event.spi.ResolveNaturalIdEvent;
import org.hibernate.resource.beans.container.internal.NoSuchBeanException;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;

@Controller
@ControllerAdvice
public class BankControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException){
        return new ResponseEntity<String>("Null value is not allowed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        return new ResponseEntity<String>("Element not found!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound notFound){
        return new ResponseEntity<String>("Element not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(HttpServerErrorException.InternalServerError internalServerError){
        return new ResponseEntity<String>("Backend Server is not able to process  your request at this time", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchBeanException.class)
    public ResponseEntity<String> handleNoSuchBeanException(NoSuchBeanException noSuchBeanException){
        return new ResponseEntity<String>("Required bean doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    public ResponseEntity<String> handleNoMethodAllowedException(HttpClientErrorException.MethodNotAllowed methodNotAllowed){
        return new ResponseEntity<String>("Method not allowed. Please change the reqeust and look into it", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleClientErrorException(HttpClientErrorException clientErrorException){
        return new ResponseEntity<String>("Something is wrong at your hand. Please look into your request!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<String> handleForbiddenException(){
        return new ResponseEntity<String>("You are not authorized to access this resource", HttpStatus.FORBIDDEN);
    }
}
