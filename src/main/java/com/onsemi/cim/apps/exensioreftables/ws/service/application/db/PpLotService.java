package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.PpLotRepository;
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
public class PpLotService {

    private static final Logger LOG = LoggerFactory.getLogger(PpLotService.class);

    private final PpLotRepository ppLotRepository;


    public PpLotService(
            PpLotRepository ppLotRepository
    ) {

        this.ppLotRepository = ppLotRepository;
    }

    public List<PpLot> findAll() {
        return ppLotRepository.findAll();
    }

    public Optional<PpLot> findById(Long id) {
        return ppLotRepository.findById(id);
    }

    public Optional<PpLot> findByLotId(String lotId) {
        lotId = StringUtils.upperCase(lotId);
        return ppLotRepository.findByLot(lotId);
    }

    public long count() {
        return ppLotRepository.count();
    }

    public void delete(PpLot ppLot) {
        ppLotRepository.delete(ppLot);
    }

    public PpLot save(PpLot ppLot) {
        if (ppLot == null) {
            LOG.error("PpLot is null when saving it");
            return null;
        }
        return ppLotRepository.save(ppLot);
    }

    public ResponseEntity<PpLotDto> createFromDto(PpLotDto ppLotDto) {
        Date insertTime = DateUtils.convertStringToDate(ppLotDto.getInsertTime());
        if (insertTime == null) {
            PpLotDto ppLotDtoResult = new PpLotDto();
            return new ResponseEntity<>(ppLotDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        PpLot ppLot = save(new PpLot(ppLotDto));
        PpLotDto ppLotDtoResult = new PpLotDto(ppLot);
        ResponseEntity<PpLotDto> responseModel = new ResponseEntity<>(ppLotDtoResult, HttpStatus.CREATED);
        LOG.info("PpLot entity created");
        return responseModel;
    }

    public ResponseEntity<PpLotDto> updateFromDto(PpLot ppLot, PpLotDto ppLotDto) {
        if (ppLotDto.getLot() != null) {
            ppLot.setLot(StringUtils.upperCase(ppLotDto.getLot()));
        }
        if (ppLotDto.getParentLot() != null) {
            ppLot.setParentLot(StringUtils.upperCase(ppLotDto.getParentLot()));
        }
        if (ppLotDto.getProduct() != null) {
            ppLot.setProduct(StringUtils.upperCase(ppLotDto.getProduct()));
        }
        if (ppLotDto.getLotOwner() != null) {
            ppLot.setLotOwner(StringUtils.upperCase(ppLotDto.getLotOwner()));
        }
        if (ppLotDto.getParentProduct() != null) {
            ppLot.setParentProduct(StringUtils.upperCase(ppLotDto.getParentProduct()));
        }
        if (ppLotDto.getSourceLot() != null) {
            ppLot.setSourceLot(StringUtils.upperCase(ppLotDto.getSourceLot()));
        }
        if (ppLotDto.getInsertTime() != null) {
            Date insertTime = DateUtils.convertStringToDate(ppLotDto.getInsertTime());
            if (insertTime == null) {
                PpLotDto ppLotDtoResult = new PpLotDto();
                return new ResponseEntity<>(ppLotDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            ppLot.setInsertTime(insertTime);
        }

        try {
            PpLotDto ppLotDtoResult = new PpLotDto(save(ppLot));
            LOG.info("PpLot entity with id='{}' updated", ppLot.getId());
            return new ResponseEntity<>(ppLotDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            PpLotDto ppLotDtoResult = new PpLotDto();
            return new ResponseEntity<>(ppLotDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
