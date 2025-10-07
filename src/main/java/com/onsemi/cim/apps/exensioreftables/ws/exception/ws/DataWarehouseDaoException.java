package com.onsemi.cim.apps.exensioreftables.ws.exception.ws;

import java.sql.SQLException;

public class DataWarehouseDaoException extends SQLException{

    public DataWarehouseDaoException() {
    }

    public DataWarehouseDaoException(String message) {
        super(message);
    }

    public DataWarehouseDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataWarehouseDaoException(Throwable cause) {
        super(cause);
    }
}
