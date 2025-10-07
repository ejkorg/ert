package com.onsemi.cim.apps.exensioreftables.ws.controller;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSlice;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnSliceDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnSliceService;
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


/**
 * @author fg7ngj - Outericky, 5/24/2022
 */

@RestController
@RequestMapping("/api/onslice")
@Tag(name = "OnSlice service", description = "The OnSlice service, you can list all and find items using it.")
public class OnSliceController {

    private static final Logger LOG = LoggerFactory.getLogger(OnSliceController.class);

    private final OnSliceService service;

    public OnSliceController(OnSliceService service) {
        this.service = service;
    }

    @Operation(summary = "List all the OnSlice items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnSliceDto.class))))
    })
    @GetMapping("/all")
    public List<OnSliceDto> all() {
        LOG.info("Obtaining all the OnSlice data");
        List<OnSliceDto> models = new ArrayList<>();
        for (OnSlice entity : service.findAll()) {
            models.add(new OnSliceDto(entity));
        }
        LOG.info("All the OnSlice data obtained");
        return models;
    }


    @Operation(summary = "Find OnSlice by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnSliceDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnSliceDto byId(
            @Parameter(description = "Id of the OnSlice to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnSlice data by id='{}'", id);

        try {
            Optional<OnSlice> onSlice = service.findById(id);
            return createDtoFromOnSlice(onSlice, "id", id);
        } catch (Exception e) {
            return createErrorDto("id", id, e);
        }
    }


    @Operation(summary = "Find OnSlice by slice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnSliceDto.class))),
    })
    @GetMapping("/byslice/{slice}")
    public OnSliceDto bySlice(
            @Parameter(description = "Slice of the OnSlice to be obtained. Cannot be empty.", required = true)
            @PathVariable String slice) {

        slice = StringUtils.upperCase(slice);
        LOG.info("Obtaining OnSlice data by slice '{}'", slice);

        try {
            Optional<OnSlice> onSlice = service.findBySlice(slice);
            return createDtoFromOnSlice(onSlice, "slice", slice);
        } catch (Exception e) {
            return createErrorDto("slice", slice, e);
        }
    }


    @Operation(summary = "Find OnSlice by globalWaferId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnSliceDto.class))),
    })
    @GetMapping("/byglobalwaferid/{globalWaferId}")
    public OnSliceDto byGlobalWaferId(
            @Parameter(description = "GlobalWaferId of the OnSlice to be obtained. Cannot be empty.", required = true)
            @PathVariable String globalWaferId) {

        globalWaferId = StringUtils.upperCase(globalWaferId);
        LOG.info("Obtaining OnSlice data by globalWaferId '{}'", globalWaferId);

        try {
            Optional<OnSlice> onSlice = service.findByGlobalWaferId(globalWaferId);
            return createDtoFromOnSlice(onSlice, "globalWaferId", globalWaferId);
        } catch (Exception e) {
            return createErrorDto("globalWaferId", globalWaferId, e);
        }
    }


    @Operation(summary = "Add a new OnSlice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnSlice created",
                    content = @Content(schema = @Schema(implementation = OnSliceDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnSlice already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnSliceDto> createOnSlice(
            @Parameter(description = "OnSlice to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnSliceDto.class))
            @RequestBody OnSliceDto onSliceDto) {

        try {
            LOG.info("Creating onSlice entity");
            return service.createFromDto(onSliceDto);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            OnSliceDto onSliceDtoResult = new OnSliceDto();
            onSliceDtoResult.setStatus(Status.ERROR);
            onSliceDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onSliceDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnSlice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnSlice not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnSliceDto> updateOnSlice(
            @Parameter(description = "Id of the OnSlice to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnSlice to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnSliceDto.class))
            @RequestBody OnSliceDto onSliceDto) {

        LOG.info("Updating onSlice entity with id='{}'", id);
        if (id != null) {
            Optional<OnSlice> onSliceInner = service.findById(id);

            if (onSliceInner.isPresent()) {
                OnSlice onSlice = onSliceInner.get();
                return service.updateFromDto(onSlice, onSliceDto);
            }
        }
        LOG.info("OnSlice entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private OnSliceDto createDtoFromOnSlice(Optional<OnSlice> onSlice, String nameOfInputField, Object valueOfInputField) {

        OnSliceDto onSliceDto;

        if (onSlice.isPresent()) {
            onSliceDto = new OnSliceDto(onSlice.get());
            onSliceDto.setStatus(Status.FOUND);
            LOG.info("OnSlice data for {}='{}' obtained.", nameOfInputField, valueOfInputField);
        } else {
            onSliceDto = new OnSliceDto();
            onSliceDto.setStatus(Status.NO_DATA);
            LOG.info("OnSlice data for {}='{}' not found.", nameOfInputField, valueOfInputField);
        }
        return onSliceDto;

    }

    private OnSliceDto createErrorDto(String nameOfInputField, Object valueOfInputField, Exception e) {
        LOG.error("Error during getting OnSlice by " + nameOfInputField + " = " + valueOfInputField, e);
        OnSliceDto onSliceDto = new OnSliceDto();
        onSliceDto.setStatus(Status.ERROR);
        onSliceDto.setErrorMessage(e.getMessage());
        return onSliceDto;
    }
}
