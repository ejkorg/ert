package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnProdRepository;
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
public class OnProdService {

    private static final Logger LOG = LoggerFactory.getLogger(OnProdService.class);

    private final OnProdRepository onProdRepository;

    public OnProdService(OnProdRepository onProdRepository) {
        this.onProdRepository = onProdRepository;
    }

    public List<OnProd> findAll() {
        return onProdRepository.findAll();
    }

    public Optional<OnProd> findById(Long id) {
        return onProdRepository.findById(id);
    }

    public Optional<OnProd> findByProduct(String product) {
        return onProdRepository.findByProduct(StringUtils.upperCase(product));
    }

    public long count() {
        return onProdRepository.count();
    }

    public void delete(OnProd onProd) {
        onProdRepository.delete(onProd);
    }

    public OnProd save(OnProd onProd) {
        if (onProd == null) {
            LOG.error("OnProd is null when saving it");
            return null;
        }
        return onProdRepository.save(onProd);
    }

    public ResponseEntity<OnProdDto> createFromDto(OnProdDto onProdDto) {
        Date insertTime = DateUtils.convertStringToDate(onProdDto.getInsertTime());
        if (insertTime == null) {
            OnProdDto onProdDtoResult = new OnProdDto();
            onProdDtoResult.setStatus(Status.ERROR);
            onProdDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onProdDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        OnProd onProd = save(new OnProd(onProdDto));
        OnProdDto onProdDtoResult = new OnProdDto(onProd);
        ResponseEntity<OnProdDto> responseModel = new ResponseEntity<>(onProdDtoResult, HttpStatus.CREATED);
        LOG.info("OnProd entity created");
        return responseModel;
    }

    public ResponseEntity<OnProdDto> updateFromOnDto(OnProd onProd, OnProdDto onProdDto) {
        if (onProdDto.getStatus() != null) {
            onProd.setStatus(onProdDto.getStatus());
        }
        if (onProdDto.getProduct() != null) {
            onProd.setProduct(StringUtils.upperCase(onProdDto.getProduct()));
        }
        if (onProdDto.getItemType() != null) {
            onProd.setItemType(StringUtils.upperCase(onProdDto.getItemType()));
        }
        if (onProdDto.getFab() != null) {
            onProd.setFab(StringUtils.upperCase(onProdDto.getFab()));
        }
        if (onProdDto.getFabDesc() != null) {
            onProd.setFabDesc(StringUtils.upperCase(onProdDto.getFabDesc()));
        }
        if (onProdDto.getAfm() != null) {
            onProd.setAfm(StringUtils.upperCase(onProdDto.getAfm()));
        }
        if (onProdDto.getProcess() != null) {
            onProd.setProcess(StringUtils.upperCase(onProdDto.getProcess()));
        }
        if (onProdDto.getFamily() != null) {
            onProd.setFamily(StringUtils.upperCase(onProdDto.getFamily()));
        }
        if (onProdDto.getPackage() != null) {
            onProd.setPackage(StringUtils.upperCase(onProdDto.getPackage()));
        }
        if (onProdDto.getGdpw() != null) {
            onProd.setGdpw(onProdDto.getGdpw());
        }
        if (onProdDto.getWfUnits() != null) {
            onProd.setWfUnits(onProdDto.getWfUnits());
        }
        if (onProdDto.getWfSize() != null) {
            onProd.setWfSize(onProdDto.getWfSize());
        }
        if (onProdDto.getDieUnits() != null) {
            onProd.setDieUnits(onProdDto.getDieUnits());
        }
        if (onProdDto.getDieHeight() != null) {
            onProd.setDieHeight(onProdDto.getDieHeight());
        }
        if (onProdDto.getDieWidth() != null) {
            onProd.setDieWidth(onProdDto.getDieWidth());
        }
        if (onProdDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(onProdDto.getInsertTime());
            if (insertTime == null) {
                OnProdDto onProdDtoResult = new OnProdDto();
                onProdDtoResult.setStatus(Status.ERROR);
                onProdDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onProdDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onProd.setInsertTime(insertTime);
        }
        if (onProdDto.getPti4() != null) {
            onProd.setPti4(StringUtils.upperCase(onProdDto.getPti4()));
        }
        if (onProdDto.getTechnology() != null) {
            onProd.setTechnology(StringUtils.upperCase(onProdDto.getTechnology()));
        }
        if (onProdDto.getMaskSet() != null) {
            onProd.setMaskSet(StringUtils.upperCase(onProdDto.getMaskSet()));
        }

        try {
            OnProdDto onProdDtoResult = new OnProdDto(save(onProd));
            LOG.info("OnProd entity with id='{}' updated", onProd.getId());
            return new ResponseEntity<>(onProdDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            OnProdDto onProdDtoResult = new OnProdDto();
            onProdDtoResult.setStatus(Status.ERROR);
            onProdDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onProdDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
