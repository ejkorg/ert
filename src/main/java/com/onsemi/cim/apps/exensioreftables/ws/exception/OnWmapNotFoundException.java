package com.onsemi.cim.apps.exensioreftables.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnWmapNotFoundException extends ResponseStatusException {

    public OnWmapNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnWmap by id '%d'", id));
    }

    public OnWmapNotFoundException(Long id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnWmap by id '%d'", id), t);
    }

    public OnWmapNotFoundException(String product) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnWmap by product '%s'", product));
    }

    public OnWmapNotFoundException(String product, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnWmap by product '%s'", product), t);
    }
}
