package org.aninha.apirestjava.api.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
    protected AbstractException(String message) {
        super(message); // captura a mensagem da classe que esta herdando (no caso Runtime Ex)
    }

   public abstract HttpStatus getStatus();

}
