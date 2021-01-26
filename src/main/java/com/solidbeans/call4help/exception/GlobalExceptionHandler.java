package com.solidbeans.call4help.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {


    //NOT UNAUTHORIZED
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> notAcceptedExceptionExceptionHandling(RegistrationException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
