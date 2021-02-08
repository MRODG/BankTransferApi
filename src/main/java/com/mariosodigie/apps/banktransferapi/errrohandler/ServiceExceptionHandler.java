package com.mariosodigie.apps.banktransferapi.errrohandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String error = "JSON request has an invalid format";
        return buildResponseEntity(new ServiceError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ServiceError serviceError) {
        return new ResponseEntity<>(serviceError, serviceError.getStatus());
    }

    @ExceptionHandler(AccountNumberNotFoundException.class)
    protected ResponseEntity<Object> handleAccountNumberNotFound(
            AccountNumberNotFoundException ex) {
        ServiceError serviceError = new ServiceError(HttpStatus.NOT_FOUND);
        serviceError.setMessage(ex.getMessage());
        serviceError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(serviceError);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    protected ResponseEntity<Object> handleInsufficientFunds(
            InsufficientFundsException ex) {
        ServiceError serviceError = new ServiceError(HttpStatus.NOT_ACCEPTABLE);
        serviceError.setMessage(ex.getMessage());
        serviceError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(serviceError);
    }
}
