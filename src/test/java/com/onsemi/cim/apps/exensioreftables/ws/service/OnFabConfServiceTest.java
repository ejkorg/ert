package com.onsemi.cim.apps.exensioreftables.ws.service;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnFabConfRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.db.OnFabConfService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class OnFabConfServiceTest {

    @Mock
    OnFabConfRepository onFabConfRepository;
    OnFabConfService onFabConfService;

    @Test
    public void getByFoundryFabAndDataTypeDataType() {
        OnFabConf onFabConf1 = new OnFabConf();
        onFabConf1.setId(1L);
        OnFabConf onFabConf2 = new OnFabConf();
        onFabConf2.setId(2L);
        List<OnFabConf> listOnFabConf = new ArrayList<>();
        listOnFabConf.add(onFabConf1);
        listOnFabConf.add(onFabConf2);
        Mockito.when(onFabConfRepository.getAllByFoundryFab("BE2:ON BELGIUM FAB2")).thenReturn(Optional.of(listOnFabConf));
        Mockito.when(onFabConfRepository.getByFoundryFabAndDataType("BE2:ON BELGIUM FAB2", "PROBE")).thenReturn(Optional.of(onFabConf1));

        Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly("BE2:ON BELGIUM FAB2", "PROBE");
        assertTrue(onFabConf.isPresent(), "No OnFabConf found");
        assertEquals(1L, onFabConf.get().getId(), String.format("Should found id: 1, found: %s", onFabConf.get().getId()));
    }

    @Before
    public void before() {
        onFabConfService = new OnFabConfService(onFabConfRepository);
    }
}
