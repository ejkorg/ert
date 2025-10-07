package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.TestProgramVersionReference;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.TestProgramVersionReferenceRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.TestProgramVersionReferenceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Pavel Outericky, fg7ngj
 * 12/19/2024
 */
@RunWith(MockitoJUnitRunner.class)
public class TestProgramVersionReferenceServiceTest {

    @Mock
    public TestProgramVersionReferenceRepository testProgramVersionReferenceRepository;
    public TestProgramVersionReferenceService testProgramVersionReferenceService;

    @Test
    public void getByProgramNameAndParametersHash() {

        //existing dummy records
        TestProgramVersionReference testProgramVersionReferenceA = new TestProgramVersionReference();
        testProgramVersionReferenceA.setProgramName("testProgramA");
        testProgramVersionReferenceA.setParametersHash("dummyhashValueA");
        testProgramVersionReferenceA.setProgramVersion(1L);

        TestProgramVersionReference testProgramVersionReferenceB1 = new TestProgramVersionReference();
        testProgramVersionReferenceB1.setProgramName("testProgramB");
        testProgramVersionReferenceB1.setParametersHash("dummyhashValueB1");
        testProgramVersionReferenceB1.setProgramVersion(1L);

        //not existing dummy record that should be created by the service
        TestProgramVersionReference testProgramVersionReferenceB2 = new TestProgramVersionReference();
        testProgramVersionReferenceB2.setProgramName("testProgramB");
        testProgramVersionReferenceB2.setParametersHash("dummyhashValueB2");
        testProgramVersionReferenceB2.setProgramVersion(2L);


        Mockito.when(testProgramVersionReferenceRepository.findFirstByProgramNameAndParametersHash("testProgramA", "dummyhashValueA")).thenReturn(Optional.of(testProgramVersionReferenceA));
        Mockito.when(testProgramVersionReferenceRepository.findFirstByProgramNameOrderByProgramVersionDesc("testProgramB")).thenReturn(Optional.of(testProgramVersionReferenceB1));

        //Test of existing record
        TestProgramVersionReference fetchedTestProgramVersionReference = testProgramVersionReferenceService.getByProgramNameAndParametersHash("testProgramA", "dummyhashValueA");
        assertNotNull(fetchedTestProgramVersionReference);
        assertEquals(testProgramVersionReferenceA.getProgramName(), fetchedTestProgramVersionReference.getProgramName());
        assertEquals(testProgramVersionReferenceA.getParametersHash(), fetchedTestProgramVersionReference.getParametersHash());

        //Test of record that does not exist yet and will be created
        fetchedTestProgramVersionReference = testProgramVersionReferenceService.getByProgramNameAndParametersHash("testProgramB", "dummyhashValueB2");
        assertNotNull(fetchedTestProgramVersionReference);
        assertEquals(testProgramVersionReferenceB2.getProgramName(), fetchedTestProgramVersionReference.getProgramName());
        assertEquals(testProgramVersionReferenceB2.getParametersHash(), fetchedTestProgramVersionReference.getParametersHash());

    }

    @Before
    public void before() {
        testProgramVersionReferenceService = new TestProgramVersionReferenceService(testProgramVersionReferenceRepository);
    }

}

