package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferId;
import com.onsemi.cim.apps.exensioreftables.ws.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * see method getDescription
 *
 * @author fg6zdy
 */
public class WaferIdLoader {

    private static final Logger LOG = LoggerFactory.getLogger(WaferIdLoader.class);

    private WaferIdLoader() {
    }

    /**
     * Query WaferId service
     *
     * @param url         WaferId URL
     * @param scribeId    ScribeId param
     * @param lotId       LotId param
     * @return WaferId
     * @throws IllegalArgumentException When: 1. Url is null.
     *                                  2. WafewrId is null.
     */
    public static WaferId loadWaferIdByScribeId(final String url, final String scribeId, final String lotId) {
        boolean error = false;

        final StringBuilder sb = new StringBuilder();
        final boolean urlContainsLotId;
        if (url == null) {
            sb.append("URL cannot be null.\n");
            error = true;
            urlContainsLotId = false;
        } else {
            urlContainsLotId = UrlUtils.containsUrlLotId(url);
        }

        if (scribeId == null) {
            sb.append("WaferScribe cannot be null.\n");
            error = true;
        }

        if (urlContainsLotId && lotId == null) {
            sb.append("LotId cannot be null.\n");
            error = true;
        }

        if (error) {
            throw new IllegalArgumentException(sb.toString());
        }

        final WaferId waferId;
        if (urlContainsLotId) {
            waferId = new RestTemplate().getForObject(url, WaferId.class, lotId, scribeId);
            LOG.info("Result: lotId='{}', scribeId='{}' -> waferId='{}'", lotId, scribeId, waferId);
        } else {
            waferId = new RestTemplate().getForObject(url, WaferId.class, scribeId);
            LOG.info("Result: scribeId='{}' -> waferId='{}'", scribeId, waferId);
        }
        return waferId;
    }

    /**
     * Query WaferId service
     *
     * @param url         WaferId URL
     * @param lotId       LotId param
     * @param vid         Vid param
     * @return WaferId
     * @throws IllegalArgumentException When: 1. Url is null.
     *                                  2. LotId is null.
     *                                  3. vid is null.
     */
    public static WaferId loadWaferIdByLotIdAndWaferNumber(String url, String lotId, Integer vid) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null.");
        }

        if (lotId == null) {
            throw new IllegalArgumentException("LotId cannot be null!");
        }

        if (vid == null) {
            throw new IllegalArgumentException("WaferNumber cannot be null!");
        }

        final WaferId waferId = new RestTemplate().getForObject(url, WaferId.class, lotId, vid);
        LOG.info("Result: {}/{} -> {}", lotId, vid, waferId);
        return waferId;
    }
}
