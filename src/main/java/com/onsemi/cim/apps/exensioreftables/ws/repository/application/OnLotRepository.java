package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnLotRepository extends JpaRepository<OnLot, Long> {

    Optional<OnLot> findByLot(String lot);
}
