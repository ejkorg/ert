package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PpLotRepository extends JpaRepository<PpLot, Long> {

    Optional<PpLot> findByLot(String lot);
}
