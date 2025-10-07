package com.onsemi.cim.apps.exensioreftables.ws.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Hidden
public class ExensioRefTablesWsController {

    @GetMapping("/")
    public RedirectView handler() {
        return new RedirectView("api-docs");
    }
}
