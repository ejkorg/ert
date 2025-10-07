package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;

/**
 * All checked exception thrown from DataWarehouseService should be wrapped into
 * this one.
 *
 * @author fg6zdy
 */
public class LotGDbServiceException extends BusinessException {

    public LotGDbServiceException(String message) {
        super(message);
    }

    public LotGDbServiceException(Throwable t) {
        super(t);
    }

    public LotGDbServiceException(String message, Throwable t) {
        super(message, t);
    }
}
