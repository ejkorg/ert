package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.TestProgramVersionReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */
public interface TestProgramVersionReferenceRepository extends JpaRepository<TestProgramVersionReference, Long> {

    Optional<TestProgramVersionReference> findFirstByProgramNameAndParametersHash(String programName, String parametersHash);

    Optional<TestProgramVersionReference> findFirstByProgramNameOrderByProgramVersionDesc(String programName);

}

