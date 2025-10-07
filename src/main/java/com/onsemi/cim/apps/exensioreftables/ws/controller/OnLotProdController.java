package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.*;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnLotService;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnProdService;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/onlotprod")
@Tag(name = "OnLotProd service", description = "The OnLotProd service, you can list all and find items using it.")
class OnLotProdController {

    private static final Logger LOG = LoggerFactory.getLogger(OnLotProdController.class);

    private final OnLotService onLotService;
    private final OnProdService onProdService;

    public OnLotProdController(OnLotService onLotService, OnProdService onProdService) {
        this.onLotService = onLotService;
        this.onProdService = onProdService;
    }


    @Operation(summary = "Find OnLotProd by LotId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnLotProdDto.class))),
    })
    @GetMapping("/bylotid/{lotId}")
    public OnLotProdDto byLotId(
            @Parameter(description = "LotId of the OnLot to be obtained. Cannot be empty.", required = true)
            @PathVariable String lotId,
            @Parameter(description = "Product to be taken to query DataWarehouse when LotG is not found.")
            @RequestParam(required = false) String alternateProduct,
            @Parameter(description = "Fab to be taken when LotG is not found.")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Site on which to call the MES.")
            @RequestParam(required = false) String site) {

        OnLotProdDto onLotProdDto = new OnLotProdDto();
        try {
            lotId = StringUtils.upperCase(lotId);

            LOG.info("Obtaining OnLot data by lotId '{}'", lotId);
            Optional<OnLot> onLot = onLotService.findByLotId(lotId, alternateProduct, fab, dataType, site, false);
            if (onLot.isPresent()) {
                OnLotDto onLotDto = new OnLotDto(onLot.get());
                onLotProdDto.setOnLot(onLotDto);
                LOG.info("OnLot data for lotId='{}' obtained", lotId);
                if (onLotDto.getProduct() != null) {
                    Optional<OnProd> onProd = onProdService.findByProduct(onLotDto.getProduct());
                    if (onProd.isPresent()) {
                        OnProdDto onProdDto = new OnProdDto(onProd.get());
                        onLotProdDto.setOnProd(onProdDto);
                        LOG.info("OnProd data for product='{}' obtained", onLotDto.getProduct());
                    }
                } else {
                    onLotProdDto.setOnProd(new OnProdDto());
                }
                return onLotProdDto;
            } else {
                onLotProdDto.setOnLot(new OnLotDto());
                onLotProdDto.setOnProd(new OnProdDto());
                LOG.info("OnLot data for lotId='{}' not found", lotId);
                return onLotProdDto;
            }
        } catch (Exception e) {
            OnLotDto onLotDto = new OnLotDto();
            onLotDto.setStatus(Status.ERROR);
            onLotDto.setErrorMessage(e.getMessage());
            onLotProdDto.setOnLot(onLotDto);
            OnProdDto onProdDto = new OnProdDto();
            onProdDto.setStatus(Status.ERROR);
            onProdDto.setErrorMessage(e.getMessage());
            onLotProdDto.setOnProd(onProdDto);
            // Logging of the exception was removed because this happens many times in a day and the exception took big part of the log.
            // It was difficult to find out important information in the log when it was full of these exceptions
            LOG.error(String.format("Error during getting OnLot by lotId='%s'", lotId));
            return onLotProdDto;
        }
    }
}
