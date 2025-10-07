package com.onsemi.cim.apps.exensioreftables.ws.repository.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.MesType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OnSiteConfRepository extends JpaRepository<OnSiteConf, Long> {

    Optional<OnSiteConf> getBySite(String site);
}
