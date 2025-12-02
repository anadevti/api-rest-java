package org.aninha.apirestjava.api.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractException {
    protected NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }

}
