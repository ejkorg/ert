package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.common.ws.exception.WsMethodExecutionFault;
import com.onsemi.cim.apps.common.wsclients.WsClientConfig;
import com.onsemi.cim.apps.common.wsclients.WsConnectionException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.map.repository.client.MapRepositoryClient;
import com.onsemi.cim.apps.repository.metadata.MetadataPackage;
import com.onsemi.cim.apps.repository.model.MapRepositoryKey;
import com.onsemi.cim.apps.repository.model.MapRepositoryOption;
import com.onsemi.cim.apps.repository.model.MapType;
import com.onsemi.cim.apps.repository.model.request.MapRepositoryParam;
import com.onsemi.cim.apps.repository.model.response.QueryMapResult;
import com.onsemi.cim.apps.repository.model.response.ReadMapResult;
import com.onsemi.cim.apps.umr.metadata.CommonMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

public class MapRepositoryLoader {

    private static final Logger LOG = LoggerFactory.getLogger(MapRepositoryLoader.class);

    private MapRepositoryLoader() {
    }

    public static ReadMapResult getWaferMapReadMapResult(String url, String lot) throws BusinessException {

        MapRepositoryParam input = new MapRepositoryParam();
        input.addOption(new MapRepositoryOption(MapRepositoryKey.MAP_TYPE, MapType.WAFER_MAP.name()));
        input.addOption(new MapRepositoryOption(MapRepositoryKey.LOT, lot));
        input.addOption(new MapRepositoryOption(MapRepositoryKey.PARTIAL, Boolean.FALSE));
        input.addOption(new MapRepositoryOption(MapRepositoryKey.MAP_SOURCE, "mapper"));
        input.addOption(new MapRepositoryOption(MapRepositoryKey.MAPS_LIMIT, 1L));

        LOG.info("Obtaining wafer map metadata by url='{}', lotId='{}'", url, lot);

        try {
            MapRepositoryClient client = new MapRepositoryClient();
            WsClientConfig config = new WsClientConfig();
            config.setWsUrl(url);
            client.setConfig(config);

            QueryMapResult result = client.queryMapFile(input);
            List<MetadataPackage> metadataList = result.getMetadataPackages();
            Long idMap;
            if (metadataList == null || metadataList.isEmpty()) {
                return null;
            } else {
                idMap = metadataList.get(0).getMetadataElement(CommonMetadata.ID_MAP).getLongValue();
            }

            //get map
            ReadMapResult rmr = client.readMapFile(String.valueOf(idMap), new MapRepositoryParam());

            if (rmr == null || rmr.getMapContent() == null || rmr.getRepositoryMapId() == null || rmr.getRepositoryMapId().isEmpty()) {
                throw new BusinessException("Can not find map in MapVault for lot = " + lot);
            }

            LOG.info("Wafer map configuration ReadMapResult obtained by lotId='{}', RMR='{}'", lot, rmr);
            return rmr;

        } catch (SOAPFaultException | WsMethodExecutionFault ex) {
            LOG.error("Issue with getting wafer map metadata from map vault: {}", ex.getMessage());
        } catch (WsConnectionException ex) {
            throw new BusinessException(ex);
        }
        return null;
    }

}
