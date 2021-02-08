package com.mariosodigie.apps.banktransferapi.errrohandler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    //private List<ApiSubError> subErrors;

    private ServiceError() {
        timestamp = LocalDateTime.now();
    }

    ServiceError(HttpStatus status) {
        this();
        this.status = status;
    }

    ServiceError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ServiceError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
