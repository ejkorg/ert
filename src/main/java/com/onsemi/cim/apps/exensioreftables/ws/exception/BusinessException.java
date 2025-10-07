package com.onsemi.cim.apps.exensioreftables.ws.exception;

public class BusinessException extends Exception {

    public BusinessException(String text) {
        super(text);
    }

    public BusinessException(String text, Throwable e) {
        super(text, e);
    }

    public BusinessException(Throwable e) {
        super(e);
    }
}
