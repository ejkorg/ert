package com.onsemi.cim.apps.exensioreftables.ws.utils;

import org.apache.commons.lang.StringUtils;

public class UrlUtils {

    private UrlUtils() {
    }

    public static boolean containsUrlLotId(String url) {
        return StringUtils.contains(url, "{lotId}");
    }
}
