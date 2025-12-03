package org.aninha.apirestjava.api.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
@Log4j2
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    private final View error;

    public ApplicationControllerAdvice(View error) {
        this.error = error;
    }

    public static final String METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE = "Campo invalido: '%s'. Causa: '%s'.";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       logger.error(ex.getMessage(), ex);
       String errorMessage = getErrorMessages(ex);
       HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
       final ErrorResponse errorResponse = new ErrorResponse(ex.getClass(), httpStatus, errorMessage, LocalDateTime.now());
       return new ResponseEntity<>(errorResponse, headers, httpStatus);
   }

   private String getErrorMessages(BindingResult bindingResult) {
       return Stream.concat(
               bindingResult.getFieldErrors().stream().map(this::getMethodArgumentNotValidErrorMessage),
               bindingResult.getFieldErrors().stream().map(this::getMethodArgumentNotValidErrorMessage)
       ).collect(Collectors.joining(","));
   }

   private String getMethodArgumentNotValidErrorMessage(FieldError error) {
       return String.format(METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE, error.getField(), error.getDefaultMessage());
   }

    private String getMethodArgumentNotValidErrorMessage(ObjectError error) {
        return String.format(METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE, error.getObjectName(), error.getDefaultMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExHandler(NotFoundException ex){
        final ErrorResponse errorResponse = new ErrorResponse(ex.getClass(), ex.getStatus(), ex.getMessage(), LocalDateTime.now());
        log.debug(ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){
        final ErrorResponse errorResponse =
                new ErrorResponse( ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
