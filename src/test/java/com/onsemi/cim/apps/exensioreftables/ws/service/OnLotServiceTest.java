package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnLotRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnLotService;
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
public class OnLotServiceTest {

    @Mock
    public OnLotRepository onLotRepository;
    public OnLotService onLotService;

    @Test
    public void findAll() {
        OnLot newOnLot1 = new OnLot();
        OnLot newOnLot2 = new OnLot();
        Mockito.when(onLotRepository.findAll()).thenReturn(Arrays.asList(newOnLot1, newOnLot2));

        List<OnLot> onLots = onLotService.findAll();
        assertNotNull(onLots, "No OnLot found");
        assertEquals(2, onLots.size(), String.format("Should found: 2, found: %d", onLots.size()));
    }

    @Test
    public void findById() {
        OnLot newOnLot = new OnLot();
        newOnLot.setId(1L);
        Mockito.when(onLotRepository.findById(1L)).thenReturn(Optional.of(newOnLot));

        Optional<OnLot> onLot = onLotService.findById(1L);
        assertTrue(onLot.isPresent(), "No OnLot found");
        assertEquals(1L, onLot.get().getId(), String.format("Should found id: 1, found: %s", onLot.get().getId()));
    }

    @Test
    public void findByLotId() throws BusinessException {
        OnLot newOnLot = new OnLot();
        newOnLot.setLot("1");
        newOnLot.setStatus(Status.FOUND);
        Mockito.when(onLotRepository.findByLot("1")).thenReturn(Optional.of(newOnLot));

        Optional<OnLot> onLot = onLotService.findByLotId("1", null, null, null, null, false);
        assertTrue(onLot.isPresent(), "No OnLot found");
        assertEquals("1", onLot.get().getLot(), String.format("Should found lotId: 1, found: %s", onLot.get().getLot()));
    }

    @Test
    public void count() {
        Mockito.when(onLotRepository.count()).thenReturn(1L);

        assertEquals(1L, onLotService.count(), String.format("Should found: 1, found: %s", onLotRepository.count()));
    }

    @Before
    public void before() {
        onLotService = new OnLotService(null, onLotRepository, null, null, null, null, null, null, null, null);
    }
}
