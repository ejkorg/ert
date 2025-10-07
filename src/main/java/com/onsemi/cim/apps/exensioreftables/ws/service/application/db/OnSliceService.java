package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSlice;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnSliceDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnSliceRepository;
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

/**
 * @author fg7ngj - Outericky, 5/24/2022
 */

@Service
@Transactional
public class OnSliceService {

    private static final Logger LOG = LoggerFactory.getLogger(OnSliceService.class);

    OnSliceRepository onSliceRepository;


    public OnSliceService(   OnSliceRepository onSliceRepository ) {
        this.onSliceRepository = onSliceRepository;
    }

    public List<OnSlice> findAll() {
        return onSliceRepository.findAll();
    }

    public Optional<OnSlice> findById(Long id) {
        return onSliceRepository.findById(id);
    }

    public Optional<OnSlice> findBySlice(String slice){
        return onSliceRepository.findBySlice(slice);
    }

    public Optional<OnSlice> findByGlobalWaferId(String globalWaferId){
        return onSliceRepository.findByGlobalWaferId(globalWaferId);
    }

    public ResponseEntity<OnSliceDto> createFromDto(OnSliceDto onSliceDto) {
        OnSlice onSlice = save(new OnSlice(onSliceDto));
        OnSliceDto onSliceDtoResult = new OnSliceDto(onSlice);
        ResponseEntity<OnSliceDto> responseModel = new ResponseEntity<>(onSliceDtoResult, HttpStatus.CREATED);
        LOG.info("OnSlice entity created");
        return responseModel;
    }

    public ResponseEntity<OnSliceDto> updateFromDto(OnSlice onSlice, OnSliceDto onSliceDto) {

        //TODO - TO CONSIDER - checking nulls to update only the values which are defined and the othes keep as they are. Need to consider if this is OK
        // (not possible to set a value to null using this implementation) Maybe create two methods for these cases (one updates all, one just the nul null values).

        if (onSliceDto.getSlice() != null) {
            onSlice.setSlice(StringUtils.upperCase(onSliceDto.getSlice()));
        }
        if (onSliceDto.getPuckId() != null) {
            onSlice.setPuckId(StringUtils.upperCase(onSliceDto.getPuckId()));
        }
        if (onSliceDto.getRunId() != null) {
            onSlice.setRunId(StringUtils.upperCase(onSliceDto.getRunId()));
        }
        if (onSliceDto.getSliceSourceLot() != null) {
            onSlice.setSliceSourceLot(StringUtils.upperCase(onSliceDto.getSliceSourceLot()));
        }
        if (onSliceDto.getStartLot() != null) {
            onSlice.setStartLot(StringUtils.upperCase(onSliceDto.getStartLot()));
        }
        if (onSliceDto.getFabWaferId() != null) {
            onSlice.setFabWaferId(StringUtils.upperCase(onSliceDto.getFabWaferId()));
        }
        if (onSliceDto.getFabSourceLot() != null) {
            onSlice.setFabSourceLot(StringUtils.upperCase(onSliceDto.getFabSourceLot()));
        }
        if (onSliceDto.getSliceStartTime() != null) {
            Date sliceStartTime = DateUtils.convertStringToDate(onSliceDto.getSliceStartTime());
            if (sliceStartTime == null) {
                return createErrorResponse(String.format("Error parsing SliceStartTime, format '%s'", DateUtils.DATE_FORMAT));
            }
            onSlice.setSliceStartTime(sliceStartTime);
        }
        if (onSliceDto.getSlicePartname() != null) {
            onSlice.setSlicePartname(StringUtils.upperCase(onSliceDto.getSlicePartname()));
        }
        if (onSliceDto.getSliceLottype() != null) {
            onSlice.setSliceLottype(StringUtils.upperCase(onSliceDto.getSliceLottype()));
        }
        if (onSliceDto.getSliceSuplierid() != null) {
            onSlice.setSliceSuplierid(StringUtils.upperCase(onSliceDto.getSliceSuplierid()));
        }
        if (onSliceDto.getPuckHeight() != null) {
            onSlice.setPuckHeight(onSliceDto.getPuckHeight());
        }
        if (onSliceDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(onSliceDto.getInsertTime());
            if (insertTime == null) {
                return createErrorResponse(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
            }
            onSlice.setInsertTime(insertTime);
        }
        if (onSliceDto.getGlobalWaferId() != null) {
            onSlice.setGlobalWaferId(onSliceDto.getGlobalWaferId());
        }

        try {
            OnSliceDto onSlceDtoResult = new OnSliceDto(save(onSlice));
            LOG.info("OnSlice entity with id='{}' updated", onSlice.getId());
            return new ResponseEntity<>(onSlceDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    private ResponseEntity<OnSliceDto> createErrorResponse(String errorMessage){
        OnSliceDto onSliceDtoResult = new OnSliceDto();
        onSliceDtoResult.setStatus(Status.ERROR);
        onSliceDtoResult.setErrorMessage(errorMessage);
        return new ResponseEntity<>(onSliceDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public OnSlice save(OnSlice onSlice) {
        if (onSlice == null) {
            LOG.error("OnSlice is null. Are you sure you have connected your form to the application?");
            return null;
        }
        return onSliceRepository.save(onSlice);
    }

}
