/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Loads Scribe.
 *
 * @author fg6zdy
 */
public class ScribeLoader {

    private static final Logger LOG = LoggerFactory.getLogger(ScribeLoader.class);

    private ScribeLoader() {
    }

    public static WaferId loadScribe(String url, String scribe) throws BusinessException {
        LOG.info("Obtaining Scribe WaferId by url='{}', scribeId='{}'", url, scribe);
        if (url == null || url.isEmpty()) {
            throw new BusinessException("Url is null or empty");
        }

        if (scribe == null || scribe.isEmpty()) {
            throw new BusinessException("Scribe is null or empty");
        }

        WaferId result = new RestTemplate().getForObject(url, WaferId.class, scribe);

        if (result == null) {
            throw new BusinessException(String.format("Lot id is missing for scribe %s", scribe));
        }

        if (result.getError() != null && !result.getError().isEmpty()) {
            throw new BusinessException(String.format("Lot id is missing for scribe %s", scribe));
        }

        LOG.info("Scribe WaferId obtained by scribeId='{}', WaferId='{}'", scribe, result);
        return result;
    }
}
