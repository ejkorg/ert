package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnWmap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnWmapRepository extends JpaRepository<OnWmap, Long> {

    Optional<OnWmap> findByProduct(String product);

    Optional<OnWmap> findByCfgId(Long  idWaferMapConfiguration);
}
