package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PpProdRepository extends JpaRepository<PpProd, Long> {

    Optional<PpProd> findByProduct(String product);
}
