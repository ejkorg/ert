package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnWmap;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnWmapDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnFabConfService;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnWmapService;
import com.onsemi.cim.apps.exensioreftables.ws.ws.MatchupLoader;
import com.onsemi.cim.apps.wafer.configuration.info.WaferMapMatchup;
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

/*TODO
would be better to put some code into methods to not repeat the exact same code part in the class again more times.
*/

@RestController
@RequestMapping("/api/onwmap")
@Tag(name = "OnWmap service", description = "The OnWmap service, you can list all and find items using it.")
class OnWmapController {

    private static final Logger LOG = LoggerFactory.getLogger(OnWmapController.class);

    private final OnWmapService service;

    private final OnFabConfService onFabConfService;

    public OnWmapController(OnWmapService service, OnFabConfService onFabConfService) {
        this.service = service;
        this.onFabConfService = onFabConfService;
    }


    @Operation(summary = "List all the OnWmap items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = OnWmapDto.class))))
    })
    @GetMapping("/all")
    public List<OnWmapDto> all() {
        LOG.info("Obtaining all the OnWmap data");
        List<OnWmapDto> models = new ArrayList<>();
        for (OnWmap entity : service.findAll()) {
            models.add(new OnWmapDto(entity));
        }
        LOG.info("All the OnWmap data obtained");
        return models;
    }

    @Operation(summary = "Find OnWmap by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnWmapDto.class))),
    })
    @GetMapping("/byid/{id}")
    public OnWmapDto byId(
            @Parameter(description = "Id of the OnWmap to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id) {

        LOG.info("Obtaining OnWmap data by id='{}'", id);
        try {
            Optional<OnWmap> onWmap = service.findById(id);
            if (onWmap.isPresent()) {
                OnWmapDto onWmapDto = new OnWmapDto(onWmap.get());
                LOG.info("OnWmap data for id='{}' obtained", id);
                return onWmapDto;
            } else {
                OnWmapDto onWmapDto = new OnWmapDto();
                LOG.info("OnWmap data for id='{}' not found", id);
                onWmapDto.setStatus(Status.NO_DATA);
                return onWmapDto;
            }
        } catch (Exception e) {
            LOG.error(String.format("Error during getting OnWmap by id='%s'", id), e);
            OnWmapDto onWmapDto = new OnWmapDto();
            onWmapDto.setStatus(Status.ERROR);
            onWmapDto.setErrorMessage(e.getMessage());
            return onWmapDto;
        }
    }

    @Operation(summary = "Find OnWmap by Product and SearchLotPart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = OnWmapDto.class))),
    })
    @GetMapping("/byproduct/{wmcServiceKey}/{product}/{searchedLotPart}")
    public OnWmapDto byProduct(
            @Parameter(description = "WmcServiceKey of the OnWmap to be obtained. Cannot be empty.", required = true)
            @PathVariable String wmcServiceKey,
            @Parameter(description = "Product of the OnWmap to be obtained. Cannot be empty.", required = true)
            @PathVariable String product,
            @Parameter(description = "SearchedLotPart of the OnWmap to be obtained. Cannot be empty.", required = true)
            @PathVariable String searchedLotPart,
            @Parameter(description = "Source fab of the datalog.")
            @RequestParam(required = false) String fab,
            @Parameter(description = "Data type of the flow.")
            @RequestParam(required = false) String dataType,
            @Parameter(description = "Scribe of the datalog")
            @RequestParam(required = false) String scribe,
            @Parameter(description = "Probe end datetime.")
            @RequestParam(required = false) String endDate) {

        String matchupUrl = null;
        Optional<OnWmap> onWmap;
        final Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);
        wmcServiceKey = StringUtils.upperCase(wmcServiceKey);
        product = StringUtils.upperCase(product);
        searchedLotPart = StringUtils.upperCase(searchedLotPart);
        fab = StringUtils.upperCase(fab);
        dataType = StringUtils.upperCase(dataType);

        if(onFabConf.isPresent()) {
            if(StringUtils.isNotBlank(onFabConf.get().getMatchupUrl())) {
                matchupUrl = onFabConf.get().getMatchupUrl();
            } else {
                matchupUrl = "";
            }
        }

        if(onFabConf.isPresent() && StringUtils.isNotBlank(matchupUrl)) {
            Long idWaferMapConfiguration = null;
            try {
                LOG.info("Try to load matchup with matchupUrl='{}', lot='{}', scribe='{}', endDate='{}'", matchupUrl, wmcServiceKey, scribe, endDate);
                WaferMapMatchup matchup = MatchupLoader.loadMatchupByLotScribeEndDate(matchupUrl, wmcServiceKey, scribe, endDate);
                LOG.info("Matchup is FOUND. status = '{}'", matchup.getStatus());
                idWaferMapConfiguration = matchup.getIdWaferMapCOnfiguration();
                LOG.info("idWaferMapConfiguration='{}'", idWaferMapConfiguration);
                LOG.info("Obtaining OnWmap by config ID (CfgId)");
                onWmap = service.findByCfgId(idWaferMapConfiguration, product, searchedLotPart, fab, dataType);
                if(onWmap.isPresent()) {
                    OnWmapDto onWmapDto = new OnWmapDto(onWmap.get());
                    LOG.info("OnWmap data for wmcId='{}', product='{}', searchedLotPart='{}' obtained", idWaferMapConfiguration, product, searchedLotPart);
                    return onWmapDto;
                } else {
                    OnWmapDto onWmapDto = new OnWmapDto();
                    LOG.info("OnWmap data for wmcId='{}', product='{}', searchedLotPart='{}' not found", idWaferMapConfiguration, product, searchedLotPart);
                    onWmapDto.setStatus(Status.NO_DATA);
                    return onWmapDto;
                }
            } catch (Exception e) {
                LOG.error(String.format("Error during getting OnWmap by wmcId='%s', product='%s', searchedLotPart='%s'", idWaferMapConfiguration, product, searchedLotPart), e);
                OnWmapDto onWmapDto = new OnWmapDto();
                onWmapDto.setStatus(Status.ERROR);
                onWmapDto.setErrorMessage(e.getMessage());
                return onWmapDto;
            }
        } else {
            try {
                LOG.info("Obtaining OnWmap data by wmcServiceKey='{}', product='{}', searchedLotPart='{}'", wmcServiceKey, product, searchedLotPart);
                onWmap = service.findByProduct(wmcServiceKey, product, searchedLotPart, fab, dataType);
                if (onWmap.isPresent()) {
                    OnWmapDto onWmapDto = new OnWmapDto(onWmap.get());
                    LOG.info("OnWmap data for wmcServiceKey='{}', product='{}', searchedLotPart='{}' obtained", wmcServiceKey, product, searchedLotPart);
                    return onWmapDto;
                } else {
                    OnWmapDto onWmapDto = new OnWmapDto();
                    LOG.info("OnWmap data for wmcServiceKey='{}', product='{}', searchedLotPart='{}' not found", wmcServiceKey, product, searchedLotPart);
                    onWmapDto.setStatus(Status.NO_DATA);
                    return onWmapDto;
                }
            } catch (Exception e) {
                LOG.error(String.format("Error during getting OnWmap by wmcServiceId='%s', product='%s', searchedLotPart='%s'", wmcServiceKey, product, searchedLotPart), e);
                OnWmapDto onWmapDto = new OnWmapDto();
                onWmapDto.setStatus(Status.ERROR);
                onWmapDto.setErrorMessage(e.getMessage());
                return onWmapDto;
            }

        }
    }

    @Operation(summary = "Add a new OnWmap")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OnWmap created",
                    content = @Content(schema = @Schema(implementation = OnWmapDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "OnWmap already exists")})
    @PostMapping("/create")
    public ResponseEntity<OnWmapDto> createOnWmap(
            @Parameter(description = "OnWmap to add. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnWmapDto.class))
            @RequestBody OnWmapDto onWmapDto) {

        try {
            LOG.info("Creating onWmap entity");
            return service.createFromDto(onWmapDto);

        } catch (Exception e) {
            OnWmapDto onWmapDtoResult = new OnWmapDto();
            onWmapDtoResult.setStatus(Status.ERROR);
            onWmapDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onWmapDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing OnWmap")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "OnWmap not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping("/update/{id}")
    public ResponseEntity<OnWmapDto> updateOnWmap(
            @Parameter(description = "Id of the OnWmap to be updated. Cannot be empty.", required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "OnWmap to update. Cannot be null or empty.", required = true, schema = @Schema(implementation = OnWmapDto.class))
            @RequestBody OnWmapDto onWmapDto) {

        LOG.info("Updating onWmap entity with id='{}'", id);
        if (id != null) {
            Optional<OnWmap> onWmapOptional = service.findById(id);

            if (onWmapOptional.isPresent()) {
                OnWmap onWmap = onWmapOptional.get();
                return service.updateFromDto(onWmap, onWmapDto);
            }
        }
        LOG.info("OnWmap entity with id='{}' not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
