package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnScribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnScribeRepository extends JpaRepository<OnScribe, Long> {

    Optional<OnScribe> findByLotAndWaferNum(String lot, Integer waferNum);

    Optional<OnScribe> findByScribeId(String scribeId);
}
