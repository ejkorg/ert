package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnWmap;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnWmapRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnWmapService;
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
public class OnWmapServiceTest {

    @Mock
    public OnWmapRepository onWmapRepository;
    public OnWmapService onWmapService;

    @Test
    public void findAll() {
        OnWmap newOnWmap1 = new OnWmap();
        OnWmap newOnWmap2 = new OnWmap();
        Mockito.when(onWmapRepository.findAll()).thenReturn(Arrays.asList(newOnWmap1, newOnWmap2));

        List<OnWmap> onWmaps = onWmapService.findAll();
        assertNotNull(onWmaps, "No OnWmap found");
        assertEquals(2, onWmaps.size(), String.format("Should found: 2, found: %d", onWmaps.size()));
    }

    @Test
    public void findById() {
        OnWmap newOnWmap = new OnWmap();
        newOnWmap.setId(1L);
        Mockito.when(onWmapRepository.findById(1L)).thenReturn(Optional.of(newOnWmap));

        Optional<OnWmap> onWmap = onWmapService.findById(1L);
        assertTrue(onWmap.isPresent(), "No OnWmap found");
        assertEquals(1L, onWmap.get().getId(), String.format("Should found id: 1, found: %s", onWmap.get().getId()));
    }

    @Test
    public void findByProduct() throws BusinessException {
        OnWmap newOnWmap = new OnWmap();
        newOnWmap.setProduct("1");
        newOnWmap.setStatus(Status.FOUND);
        Mockito.when(onWmapRepository.findByProduct("1")).thenReturn(Optional.of(newOnWmap));

        Optional<OnWmap> onWmaps = onWmapService.findByProduct(null, null, "1", null, null);
        assertTrue(onWmaps.isPresent(), "No OnWmap found");
        assertEquals("1", onWmaps.get().getProduct(), String.format("Should found product: 1, found: %s", onWmaps.get().getProduct()));
    }

    @Test
    public void count() {
        Mockito.when(onWmapRepository.count()).thenReturn(1L);

        assertEquals(1L, onWmapService.count(), String.format("Should found: 1, found: %s", onWmapRepository.count()));
    }

    @Before
    public void before() {
        onWmapService = new OnWmapService(null, null, null, onWmapRepository);
    }
}
