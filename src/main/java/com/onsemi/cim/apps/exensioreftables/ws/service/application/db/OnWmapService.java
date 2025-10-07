package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnWmap;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.DeviceMissingInWmcException;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnWmapDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc.WaferMapConfiguration;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnWmapRepository;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OnWmapService {

    private static final Logger LOG = LoggerFactory.getLogger(OnWmapService.class);

    private final Caller caller;
    private final OnFabConfService onFabConfService;
    private final ErtConfService ertConfService;
    private final OnWmapRepository onWmapRepository;

    public OnWmapService(
            Caller caller,
            OnFabConfService onFabConfService,
            ErtConfService ertConfService,
            OnWmapRepository onWmapRepository
    ) {

        this.caller = caller;
        this.onFabConfService = onFabConfService;
        this.ertConfService = ertConfService;
        this.onWmapRepository = onWmapRepository;
    }

    public List<OnWmap> findAll() {
        return onWmapRepository.findAll();
    }

    public Optional<OnWmap> findById(Long id) {
        return onWmapRepository.findById(id);
    }

    public Optional<OnWmap> findByCfgId(Long idWaferMapConfiguration, String product, String searchedLotPart, String fab,
           String dataType) throws BusinessException {
        final Optional<OnWmap> byCfgId = onWmapRepository.findByCfgId(idWaferMapConfiguration);
        if(byCfgId.isPresent() && byCfgId.get().getStatus() != null && byCfgId.get().getStatus().isFoundAll()) {
            return byCfgId;
        }
        final OnWmap byService = new OnWmap();
        WaferMapConfiguration waferMapConfiguration = null;
        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);
        final String wmcUrl;
        if(onFabConf.isPresent() && StringUtils.isNotBlank((onFabConf.get().getWmcUrl()))) {
            wmcUrl = onFabConf.get().getWmcUrl();
        } else {
            wmcUrl = ertConfService.getStringByName(ErtConfService.WS_WMC_URL);
        }
        if(StringUtils.isNotBlank(wmcUrl)) {
            try {
                LOG.info("going to get WMC via wmcId='{}' ", idWaferMapConfiguration);
                LOG.info("going to get WMC via wmcURL='{}' ", wmcUrl);
                waferMapConfiguration = caller.getWmcByWmcId(wmcUrl, idWaferMapConfiguration);
            } catch (DeviceMissingInWmcException dmiwe) {
                LOG.error("No data found for WmcId='{}' in WMC service='{}'", idWaferMapConfiguration, wmcUrl);
            }
        }
        if(waferMapConfiguration != null) {
            caller.assignWmcResults(waferMapConfiguration, product, searchedLotPart, byService);
            byService.setStatus(Status.FOUND);
            byService.setInsertTime(DateUtils.getSqlDateNow());
            onWmapRepository.save(byService);
            return Optional.of(byService);
        }
        return Optional.empty();
    }

    public Optional<OnWmap> findByProduct(String wmcServiceKey, String product, String searchedLotPart, String fab,
            String dataType) throws BusinessException {
        final Optional<OnWmap> byProd = onWmapRepository.findByProduct(searchedLotPart);
        if (byProd.isPresent() && byProd.get().getStatus() != null  && byProd.get().getStatus().isFoundAll()) {
            return byProd;
        }

        // DB entities created
        final OnWmap byService = new OnWmap();
        WaferMapConfiguration waferMapConfiguration = null;

        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);

        final String wmcUrl;
        if (onFabConf.isPresent() && StringUtils.isNotBlank(onFabConf.get().getWmcUrl())) {
            wmcUrl = onFabConf.get().getWmcUrl();
        } else {
            wmcUrl = ertConfService.getStringByName(ErtConfService.WS_WMC_URL);
        }
        if (StringUtils.isNotBlank(wmcUrl)) {
            try {
                waferMapConfiguration = caller.getWmcByDevice(wmcUrl, wmcServiceKey);
            } catch (DeviceMissingInWmcException dmiwe) {
                LOG.warn("No data found for WmcServiceKey='{}' in WMC service='{}'", wmcServiceKey, wmcUrl);
                String wsTempUrl = wmcUrl.replace("/SORT", "/PCM");
                try {
                    waferMapConfiguration = caller.getWmcByDevice(wsTempUrl, product);
                } catch (DeviceMissingInWmcException dmiwe2) {
                    LOG.error("No data found for Product='{}' in WMC service='{}'", product, wsTempUrl);
                }
            }
        }

        if (waferMapConfiguration != null) {
            caller.assignWmcResults(waferMapConfiguration, product, searchedLotPart, byService);
            byService.setStatus(Status.FOUND);
            byService.setInsertTime(DateUtils.getSqlDateNow());
            onWmapRepository.save(byService);
            return Optional.of(byService);
        }

        return Optional.empty();
    }

    public ResponseEntity<OnWmapDto> createFromDto(OnWmapDto onWmapDto) {
        Date insertTime = DateUtils.convertStringToDate(onWmapDto.getInsertTime());
        if (insertTime == null) {
            OnWmapDto onWmapDtoResult = new OnWmapDto();
            onWmapDtoResult.setStatus(Status.ERROR);
            onWmapDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }
        Date confirmTime = DateUtils.convertStringToDate(onWmapDto.getInsertTime());
        if (confirmTime == null) {
            OnWmapDto onWmapDtoResult = new OnWmapDto();
            onWmapDtoResult.setStatus(Status.ERROR);
            onWmapDtoResult.setErrorMessage(String.format("Error parsing ConfirmTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }
        Date refDieInitDt = DateUtils.convertStringToDate(onWmapDto.getInsertTime());
        if (refDieInitDt == null) {
            OnWmapDto onWmapDtoResult = new OnWmapDto();
            onWmapDtoResult.setStatus(Status.ERROR);
            onWmapDtoResult.setErrorMessage(String.format("Error parsing RefDieInitDtTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        OnWmap onWmap = save(new OnWmap(onWmapDto));
        OnWmapDto onWmapDtoResult = new OnWmapDto(onWmap);
        ResponseEntity<OnWmapDto> responseModel = new ResponseEntity<>(onWmapDtoResult, HttpStatus.CREATED);
        LOG.info("OnWmap entity created");
        return responseModel;
    }

    public ResponseEntity<OnWmapDto> updateFromDto(OnWmap onWmap, OnWmapDto onWmapDto) {
        if (onWmapDto.getStatus() != null) {
            onWmap.setStatus(onWmapDto.getStatus());
        }
        if (onWmapDto.getProduct() != null) {
            onWmap.setProduct(StringUtils.upperCase(onWmapDto.getProduct()));
        }
        if (onWmapDto.getWfUnits() != null) {
            onWmap.setWfUnits(onWmapDto.getWfUnits());
        }
        if (onWmapDto.getWfSize() != null) {
            onWmap.setWfSize(onWmapDto.getWfSize());
        }
        if (onWmapDto.getFlatType() != null) {
            onWmap.setFlatType(StringUtils.upperCase(onWmapDto.getFlatType()));
        }
        if (onWmapDto.getFlat() != null) {
            onWmap.setFlat(StringUtils.upperCase(onWmapDto.getFlat()));
        }
        if (onWmapDto.getDieWidth() != null) {
            onWmap.setDieWidth(onWmapDto.getDieWidth());
        }
        if (onWmapDto.getDieHeight() != null) {
            onWmap.setDieHeight(onWmapDto.getDieHeight());
        }
        if (onWmapDto.getCenterX() != null) {
            onWmap.setCenterX(onWmapDto.getCenterX());
        }
        if (onWmapDto.getCenterY() != null) {
            onWmap.setCenterY(onWmapDto.getCenterY());
        }
        if (onWmapDto.getPositiveX() != null) {
            onWmap.setPositiveX(onWmapDto.getPositiveX());
        }
        if (onWmapDto.getPositiveY() != null) {
            onWmap.setPositiveY(onWmapDto.getPositiveY());
        }
        if (onWmapDto.getReticleRows() != null) {
            onWmap.setReticleRows(onWmapDto.getReticleRows());
        }
        if (onWmapDto.getReticleCols() != null) {
            onWmap.setReticleCols(onWmapDto.getReticleCols());
        }
        if (onWmapDto.getReticleRowOffset() != null) {
            onWmap.setReticleColOffset(onWmapDto.getReticleColOffset());
        }
        if (onWmapDto.getReticleRowOffset() != null) {
            onWmap.setReticleRowOffset(onWmapDto.getReticleRowOffset());
        }
        if (onWmapDto.getConfirmed() != null) {
            onWmap.setConfirmed(StringUtils.upperCase(onWmapDto.getConfirmed()));
        }
        if (onWmapDto.getDeviceCount() != null) {
            onWmap.setDeviceCount(onWmapDto.getDeviceCount());
        }
        if (onWmapDto.getConfirmTime() != null) {
            Date confirmTime = DateUtils.convertStringToDate(onWmapDto.getConfirmTime());
            if (confirmTime == null) {
                OnWmapDto onWmapDtoResult = new OnWmapDto();
                onWmapDtoResult.setStatus(Status.ERROR);
                onWmapDtoResult.setErrorMessage(String.format("Error parsing ConfirmTime, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onWmap.setConfirmTime(confirmTime);
        }
        if (onWmapDto.getComments() != null) {
            onWmap.setComments(StringUtils.upperCase(onWmapDto.getComments()));
        }
        if (onWmapDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(onWmapDto.getInsertTime());
            if (insertTime == null) {
                OnWmapDto onWmapDtoResult = new OnWmapDto();
                onWmapDtoResult.setStatus(Status.ERROR);
                onWmapDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onWmap.setInsertTime(insertTime);
        }
        if (onWmapDto.getInputFile() != null) {
            onWmap.setInputFile(onWmapDto.getInputFile());
        }
        if (onWmapDto.getCfgId() != null) {
            onWmap.setCfgId(onWmapDto.getCfgId());
        }
        if (onWmapDto.getLocation() != null) {
            onWmap.setLocation(StringUtils.upperCase(onWmapDto.getLocation()));
        }
        if (onWmapDto.getRefDieX() != null) {
            onWmap.setRefDieX(onWmapDto.getRefDieX());
        }
        if (onWmapDto.getRefDieY() != null) {
            onWmap.setRefDieY(onWmapDto.getRefDieY());
        }
        if (onWmapDto.getRefDieInitDt() != null) {
            Date refDieInitDt = DateUtils.convertStringToDate(onWmapDto.getRefDieInitDt());
            if (refDieInitDt == null) {
                OnWmapDto onWmapDtoResult = new OnWmapDto();
                onWmapDtoResult.setStatus(Status.ERROR);
                onWmapDtoResult.setErrorMessage(String.format("Error parsing RefDieInitDt, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onWmapDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onWmap.setRefDieInitDt(refDieInitDt);
        }
        if (onWmapDto.getWmcDevice() != null) {
            onWmap.setWmcDevice(StringUtils.upperCase(onWmapDto.getWmcDevice()));
        }

        try {
            OnWmapDto onWmapDtoResult = new OnWmapDto(save(onWmap));
            LOG.info("OnWmap entity with id='{}' updated", onWmap.getId());
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            OnWmapDto onWmapDtoResult = new OnWmapDto();
            onWmapDtoResult.setStatus(Status.ERROR);
            onWmapDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public long count() {
        return onWmapRepository.count();
    }

    public void delete(OnWmap onWmap) {
        onWmapRepository.delete(onWmap);
    }

    public OnWmap save(OnWmap onWmap) {
        if (onWmap == null) {
            LOG.error("OnWmap is null when saving it");
            return null;
        }
        return onWmapRepository.save(onWmap);
    }
}
