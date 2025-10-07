package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnScribe;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnScribeRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnScribeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OnScribeServiceTest {

    @Mock
    public OnScribeRepository onScribeRepository;
    public OnScribeService onScribeService;



    @Test
    public void findAll() {
        OnScribe newOnScribe1 = new OnScribe();
        OnScribe newOnScribe2 = new OnScribe();
        Mockito.when(onScribeRepository.findAll()).thenReturn(Arrays.asList(newOnScribe1, newOnScribe2));

        List<OnScribe> onScribe = onScribeService.findAll();
        assertNotNull(onScribe, "No OnScribe found");
        assertEquals(2, onScribe.size(), String.format("Should found: 2, found: %d", onScribe.size()));
    }

    @Test
    public void findById() {
        OnScribe newOnScribe = new OnScribe();
        newOnScribe.setId(1L);
        Mockito.when(onScribeRepository.findById(1L)).thenReturn(Optional.of(newOnScribe));

        Optional<OnScribe> onScribe = onScribeService.findById(1L);
        assertTrue(onScribe.isPresent(), "No OnScribe found");
        assertEquals(1L, onScribe.get().getId(), String.format("Should found id: 1, found: %s", onScribe.get().getId()));
    }

    @Test
    public void findByLotAndWaferNum() throws BusinessException {
        OnScribe newOnScribe = new OnScribe();
        newOnScribe.setLot("1");
        newOnScribe.setStatus(Status.FOUND);
        Mockito.when(onScribeRepository.findByLotAndWaferNum("1", 2)).thenReturn(Optional.of(newOnScribe));

        Optional<OnScribe> onScribe = onScribeService.findByLotAndWaferNum("1", "1", 2, null, null, null);
        assertTrue(onScribe.isPresent(), "No OnScribe found");
        assertEquals("1", onScribe.get().getLot(), String.format("Should found lot: 1 waferNum: 2, found: %s, %s", onScribe.get().getLot(), onScribe.get().getWaferNum()));
    }

    @Test
    public void findByScribeId() throws BusinessException {
        OnScribe newOnScribe = new OnScribe();
        newOnScribe.setLot("1");
        newOnScribe.setStatus(Status.FOUND);
        Mockito.when(onScribeRepository.findByScribeId("1")).thenReturn(Optional.of(newOnScribe));

        Optional<OnScribe> onScribe = onScribeService.findByScribeId("1", null, null, null, null);
        assertTrue(onScribe.isPresent(), "No OnScribe found");
        assertEquals("1", onScribe.get().getLot(), String.format("Should found scribeId: 1, found: %s", onScribe.get().getScribeId()));
    }

    @Test
    public void count() {
        Mockito.when(onScribeRepository.count()).thenReturn(1L);

        assertEquals(1L, onScribeService.count(), String.format("Should found: 1, found: %s", onScribeService.count()));
    }

    @Before
    public void before() {
        onScribeService = new OnScribeService(null, null, null, null, onScribeRepository);
    }
}
