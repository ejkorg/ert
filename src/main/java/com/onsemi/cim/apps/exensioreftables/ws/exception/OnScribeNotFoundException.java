package com.onsemi.cim.apps.exensioreftables.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnScribeNotFoundException extends ResponseStatusException {

    public OnScribeNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by id '%d'", id));
    }

    public OnScribeNotFoundException(Long id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by id '%d'", id), t);
    }

    public OnScribeNotFoundException(String lotId, String waferNum) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by lotId '%s', waferNum '%s'", lotId, waferNum));
    }

    public OnScribeNotFoundException(String lotId, String waferNum, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by lotId '%s', waferNum '%s'", lotId, waferNum), t);
    }

    public OnScribeNotFoundException(String scribeId) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by scribeId '%s'", scribeId));
    }

    public OnScribeNotFoundException(String scribeId, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnScribe by scribeId '%s'", scribeId), t);
    }
}
