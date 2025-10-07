package com.onsemi.cim.apps.exensioreftables.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnLotNotFoundException extends ResponseStatusException {

    public OnLotNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnLot by id '%d'", id));
    }

    public OnLotNotFoundException(Long id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnLot by id '%d'", id), t);
    }

    public OnLotNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnLot by lotId '%s'", id));
    }

    public OnLotNotFoundException(String id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnLot by lotId '%s'", id), t);
    }
}
