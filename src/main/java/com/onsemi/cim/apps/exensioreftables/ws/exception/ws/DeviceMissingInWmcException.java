/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;

/**
 *
 * @author fg6zdy
 */
public class DeviceMissingInWmcException extends BusinessException {

    public DeviceMissingInWmcException(Throwable cause) {
        super(cause);
    }

    public DeviceMissingInWmcException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceMissingInWmcException(String message) {
        super(message);
    }
}
