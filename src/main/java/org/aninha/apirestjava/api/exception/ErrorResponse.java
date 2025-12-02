package org.aninha.apirestjava.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private Class <?> excpetionClass;
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime timestamp;


    public <T> ErrorResponse(Class <T> excpetionClass, HttpStatus httpStatus, String message, LocalDateTime timestamp) {
        this.excpetionClass = excpetionClass;
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }


}
