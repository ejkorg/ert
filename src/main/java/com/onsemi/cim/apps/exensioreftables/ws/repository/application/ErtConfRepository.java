package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ErtConf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ErtConfRepository extends JpaRepository<ErtConf, Long> {

    Optional<ErtConf> getByConfName(String confName);
}
