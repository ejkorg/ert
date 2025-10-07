package com.onsemi.cim.apps.exensioreftables.ws.mes.torrent;

import java.util.HashMap;
import java.util.Map;

public class TorrentSiteDataSources {

    private static final TorrentSiteDataSources instance = new TorrentSiteDataSources();

    private TorrentSiteDataSources() {
        // We don't want the constructor to be called from outside the class, use getInstance method to get instance.
    }

    public static TorrentSiteDataSources getInstance() {
        return instance;
    }

    private final Map<String, TorrentSiteDataSource> siteDataSourceMap = new HashMap<>();

    public TorrentSiteDataSource getOrCreateDataSource(String site, String driverClassName, String dbUrl, String dbUsername, String dbPassword, String sqlLtmProductReplacement) {
        if (!siteDataSourceMap.containsKey(site)) {
            siteDataSourceMap.put(site, new TorrentSiteDataSource(site, driverClassName, dbUrl, dbUsername, dbPassword, sqlLtmProductReplacement));
        }
        return siteDataSourceMap.get(site);
    }
}
