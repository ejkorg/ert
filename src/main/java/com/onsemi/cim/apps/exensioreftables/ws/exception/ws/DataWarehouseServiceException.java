package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;

/**
 * All checked exception thrown from DataWarehouseService should be wrapped into
 * this one.
 *
 * @author fg6zdy
 */
public class DataWarehouseServiceException extends BusinessException {

    public DataWarehouseServiceException(String message) {
        super(message);
    }

    public DataWarehouseServiceException(Throwable t) {
        super(t);
    }

    public DataWarehouseServiceException(String message, Throwable t) {
        super(message, t);
    }
}
