package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.LotMissingInLotGException;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg.SourceLotInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Loading data from LotG WS
 *
 * @author fg6zdy
 */
public class LotgLoader {

    private static final Logger LOG = LoggerFactory.getLogger(LotgLoader.class);

    private LotgLoader() {
    }

    public static SourceLotInfo loadSourceLotInfo(String url, String lot) throws BusinessException {
        LOG.info("Obtaining LotG SourceLotInfo by url='{}', lotId='{}'", url, lot);

        if (url == null || url.isEmpty()) {
            throw new BusinessException("LotG URL is not set");
        }

        if (lot == null || lot.isEmpty()) {
            throw new BusinessException("LotG lot string is null or is not set");
        }

        SourceLotInfo lotGInfo = new RestTemplate().getForObject(url, SourceLotInfo.class, lot);
        if (lotGInfo == null) {
            throw new BusinessException("LotG Web service is not available: " + url);
        }

        if (!(lotGInfo.getStatus() == Status.Found || lotGInfo.getStatus() == Status.Found_BOM || lotGInfo.getStatus() == Status.No_Data)) {
            throw new LotMissingInLotGException("Lot " + lot + " is missing in LotG");
        }

        LOG.info("LotG SourceLotInfo obtained by lotId='{}', LotInfo='{}'", lot, lotGInfo);

        return lotGInfo;
    }
}
