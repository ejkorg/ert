package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpProdService;
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
@RequestMapping("/api/ppprod")
@Tag(name = "PpProd service", description = "The PpProd service, you can list all and find items using it.")
class PpProdController {

    private static final Logger LOG = LoggerFactory.getLogger(PpProdController.class);

    private final PpProdService service;

    public PpProdController(PpProdService service) {
        this.service = service;
    }


    @Operation(summary = "List all the PpProd items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PpProdDto.class))))
    })
    @GetMapping("/all")
    public List<PpProdDto> all() {
        LOG.info("Obtaining all the PpProd data");
        List<PpProdDto> models = new ArrayList<>();
        for (PpProd entity : service.findAll()) {
            models.add(new PpProdDto(entity));
        }
        LOG.info("All the PpProd data obtained");
        return models;
    }

    @Operation(summary = "Find PpProd by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PpProdDto.class))),
    })
    @GetMapping("/byid/{id}")
    public PpProdDto byId(
            @Parameter(description = "Id of the PpProd to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining PpProd data by id='{}'", id);
        try {
            Optional<PpProd> ppProd = service.findById(id);
            if (ppProd.isPresent()) {
                PpProdDto ppProdDto = new PpProdDto(ppProd.get());
                ppProdDto.setStatus(Status.FOUND);
                LOG.info("PpProd data for id='{}' obtained", id);
                return ppProdDto;
            } else {
                PpProdDto ppProdDto = new PpProdDto();
                ppProdDto.setStatus(Status.NO_DATA);
                LOG.info("PpProd data for id='{}' not found", id);
                return ppProdDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting PpProd by id='%s'", id), e);
            PpProdDto ppProdDto = new PpProdDto();
            ppProdDto.setStatus(Status.ERROR);
            ppProdDto.setErrorMessage(e.getMessage());
            return ppProdDto;
        }
    }

    @Operation(summary = "Find PpProd by Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PpProdDto.class))),
    })
    @GetMapping("/byproduct/{product}")
    public PpProdDto byProduct(
            @Parameter(description = "Product of the PpProd to be obtained. Cannot be empty.", required = true)
            @PathVariable String product) {

        try {
            product = StringUtils.upperCase(product);

            LOG.info("Obtaining PpProd data by product='{}'", product);
            Optional<PpProd> ppProd = service.findByProduct(product);
            if (ppProd.isPresent()) {
                PpProdDto ppProdDto = new PpProdDto(ppProd.get());
                ppProdDto.setStatus(Status.FOUND);
                LOG.info("PpProd data for product='{}' obtained", product);
                return ppProdDto;
            } else {
                PpProdDto ppProdDto = new PpProdDto();
                ppProdDto.setStatus(Status.NO_DATA);
                LOG.info("PpProd data for product='{}' not found", product);
                return ppProdDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting PpProd by product='%s'", product), e);
            PpProdDto ppProdDto = new PpProdDto();
            ppProdDto.setStatus(Status.ERROR);
            ppProdDto.setErrorMessage(e.getMessage());
            return ppProdDto;
        }
    }

    @Operation(summary = "Add a new PpProd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PpProd created",
                    content = @Content(schema = @Schema(implementation = PpProdDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "PpProd already exists")})
    @PostMapping("/create")
    public ResponseEntity<PpProdDto> createPpProd(
            @Parameter(description = "PpProd to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = PpProdDto.class))
            @RequestBody PpProdDto ppProdDto) {

        try {
            LOG.info("Creating ppProd entity");
            return service.createFromDto(ppProdDto);
        } catch (Exception e) {
            PpProdDto ppProdDtoResult = new PpProdDto();
            ppProdDtoResult.setStatus(Status.ERROR);
            ppProdDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(ppProdDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing PpProd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "PpProd not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<PpProdDto> updatePpProd(
            @Parameter(description = "Id of the PpProd to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "PpProd to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = PpProdDto.class))
            @RequestBody PpProdDto ppProdDto) {

        LOG.info("Updating ppProd entity with id='{}'", id);
        if (id != null) {
            Optional<PpProd> ppProdInner = service.findById(id);

            if (ppProdInner.isPresent()) {
                PpProd ppProd = ppProdInner.get();
                return service.updateFromOnDto(ppProd, ppProdDto);
            }
        }
        LOG.info("PpProd entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
