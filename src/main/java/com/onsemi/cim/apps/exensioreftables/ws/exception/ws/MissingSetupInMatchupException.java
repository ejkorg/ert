package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

/**
 * @author fg8n8x
 */
public class MissingSetupInMatchupException extends RuntimeException {
    public MissingSetupInMatchupException(Throwable cause) {
        super(cause);
    }
    public MissingSetupInMatchupException(String message, Throwable cause) {
        super(message, cause);
    }
    public MissingSetupInMatchupException(String message) {
        super(message);
    }
    public MissingSetupInMatchupException() {

    }
}
