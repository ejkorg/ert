package com.onsemi.cim.apps.exensioreftables.ws.service.datawarehouse;

import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.DataWarehouseServiceException;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseLotClass;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseMfgArea;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehousePlm;
import com.onsemi.cim.apps.exensioreftables.ws.repository.datawarehouse.DataWarehouseCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataWarehouseService {

    private static final Logger LOG = LoggerFactory.getLogger(DataWarehouseService.class);

    DataWarehouseCustomRepository dataWarehouseCustomRepository;

    public DataWarehouseService(DataWarehouseCustomRepository dataWarehouseCustomRepository) {
        this.dataWarehouseCustomRepository = dataWarehouseCustomRepository;
    }

    public DataWarehousePlm getDwPlmByPartId(String partId) throws DataWarehouseServiceException {
        LOG.info("Obtaining DataWarehouse Plm by partId='{}'", partId);
        DataWarehousePlm dataWarehousePlm = dataWarehouseCustomRepository.getDwPlmByPartId(partId);
        if (dataWarehousePlm != null) {
            LOG.info("DataWarehouse Plm obtained by partId='{}', DataWarehousePlm='{}'", partId, dataWarehousePlm);
        } else {
            LOG.info("DataWarehouse Plm not obtained by partId='{}''", partId);
        }
        return dataWarehousePlm;
    }

    public DataWarehouseLotClass getLotClassByLot(String lotId) {
        LOG.info("Obtaining DataWarehouse Lot Class by lotId='{}'", lotId);

        DataWarehouseLotClass dataWarehouseLotClass = dataWarehouseCustomRepository.getLotClassByLot(lotId);
        if (dataWarehouseLotClass != null) {
            LOG.info("DataWarehouse Lot Class obtained by lotId='{}', DataWarehouseLotClass='{}'", lotId, dataWarehouseLotClass);
        } else {
            LOG.info("DataWarehouse Lot Class not obtained by lotId='{}'", lotId);
        }
        return dataWarehouseLotClass;
    }

    public DataWarehouseMfgArea getMfgAreaByMfgAreaCd(String mfgAreaCd) throws DataWarehouseServiceException {
        LOG.info("Obtaining DataWarehouse Lot Class by mfgAreaCd='{}'", mfgAreaCd);

        DataWarehouseMfgArea dataWarehouseMfgArea = dataWarehouseCustomRepository.getDwMfgAreaByMfgAreaCd(mfgAreaCd);
        if (dataWarehouseMfgArea != null) {
            LOG.info("DataWarehouse Mfg Area obtained by mfgAreaCd='{}', DataWarehouseMfgArea='{}'", mfgAreaCd, dataWarehouseMfgArea);
        } else {
            LOG.info("DataWarehouse Mfg Area not obtained by mfgAreaCd='{}'", mfgAreaCd);
        }
        return dataWarehouseMfgArea;
    }
}
