package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnProdService;
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
@RequestMapping("/api/onprod")
@Tag(name = "OnProd service", description = "The OnProd service, you can list all and find items using it.")
class OnProdController {

    private static final Logger LOG = LoggerFactory.getLogger(OnProdController.class);

    private final OnProdService service;

    public OnProdController(OnProdService service) {
        this.service = service;
    }


    @Operation(summary = "List all the OnProd items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnProdDto.class))))
    })
    @GetMapping("/all")
    public List<OnProdDto> all() {
        LOG.info("Obtaining all the OnProd data");
        List<OnProdDto> models = new ArrayList<>();
        for (OnProd entity : service.findAll()) {
            models.add(new OnProdDto(entity));
        }
        LOG.info("All the OnProd data obtained");
        return models;
    }

    @Operation(summary = "Find OnProd by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnProdDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnProdDto byId(
            @Parameter(description = "Id of the OnProd to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnProd data by id='{}'", id);
        try {
            Optional<OnProd> onProd = service.findById(id);
            if (onProd.isPresent()) {
                OnProdDto onProdDto = new OnProdDto(onProd.get());
                LOG.info("OnProd data for id='{}' obtained", id);
                return onProdDto;
            } else {
                OnProdDto onProdDto = new OnProdDto();
                LOG.info("OnProd data for id='{}' not found", id);
                onProdDto.setStatus(Status.NO_DATA);
                return onProdDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnProd by id='%s'", id), e);
            OnProdDto onProdDto = new OnProdDto();
            onProdDto.setStatus(Status.ERROR);
            onProdDto.setErrorMessage(e.getMessage());
            return onProdDto;
        }
    }

    @Operation(summary = "Find OnProd by Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnProdDto.class))),
    })
    @GetMapping("/byproduct/{product}")
    public OnProdDto byProduct(
            @Parameter(description = "Product of the OnProd to be obtained. Cannot be empty.", required = true)
            @PathVariable String product) {

        try {
            product = StringUtils.upperCase(product);

            LOG.info("Obtaining OnProd data by product='{}'", product);
            Optional<OnProd> onProd = service.findByProduct(product);
            if (onProd.isPresent()) {
                OnProdDto onProdDto = new OnProdDto(onProd.get());
                LOG.info("OnProd data for product='{}' obtained", product);
                return onProdDto;
            } else {
                OnProdDto onProdDto = new OnProdDto();
                LOG.info("OnProd data for product='{}' not found", product);
                onProdDto.setStatus(Status.NO_DATA);
                return onProdDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnProd by product='%s'", product), e);
            OnProdDto onProdDto = new OnProdDto();
            onProdDto.setStatus(Status.ERROR);
            onProdDto.setErrorMessage(e.getMessage());
            return onProdDto;
        }
    }

    @Operation(summary = "Add a new OnProd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnProd created",
                    content = @Content(schema = @Schema(implementation = OnProdDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnProd already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnProdDto> createOnProd(
            @Parameter(description = "OnProd to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnProdDto.class))
            @RequestBody OnProdDto onProdDto) {

        try {
            LOG.info("Creating onProd entity");
            return service.createFromDto(onProdDto);
        } catch (Exception e) {
            OnProdDto onProdDtoResult = new OnProdDto();
            onProdDtoResult.setStatus(Status.ERROR);
            onProdDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onProdDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnProd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnProd not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnProdDto> updateOnProd(
            @Parameter(description = "Id of the OnProd to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnProd to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnProdDto.class))
            @RequestBody OnProdDto onProdDto) {

        LOG.info("Updating onProd entity with id='{}'", id);
        if (id != null) {
            Optional<OnProd> onProdInner = service.findById(id);

            if (onProdInner.isPresent()) {
                OnProd onProd = onProdInner.get();
                return service.updateFromOnDto(onProd, onProdDto);
            }
        }
        LOG.info("OnProd entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
