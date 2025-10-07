/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

/**
 *
 * @author fg6zdy
 */
public class WaferMissingInS2VException extends RuntimeException {

    public WaferMissingInS2VException(Throwable cause) {
        super(cause);
    }

    public WaferMissingInS2VException(String message, Throwable cause) {
        super(message, cause);
    }

    public WaferMissingInS2VException(String message) {
        super(message);
    }

    public WaferMissingInS2VException() {
    }
    
}
