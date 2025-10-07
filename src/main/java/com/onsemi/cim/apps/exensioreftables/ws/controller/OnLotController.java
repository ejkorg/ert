package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnLotService;
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
@RequestMapping("/api/onlot")
@Tag(name = "OnLot service", description = "The OnLot service, you can list all and find items using it.")
class OnLotController {

    private static final Logger LOG = LoggerFactory.getLogger(OnLotController.class);

    private final OnLotService service;

    public OnLotController(OnLotService service) {
        this.service = service;
    }


    @Operation(summary = "List all the OnLot items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnLotDto.class))))
    })
    @GetMapping("/all")
    public List<OnLotDto> all() {
        LOG.info("Obtaining all the OnLot data");
        List<OnLotDto> models = new ArrayList<>();
        for (OnLot entity : service.findAll()) {
            models.add(new OnLotDto(entity));
        }
        LOG.info("All the OnLot data obtained");
        return models;
    }

    @Operation(summary = "Find OnLot by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnLotDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnLotDto byId(
            @Parameter(description = "Id of the OnLot to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnLot data by id='{}'", id);
        try {
            Optional<OnLot> onLot = service.findById(id);
            if (onLot.isPresent()) {
                OnLotDto onLotDto = new OnLotDto(onLot.get());
                LOG.info("OnLot data for id='{}' obtained", id);
                return onLotDto;
            } else {
                OnLotDto onLotDto = new OnLotDto();
                LOG.info("OnLot data for id='{}' not found", id);
                onLotDto.setStatus(Status.NO_DATA);
                return onLotDto;
            }
        } catch (Exception e) {
            // Logging of the exception was removed because this happens many times in a day and the exception took big part of the log.
            // It was difficult to find out important information in the log when it was full of these exceptions
            LOG.error(String.format("Error during getting OnLot by id='%s'", id));
            OnLotDto onLotDto = new OnLotDto();
            onLotDto.setStatus(Status.ERROR);
            onLotDto.setErrorMessage(e.getMessage());
            return onLotDto;
        }
    }

    @Operation(summary = "Find OnLot by LotId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnLotDto.class))),
    })
    @GetMapping("/bylotid/{lotId}")
    public OnLotDto byLotId(
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

        try {
            lotId = StringUtils.upperCase(lotId);
            alternateProduct = StringUtils.upperCase(alternateProduct);
            fab = StringUtils.upperCase(fab);
            dataType = StringUtils.upperCase(dataType);

            LOG.info("Obtaining OnLot data by lotId '{}'", lotId);
            Optional<OnLot> onLot = service.findByLotId(lotId, alternateProduct, fab, dataType, site, false);
            if (onLot.isPresent()) {
                OnLotDto onLotDto = new OnLotDto(onLot.get());
                LOG.info("OnLot data for lotId='{}' obtained", lotId);
                return onLotDto;
            } else {
                OnLotDto onLotDto = new OnLotDto();
                LOG.info("OnLot data for lotId='{}' not found", lotId);
                onLotDto.setStatus(Status.NO_DATA);
                return onLotDto;
            }
        } catch (Exception e) {
            // Logging of the exception was removed because this happens many times in a day and the exception took big part of the log.
            // It was difficult to find out important information in the log when it was full of these exceptions
            LOG.error(String.format("Error during getting OnLot by lotId='%s'", lotId));
            OnLotDto onLotDto = new OnLotDto();
            onLotDto.setStatus(Status.ERROR);
            onLotDto.setErrorMessage(e.getMessage());
            return onLotDto;
        }
    }

    @Operation(summary = "Add a new OnLot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnLot created",
                    content = @Content(schema = @Schema(implementation = OnLotDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnLot already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnLotDto> createOnLot(
            @Parameter(description = "OnLot to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnLotDto.class))
            @RequestBody OnLotDto onLotDto) {

        try {
            LOG.info("Creating onLot entity");
            return service.createFromDto(onLotDto);
        } catch (Exception e) {
            OnLotDto onLotDtoResult = new OnLotDto();
            onLotDtoResult.setStatus(Status.ERROR);
            onLotDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onLotDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnLot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnLot not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnLotDto> updateOnLot(
            @Parameter(description = "Id of the OnLot to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnLot to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnLotDto.class))
            @RequestBody OnLotDto onLotDto) {

        LOG.info("Updating onLot entity with id='{}'", id);
        if (id != null) {
            Optional<OnLot> onLotInner = service.findById(id);

            if (onLotInner.isPresent()) {
                OnLot onLot = onLotInner.get();
                return service.updateFromDto(onLot, onLotDto);
            }
        }
        LOG.info("OnLot entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
