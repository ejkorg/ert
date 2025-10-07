package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnScribe;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnScribe")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnScribeDto {

    private Long id;
    private Status status;
    private String scribeId;
    private WaferIdSource waferIdSource;
    private Integer waferNum;
    private String waferId;
    private String lot;
    private String fab;
    private String insertTime;
    private String errorMessage;


    public OnScribeDto() {
    }

    public OnScribeDto(OnScribe onScribe) {
        id = onScribe.getId();
        status = onScribe.getStatus();
        scribeId = onScribe.getScribeId();
        waferIdSource = onScribe.getWaferIdSource();
        waferNum = onScribe.getWaferNum();
        waferId = onScribe.getWaferId();
        lot = onScribe.getLot();
        fab = onScribe.getFab();
        insertTime = DateUtils.convertDateToString(onScribe.getInsertTime());    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlAttribute(name="ScribeId")
    public String getScribeId() {
        return scribeId;
    }

    public void setScribeId(String scribeId) {
        this.scribeId = scribeId;
    }

    // We forgot to change the XmlAttribute name to WaferIdSource, when implementing https://jira.onsemi.com/browse/CE-1085
    // It is not possible to change it now because even if we change it in ERT and CP at the same time, the old versions
    // of CP won't be working.
    @XmlAttribute(name="ScribeIdSource")
    public WaferIdSource getWaferIdSource() {
        return waferIdSource;
    }

    public void setWaferIdSource(WaferIdSource waferIdSource) {
        this.waferIdSource = waferIdSource;
    }

    @XmlAttribute(name="WaferNum")
    public Integer getWaferNum() {
        return waferNum;
    }

    public void setWaferNum(Integer waferNum) {
        this.waferNum = waferNum;
    }

    @XmlAttribute(name="WaferId")
    public String getWaferId() {
        return waferId;
    }

    public void setWaferId(String waferId) {
        this.waferId = waferId;
    }

    @XmlAttribute(name="Lot")
    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @XmlAttribute(name="Fab")
    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    @XmlAttribute(name="InsertTime")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @XmlAttribute(name="ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "OnScribeDto{" +
                "id=" + id +
                ", status=" + status +
                ", scribeId='" + scribeId + '\'' +
                ", scribeIdSource=" + waferIdSource +
                ", waferNum=" + waferNum +
                ", waferId='" + waferId + '\'' +
                ", lot='" + lot + '\'' +
                ", fab='" + fab + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
