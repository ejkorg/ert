package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OnFabConfRepository extends JpaRepository<OnFabConf, Long> {

    Optional<OnFabConf> getByFoundryFabAndDataType(String foundryFab, String dataType);

    Optional<List<OnFabConf>> getAllByFoundryFab(String foundryFab);
}
