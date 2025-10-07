package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnWmap;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnWmap")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnWmapDto {

    private Long id;
    private Status status;
    private String product;
    private String wfUnits;
    private Double wfSize;
    private String flatType;
    private String flat;
    private Double dieWidth;
    private Double dieHeight;
    private Double centerX;
    private Double centerY;
    private String positiveX;
    private String positiveY;
    private Long reticleRows;
    private Long reticleCols;
    private Long reticleRowOffset;
    private Long reticleColOffset;
    private String confirmed;
    private Long deviceCount;
    private String confirmTime;
    private String comments;
    private String insertTime;
    private String inputFile;
    private Long cfgId;
    private String location;
    private Integer refDieX;
    private Integer refDieY;
    private String refDieInitDt;
    private String wmcDevice;
    private String errorMessage;


    public OnWmapDto() {
    }

    public OnWmapDto(OnWmap onWmap) {
        id = onWmap.getId();
        status = onWmap.getStatus();
        product = onWmap.getProduct();
        wfUnits = onWmap.getWfUnits();
        wfSize = onWmap.getWfSize();
        flatType = onWmap.getFlatType();
        flat = onWmap.getFlat();
        dieWidth = onWmap.getDieWidth();
        dieHeight = onWmap.getDieHeight();
        centerX = onWmap.getCenterX();
        centerY = onWmap.getCenterY();
        positiveX = onWmap.getPositiveX();
        positiveY = onWmap.getPositiveY();
        reticleRows = onWmap.getReticleRows();
        reticleCols = onWmap.getReticleCols();
        reticleRowOffset = onWmap.getReticleRowOffset();
        reticleColOffset = onWmap.getReticleColOffset();
        confirmed = onWmap.getConfirmed();
        deviceCount = onWmap.getDeviceCount();
        confirmTime = DateUtils.convertDateToString(onWmap.getConfirmTime());
        comments = onWmap.getComments();
        insertTime = DateUtils.convertDateToString(onWmap.getInsertTime());
        inputFile = onWmap.getInputFile();
        cfgId = onWmap.getCfgId();
        location = onWmap.getLocation();
        refDieX = onWmap.getRefDieX();
        refDieY = onWmap.getRefDieY();
        refDieInitDt = DateUtils.convertDateToString(onWmap.getRefDieInitDt());
        wmcDevice = onWmap.getWmcDevice();
    }

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

    @XmlAttribute(name="Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @XmlAttribute(name="WfUnits")
    public String getWfUnits() {
        return wfUnits;
    }

    public void setWfUnits(String wfUnits) {
        this.wfUnits = wfUnits;
    }

    @XmlAttribute(name="WfSize")
    public Double getWfSize() {
        return wfSize;
    }

    public void setWfSize(Double wfSize) {
        this.wfSize = wfSize;
    }

    @XmlAttribute(name="FlatType")
    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    @XmlAttribute(name="Flat")
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @XmlAttribute(name="DieWidth")
    public Double getDieWidth() {
        return dieWidth;
    }

    public void setDieWidth(Double dieWidth) {
        this.dieWidth = dieWidth;
    }

    @XmlAttribute(name="DieHeight")
    public Double getDieHeight() {
        return dieHeight;
    }

    public void setDieHeight(Double dieHeight) {
        this.dieHeight = dieHeight;
    }

    @XmlAttribute(name="CenterX")
    public Double getCenterX() {
        return centerX;
    }

    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    @XmlAttribute(name="CenterY")
    public Double getCenterY() {
        return centerY;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    @XmlAttribute(name="PositiveX")
    public String getPositiveX() {
        return positiveX;
    }

    public void setPositiveX(String positiveX) {
        this.positiveX = positiveX;
    }

    @XmlAttribute(name="PositiveY")
    public String getPositiveY() {
        return positiveY;
    }

    public void setPositiveY(String positiveY) {
        this.positiveY = positiveY;
    }

    @XmlAttribute(name="ReticleRows")
    public Long getReticleRows() {
        return reticleRows;
    }

    public void setReticleRows(Long reticleRows) {
        this.reticleRows = reticleRows;
    }

    @XmlAttribute(name="ReticleCols")
    public Long getReticleCols() {
        return reticleCols;
    }

    public void setReticleCols(Long reticleCols) {
        this.reticleCols = reticleCols;
    }

    @XmlAttribute(name="ReticleRowOffset")
    public Long getReticleRowOffset() {
        return reticleRowOffset;
    }

    public void setReticleRowOffset(Long reticleRowOffset) {
        this.reticleRowOffset = reticleRowOffset;
    }

    @XmlAttribute(name="RetivcleColOffset")
    public Long getReticleColOffset() {
        return reticleColOffset;
    }

    public void setReticleColOffset(Long reticleColOffset) {
        this.reticleColOffset = reticleColOffset;
    }

    @XmlAttribute(name="Confirmed")
    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @XmlAttribute(name="DeviceCount")
    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    @XmlAttribute(name="ConfirmTime")
    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    @XmlAttribute(name="Comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlAttribute(name="InsertTime")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @XmlAttribute(name="InputFile")
    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    @XmlAttribute(name="CfgId")
    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
    }

    @XmlAttribute(name="Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlAttribute(name="RefDieX")
    public Integer getRefDieX() {
        return refDieX;
    }

    public void setRefDieX(Integer refDieX) {
        this.refDieX = refDieX;
    }

    @XmlAttribute(name="RefDieY")
    public Integer getRefDieY() {
        return refDieY;
    }

    public void setRefDieY(Integer refDieY) {
        this.refDieY = refDieY;
    }

    @XmlAttribute(name="RefDieInitDt")
    public String getRefDieInitDt() {
        return refDieInitDt;
    }

    public void setRefDieInitDt(String refDieInitDt) {
        this.refDieInitDt = refDieInitDt;
    }

    @XmlAttribute(name="WmcDevice")
    public String getWmcDevice() {
        return wmcDevice;
    }

    public void setWmcDevice(String wmcDevice) {
        this.wmcDevice = wmcDevice;
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
        return "OnWmapModel{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", product='" + product + '\'' +
                ", wfUnits='" + wfUnits + '\'' +
                ", wfSize=" + wfSize +
                ", flatType='" + flatType + '\'' +
                ", flat='" + flat + '\'' +
                ", dieWidth=" + dieWidth +
                ", dieHeight=" + dieHeight +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", positiveX='" + positiveX + '\'' +
                ", positiveY='" + positiveY + '\'' +
                ", reticleRows='" + reticleRows + '\'' +
                ", reticleCols='" + reticleCols + '\'' +
                ", reticleRowOffset='" + reticleRowOffset + '\'' +
                ", reticleColOffset='" + reticleColOffset + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deviceCount='" + deviceCount + '\'' +
                ", confirmTime='" + confirmTime + '\'' +
                ", comments='" + comments + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", inputFile='" + inputFile + '\'' +
                ", cfgId='" + cfgId + '\'' +
                ", location='" + location + '\'' +
                ", refDieX='" + refDieX + '\'' +
                ", refDieY='" + refDieY + '\'' +
                ", refDieInitDt='" + refDieInitDt + '\'' +
                ", wmcDevice='" + wmcDevice + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
