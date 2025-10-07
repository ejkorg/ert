package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.configuration.ApplicationConfiguration;
import com.onsemi.cim.apps.exensioreftables.ws.configuration.DataWarehouseConfiguration;
import com.onsemi.cim.apps.exensioreftables.ws.configuration.LotgConfiguration;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.DataSourceDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.lotg.LotgInfoCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class InformationService {

    private static final Logger LOG = LoggerFactory.getLogger(InformationService.class);

    @Value("${application.version}")
    private String applicationVersion;

    private ApplicationConfiguration applicationConfiguration;
    private DataWarehouseConfiguration dataWarehouseConfiguration;
    private LotgConfiguration lotGConfiguration;

    public InformationService(ApplicationConfiguration applicationConfiguration,
            DataWarehouseConfiguration dataWarehouseConfiguration,
            LotgConfiguration lotGConfiguration) {

        this.applicationConfiguration = applicationConfiguration;
        this.dataWarehouseConfiguration = dataWarehouseConfiguration;
        this.lotGConfiguration = lotGConfiguration;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public DataSourceDto getApplicationDatasource() {
        try {
            final String url = applicationConfiguration.getApplicationDataSource().getConnection().getMetaData().getURL();
            final String username = applicationConfiguration.getApplicationDataSource().getConnection().getMetaData().getUserName();
            return new DataSourceDto(url, username, null, null);
        } catch (SQLException e) {
            LOG.error("Cannot get data source for Application", e);
        }
        return null;
    }

    public DataSourceDto getDatawarehouseDatasource() {
        try {
            final String url = dataWarehouseConfiguration.getDataWarehouseDataSource().getConnection().getMetaData().getURL();
            final String username = dataWarehouseConfiguration.getDataWarehouseDataSource().getConnection().getMetaData().getUserName();
            return new DataSourceDto(url, username, null, null);
        } catch (SQLException e) {
            LOG.error("Cannot get data source for DataWarehouse", e);
        }
        return null;
    }

    public DataSourceDto getLotgDatasource() {
        try {
            final String url = lotGConfiguration.getLotGDataSource().getConnection().getMetaData().getURL();
            final String username = lotGConfiguration.getLotGDataSource().getConnection().getMetaData().getUserName();
            return new DataSourceDto(url, username, LotgInfoCustomRepository.SQL_LOTG_VERSION, LotgInfoCustomRepository.SQL_LOTG_QUERY);
        } catch (SQLException e) {
            LOG.error("Cannot get data source for LotG", e);
        }
        return null;
    }
}
