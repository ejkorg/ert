package com.onsemi.cim.apps.exensioreftables.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnFabConfNotFoundException extends ResponseStatusException {

    public OnFabConfNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnFabConf by id '%d'", id));
    }

    public OnFabConfNotFoundException(Long id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnFabConf by id '%d'", id), t);
    }

    public OnFabConfNotFoundException(String fab, String dataType) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnFabConf by fab '%s', dataType '%s'", fab, dataType));
    }

    public OnFabConfNotFoundException(String fab, String dataType, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnFabConf by fab '%s', dataType '%s'", fab, dataType), t);
    }
}
