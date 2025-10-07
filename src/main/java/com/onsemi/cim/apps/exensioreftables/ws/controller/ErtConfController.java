package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ErtConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.ErtConfDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnFabConfDto;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.ErtConfRepository;
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
@RequestMapping("/api/ertconf")
@Tag(name = "ErtConf service", description = "The ErtConf service, you can create new, update, delete and list items using it.")
class ErtConfController {

    private static final Logger LOG = LoggerFactory.getLogger(ErtConfController.class);

    private final ErtConfRepository repository;

    public ErtConfController(ErtConfRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "List all the ErtConf items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErtConfDto.class))))
    })
    @GetMapping("/all")
    public List<ErtConfDto> all() {
        LOG.info("Obtaining all the ErtConf data");
        List<ErtConfDto> ertConfDtos = new ArrayList<>();
        for (ErtConf entity : repository.findAll()) {
            ertConfDtos.add(new ErtConfDto(entity));
        }
        LOG.info("All the ErtConf data obtained");
        return ertConfDtos;
    }

    @Operation(summary = "Find ErtConf by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ErtConfDto.class))),
    })
    @GetMapping("/byid/{id}")
    public ErtConfDto byId(
            @Parameter(description = "Id of the ErtConf to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining ErtConf data for id='{}'", id);
        if (id != null) {
            Optional<ErtConf> ertConf = repository.findById(id);
            if (ertConf.isPresent()) {
                ErtConfDto ertConfDto = new ErtConfDto(ertConf.get());
                LOG.info("ErtConf data for id='{}' obtained", id);
                return ertConfDto;
            } else {
                ErtConfDto ertConfDto = new ErtConfDto();
                ertConfDto.setStatus(Status.NO_DATA);
                LOG.info("ErtConf data for id='{}' not found", id);
                return ertConfDto;
            }
        } else {
            String errorMessage = "ErtConf entity id is null";
            LOG.error(errorMessage);
            ErtConfDto ertConfDto = new ErtConfDto();
            ertConfDto.setStatus(Status.ERROR);
            return ertConfDto;
        }
    }

    @Operation(summary = "Find ErtConf by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ErtConfDto.class))),
    })
    @GetMapping("/byconfname/{confName}")
    public ErtConfDto byConfName(
            @Parameter(description = "ConfName of the ErtConf to be obtained. Cannot be empty.", required = true)
            @PathVariable String confName) {

        LOG.info("Obtaining ErtConf data for name='{}'", confName);
        Optional<ErtConf> ertConf = repository.getByConfName(confName);
        if (ertConf.isPresent()) {
            ErtConfDto ertConfDto = new ErtConfDto(ertConf.get());
            LOG.info("ErtConf data for confName='{}' obtained", confName);
            return ertConfDto;
        } else {
            ErtConfDto ertConfDto = new ErtConfDto();
            ertConfDto.setStatus(Status.NO_DATA);
            LOG.info("ErtConf data for confName='{}' not found", confName);
            return ertConfDto;
        }
    }

    @Operation(summary = "Add a new ErtConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ErtConf created",
                    content = @Content(schema = @Schema(implementation = ErtConfDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "ErtConf already exists")})
    @PostMapping("/create")
    public ResponseEntity<ErtConfDto> createErtConf(
            @Parameter(description = "ErtConf to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = ErtConfDto.class))
            @RequestBody ErtConfDto ertConfDto) {

        try {
            LOG.info("Creating ertConf entity");
            ErtConf ertConf = repository.save(new ErtConf(ertConfDto));
            ErtConfDto ertConfDtoResult = new ErtConfDto(ertConf);
            ResponseEntity<ErtConfDto> responseModel = new ResponseEntity<>(ertConfDtoResult, HttpStatus.CREATED);
            LOG.info("ErtConf entity created");
            return responseModel;
        } catch (Exception e) {
            LOG.info("ErtConf entity with cannot be created", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing ErtConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "ErtConf not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<ErtConfDto> updateErtConf(
            @Parameter(description = "Id of the ErtConf to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "ErtConf to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = ErtConfDto.class))
            @RequestBody ErtConfDto ertConfDto) {

        LOG.info("Updating ertConf entity with id='{}'", id);
        if (id != null) {
            Optional<ErtConf> ertConfInner = repository.findById(id);

            if (ertConfInner.isPresent()) {
                ErtConf ertConf = ertConfInner.get();
                ertConf.setConfName(ertConfDto.getConfName());
                ertConf.setValueString(ertConfDto.getValueString());
                ertConf.setValueNumber(ertConfDto.getValueNumber());
                ertConf.setValueBoolean(ertConfDto.isValueBoolean());
                ertConf.setDataType(ertConfDto.getDataType());
                ErtConfDto ertConfDtoResult = new ErtConfDto(repository.save(ertConf));
                LOG.info("ErtConf entity with id='{}' updated", id);
                return new ResponseEntity<>(ertConfDtoResult, HttpStatus.OK);
            } else {
                LOG.info("ErtConf entity with id='{}' not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            LOG.info("ErtConf entity id is null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletes a ErtConf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "ErtConf not found")})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteErtConf(
            @Parameter(description = "Id of the ErtConf to be deleted. Cannot be empty.", required = true)
            @PathVariable("id") Long id) {

        try {
            LOG.info("Deleting ertConf entity with id='{}'", id);
            if (id != null) {
                repository.deleteById(id);
                LOG.info("ErtConf entity with id='{}' deleted", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                LOG.info("ErtConf entity id is null");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.info(String.format("ErtConf entity with id='%d' cannot be deleted", id), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
