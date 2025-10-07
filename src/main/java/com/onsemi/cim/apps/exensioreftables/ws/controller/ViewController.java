package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.DataType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.LotIdForOnScribeType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ScribeResultType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.WaferIdSource;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private InformationService informationService;


    @GetMapping("/gui")
    public ModelAndView loadMainView() {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index");
            modelAndView.addObject("applicationVersion", informationService.getApplicationVersion());
            modelAndView.addObject("statuses", Status.values());
            modelAndView.addObject("dataTypes", DataType.values());
            modelAndView.addObject("scribeResultTypes", ScribeResultType.values());
            modelAndView.addObject("lotIds", LotIdForOnScribeType.values());
            modelAndView.addObject("wafIdSources", WaferIdSource.values());
            modelAndView.addObject("defaultStatus", Status.MANUAL);
            return modelAndView;
        } catch (IndexOutOfBoundsException e) {
            return new ModelAndView("error");
        }
    }

    @GetMapping("/error")
    public ModelAndView loadErrorView() {
        return new ModelAndView("error");
    }
}
