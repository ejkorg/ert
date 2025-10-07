package com.onsemi.cim.apps.exensioreftables.ws.mes.genesis;

import java.util.HashMap;
import java.util.Map;

public class GenesisSiteDataSources {

    private static final GenesisSiteDataSources instance = new GenesisSiteDataSources();

    private GenesisSiteDataSources() {
        // We don't want the constructor to be called from outside the class, use getInstance method to get instance.
    }

    public static GenesisSiteDataSources getInstance() {
        return instance;
    }

    private final Map<String, GenesisSiteDataSource> siteDataSourceMap = new HashMap<>();

    public GenesisSiteDataSource getOrCreateDataSource(String site, String driverClassName, String dbUrl, String dbUsername, String dbPassword, String sqlLtmProductReplacement) {
        if (!siteDataSourceMap.containsKey(site)) {
            siteDataSourceMap.put(site, new GenesisSiteDataSource(site, driverClassName, dbUrl, dbUsername, dbPassword, sqlLtmProductReplacement));
        }
        return siteDataSourceMap.get(site);
    }
}
