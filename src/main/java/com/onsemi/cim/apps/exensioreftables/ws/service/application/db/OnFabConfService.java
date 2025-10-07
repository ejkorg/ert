package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnFabConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OnFabConfService {

    private static final Logger LOG = LoggerFactory.getLogger(OnFabConfService.class);

    private final OnFabConfRepository onFabConfRepository;


    public OnFabConfService(
            OnFabConfRepository onFabConfRepository) {

        this.onFabConfRepository = onFabConfRepository;
    }


   //If OnFabConf is not found by fab+DataType, it tries to find some by fab only
   public Optional<OnFabConf> getByFabAndDataTypeOrFabOnly(String fab, String dataType) {

        Optional<List<OnFabConf>> onFabConfsOptional = onFabConfRepository.getAllByFoundryFab(fab);
        OnFabConf onFabConf = null;
        if (onFabConfsOptional.isPresent()) {
            List<OnFabConf> onFabConfs = onFabConfsOptional.get();
            if (onFabConfs.size() > 1) {
                Optional<OnFabConf> onFabConfOptional = onFabConfRepository.getByFoundryFabAndDataType(fab, dataType);
                if (onFabConfOptional.isPresent()) {
                    LOG.info("Found onFabConf for fab and dataTpe");
                    onFabConf = onFabConfOptional.get();
                }
            } else if (!onFabConfs.isEmpty()) {
                LOG.info("Found onFabConf for fab only");
                onFabConf = onFabConfs.get(0);
            }
        }

        if (onFabConf != null) {
            LOG.info("Found OnFabConf for fab='{}', dataType='{}', OnFabConf='{}'", fab, dataType, onFabConf);
            return Optional.of(onFabConf);
        } else {
            LOG.info("Return empty OnFabConf for fab='{}', dataType='{}'", fab, dataType);
            return Optional.empty();
        }
    }
}
