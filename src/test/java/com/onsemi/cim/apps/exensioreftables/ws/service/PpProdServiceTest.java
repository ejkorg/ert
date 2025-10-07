package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.PpProdRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.PpProdService;
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
public class PpProdServiceTest {

    @Mock
    public PpProdRepository ppProdRepository;
    public PpProdService ppProdService;

    @Test
    public void findAll() {
        PpProd newPpProd1 = new PpProd();
        PpProd newPpProd2 = new PpProd();
        Mockito.when(ppProdRepository.findAll()).thenReturn(Arrays.asList(newPpProd1, newPpProd2));

        List<PpProd> ppProds = ppProdService.findAll();
        assertNotNull(ppProds, "No PpProd found");
        assertEquals(2, ppProds.size(), String.format("Should found: 2, found: %d", ppProds.size()));
    }

    @Test
    public void findById() {
        PpProd newPpProd = new PpProd();
        newPpProd.setId(1L);
        Mockito.when(ppProdRepository.findById(1L)).thenReturn(Optional.of(newPpProd));

        Optional<PpProd> ppProd = ppProdService.findById(1L);
        assertTrue(ppProd.isPresent(), "No PpProd found");
        assertEquals(1L, ppProd.get().getId(), String.format("Should found id: 1, found: %s", ppProd.get().getId()));
    }

    @Test
    public void findByProduct() {
        PpProd newPpProd = new PpProd();
        newPpProd.setProduct("1");
        Mockito.when(ppProdRepository.findByProduct("1")).thenReturn(Optional.of(newPpProd));

        Optional<PpProd> onProd = ppProdService.findByProduct("1");
        assertTrue(onProd.isPresent(), "No PpProd found");
        assertEquals("1", onProd.get().getProduct(), String.format("Should found product: 1, found: %s", onProd.get().getProduct()));
    }

    @Test
    public void count() {
        Mockito.when(ppProdRepository.count()).thenReturn(1L);

        assertEquals(1L, ppProdService.count(), String.format("Should found: 1, found: %s", ppProdRepository.count()));
    }

    @Before
    public void before() {
        ppProdService = new PpProdService(ppProdRepository);
    }
}
