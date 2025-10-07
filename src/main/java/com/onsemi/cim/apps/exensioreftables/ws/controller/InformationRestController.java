package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.ApplicationDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.DataSourceDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.DataSourcesDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.InfoDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.InformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
@Tag(name = "Information service", description = "The Information service, use it to get information about the application.")
public class InformationRestController {

    private static final Logger LOG = LoggerFactory.getLogger(InformationRestController.class);

    private final InformationService informationService;

    public InformationRestController(InformationService informationService) {
        this.informationService = informationService;
    }

    @Operation(summary = "Display the information about the application")
    @GetMapping("/xml")
    public InfoDto handler() {
        LOG.info("Showing information page in Xml");

        final ApplicationDto applicationDto = new ApplicationDto(informationService.getApplicationVersion());
        final DataSourceDto applicationDataSource = informationService.getApplicationDatasource();
        final DataSourceDto datawarehouseDataSource = informationService.getDatawarehouseDatasource();
        final DataSourceDto lotgDataSource = informationService.getLotgDatasource();
        final DataSourcesDto dataSourcesDto = new DataSourcesDto(applicationDataSource, datawarehouseDataSource, lotgDataSource);

        return new InfoDto(applicationDto, dataSourcesDto);
    }
}
