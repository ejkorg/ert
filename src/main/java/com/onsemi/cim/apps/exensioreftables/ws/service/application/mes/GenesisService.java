package com.onsemi.cim.apps.exensioreftables.ws.service.application.mes;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.MesType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.mes.genesis.GenesisSiteDataSource;
import com.onsemi.cim.apps.exensioreftables.ws.mes.genesis.GenesisSiteDataSources;
import com.onsemi.cim.apps.exensioreftables.ws.model.MesDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnSiteConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class GenesisService {

    private static final Logger LOG = LoggerFactory.getLogger(GenesisService.class);
    private static final String COLUMN_PRODUCT = "PRODUCT";
    private static final String COLUMN_PTI_CODE = "PTI_CODE";
    private static final String COLUMN_PROCESS = "PROCESS";

    private static final int FAMILY_MIN_LENGTH = 2;

    private final OnSiteConfService onSiteConfService;

    public GenesisService(OnSiteConfService onSiteConfService) {
        this.onSiteConfService = onSiteConfService;
    }

    public MesDto getMesDto(String site, String lot) throws BusinessException {
        final Optional<OnSiteConf> onSiteConfDtoOptional = onSiteConfService.getBySite(site);
        if (onSiteConfDtoOptional.isPresent()) {
            final OnSiteConf onSiteConf = onSiteConfDtoOptional.get();
            final GenesisSiteDataSources genesisSiteDataSources = GenesisSiteDataSources.getInstance();
            final GenesisSiteDataSource genesisSiteDataSource = genesisSiteDataSources.getOrCreateDataSource(site, onSiteConf.getDriverClassName(), onSiteConf.getConnectionString(), onSiteConf.getDbUsername(), onSiteConf.getDbPassword(), onSiteConf.getSqlLtmProductReplacement());

            if (genesisSiteDataSource != null) {
                try (final Connection connection = genesisSiteDataSource.getConnection();
                     final PreparedStatement preparedStatement = connection.prepareStatement(genesisSiteDataSource.getSqlLtmProductReplacement())) {

                    preparedStatement.setString(1, lot);
                    final ResultSet rs = preparedStatement.executeQuery();
                    final MesDto mesDto = new MesDto(MesType.GENESIS);
                    if (rs.next()) {
                        mesDto.setProduct(rs.getString(COLUMN_PRODUCT));
                        mesDto.setProcess(rs.getString(COLUMN_PROCESS));
                        mesDto.setFamily(getFamilyFromPtiCode(rs.getString(COLUMN_PTI_CODE)));
                        mesDto.setPti4(getPti4FromPtiCode(rs.getString(COLUMN_PTI_CODE)));
                    }
                    return mesDto;

                } catch (SQLException e) {
                    throw new BusinessException("Cannot get the values from the ResultSet", e);
                }
            } else {
                throw new BusinessException(String.format("SiteDataSource does not exist for site - %s", site));
            }
        } else {
            throw new BusinessException(String.format("Cannot get OnSiteConf for site = %s", site));
        }
    }

    private String getFamilyFromPtiCode(String ptiCode) {
        if (ptiCode != null && ptiCode.length() >= FAMILY_MIN_LENGTH) {
            return ptiCode.substring(0, FAMILY_MIN_LENGTH);
        }

        return ptiCode;
    }

    private String getPti4FromPtiCode(String ptiCode) {
        return ptiCode;
    }
}
