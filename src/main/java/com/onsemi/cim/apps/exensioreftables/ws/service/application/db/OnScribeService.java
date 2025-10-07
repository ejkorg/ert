package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.*;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnScribeDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.WaferIdSource;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferId;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnScribeRepository;
import com.onsemi.cim.apps.exensioreftables.ws.utils.AttributeUtils;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import com.onsemi.cim.apps.exensioreftables.ws.utils.UrlUtils;
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
public class OnScribeService {

    private static final Logger LOG = LoggerFactory.getLogger(OnScribeService.class);

    private static final String STATUS_FOUND = "FOUND";

    private final Caller caller;
    private final OnFabConfService onFabConfService;
    private final OnLotService onLotService;
    private final ErtConfService ertConfService;
    private final OnScribeRepository onScribeRepository;



    public OnScribeService(
            Caller caller,
            OnFabConfService onFabConfService,
            OnLotService onLotService,
            ErtConfService ertConfService,
            OnScribeRepository onScribeRepository) {

        this.caller = caller;
        this.onFabConfService = onFabConfService;
        this.onLotService = onLotService;
        this.ertConfService = ertConfService;
        this.onScribeRepository = onScribeRepository;
    }

    public List<OnScribe> findAll() {
        return onScribeRepository.findAll();
    }

    public Optional<OnScribe> findById(Long id) {
        return onScribeRepository.findById(id);
    }

    public Optional<OnScribe> findByLotAndWaferNum(String lot, String mfgLot, Integer waferNum, String fab,
            String dataType, String site) throws BusinessException {

        final Optional<OnScribe> byLotAndWafNum = onScribeRepository.findByLotAndWaferNum(lot, waferNum);
        if (byLotAndWafNum.isPresent() && byLotAndWafNum.get().getStatus().isFoundAll()) {
            return byLotAndWafNum;
        }

        // DB entities created
        final OnScribe onScribe = byLotAndWafNum.orElseGet(OnScribe::new);

        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);

        String waferIdCreationPattern = onFabConf.map(OnFabConf::getWaferIdCreationPattern).orElse(null);

        final String vid2ScribeUrl;
        final ScribeResultType scribeResultType;
        final boolean onScribeWaferIdEqualsScribeId;
        final String lotId;
        if (onFabConf.isPresent()) {
            if (StringUtils.isNotBlank(onFabConf.get().getVid2ScribeUrl())) {
                vid2ScribeUrl = onFabConf.get().getVid2ScribeUrl();
                scribeResultType = onFabConf.get().getScribeResultType() == null ? ScribeResultType.UNKNOWN : onFabConf.get().getScribeResultType();
            } else {
                vid2ScribeUrl = ertConfService.getStringByName(ErtConfService.WS_VID_TO_SCRIBE_URL);
                scribeResultType = ScribeResultType.UNKNOWN;
            }
            onScribeWaferIdEqualsScribeId = onFabConf.get().isOnScribeWaferIdEqualsScribeId();
            if (onFabConf.get().getLotIdForOnScribeType() == LotIdForOnScribeType.MFG_LOT) {
                lotId = mfgLot;
            } else {
                lotId = lot;
            }
        } else {
            vid2ScribeUrl = ertConfService.getStringByName(ErtConfService.WS_VID_TO_SCRIBE_URL);
            scribeResultType = ScribeResultType.UNKNOWN;
            onScribeWaferIdEqualsScribeId = false;
            lotId = lot;
        }

        WaferId waferId = null;
        try {
            waferId = caller.getWaferIdByLotAndWaferNum(vid2ScribeUrl, scribeResultType, lotId, waferNum);
        } catch (Exception e) {
            LOG.error(String.format("Exception occurred when calling service with url %s. Will use calculated scribeId instead.", vid2ScribeUrl));
        }

        onScribe.setInsertTime(DateUtils.getSqlDateNow());

        final OnLot onLot = onLotService.findByLotId(lotId, null, fab, dataType, site, true).orElseGet(OnLot::new);

        if (waferId != null && (waferId.getError() == null || !waferId.getError().equals("No LaserScribe"))) {
            onScribe.setStatus(Status.FOUND);
            caller.fillOnScribeByWaferIdResults(waferId, onLot, onScribe, fab, dataType, onScribeWaferIdEqualsScribeId, waferIdCreationPattern);

        } else {
            LOG.info("WaferId was not found for lot='{}', vid='{}'", lot, waferNum);
            onScribe.setStatus(Status.FOUND);
            String calculatedWaferId = AttributeUtils.calculateWaferId(lotId, waferNum, onLot.getSourceLot(),  waferIdCreationPattern);
            onScribe.setWaferId(calculatedWaferId);
            onScribe.setWaferIdSource(WaferIdSource.CALCULATED);
            onScribe.setFab(fab);
            onScribe.setLot(lotId);
            onScribe.setWaferNum(waferNum);
            if (onScribeWaferIdEqualsScribeId) {
                onScribe.setScribeId(calculatedWaferId);
            }
            return Optional.of(onScribe);
        }

        onScribeRepository.save(onScribe);
        return Optional.of(onScribe);
    }

    public Optional<OnScribe> findByScribeId(String scribeId, String lotId, String fab,
            String dataType, String site) throws BusinessException {

        final Optional<OnScribe> byScribeId = onScribeRepository.findByScribeId(scribeId);
        if (byScribeId.isPresent() && byScribeId.get().getStatus() != null && byScribeId.get().getStatus().isFoundAll()) {
            return byScribeId;
        }

        // DB entities created
        final OnScribe byService = byScribeId.orElseGet(OnScribe::new);

        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);

        final String scribe2VidUrl;
        final ScribeResultType scribeResultType;
        final boolean onScribeWaferIdEqualsScribeId;
        if (onFabConf.isPresent()) {
            scribe2VidUrl = onFabConf.get().getScribe2VidUrl();
            scribeResultType = onFabConf.get().getScribeResultType() == null ? ScribeResultType.UNKNOWN : onFabConf.get().getScribeResultType();
            onScribeWaferIdEqualsScribeId = onFabConf.get().isOnScribeWaferIdEqualsScribeId();
        } else {
            scribe2VidUrl = ertConfService.getStringByName(ErtConfService.WS_SCRIBE_TO_VID_URL);
            scribeResultType = ScribeResultType.UNKNOWN;
            onScribeWaferIdEqualsScribeId = false;
        }

        if (scribeResultType == ScribeResultType.WAFER_ID && UrlUtils.containsUrlLotId(scribe2VidUrl) && StringUtils.isBlank(lotId)) {
            throw new BusinessException("If ResultType is WAFER_ID and Url contains {lotId}, LotId cannot be blank");
        }

        final WaferId waferId = caller.getWaferIdByScribeId(scribe2VidUrl, scribeResultType, scribeId, lotId);
        if (waferId != null && (waferId.getStatus() == null || waferId.getStatus().equalsIgnoreCase(STATUS_FOUND))) {
            if (waferId.getLotId() == null || waferId.getVid() == null || waferId.getLaserScribe() == null) {
                byService.setStatus(Status.NO_DATA);
            } else {
                updateOnScribeByWaferId(byService, waferId, fab, dataType, site, onScribeWaferIdEqualsScribeId);
            }
        } else {
            throw new BusinessException(String.format("WaferId was not found for scribeId='%s'", scribeId));
        }

        return Optional.of(byService);
    }

    //TODO change name of this method - it does not search by ByLotIdAndWaferNumAndScribeId. The logic is different inside of it.
    public Optional<OnScribe> findByLotIdAndWaferNumAndScribeId(String lotId, String waferNum, String scribeId,
            String fab, String dataType, String site) throws BusinessException {

        final Optional<OnScribe> byScribeId = onScribeRepository.findByScribeId(scribeId);
        if (byScribeId.isPresent() && byScribeId.get().getStatus() != null && byScribeId.get().getStatus().isFoundAll()) {
            return byScribeId;
        }

        // DB entities created
        final OnScribe byService = byScribeId.orElseGet(OnScribe::new);

        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);

        String waferIdCreationPattern = onFabConf.map(OnFabConf::getWaferIdCreationPattern).orElse(null);

        final String scribe2VidUrl;
        final ScribeResultType scribeResultType;
        final boolean onScribeWaferIdEqualsScribeId;
        if (onFabConf.isPresent()) {
            scribe2VidUrl = onFabConf.get().getScribe2VidUrl();
            scribeResultType = onFabConf.get().getScribeResultType() == null ? ScribeResultType.UNKNOWN : onFabConf.get().getScribeResultType();
            onScribeWaferIdEqualsScribeId = onFabConf.get().isOnScribeWaferIdEqualsScribeId();
        } else {
            scribe2VidUrl = ertConfService.getStringByName(ErtConfService.WS_SCRIBE_TO_VID_URL);
            scribeResultType = ScribeResultType.UNKNOWN;
            onScribeWaferIdEqualsScribeId = false;
        }

        WaferId scribe = null;
        try {
            scribe = caller.getWaferIdByScribeId(scribe2VidUrl, scribeResultType, scribeId, lotId);
        }catch (BusinessException be){
            //TODO maybe add some comment or log here that something went wrong and therefore usign a different approach (I am not sure what exactly is the logic here and why so cannot add the comment)
            updateOnScribeByLotIdScribeIdWaferNumFab(byService, lotId, scribeId, waferNum, fab, dataType, site, waferIdCreationPattern);
        }

        if (scribe != null && scribe.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            updateOnScribeByWaferId(byService, scribe, fab, dataType, site, onScribeWaferIdEqualsScribeId);
        } else {
            updateOnScribeByLotIdScribeIdWaferNumFab(byService, lotId, scribeId, waferNum, fab, dataType, site, waferIdCreationPattern);
        }

        return Optional.of(byService);
    }

    public long count() {
        return onScribeRepository.count();
    }

    public void delete(OnScribe onScribe) {
        onScribeRepository.delete(onScribe);
    }

    public OnScribe save(OnScribe onScribe) {
        if (onScribe == null) {
            LOG.error("OnScribe is null when saving it");
            return null;
        }
        return onScribeRepository.save(onScribe);
    }

    public ResponseEntity<OnScribeDto> createFromDto(OnScribeDto onScribeDto) {
        Date insertTime = DateUtils.convertStringToDate(onScribeDto.getInsertTime());
        if (insertTime == null) {
            OnScribeDto onScribeDtoResult = new OnScribeDto();
            onScribeDtoResult.setStatus(Status.ERROR);
            onScribeDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onScribeDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        OnScribe onScribe = save(new OnScribe(onScribeDto));
        OnScribeDto onScribeDtoResult = new OnScribeDto(onScribe);
        ResponseEntity<OnScribeDto> responseModel = new ResponseEntity<>(onScribeDtoResult, HttpStatus.CREATED);
        LOG.info("OnScribe entity created");
        return responseModel;
    }

    public ResponseEntity<OnScribeDto> updateFromDto(OnScribe onScribe, OnScribeDto onScribeDto) {
        if (onScribeDto.getStatus() != null) {
            onScribe.setStatus(onScribeDto.getStatus());
        }
        if (onScribeDto.getScribeId() != null) {
            onScribe.setScribeId(StringUtils.upperCase(onScribeDto.getScribeId()));
        }
        if (onScribeDto.getWaferId() != null) {
            onScribe.setWaferId(StringUtils.upperCase(onScribeDto.getWaferId()));
        }
        if (onScribeDto.getWaferNum() != null) {
            onScribe.setWaferNum(onScribeDto.getWaferNum());
        }
        if (onScribeDto.getLot() != null) {
            onScribe.setLot(StringUtils.upperCase(onScribeDto.getLot()));
        }
        if (onScribeDto.getFab() != null) {
            onScribe.setFab(StringUtils.upperCase(onScribeDto.getFab()));
        }
        if (onScribeDto.getWaferIdSource() != null) {
            onScribe.setWaferIdSource(onScribeDto.getWaferIdSource());
        }

        if (onScribeDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(onScribeDto.getInsertTime());
            if (insertTime == null) {
                OnScribeDto onScribeDtoResult = new OnScribeDto();
                onScribeDtoResult.setStatus(Status.ERROR);
                onScribeDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onScribeDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onScribe.setInsertTime(insertTime);
        }

        try {
            OnScribeDto onScribeDtoResult = new OnScribeDto(save(onScribe));
            LOG.info("OnScribe entity with id='{}' updated", onScribe.getId());
            return new ResponseEntity<>(onScribeDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            OnScribeDto onScribeDtoResult = new OnScribeDto();
            onScribeDtoResult.setStatus(Status.ERROR);
            onScribeDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onScribeDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void updateOnScribeByWaferId(OnScribe onScribe, WaferId waferId, String fab, String dataType,
            String site, boolean onScribeWaferIdEqualsScribeId) throws BusinessException {
        LOG.info("Updating onScribe, waferId='{}', fab='{}', dataType='{}'", waferId, fab, dataType);
        onScribe.setStatus(Status.FOUND);
        onScribe.setWaferIdSource(WaferIdSource.FROM_WS);
        onScribe.setInsertTime(DateUtils.getSqlDateNow());

        Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);
        String waferIdCreationPattern = onFabConf.map(OnFabConf::getWaferIdCreationPattern).orElse(null);

        final OnLot onLot = onLotService.findByLotId(onScribe.getLot(), null, fab, dataType, site, true).orElseGet(OnLot::new);

        caller.fillOnScribeByWaferIdResults(waferId, onLot, onScribe, fab, dataType, onScribeWaferIdEqualsScribeId, waferIdCreationPattern);
        onScribeRepository.save(onScribe);
    }

    private void updateOnScribeByLotIdScribeIdWaferNumFab(OnScribe onScribe, String lotId, String scribeId,
            String waferNum, String fab, String dataType, String site, String waferIdCreationPattern) throws BusinessException {

        LOG.info("Updating onScribe, lotId='{}', scribeId='{}', waferNum='{}, fab='{}'", lotId, scribeId, waferNum, fab);
        onScribe.setStatus(Status.FOUND);
        onScribe.setInsertTime(DateUtils.getSqlDateNow());
        onScribe.setLot(lotId);
        try {
            final Integer waferNumInteger = Integer.valueOf(waferNum);
            onScribe.setWaferNum(waferNumInteger);
            onScribe.setScribeId(StringUtils.upperCase(scribeId));

            final OnLot onLot = onLotService.findByLotId(lotId, null, fab, dataType, site, true).orElseGet(OnLot::new);

            onScribe.setWaferId(StringUtils.upperCase(AttributeUtils.calculateWaferId(lotId, waferNumInteger, onLot.getSourceLot(), waferIdCreationPattern)));
            onScribe.setWaferIdSource(WaferIdSource.CALCULATED);
            onScribe.setFab(StringUtils.upperCase(fab));
            onScribeRepository.save(onScribe);
        } catch (NumberFormatException nfe) {
            throw new BusinessException(String.format("Cannot convert waferNum='%s' to Integer", waferNum));
        }
    }
}
