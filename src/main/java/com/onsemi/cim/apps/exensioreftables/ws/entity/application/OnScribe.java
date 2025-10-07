package com.onsemi.cim.apps.exensioreftables.ws.entity.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnScribeDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.WaferIdSource;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Entity(name = "ON_SCRIBE")
public class OnScribe extends ApplicationEntity {

    @Column(name = "STATUS") @Enumerated(EnumType.STRING) private Status status;
    @Column(name = "SCRIBEID") private String scribeId;
    @Column(name = "WAFERID_SOURCE") @Enumerated(EnumType.STRING) private WaferIdSource waferIdSource;
    @Column(name = "WAFER_NUM") private Integer waferNum;
    @Column(name = "WAFERID") private String waferId;
    @Column(name = "LOT") private String lot;
    @Column(name = "FAB") private String fab;
    @Column(name = "INSERT_TIME") private Date insertTime;


    public OnScribe() {
    }


    public OnScribe(OnScribeDto onScribeDto) {
        this.status = onScribeDto.getStatus();
        this.scribeId = StringUtils.upperCase(onScribeDto.getScribeId());
        this.waferIdSource = onScribeDto.getWaferIdSource();
        this.waferNum = onScribeDto.getWaferNum();
        this.waferId = StringUtils.upperCase(onScribeDto.getWaferId());
        this.lot = StringUtils.upperCase(onScribeDto.getLot());
        this.fab = StringUtils.upperCase(onScribeDto.getFab());
        this.insertTime = DateUtils.convertStringToDate(onScribeDto.getInsertTime());
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getScribeId() {
        return scribeId;
    }

    public void setScribeId(String scribeId) {
        this.scribeId = scribeId;
    }

    public WaferIdSource getWaferIdSource() {
        return waferIdSource;
    }

    public void setWaferIdSource(WaferIdSource waferIdSource) {
        this.waferIdSource = waferIdSource;
    }

    public Integer getWaferNum() {
        return waferNum;
    }

    public void setWaferNum(Integer waferNum) {
        this.waferNum = waferNum;
    }


    public String getWaferId() {
        return waferId;
    }

    public void setWaferId(String waferId) {
        this.waferId = waferId;
    }


    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }


    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }


    public java.sql.Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(java.sql.Date insertTime) {
        this.insertTime = insertTime;
    }


    @Override
    public String toString() {
        return "OnScribe{" +
                "status=" + status +
                ", scribeId='" + scribeId + '\'' +
                ", waferIdSource=" + waferIdSource +
                ", waferNum=" + waferNum +
                ", waferId='" + waferId + '\'' +
                ", lot='" + lot + '\'' +
                ", fab='" + fab + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }
}
