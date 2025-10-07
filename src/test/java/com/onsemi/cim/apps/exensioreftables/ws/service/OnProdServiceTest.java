package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnProdRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnProdService;
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
public class OnProdServiceTest {

    @Mock
    public OnProdRepository onProdRepository;
    public OnProdService onProdService;

    @Test
    public void findAll() {
        OnProd newOnProd1 = new OnProd();
        OnProd newOnProd2 = new OnProd();
        Mockito.when(onProdRepository.findAll()).thenReturn(Arrays.asList(newOnProd1, newOnProd2));

        List<OnProd> onProds = onProdService.findAll();
        assertNotNull(onProds, "No OnProd found");
        assertEquals(2, onProds.size(), String.format("Should found: 2, found: %d", onProds.size()));
    }

    @Test
    public void findById() {
        OnProd newOnProd = new OnProd();
        newOnProd.setId(1L);
        Mockito.when(onProdRepository.findById(1L)).thenReturn(Optional.of(newOnProd));

        Optional<OnProd> onProd = onProdService.findById(1L);
        assertTrue(onProd.isPresent(), "No OnProd found");
        assertEquals(1L, onProd.get().getId(), String.format("Should found id: 1, found: %s", onProd.get().getId()));
    }

    @Test
    public void findByProduct() {
        OnProd newOnProd = new OnProd();
        newOnProd.setProduct("1");
        Mockito.when(onProdRepository.findByProduct("1")).thenReturn(Optional.of(newOnProd));

        Optional<OnProd> onProd = onProdService.findByProduct("1");
        assertTrue(onProd.isPresent(), "No OnProd found");
        assertEquals("1", onProd.get().getProduct(), String.format("Should found product: 1, found: %s", onProd.get().getProduct()));
    }

    @Test
    public void count() {
        Mockito.when(onProdRepository.count()).thenReturn(1L);

        assertEquals(1L, onProdService.count(), String.format("Should found: 1, found: %s", onProdRepository.count()));
    }

    @Before
    public void before() {
        onProdService = new OnProdService(onProdRepository);
    }
}
