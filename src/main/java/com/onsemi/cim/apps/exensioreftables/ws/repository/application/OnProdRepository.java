package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnProdRepository extends JpaRepository<OnProd, Long> {

    Optional<OnProd> findByProduct(String product);
}
