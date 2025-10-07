package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpLotService;
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
@RequestMapping("/api/pplot")
@Tag(name = "PpLot service", description = "The PpLot service, you can list all and find items using it.")
class PpLotController {

    private static final Logger LOG = LoggerFactory.getLogger(PpLotController.class);

    private final PpLotService service;

    public PpLotController(PpLotService service) {
        this.service = service;
    }


    @Operation(summary = "List all the PpLot items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PpLotDto.class))))
    })
    @GetMapping("/all")
    public List<PpLotDto> all() {
        LOG.info("Obtaining all the PpLot data");
        List<PpLotDto> models = new ArrayList<>();
        for (PpLot entity : service.findAll()) {
            models.add(new PpLotDto(entity));
        }
        LOG.info("All the PpLot data obtained");
        return models;
    }

    @Operation(summary = "Find PpLot by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PpLotDto.class))),
    })
    @GetMapping("/byid/{id}")
    public PpLotDto byId(
            @Parameter(description = "Id of the PpLot to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining PpLot data by id='{}'", id);
        try {
            Optional<PpLot> ppLot = service.findById(id);
            if (ppLot.isPresent()) {
                PpLotDto ppLotDto = new PpLotDto(ppLot.get());
                ppLotDto.setStatus(Status.FOUND);
                LOG.info("PpLot data for id='{}' obtained", id);
                return ppLotDto;
            } else {
                PpLotDto ppLotDto = new PpLotDto();
                ppLotDto.setStatus(Status.NO_DATA);
                LOG.info("PpLot data for id='{}' not found", id);
                return ppLotDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting PpLot by id='%s'", id), e);
            PpLotDto ppLotDto = new PpLotDto();
            ppLotDto.setStatus(Status.ERROR);
            ppLotDto.setErrorMessage(e.getMessage());
            return ppLotDto;
        }
    }

    @Operation(summary = "Find PpLot by LotId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PpLotDto.class))),
    })
    @GetMapping("/bylotid/{lotId}")
    public PpLotDto byLotId(
            @Parameter(description = "LotId of the PpLot to be obtained. Cannot be empty.", required = true)
            @PathVariable String lotId) {

        try {
            lotId = StringUtils.upperCase(lotId);

            LOG.info("Obtaining PpLot data by lotId '{}'", lotId);
            Optional<PpLot> ppLot = service.findByLotId(lotId);
            if (ppLot.isPresent()) {
                PpLotDto ppLotDto = new PpLotDto(ppLot.get());
                ppLotDto.setStatus(Status.FOUND);
                LOG.info("PpLot data for lotId='{}' obtained", lotId);
                return ppLotDto;
            } else {
                PpLotDto ppLotDto = new PpLotDto();
                ppLotDto.setStatus(Status.NO_DATA);
                LOG.info("PpLot data for lotId='{}' not found", lotId);
                return ppLotDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting PpLot by lotId='%s'", lotId), e);
            PpLotDto ppLotDto = new PpLotDto();
            ppLotDto.setStatus(Status.ERROR);
            ppLotDto.setErrorMessage(e.getMessage());
            return ppLotDto;
        }
    }

    @Operation(summary = "Add a new PpLot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PpLot created",
                    content = @Content(schema = @Schema(implementation = PpLotDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "PpLot already exists")})
    @PostMapping("/create")
    public ResponseEntity<PpLotDto> createPpLot(
            @Parameter(description = "PpLot to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = PpLotDto.class))
            @RequestBody PpLotDto ppLotDto) {

        try {
            LOG.info("Creating ppLot entity");
            return service.createFromDto(ppLotDto);
        } catch (Exception e) {
            PpLotDto ppLotDtoResult = new PpLotDto();
            ppLotDtoResult.setStatus(Status.ERROR);
            ppLotDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(ppLotDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing PpLot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "PpLot not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<PpLotDto> updatePpLot(
            @Parameter(description = "Id of the PpLot to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "PpLot to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = PpLotDto.class))
            @RequestBody PpLotDto ppLotDto) {

        LOG.info("Updating ppLot entity with id='{}'", id);
        if (id != null) {
            Optional<PpLot> ppLotInner = service.findById(id);

            if (ppLotInner.isPresent()) {
                PpLot ppLot = ppLotInner.get();
                return service.updateFromDto(ppLot, ppLotDto);
            }
        }
        LOG.info("PpLot entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
