package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.LotMissingInLtmException;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm.LotInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Loads LotInfo or throws exception if Lot info not loaded
 *
 * @author fg6zdy
 */
public class LtmLoader {

    private static final Logger LOG = LoggerFactory.getLogger(LtmLoader.class);

    private LtmLoader() {
    }

    /**
     * Loads LotInfo from LTM Web service. First check if url or lot input variables
     * are not null or empty. Then query LTM and returns LotInfo
     *
     * @param url   LTM url
     * @param lot   Lot to query
     * @return LotInfo
     * @throws RuntimeException         if either url or lot is null or empty or LotInfo
     *                                  returned from Web service is null
     * @throws LotMissingInLtmException if lot is missing in LTM
     */
    public static LotInfo loadLotInfo(String url, String lot) throws BusinessException {

        LOG.info("Obtaining LTM LotInfo by url='{}', lotId='{}'", url, lot);

        if (url == null || url.isEmpty()) {
            throw new BusinessException("LTM URL is not set");
        }

        if (lot == null || lot.isEmpty()) {
            throw new BusinessException("LTM lot string is null or is not set");
        }

        final LotInfo lotInfo;
        try {
            lotInfo = new RestTemplate().getForObject(url, LotInfo.class, lot);
        } catch (Exception e) {
            throw new BusinessException("Error while calling LTM", e);
        }

        if (lotInfo == null) {
            throw new BusinessException("LTM Web service is not available: " + url);
        }

        if (lotInfo.getStatus().equals("MISSING_LOT") || lotInfo.getStatus().equals("MISSING_LTM")) {
            throw new LotMissingInLtmException("Lot " + lot + " is missing in LTM");
        }

        LOG.info("LTM LotInfo obtained by lotId='{}', LotInfo='{}'", lot, lotInfo);

        return lotInfo;
    }
}
