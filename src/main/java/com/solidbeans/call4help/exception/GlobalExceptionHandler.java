package com.solidbeans.call4help.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {


    //REGISTRATION UNAUTHORIZED
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> registrationExceptionExceptionHandling(RegistrationException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    //Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandling(NotFoundException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //Not Accepted
    @ExceptionHandler(NotAcceptedException.class)
    public ResponseEntity<Object> notAcceptedExceptionExceptionHandling(NotAcceptedException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    //Unauthorized
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedExceptionHandling(UnauthorizedException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    //Unprocessable Entity
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> unprocessableEntityExceptionHandling(UnprocessableEntityException exception, WebRequest request){
        String errorMessageDesc = exception.getLocalizedMessage();
        if (errorMessageDesc == null) errorMessageDesc=exception.toString();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMessageDesc, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
