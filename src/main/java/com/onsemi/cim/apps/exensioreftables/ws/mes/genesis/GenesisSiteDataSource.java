package com.onsemi.cim.apps.exensioreftables.ws.mes.genesis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class GenesisSiteDataSource {
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final int MINIMUM_IDLE = 3;

    private final String site;
    private final HikariDataSource hikariDataSource;
    private final String sqlLtmProductReplacement;

    public GenesisSiteDataSource(String site, String driverClassName, String connectionString, String dbUsername, String dbPassword, String sqlLtmProductReplacement) {
        this.site = site;
        this.sqlLtmProductReplacement = sqlLtmProductReplacement;

        final HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(connectionString);
        config.setUsername(dbUsername);
        config.setPassword(dbPassword);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        config.setMinimumIdle(MINIMUM_IDLE);

        hikariDataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public String getSite() {
        return site;
    }

    public String getSqlLtmProductReplacement() {
        return sqlLtmProductReplacement;
    }
}
