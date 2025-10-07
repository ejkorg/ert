package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author ffv7xh
 */
public class TraceabilityCache {

    private static final Logger log = LoggerFactory.getLogger(TraceabilityCache.class);

    private static final String separator = ";";

    private final Map<String, String> cache;

    public TraceabilityCache() {
        cache = new HashMap<>();
    }

    public boolean contains(String key){
        return cache.containsKey(key);
    }

    /**
     * It checks if lot nulber 'lot' is in path for lot number 'key'.
     * @param key  lot number's we want to search
     * @param lot  lot number we want to search for in path
     * @return
     */
    public boolean isInPath(String key, String lot){
        lot = (lot == null) ? "" : lot;

        for(String x: cache.get(key).split(separator)){
            if (lot.equals(x)) {
                return true;
            }
        }
        return false;
    }

    public String getPath(String key){
        String tmp = cache.get(key);
        return tmp == null ? "" : tmp;
    }

    public void add(String lot, String previous){
        String tmp = cache.get(previous);
        if (tmp == null) {
            tmp = "";
        }
        cache.put(lot, tmp + separator + previous);
    }
}
