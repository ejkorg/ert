package com.onsemi.cim.apps.exensioreftables.ws.service.application.mes;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.MesType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.mes.torrent.TorrentSiteDataSource;
import com.onsemi.cim.apps.exensioreftables.ws.mes.torrent.TorrentSiteDataSources;
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
public class TorrentService {

    private static final Logger LOG = LoggerFactory.getLogger(TorrentService.class);
    private static final String COLUMN_LOT_ID = "LOTID";
    private static final String COLUMN_SRC_FAB = "SRC_FAB";
    private static final String COLUMN_PART_NAME = "PARTNAME";
    private static final String COLUMN_PART_VERSION = "PARTVERSION";
    private static final String COLUMN_FAMILY = "FAMILY";
    private static final String COLUMN_LOT_TYPE = "LOT_TYPE";
    private static final String COLUMN_PROCESS = "PROCESS";
    private static final String COLUMN_TECHNOLOGY = "TECHNOLOGY";
    private static final String COLUMN_PTI_CODE = "PTI_CODE";
    private static final String COLUMN_MASK_SET = "MASK_SET";

    private static final int FAMILY_MIN_LENGTH = 2;

    private final OnSiteConfService onSiteConfService;

    public TorrentService(OnSiteConfService onSiteConfService) {
        this.onSiteConfService = onSiteConfService;
    }

    public MesDto getMesDto(String site, String lot) throws BusinessException {
        final Optional<OnSiteConf> onSiteConfDtoOptional = onSiteConfService.getBySite(site);
        if (onSiteConfDtoOptional.isPresent()) {
            final OnSiteConf onSiteConf = onSiteConfDtoOptional.get();
            final TorrentSiteDataSources torrentSiteDataSources = TorrentSiteDataSources.getInstance();
            final TorrentSiteDataSource torrentSiteDataSource = torrentSiteDataSources.getOrCreateDataSource(site, onSiteConf.getDriverClassName(), onSiteConf.getConnectionString(), onSiteConf.getDbUsername(), onSiteConf.getDbPassword(), onSiteConf.getSqlLtmProductReplacement());

            if (torrentSiteDataSource != null) {
                try (final Connection connection = torrentSiteDataSource.getConnection();
                     final PreparedStatement preparedStatement = connection.prepareStatement(torrentSiteDataSource.getSqlLtmProductReplacement())) {

                    preparedStatement.setString(1, lot);
                    final ResultSet rs = preparedStatement.executeQuery();
                    final MesDto mesDto = new MesDto(MesType.TORRENT);
                    if (rs.next()) {
                        mesDto.setLot(rs.getString(COLUMN_LOT_ID));
                        mesDto.setFab(rs.getString(COLUMN_SRC_FAB));
                        mesDto.setProduct(rs.getString(COLUMN_PART_NAME));
                        mesDto.setProductVersion(rs.getString(COLUMN_PART_VERSION));
                        mesDto.setFamily(rs.getString(COLUMN_FAMILY));
                        mesDto.setLotType(rs.getString(COLUMN_LOT_TYPE));
                        mesDto.setProcess(rs.getString(COLUMN_PROCESS));
                        mesDto.setTechnology(rs.getString(COLUMN_TECHNOLOGY));
                        mesDto.setPti4(rs.getString(COLUMN_PTI_CODE));
                        mesDto.setMaskSet(rs.getString(COLUMN_MASK_SET));
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
