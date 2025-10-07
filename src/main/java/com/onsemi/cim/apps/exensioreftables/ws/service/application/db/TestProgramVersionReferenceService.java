package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.TestProgramVersionReference;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.TestProgramVersionReferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */
@Service
@Transactional
public class TestProgramVersionReferenceService {

    private static final Logger LOG = LoggerFactory.getLogger(TestProgramVersionReferenceService.class);

    private final TestProgramVersionReferenceRepository testProgramVersionReferenceRepository;

    public TestProgramVersionReferenceService(TestProgramVersionReferenceRepository testProgramVersionReferenceRepository) {
        this.testProgramVersionReferenceRepository = testProgramVersionReferenceRepository;
    }

    public TestProgramVersionReference getByProgramNameAndParametersHash(String programName, String parametersHash) {

        //Try to find record by the programName and parametersHash
        Optional<TestProgramVersionReference> testProgramVersionReference =
                testProgramVersionReferenceRepository.findFirstByProgramNameAndParametersHash(programName, parametersHash);

        if (testProgramVersionReference.isPresent()) {
            //if found then return that
            return testProgramVersionReference.get();
        }

        //if not found then select one with the same programName and the latest programVersion
        //and create new one for the provided programName+parametersHash combination with incremented programVersion

        LOG.info("Version for combination {}:{} was not found. Creating record for this combination with incremented version for programName {}.", programName, parametersHash, programName);

        testProgramVersionReference =
                testProgramVersionReferenceRepository.findFirstByProgramNameOrderByProgramVersionDesc(programName);

        Long latestVersion = 0L;

        if (testProgramVersionReference.isPresent()) {
            latestVersion = testProgramVersionReference.get().getProgramVersion();
        }

        TestProgramVersionReference newTestProgramVersionReference = new TestProgramVersionReference();
        newTestProgramVersionReference.setProgramName(programName);
        newTestProgramVersionReference.setParametersHash(parametersHash);
        newTestProgramVersionReference.setProgramVersion(++latestVersion);

        testProgramVersionReferenceRepository.save(newTestProgramVersionReference);
        return newTestProgramVersionReference;
    }
}

