package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnScribe;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnScribeDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnScribeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/onscribe")
@Tag(name = "OnScribe service", description = "The OnScribe service, you can list all and find items using it.")
class OnScribeController {

    private static final Logger LOG = LoggerFactory.getLogger(OnScribeController.class);

    private final OnScribeService service;

    public OnScribeController(OnScribeService service) {
        this.service = service;
    }


    @Operation(summary = "List all the OnScribe items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnScribeDto.class))))
    })
    @GetMapping("/all")
    public List<OnScribeDto> all() {
        LOG.info("Obtaining all the OnScribe data");
        List<OnScribeDto> models = new ArrayList<>();
        for (OnScribe entity : service.findAll()) {
            models.add(new OnScribeDto(entity));
        }
        LOG.info("All the OnScribe data obtained");
        return models;
    }

    @Operation(summary = "Find OnScribe by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnScribeDto byId(
            @Parameter(description = "Id of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnScribe data by id='{}'", id);
        try {
            Optional<OnScribe> onScribe = service.findById(id);
            if (onScribe.isPresent()) {
                OnScribeDto onScribeDto = new OnScribeDto(onScribe.get());
                LOG.info("OnScribe data for id='{}' obtained", id);
                return onScribeDto;
            } else {
                OnScribeDto onScribeDto = new OnScribeDto();
                LOG.info("OnScribe data for id='{}' not found", id);
                onScribeDto.setStatus(Status.NO_DATA);
                return onScribeDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnScribe by id='%s'", id), e);
            OnScribeDto onScribeDto = new OnScribeDto();
            onScribeDto.setStatus(Status.ERROR);
            onScribeDto.setErrorMessage(e.getMessage());
            return onScribeDto;
        }
    }

    @Operation(summary = "Find OnScribe by LotId and WaferNumber")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
    })
    @GetMapping("/bylotidandwafernum/{lotId}/{waferNum}")
    public OnScribeDto byLotIdAndWaferNum(
            @Parameter(description = "LotId of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable String lotId,
            @Parameter(description = "WaferNumber of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable Integer waferNum,
            @Parameter(description = "FAB for this Lot. Will be stored in the Reference table DB")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Site on which to call the MES.")
            @RequestParam(required = false) String site) {

        return getOnScribeDto(lotId, lotId, waferNum, fab, dataType, site);
    }

    @Operation(summary = "Find OnScribe by LotId and WaferNumber. The LotId can be taken even from MfgLot.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
    })
    @GetMapping("/bylotidsandwafernum")
    public OnScribeDto byLotIdsAndWaferNum(
            @Parameter(description = "LotId of the OnScribe to be obtained.")
            @RequestParam(required = false) String lotId,
            @Parameter(description = "LotId from the MfgLot of the OnScribe to be obtained.")
            @RequestParam(required = false) String mfgLot,
            @Parameter(description = "WaferNumber of the OnScribe to be obtained. Cannot be empty.", required = true)
            @RequestParam Integer waferNum,
            @Parameter(description = "FAB for this Lot. Will be stored in the Reference table DB")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Site on which to call the MES.")
            @RequestParam(required = false) String site) {

        return getOnScribeDto(lotId, mfgLot, waferNum, fab, dataType, site);
    }

    private OnScribeDto getOnScribeDto(String lotId, String mfgLot, Integer waferNum, String fab, String dataType, String site) {
        try {
            lotId = StringUtils.upperCase(lotId);
            fab = StringUtils.upperCase(fab);
            dataType = StringUtils.upperCase(dataType);

            LOG.info("Obtaining OnScribe data by lotId='{}', waferNum='{}', fab='{}', dataType='{}'", lotId, waferNum, fab, dataType);
            Optional<OnScribe> onScribe = service.findByLotAndWaferNum(lotId, mfgLot, waferNum, fab, dataType, site);
            if (onScribe.isPresent()) {
                OnScribeDto onScribeDto = new OnScribeDto(onScribe.get());
                LOG.info("OnScribe data for lotId='{}', waferNum='{}', fab='{}', dataType='{}' obtained", lotId, waferNum, fab, dataType);
                return onScribeDto;
            } else {
                OnScribeDto onScribeDto = new OnScribeDto();
                LOG.info("OnScribe data for lotId='{}', waferNum='{}', fab='{}', dataType='{}' not found", lotId, waferNum, fab, dataType);
                onScribeDto.setStatus(Status.NO_DATA);
                return onScribeDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnScribe by lotId='%s', waferNum='%d', fab='%s', dataType='%s'", lotId, waferNum, fab, dataType), e);
            OnScribeDto onScribeDto = new OnScribeDto();
            onScribeDto.setStatus(Status.ERROR);
            onScribeDto.setErrorMessage(e.getMessage());
            return onScribeDto;
        }
    }

    @Operation(summary = "Find OnScribe by ScribeId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
    })
    @GetMapping("/byscribeid/{scribeId}")
    public OnScribeDto byScribeId(
            @Parameter(description = "ScribeId of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable String scribeId,
            @Parameter(description = "LotId, this is necessary for WaferId WS")
            @RequestParam(required = false) String lotId,
            @Parameter(description = "FAB for this Lot. Will be stored in the Reference table DB")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Site on which to call the MES.")
            @RequestParam(required = false) String site) {

        try {
            scribeId = StringUtils.upperCase(scribeId);
            lotId = StringUtils.upperCase(lotId);
            fab = StringUtils.upperCase(fab);
            dataType = StringUtils.upperCase(dataType);

            LOG.info("Obtaining OnScribe data by scribeId='{}', fab='{}'", scribeId, fab);
            Optional<OnScribe> onScribe = service.findByScribeId(scribeId, lotId, fab, dataType, site);
            if (onScribe.isPresent()) {
                OnScribeDto onScribeDto = new OnScribeDto(onScribe.get());
                LOG.info("OnScribe data for scribeId='{}', fab='{}' obtained", scribeId, fab);
                return onScribeDto;
            } else {
                OnScribeDto onScribeDto = new OnScribeDto();
                LOG.info("OnScribe data for scribeId='{}', fab='{}' not found", scribeId, fab);
                onScribeDto.setStatus(Status.NO_DATA);
                return onScribeDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnScribe by scribeId='%s', fab='%s'", scribeId, fab), e);
            OnScribeDto onScribeDto = new OnScribeDto();
            onScribeDto.setStatus(Status.ERROR);
            onScribeDto.setErrorMessage(e.getMessage());
            return onScribeDto;
        }
    }

    @Operation(summary = "Find OnScribe by Stdf info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
    })
    @GetMapping("/bystdfinfo/{lotId}/{waferNum}/{scribeId}")
    public OnScribeDto byStdfInfo(
            @Parameter(description = "LotId of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable String lotId,
            @Parameter(description = "WaferNumber of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable String waferNum,
            @Parameter(description = "ScribeId of the OnScribe to be obtained. Cannot be empty.", required = true)
            @PathVariable String scribeId,
            @Parameter(description = "FAB for this Lot. Will be stored in the Reference table DB")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Site on which to call the MES.")
            @RequestParam(required = false) String site) {

        try {
            lotId = StringUtils.upperCase(lotId);
            waferNum = StringUtils.upperCase(waferNum);
            scribeId = StringUtils.upperCase(scribeId);
            fab = StringUtils.upperCase(fab);
            dataType = StringUtils.upperCase(dataType);

            LOG.info("Obtaining OnScribe data by lotId='{}', waferNum='{}', scribeId='{}', fab='{}', dataType='{}'", lotId, waferNum, scribeId, fab, dataType);
            Optional<OnScribe> onScribe = service.findByLotIdAndWaferNumAndScribeId(lotId, waferNum, scribeId, fab, dataType, site);
            if (onScribe.isPresent()) {
                OnScribeDto onScribeDto = new OnScribeDto(onScribe.get());
                LOG.info("OnScribe data for lotId='{}', waferNum='{}', scribeId='{}', fab='{}', dataType='{}' obtained", lotId, waferNum, scribeId, fab, dataType);
                return onScribeDto;
            } else {
                //TODO - why is this here? It will never goes here, since the findByLotIdAndWaferNumAndScribeId creates if does not exist (TODO wrong method name)
                OnScribeDto onScribeDto = new OnScribeDto();
                LOG.info("OnScribe data for lotId='{}', waferNum='{}', scribeId='{}', fab='{}', dataType='{}' not found", lotId, waferNum, scribeId, fab, dataType);
                onScribeDto.setStatus(Status.NO_DATA);
                return onScribeDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnScribe by lotId='%s', waferNum='%s', scribeId='%s', fab='%s', dataType='%s'", lotId, waferNum, scribeId, fab, dataType), e);
            OnScribeDto onScribeDto = new OnScribeDto();
            onScribeDto.setStatus(Status.ERROR);
            onScribeDto.setErrorMessage(e.getMessage());
            return onScribeDto;
        }
    }

    @Operation(summary = "Add a new OnScribe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnScribe created",
                    content = @Content(schema = @Schema(implementation = OnScribeDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnScribe already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnScribeDto> createOnScribe(
            @Parameter(description = "OnScribe to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnScribeDto.class))
            @RequestBody OnScribeDto onScribeDto) {

        try {
            LOG.info("Creating onScribe entity");
            return service.createFromDto(onScribeDto);
        } catch (Exception e) {
            OnScribeDto onScribeDtoResult = new OnScribeDto();
            onScribeDtoResult.setStatus(Status.ERROR);
            onScribeDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onScribeDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnScribe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnScribe not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnScribeDto> updateOnScribe(
            @Parameter(description = "Id of the OnScribe to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnScribe to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnScribeDto.class))
            @RequestBody OnScribeDto onScribeDto) {

        LOG.info("Updating onScribe entity with id='{}'", id);
        if (id != null) {
            Optional<OnScribe> onScribeInner = service.findById(id);

            if (onScribeInner.isPresent()) {
                OnScribe onScribe = onScribeInner.get();
                return service.updateFromDto(onScribe, onScribeDto);
            }
        }
        LOG.info("OnScribe entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
