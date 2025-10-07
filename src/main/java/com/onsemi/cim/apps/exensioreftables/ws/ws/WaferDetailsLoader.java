package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Loads WaferDetails - using component of LotG service which returns genealogy of defined wafer, not just lot.
 *
 * @author fg6zdy
 */
public class WaferDetailsLoader {

    private static final Logger LOG = LoggerFactory.getLogger(WaferDetailsLoader.class);

    private static final String URL_NULL = "URL is null.";
    private static final String WAFER_SCRIBE_OR_LOT_NULL = "WaferScribe or Lot is not defined!";
    private static final String QUERY_RESULT = "Result: {} -> {}";

    /**
     * Query WaferDetails service.
     *
     * @param url        WaferDetails URL
     * @param identifier WaferScribe or Lot
     * @return WaferDetails
     * @throws IllegalArgumentException When: 1. Url is null.
     *                                  2. Identifier (waferscribe or lot) is null.
     */
    public static WaferDetails loadWaferDetails(String url, String identifier) {

        LOG.info("Obtaining Wafer details by url='{}', identifier='{}'", url, identifier);
        if (url == null) {
            throw new IllegalArgumentException(URL_NULL);
        }

        if (identifier == null) {
            throw new IllegalArgumentException(WAFER_SCRIBE_OR_LOT_NULL);
        }

        final WaferDetails waferDetails = new RestTemplate().getForObject(url, WaferDetails.class, identifier);
        LOG.info(QUERY_RESULT, identifier, waferDetails);

        LOG.info("Wafer details obtained by identifier='{}', WaferDetails='{}'", identifier, waferDetails);
        return waferDetails;

    }
}
