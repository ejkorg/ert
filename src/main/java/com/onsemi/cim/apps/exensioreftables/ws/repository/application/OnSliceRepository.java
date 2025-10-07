package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSlice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author fg7ngj - Outericky, 5/26/2022
 */


public interface OnSliceRepository extends JpaRepository<OnSlice, Long> {

    Optional<OnSlice> findBySlice(String slice);

    Optional<OnSlice> findByGlobalWaferId(String slice);

}
