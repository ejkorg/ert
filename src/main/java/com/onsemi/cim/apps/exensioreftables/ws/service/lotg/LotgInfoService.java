package com.onsemi.cim.apps.exensioreftables.ws.service.lotg;

import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.LotGInfo;
import com.onsemi.cim.apps.exensioreftables.ws.repository.lotg.LotgInfoCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LotgInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(LotgInfoService.class);

    LotgInfoCustomRepository lotgInfoCustomRepository;

    public LotgInfoService(LotgInfoCustomRepository lotgInfoCustomRepository) {
        this.lotgInfoCustomRepository = lotgInfoCustomRepository;
    }
    public LotGInfo getLotGInfo(String lot) {
        LOG.info("Obtaining LotG by lotId='{}'", lot);

        LotGInfo lotGInfo = lotgInfoCustomRepository.findLotGInfo(lot);
        if (lotGInfo != null) {
            LOG.info("LotG Info obtained by lotId='{}', LotGInfo='{}'", lot, lotGInfo);
        } else {
            LOG.info("LotG Info not obtained by lotId='{}'", lot);
        }
        return lotGInfo;
    }
}
