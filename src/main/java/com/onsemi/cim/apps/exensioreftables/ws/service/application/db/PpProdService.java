package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.PpProdRepository;
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
public class PpProdService {

    private static final Logger LOG = LoggerFactory.getLogger(PpProdService.class);

    private final PpProdRepository ppProdRepository;

    public PpProdService(PpProdRepository ppProdRepository) {
        this.ppProdRepository = ppProdRepository;
    }

    public List<PpProd> findAll() {
        return ppProdRepository.findAll();
    }

    public Optional<PpProd> findById(Long id) {
        return ppProdRepository.findById(id);
    }

    public Optional<PpProd> findByProduct(String product) {
        return ppProdRepository.findByProduct(StringUtils.upperCase(product));
    }

    public long count() {
        return ppProdRepository.count();
    }

    public void delete(PpProd ppProd) {
        ppProdRepository.delete(ppProd);
    }

    public PpProd save(PpProd ppProd) {
        if (ppProd == null) {
            LOG.error("PpProd is null when saving it");
            return null;
        }
        return ppProdRepository.save(ppProd);
    }

    public ResponseEntity<PpProdDto> createFromDto(PpProdDto ppProdDto) {
        Date insertTime = DateUtils.convertStringToDate(ppProdDto.getInsertTime());
        if (insertTime == null) {
            PpProdDto ppProdDtoResult = new PpProdDto();
            return new ResponseEntity<>(ppProdDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        PpProd ppProd = save(new PpProd(ppProdDto));
        PpProdDto ppProdDtoResult = new PpProdDto(ppProd);
        ResponseEntity<PpProdDto> responseModel = new ResponseEntity<>(ppProdDtoResult, HttpStatus.CREATED);
        LOG.info("PpProd entity created");
        return responseModel;
    }

    public ResponseEntity<PpProdDto> updateFromOnDto(PpProd ppProd, PpProdDto ppProdDto) {
        if (ppProdDto.getProduct() != null) {
            ppProd.setProduct(StringUtils.upperCase(ppProdDto.getProduct()));
        }
        if (ppProdDto.getItemType() != null) {
            ppProd.setItemType(StringUtils.upperCase(ppProdDto.getItemType()));
        }
        if (ppProdDto.getFab() != null) {
            ppProd.setFab(StringUtils.upperCase(ppProdDto.getFab()));
        }
        if (ppProdDto.getFabDesc() != null) {
            ppProd.setFabDesc(StringUtils.upperCase(ppProdDto.getFabDesc()));
        }
        if (ppProdDto.getAfm() != null) {
            ppProd.setAfm(StringUtils.upperCase(ppProdDto.getAfm()));
        }
        if (ppProdDto.getProcess() != null) {
            ppProd.setProcess(StringUtils.upperCase(ppProdDto.getProcess()));
        }
        if (ppProdDto.getFamily() != null) {
            ppProd.setFamily(StringUtils.upperCase(ppProdDto.getFamily()));
        }
        if (ppProdDto.getPackage() != null) {
            ppProd.setPackage(StringUtils.upperCase(ppProdDto.getPackage()));
        }
        if (ppProdDto.getGdpw() != null) {
            ppProd.setGdpw(ppProdDto.getGdpw());
        }
        if (ppProdDto.getWfUnits() != null) {
            ppProd.setWfUnits(ppProdDto.getWfUnits());
        }
        if (ppProdDto.getWfSize() != null) {
            ppProd.setWfSize(ppProdDto.getWfSize());
        }
        if (ppProdDto.getDieUnits() != null) {
            ppProd.setDieUnits(ppProdDto.getDieUnits());
        }
        if (ppProdDto.getDieHeight() != null) {
            ppProd.setDieHeight(ppProdDto.getDieHeight());
        }
        if (ppProdDto.getDieWidth() != null) {
            ppProd.setDieWidth(ppProdDto.getDieWidth());
        }
        if (ppProdDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(ppProdDto.getInsertTime());
            if (insertTime == null) {
                PpProdDto ppProdDtoResult = new PpProdDto();
                return new ResponseEntity<>(ppProdDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            ppProd.setInsertTime(insertTime);
        }

        try {
            PpProdDto ppProdDtoResult = new PpProdDto(save(ppProd));
            LOG.info("PpProd entity with id='{}' updated", ppProd.getId());
            return new ResponseEntity<>(ppProdDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            PpProdDto ppProdDtoResult = new PpProdDto();
            return new ResponseEntity<>(ppProdDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
