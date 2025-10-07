package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnFabConfDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnFabConfRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/onfabconf")
@Tag(name = "OnFabConf service", description = "The OnFabConf service, you can create new, update, delete and list items using it.")
class OnFabConfController {

    private static final Logger LOG = LoggerFactory.getLogger(OnFabConfController.class);

    private final OnFabConfRepository repository;

    public OnFabConfController(OnFabConfRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "List all the OnFabConf items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnFabConfDto.class))))
    })
    @GetMapping("/all")
    public List<OnFabConfDto> all() {
        LOG.info("Obtaining all the OnFabConf data");
        List<OnFabConfDto> models = new ArrayList<>();
        for (OnFabConf entity : repository.findAll()) {
            models.add(new OnFabConfDto(entity));
        }
        LOG.info("All the OnFabConf data obtained");
        return models;
    }

    @Operation(summary = "Find OnFabConf by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnFabConfDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnFabConfDto byId(
            @Parameter(description = "Id of the OnFabConf to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnFabConf data for id='{}'", id);
        if (id != null) {
            Optional<OnFabConf> onFabConf = repository.findById(id);
            if (onFabConf.isPresent()) {
                OnFabConfDto onFabConfDto = new OnFabConfDto(onFabConf.get());
                LOG.info("OnFabConf data for id='{}' obtained", id);
                return onFabConfDto;
            } else {
                OnFabConfDto onFabConfDto = new OnFabConfDto();
                onFabConfDto.setStatus(Status.NO_DATA);
                LOG.info("OnFabConf data for id='{}' not found", id);
                return onFabConfDto;
            }
        } else {
            String errorMessage = String.format("Error during getting OnFabConf by id='%d'", id);
            LOG.error(errorMessage);
            OnFabConfDto onFabConfDto = new OnFabConfDto();
            onFabConfDto.setStatus(Status.ERROR);
            return onFabConfDto;
        }
    }

    @Operation(summary = "Find OnFabConf by fab and data type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnFabConfDto.class))),
    })
    @GetMapping("/byfabanddatatype/{fab}/{dataType}")
    public OnFabConfDto byFabAndDataType(
            @Parameter(description = "Fab of the OnFabConf to be obtained. Cannot be empty.", required = true)
            @PathVariable String fab,
            @Parameter(description = "Data type of the OnFabConf to be obtained. Cannot be empty.", required = true)
            @PathVariable String dataType) {

        LOG.info("Obtaining OnFabConf data for fab='{}', dataType='{}'", fab, dataType);
        Optional<OnFabConf> onFabConf = repository.getByFoundryFabAndDataType(fab, dataType);
        if (onFabConf.isPresent()) {
            OnFabConfDto onFabConfDto = new OnFabConfDto(onFabConf.get());
            LOG.info("OnFabConf data for fab='{}', dataType='{}' obtained", fab, dataType);
            return onFabConfDto;
        } else {
            OnFabConfDto onFabConfDto = new OnFabConfDto();
            onFabConfDto.setStatus(Status.NO_DATA);
            LOG.info("OnFabConf data for fab='{}', dataType='{}' not found", fab, dataType);
            return onFabConfDto;
        }
    }

    @Operation(summary = "Add a new OnFabConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnFabConf created",
                    content = @Content(schema = @Schema(implementation = OnFabConfDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnFabConf already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnFabConfDto> createOnFabConf(
            @Parameter(description = "OnFabConf to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnFabConfDto.class))
            @RequestBody OnFabConfDto onFabConfDto) {

        try {
            LOG.info("Creating onFabConf entity");
            OnFabConf onFabConf = repository.save(new OnFabConf(onFabConfDto));
            OnFabConfDto onFabConfDtoResult = new OnFabConfDto(onFabConf);
            ResponseEntity<OnFabConfDto> responseModel = new ResponseEntity<>(onFabConfDtoResult, HttpStatus.CREATED);
            LOG.info("OnFabConf entity created");
            return responseModel;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnFabConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnFabConf not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnFabConfDto> updateOnFabConf(
            @Parameter(description = "Id of the OnFabConf to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnFabConf to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnFabConfDto.class))
            @RequestBody OnFabConfDto onFabConfDto) {

        LOG.info("Updating onFabConf entity with id='{}'", id);
        if (id != null) {
            Optional<OnFabConf> onFabConfInner = repository.findById(id);

            if (onFabConfInner.isPresent()) {
                OnFabConf onFabConf = onFabConfInner.get();
                onFabConf.setFoundryFab(onFabConfDto.getFoundryFab());
                onFabConf.setDataType(onFabConfDto.getDataType());
                onFabConf.setLtmUrl(onFabConfDto.getLtmUrl());
                onFabConf.setWmcUrl(onFabConfDto.getWmcUrl());
                onFabConf.setVid2ScribeUrl(onFabConfDto.getVid2ScribeUrl());
                onFabConf.setScribe2VidUrl(onFabConfDto.getScribe2VidUrl());
                onFabConf.setOnScribeWaferIdEqualsScribeId(onFabConfDto.isOnScribeWaferIdEqualsScribeId());
                onFabConf.setLotIdForOnScribeType(onFabConfDto.getLotIdForOnScribeType());
                onFabConf.setWaferIdCreationPattern(onFabConfDto.getWaferIdCreationPattern());
                onFabConf.setSourceLotAdjustmentPattern(onFabConfDto.getSourceLotAdjustmentPattern());
                onFabConf.setSecondLotgQuery(onFabConfDto.isSecondLotgQuery());
                onFabConf.setMatchupUrl(onFabConfDto.getMatchupUrl());
                LOG.info("OnFabConf entity with id='{}' updated", id);
                OnFabConfDto onFabConfDtoResult = new OnFabConfDto(repository.save(onFabConf));
                return new ResponseEntity<>(onFabConfDtoResult, HttpStatus.OK);
            }
        }
        LOG.info("OnFabConf entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a OnFabConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "OnFabConf not found")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOnFabConf(
            @Parameter(description = "Id of the OnFabConf to be deleted. Cannot be empty.", required = true)
            @PathVariable("id") Long id) {

        try {
            LOG.info("Deleting onFabConf entity with id='{}'", id);
            if (id != null) {
                repository.deleteById(id);
                LOG.info("OnFabConf entity with id='{}' deleted", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                LOG.info("OnFabConf entity with id='{}' not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.info(String.format("OnFabConf entity with id='%d' cannot be deleted", id), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
