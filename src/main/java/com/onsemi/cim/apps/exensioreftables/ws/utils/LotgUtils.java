package com.onsemi.cim.apps.exensioreftables.ws.utils;

import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseMfgArea;

public class LotgUtils {

    private LotgUtils() {
    }

    public static String getFab(String origFab, DataWarehouseMfgArea dwha) {
        if (dwha != null && dwha.getMfgAreaDesc() != null) {
            final String fab;
            if (origFab != null && !origFab.isEmpty()) {
                fab = origFab + ":";
            } else {
                fab = "";
            }
            return fab + dwha.getMfgAreaDesc();
        }
        return origFab;
    }
}
