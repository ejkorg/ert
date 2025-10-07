package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.model.application.info.DataSourceDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.InformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformationController {

    private static final Logger LOG = LoggerFactory.getLogger(InformationController.class);

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/info")
    public ModelAndView handler() {
        LOG.info("Showing information page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");

        modelAndView.addObject("applicationVersion", informationService.getApplicationVersion());

        DataSourceDto applicationDataSource = informationService.getApplicationDatasource();
        modelAndView.addObject("applicationDataSourceInformation", applicationDataSource);

        DataSourceDto datawarehouseDataSource = informationService.getDatawarehouseDatasource();
        modelAndView.addObject("datawarehouseDataSourceInformation", datawarehouseDataSource);

        DataSourceDto lotgDataSource = informationService.getLotgDatasource();
        modelAndView.addObject("lotgDataSourceInformation", lotgDataSource);

        return modelAndView;
    }
}
