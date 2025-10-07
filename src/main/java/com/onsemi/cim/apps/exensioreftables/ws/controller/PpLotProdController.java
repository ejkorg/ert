package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpLotProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpLotService;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpProdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/pplotprod")
@Tag(name = "PpLotProd service", description = "The PpLotProd service, you can list all and find items using it.")
class PpLotProdController {

    private static final Logger LOG = LoggerFactory.getLogger(PpLotProdController.class);

    private final PpLotService ppLotService;
    private final PpProdService ppProdService;

    public PpLotProdController(PpLotService ppLotService, PpProdService ppProdService) {
        this.ppLotService = ppLotService;
        this.ppProdService = ppProdService;
    }


    @Operation(summary = "Find PpLotProd by LotId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PpLotProdDto.class))),
    })
    @GetMapping("/bylotid/{lotId}")
    public PpLotProdDto byLotId(
            @Parameter(description = "LotId of the PpLot to be obtained. Cannot be empty.", required = true)
            @PathVariable String lotId) {

        PpLotProdDto ppLotProdDto = new PpLotProdDto();
        try {
            lotId = StringUtils.upperCase(lotId);

            LOG.info("Obtaining PpLot data by lotId '{}'", lotId);
            Optional<PpLot> ppLot = ppLotService.findByLotId(lotId);
            if (ppLot.isPresent()) {
                PpLotDto ppLotDto = new PpLotDto(ppLot.get());
                ppLotDto.setStatus(Status.FOUND);
                ppLotProdDto.setPpLot(ppLotDto);
                LOG.info("PpLot data for lotId='{}' obtained", lotId);
                if (ppLotDto.getProduct() != null) {
                    Optional<PpProd> ppProd = ppProdService.findByProduct(ppLotDto.getProduct());
                    if (ppProd.isPresent()) {
                        PpProdDto ppProdDto = new PpProdDto(ppProd.get());
                        ppProdDto.setStatus(Status.FOUND);
                        ppLotProdDto.setPpProd(ppProdDto);
                        LOG.info("PpProd data for product='{}' obtained", ppLotDto.getProduct());
                    }
                } else {
                    PpProdDto ppProdDto = new PpProdDto();
                    ppProdDto.setStatus(Status.NO_DATA);
                    ppLotProdDto.setPpProd(ppProdDto);
                }
                return ppLotProdDto;
            } else {
                PpLotDto ppLotDto = new PpLotDto();
                ppLotDto.setStatus(Status.NO_DATA);
                ppLotProdDto.setPpLot(ppLotDto);
                PpProdDto ppProdDto = new PpProdDto();
                ppProdDto.setStatus(Status.NO_DATA);
                ppLotProdDto.setPpProd(ppProdDto);
                LOG.info("PpLot and PpProd data for lotId='{}' not found", lotId);
                return ppLotProdDto;
            }
        } catch (Exception e) {
            PpLotDto ppLotDto = new PpLotDto();
            ppLotDto.setStatus(Status.ERROR);
            ppLotDto.setErrorMessage(e.getMessage());
            ppLotProdDto.setPpLot(ppLotDto);
            PpProdDto ppProdDto = new PpProdDto();
            ppProdDto.setStatus(Status.ERROR);
            ppProdDto.setErrorMessage(e.getMessage());
            ppLotProdDto.setPpProd(ppProdDto);
            LOG.error(String.format("Error during getting PpLot by lotId='%s'", lotId), e);
            return ppLotProdDto;
        }
    }
}
