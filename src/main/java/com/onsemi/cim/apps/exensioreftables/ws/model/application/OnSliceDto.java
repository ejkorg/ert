package com.onsemi.cim.apps.exensioreftables.ws.model.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSlice;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author fg7ngj - Outericky, 5/26/2022
 */

@XmlRootElement(name = "OnSlice")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnSliceDto {

    private Long id;
    private Status status; //status for OnSlice is only here in DTO, not in DB. Does not make sense in DB but is useful in dto.
    private String slice;
    private String puckId;
    private String runId;
    private String sliceSourceLot;
    private String startLot;
    private String fabWaferId;
    private String globalWaferId;
    private String fabSourceLot;
    private String sliceStartTime;
    private String slicePartname;
    private String sliceLottype;
    private String sliceSuplierid;
    private Long puckHeight;
    private String insertTime;
    private String errorMessage;

    public OnSliceDto() {
    }

    public OnSliceDto(OnSlice onSlice) {

        id = onSlice.getId();
        slice = onSlice.getSlice();
        puckId = onSlice.getPuckId();
        runId = onSlice.getRunId();
        sliceSourceLot = onSlice.getSliceSourceLot();
        startLot = onSlice.getStartLot();
        fabWaferId = onSlice.getFabWaferId();
        globalWaferId = onSlice.getGlobalWaferId();
        fabSourceLot = onSlice.getFabSourceLot();
        sliceStartTime = DateUtils.convertDateToString(onSlice.getSliceStartTime());
        slicePartname = onSlice.getSlicePartname();
        sliceLottype = onSlice.getSliceLottype();
        sliceSuplierid = onSlice.getSliceSuplierid();
        puckHeight = onSlice.getPuckHeight();
        insertTime = DateUtils.convertDateToString(onSlice.getInsertTime());

    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Slice")
    public String getSlice() {
        return slice;
    }

    public void setSlice(String slice) {
        this.slice = slice;
    }

    @XmlAttribute(name="PuckId")
    public String getPuckId() {
        return puckId;
    }

    public void setPuckId(String puckId) {
        this.puckId = puckId;
    }

    @XmlAttribute(name="RunId")
    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    @XmlAttribute(name="SliceSourceLot")
    public String getSliceSourceLot() {
        return sliceSourceLot;
    }

    public void setSliceSourceLot(String sliceSourceLot) {
        this.sliceSourceLot = sliceSourceLot;
    }

    @XmlAttribute(name="StartLot")
    public String getStartLot() {
        return startLot;
    }

    public void setStartLot(String startLot) {
        this.startLot = startLot;
    }

    @XmlAttribute(name="FabWaferId")
    public String getFabWaferId() {
        return fabWaferId;
    }

    public void setFabWaferId(String fabWaferId) {
        this.fabWaferId = fabWaferId;
    }

    @XmlAttribute(name="GlobalWaferId")
    public String getGlobalWaferId() {
        return globalWaferId;
    }

    public void setGlobalWaferId(String globalWaferId) {
        this.globalWaferId = globalWaferId;
    }

    @XmlAttribute(name="FabSourceLot")
    public String getFabSourceLot() {
        return fabSourceLot;
    }

    public void setFabSourceLot(String fabSourceLot) {
        this.fabSourceLot = fabSourceLot;
    }

    @XmlAttribute(name="SliceStartTime")
    public String getSliceStartTime() {
        return sliceStartTime;
    }

    public void setSliceStartTime(String sliceStartTime) {
        this.sliceStartTime = sliceStartTime;
    }

    @XmlAttribute(name="SlicePartName")
    public String getSlicePartname() {
        return slicePartname;
    }

    public void setSlicePartname(String slicePartname) {
        this.slicePartname = slicePartname;
    }

    @XmlAttribute(name="SliceLottype")
    public String getSliceLottype() {
        return sliceLottype;
    }

    public void setSliceLottype(String sliceLottype) {
        this.sliceLottype = sliceLottype;
    }

    @XmlAttribute(name="SliceSuplierid")
    public String getSliceSuplierid() {
        return sliceSuplierid;
    }

    public void setSliceSuplierid(String sliceSuplierid) {
        this.sliceSuplierid = sliceSuplierid;
    }

    @XmlAttribute(name="PuckHeight")
    public Long getPuckHeight() {
        return puckHeight;
    }

    public void setPuckHeight(Long puckHeight) {
        this.puckHeight = puckHeight;
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
}
