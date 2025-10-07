package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnSiteConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OnSiteConfService {

    private static final Logger LOG = LoggerFactory.getLogger(OnSiteConfService.class);

    private final OnSiteConfRepository onSiteConfRepository;


    public OnSiteConfService(OnSiteConfRepository onSiteConfRepository) {
        this.onSiteConfRepository = onSiteConfRepository;
    }


   //If OnFabConf is not found by fab+DataType, it tries to find some by fab only
   public Optional<OnSiteConf> getBySite(String site) {
        final Optional<OnSiteConf> onSiteConfOptional = onSiteConfRepository.getBySite(site);

        if (onSiteConfOptional.isPresent()) {
            LOG.info("Found OnSiteConf for site='{}', OnSiteConf='{}'", site, onSiteConfOptional.get());
            return onSiteConfOptional;
        } else {
            LOG.info("Return empty OnSiteConf for site='{}'", site);
            return Optional.empty();
        }
    }
}
