package com.onsemi.cim.apps.exensioreftables.ws.controller;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.TestProgramVersionReference;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.TestProgramVersionReferenceDto;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.TestProgramVersionReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */
@RestController
@RequestMapping("/api/testVersionReference")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = TestProgramVersionReferenceDto.class))),
})
@Tag(name = "TestVerReference service", description = "Service to get version number for defined TestProgram (or value from MaskSet should be used sometimes) and specific parameters values represented by its hashcode.")

public class TestProgramVersionReferenceController {

    private static final Logger LOG = LoggerFactory.getLogger(TestProgramVersionReferenceController.class);

    private final TestProgramVersionReferenceService service;

    public TestProgramVersionReferenceController(TestProgramVersionReferenceService testProgramVersionReferenceService) {
        this.service = testProgramVersionReferenceService;
    }

    @Operation(summary = "List all the OnLot items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = TestProgramVersionReferenceDto.class)))
    })
    @GetMapping("/getByTestProgramAndHash/{testProgramName}/{parametersHash}")
    public TestProgramVersionReferenceDto getByTestProgramAndHash(
            @Parameter(description = "TestProgram (or MaskSet should be used in some cases) for which we need to get version. Cannot be empty.", required = true)
            @PathVariable String testProgramName,
            @Parameter(description = "Hash of the parameters calculated from their XML representation. Cannot be empty.", required = true)
            @PathVariable(required = false) String parametersHash

    ) {
        LOG.info("Obtaining TestProgramVersionReference data for testProgramName={} and parametersHash={}", testProgramName, parametersHash);
        TestProgramVersionReference testProgramVersionReference = service.getByProgramNameAndParametersHash(testProgramName, parametersHash);
        TestProgramVersionReferenceDto testProgramVersionReferenceDto = new TestProgramVersionReferenceDto(testProgramVersionReference);
        LOG.info("TestProgramVersionReference data for testProgramName={} and parametersHash={} obtained. Result: {}", testProgramName, parametersHash, testProgramVersionReferenceDto);
        return testProgramVersionReferenceDto;
    }

}

