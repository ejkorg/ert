package com.onsemi.cim.apps.exensioreftables.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OnProdNotFoundException extends ResponseStatusException {

    public OnProdNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnProd by id '%d'", id));
    }

    public OnProdNotFoundException(Long id, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnProd by id '%d'", id), t);
    }

    public OnProdNotFoundException(String product) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnProd by product '%s'", product));
    }

    public OnProdNotFoundException(String product, Throwable t) {
        super(HttpStatus.NOT_FOUND, String.format("Could not find OnProd by product '%s'", product), t);
    }
}
