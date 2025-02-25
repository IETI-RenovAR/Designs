package edu.escuelaing.IETI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DesignNotFound extends RuntimeException {
    public DesignNotFound(String id) {
        super("Design with id: " + id + " not found.");
    }
}
