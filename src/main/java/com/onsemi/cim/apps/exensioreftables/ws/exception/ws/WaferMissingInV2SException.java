/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

/**
 *
 * @author fg6zdy
 */
public class WaferMissingInV2SException extends RuntimeException {

    public WaferMissingInV2SException(Throwable cause) {
        super(cause);
    }

    public WaferMissingInV2SException(String message, Throwable cause) {
        super(message, cause);
    }

    public WaferMissingInV2SException(String message) {
        super(message);
    }

    public WaferMissingInV2SException() {
    }
    
}
