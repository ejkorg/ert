package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnSiteConfDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnSiteConfRepository;
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
@RequestMapping("/api/onsiteconf")
@Tag(name = "OnSiteConf service", description = "The OnSiteConf service, you can create new, update, delete and list items using it.")
class OnSiteConfController {

    private static final Logger LOG = LoggerFactory.getLogger(OnSiteConfController.class);

    private final OnSiteConfRepository repository;

    public OnSiteConfController(OnSiteConfRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "List all the OnSiteConf items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnSiteConfDto.class))))
    })
    @GetMapping("/all")
    public List<OnSiteConfDto> all() {
        LOG.info("Obtaining all the OnSiteConf data");
        final List<OnSiteConfDto> models = new ArrayList<>();
        for (OnSiteConf entity : repository.findAll()) {
            models.add(new OnSiteConfDto(entity));
        }
        LOG.info("All the OnSiteConf data obtained");
        return models;
    }

    @Operation(summary = "Find OnSiteConf by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnSiteConfDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnSiteConfDto byId(
            @Parameter(description = "Id of the OnSiteConf to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnSiteConf data for id='{}'", id);
        if (id != null) {
            final Optional<OnSiteConf> onSiteConf = repository.findById(id);
            if (onSiteConf.isPresent()) {
                final OnSiteConfDto onSiteConfDto = new OnSiteConfDto(onSiteConf.get());
                LOG.info("OnSiteConf data for id='{}' obtained", id);
                return onSiteConfDto;
            } else {
                final OnSiteConfDto onSiteConfDto = new OnSiteConfDto();
                onSiteConfDto.setStatus(Status.NO_DATA);
                LOG.info("OnSiteConf data for id='{}' not found", id);
                return onSiteConfDto;
            }
        } else {
            final String errorMessage = String.format("Error during getting OnSiteConf by id='%d'", id);
            LOG.error(errorMessage);
            final OnSiteConfDto onSiteConfDto = new OnSiteConfDto();
            onSiteConfDto.setStatus(Status.ERROR);
            return onSiteConfDto;
        }
    }

    @Operation(summary = "Find OnSiteConf by site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnSiteConfDto.class))),
    })
    @GetMapping("/bysite/{site}")
    public OnSiteConfDto bySite(
            @Parameter(description = "Site of the OnSiteConf to be obtained. Cannot be empty.", required = true)
            @PathVariable String site) {

        LOG.info("Obtaining OnSiteConf data for site='{}'", site);
        final Optional<OnSiteConf> onSiteConf = repository.getBySite(site);
        if (onSiteConf.isPresent()) {
            final OnSiteConfDto onSiteConfDto = new OnSiteConfDto(onSiteConf.get());
            LOG.info("OnSiteConf data for site='{}' obtained", site);
            return onSiteConfDto;

        } else {
            final OnSiteConfDto onSiteConfDto = new OnSiteConfDto();
            onSiteConfDto.setStatus(Status.NO_DATA);
            LOG.info("OnSiteConf data for site='{}' not found", site);
            return onSiteConfDto;
        }
    }

    @Operation(summary = "Add a new OnSiteConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnSiteConf created",
                    content = @Content(schema = @Schema(implementation = OnSiteConfDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnSiteConf already exists")
    })
    @PostMapping("/create")
    public ResponseEntity<OnSiteConfDto> createOnSiteConf(
            @Parameter(description = "OnSiteConf to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnSiteConfDto.class))
            @RequestBody OnSiteConfDto onSiteConfDto) {

        try {
            // Validate lotTrimRule if present
            if (onSiteConfDto.getLotTrimRule() != null && !onSiteConfDto.getLotTrimRule().trim().isEmpty()) {
                try {
                    com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule.parse(onSiteConfDto.getLotTrimRule());
                } catch (Exception e) {
                    final OnSiteConfDto err = new OnSiteConfDto();
                    err.setStatus(Status.ERROR);
                    LOG.info("Invalid lotTrimRule: {}", e.getMessage());
                    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
                }
            }
            LOG.info("Creating onSiteConf entity");
            final OnSiteConf onSiteConf = repository.save(new OnSiteConf(onSiteConfDto));
            final OnSiteConfDto onSiteConfDtoResult = new OnSiteConfDto(onSiteConf);
            final ResponseEntity<OnSiteConfDto> responseModel = new ResponseEntity<>(onSiteConfDtoResult, HttpStatus.CREATED);
            LOG.info("OnSiteConf entity created");
            return responseModel;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnSiteConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnSiteConf not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnSiteConfDto> updateOnSiteConf(
            @Parameter(description = "Id of the OnSiteConf to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnSiteConf to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnSiteConfDto.class))
            @RequestBody OnSiteConfDto onSiteConfDto) {

        LOG.info("Updating onSiteConf entity with id='{}'", id);
        if (id != null) {
            final Optional<OnSiteConf> onSiteConfInner = repository.findById(id);

            if (onSiteConfInner.isPresent()) {
                final OnSiteConf onSiteConf = onSiteConfInner.get();
                onSiteConf.setSite(onSiteConfDto.getSite());
                onSiteConf.setMesType(onSiteConfDto.getMesType());
                // Validate and set lotTrimRule if present
                if (onSiteConfDto.getLotTrimRule() != null && !onSiteConfDto.getLotTrimRule().trim().isEmpty()) {
                    try {
                        com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule.parse(onSiteConfDto.getLotTrimRule());
                    } catch (Exception e) {
                        final OnSiteConfDto err = new OnSiteConfDto();
                        err.setStatus(Status.ERROR);
                        LOG.info("Invalid lotTrimRule: {}", e.getMessage());
                        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
                    }
                    onSiteConf.setLotTrimRule(onSiteConfDto.getLotTrimRule());
                }
                LOG.info("OnSiteConf entity with id='{}' updated", id);
                final OnSiteConfDto onSiteConfDtoResult = new OnSiteConfDto(repository.save(onSiteConf));
                return new ResponseEntity<>(onSiteConfDtoResult, HttpStatus.OK);
            }
        }
        LOG.info("OnSiteConf entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a OnSiteConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "OnSiteConf not found")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOnSiteConf(
            @Parameter(description = "Id of the OnSiteConf to be deleted. Cannot be empty.", required = true)
            @PathVariable("id") Long id) {

        try {
            LOG.info("Deleting onSiteConf entity with id='{}'", id);
            if (id != null) {
                repository.deleteById(id);
                LOG.info("OnSiteConf entity with id='{}' deleted", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                LOG.info("OnSiteConf entity with id='{}' not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.info(String.format("OnSiteConf entity with id='%d' cannot be deleted", id), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
