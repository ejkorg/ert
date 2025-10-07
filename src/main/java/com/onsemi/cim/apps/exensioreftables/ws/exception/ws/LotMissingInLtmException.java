package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

/**
 * Lot missing in LTM WS exception
 *
 * @author fg6zdy
 */
public class LotMissingInLtmException extends RuntimeException {

    public LotMissingInLtmException(Throwable cause) {
        super(cause);
    }

    public LotMissingInLtmException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotMissingInLtmException(String message) {
        super(message);
    }

    public LotMissingInLtmException() {
    }

}
