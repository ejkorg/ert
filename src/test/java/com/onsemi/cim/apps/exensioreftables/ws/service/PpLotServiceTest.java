package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.PpLotRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpLotService;
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
public class PpLotServiceTest {

    @Mock
    public PpLotRepository ppLotRepository;
    public PpLotService ppLotService;

    @Test
    public void findAll() {
        PpLot newPpLot1 = new PpLot();
        PpLot newPpLot2 = new PpLot();
        Mockito.when(ppLotRepository.findAll()).thenReturn(Arrays.asList(newPpLot1, newPpLot2));

        List<PpLot> ppLots = ppLotService.findAll();
        assertNotNull(ppLots, "No PpLot found");
        assertEquals(2, ppLots.size(), String.format("Should found: 2, found: %d", ppLots.size()));
    }

    @Test
    public void findById() {
        PpLot newPpLot = new PpLot();
        newPpLot.setId(1L);
        Mockito.when(ppLotRepository.findById(1L)).thenReturn(Optional.of(newPpLot));

        Optional<PpLot> ppLot = ppLotService.findById(1L);
        assertTrue(ppLot.isPresent(), "No PpLot found");
        assertEquals(1L, ppLot.get().getId(), String.format("Should found id: 1, found: %s", ppLot.get().getId()));
    }

    @Test
    public void findByProduct() {
        PpLot newPpLot = new PpLot();
        newPpLot.setProduct("1");
        Mockito.when(ppLotRepository.findByLot("1")).thenReturn(Optional.of(newPpLot));

        Optional<PpLot> ppLot = ppLotService.findByLotId("1");
        assertTrue(ppLot.isPresent(), "No PpLot found");
        assertEquals("1", ppLot.get().getProduct(), String.format("Should found product: 1, found: %s", ppLot.get().getProduct()));
    }

    @Test
    public void count() {
        Mockito.when(ppLotRepository.count()).thenReturn(1L);

        assertEquals(1L, ppLotService.count(), String.format("Should found: 1, found: %s", ppLotRepository.count()));
    }

    @Before
    public void before() {
        ppLotService = new PpLotService(ppLotRepository);
    }
}
