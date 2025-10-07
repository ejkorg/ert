package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

/**
 * Lot is missing in LotG exception
 * @author fg6zdy
 */
public class LotMissingInLotGException extends RuntimeException{

    public LotMissingInLotGException(Throwable cause) {
        super(cause);
    }

    public LotMissingInLotGException(String message, Throwable cause) {
        super(message, cause);
    }

    public LotMissingInLotGException(String message) {
        super(message);
    }

    public LotMissingInLotGException() {
    }
    
}
