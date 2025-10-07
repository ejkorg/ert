package com.onsemi.cim.apps.exensioreftables.ws.entity.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnWmapDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Entity(name = "ON_WMAP")
public class OnWmap extends ApplicationEntity {

    @Column(name = "STATUS") @Enumerated(EnumType.STRING) private Status status;
    @Column(name = "PRODUCT") private String product;
    @Column(name = "WF_UNITS") private String wfUnits;
    @Column(name = "WF_SIZE") private Double wfSize;
    @Column(name = "FLAT_TYPE") private String flatType;
    @Column(name = "FLAT") private String flat;
    @Column(name = "DIE_WIDTH") private Double dieWidth;
    @Column(name = "DIE_HEIGHT") private Double dieHeight;
    @Column(name = "CENTER_X") private Double centerX;
    @Column(name = "CENTER_Y") private Double centerY;
    @Column(name = "POSITIVE_X") private String positiveX;
    @Column(name = "POSITIVE_Y") private String positiveY;
    @Column(name = "RETICLE_ROWS") private Long reticleRows;
    @Column(name = "RETICLE_COLS") private Long reticleCols;
    @Column(name = "RETICLE_ROW_OFFSET") private Long reticleRowOffset;
    @Column(name = "RETICLE_COL_OFFSET") private Long reticleColOffset;
    @Column(name = "CONFIRMED") private String confirmed;
    @Column(name = "DEVICE_COUNT") private Long deviceCount;
    @Column(name = "CONFIRM_TIME") private Date confirmTime;
    @Column(name = "COMMENTS") private String comments;
    @Column(name = "INSERT_TIME") private Date insertTime;
    @Column(name = "INPUT_FILE") private String inputFile;
    @Column(name = "CFG_ID") private Long cfgId;
    @Column(name = "LOCATION") private String location;
    @Column(name = "REF_DIE_X") private Integer refDieX;
    @Column(name = "REF_DIE_Y") private Integer refDieY;
    @Column(name = "REF_DIE_INIT_DT") private Date refDieInitDt;
    @Column(name = "WMC_DEVICE") private String wmcDevice;


    public OnWmap() {
    }


    public OnWmap(OnWmapDto onWmapDto) {
        this.status = onWmapDto.getStatus();
        this.product = StringUtils.upperCase(onWmapDto.getProduct());
        this.wfUnits = onWmapDto.getWfUnits();
        this.wfSize = onWmapDto.getWfSize();
        this.flatType = StringUtils.upperCase(onWmapDto.getFlatType());
        this.flat = StringUtils.upperCase(onWmapDto.getFlat());
        this.dieWidth = onWmapDto.getDieWidth();
        this.dieHeight = onWmapDto.getDieHeight();
        this.centerX = onWmapDto.getCenterX();
        this.centerY = onWmapDto.getCenterY();
        this.positiveX = StringUtils.upperCase(onWmapDto.getPositiveX());
        this.positiveY = StringUtils.upperCase(onWmapDto.getPositiveY());
        this.reticleRows = onWmapDto.getReticleRows();
        this.reticleCols = onWmapDto.getReticleCols();
        this.reticleRowOffset = onWmapDto.getReticleRowOffset();
        this.reticleColOffset = onWmapDto.getReticleColOffset();
        this.confirmed = StringUtils.upperCase(onWmapDto.getConfirmed());
        this.deviceCount = onWmapDto.getDeviceCount();
        this.confirmTime = DateUtils.convertStringToDate(onWmapDto.getConfirmTime());
        this.comments = StringUtils.upperCase(onWmapDto.getComments());
        this.insertTime = DateUtils.convertStringToDate(onWmapDto.getInsertTime());
        this.inputFile = onWmapDto.getInputFile();
        this.cfgId = onWmapDto.getCfgId();
        this.location = StringUtils.upperCase(onWmapDto.getLocation());
        this.refDieX = onWmapDto.getRefDieX();
        this.refDieY = onWmapDto.getRefDieY();
        this.refDieInitDt = DateUtils.convertStringToDate(onWmapDto.getRefDieInitDt());
        this.wmcDevice = StringUtils.upperCase(onWmapDto.getWmcDevice());
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public String getWfUnits() {
        return wfUnits;
    }

    public void setWfUnits(String wfUnits) {
        this.wfUnits = wfUnits;
    }


    public Double getWfSize() {
        return wfSize;
    }

    public void setWfSize(Double wfSize) {
        this.wfSize = wfSize;
    }


    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }


    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }


    public Double getDieWidth() {
        return dieWidth;
    }

    public void setDieWidth(Double dieWidth) {
        this.dieWidth = dieWidth;
    }


    public Double getDieHeight() {
        return dieHeight;
    }

    public void setDieHeight(Double dieHeight) {
        this.dieHeight = dieHeight;
    }


    public Double getCenterX() {
        return centerX;
    }

    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }


    public Double getCenterY() {
        return centerY;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }


    public String getPositiveX() {
        return positiveX;
    }

    public void setPositiveX(String positiveX) {
        this.positiveX = positiveX;
    }


    public String getPositiveY() {
        return positiveY;
    }

    public void setPositiveY(String positiveY) {
        this.positiveY = positiveY;
    }


    public Long getReticleRows() {
        return reticleRows;
    }

    public void setReticleRows(Long reticleRows) {
        this.reticleRows = reticleRows;
    }


    public Long getReticleCols() {
        return reticleCols;
    }

    public void setReticleCols(Long reticleCols) {
        this.reticleCols = reticleCols;
    }


    public Long getReticleRowOffset() {
        return reticleRowOffset;
    }

    public void setReticleRowOffset(Long reticleRowOffset) {
        this.reticleRowOffset = reticleRowOffset;
    }


    public Long getReticleColOffset() {
        return reticleColOffset;
    }

    public void setReticleColOffset(Long reticleColOffset) {
        this.reticleColOffset = reticleColOffset;
    }


    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }


    public Long getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }


    public java.sql.Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(java.sql.Date confirmTime) {
        this.confirmTime = confirmTime;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public java.sql.Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(java.sql.Date insertTime) {
        this.insertTime = insertTime;
    }


    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }


    public Long getCfgId() {
        return cfgId;
    }

    public void setCfgId(Long cfgId) {
        this.cfgId = cfgId;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Integer getRefDieX() {
        return refDieX;
    }

    public void setRefDieX(Integer refDieX) {
        this.refDieX = refDieX;
    }


    public Integer getRefDieY() {
        return refDieY;
    }

    public void setRefDieY(Integer refDieY) {
        this.refDieY = refDieY;
    }


    public java.sql.Date getRefDieInitDt() {
        return refDieInitDt;
    }

    public void setRefDieInitDt(java.sql.Date refDieInitDt) {
        this.refDieInitDt = refDieInitDt;
    }


    public String getWmcDevice() {
        return wmcDevice;
    }

    public void setWmcDevice(String wmcDevice) {
        this.wmcDevice = wmcDevice;
    }

    @Override
    public String toString() {
        return "OnWmap{" +
                "id=" + id +
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
                ", confirmTime=" + confirmTime +
                ", comments='" + comments + '\'' +
                ", insertTime=" + insertTime +
                ", inputFile='" + inputFile + '\'' +
                ", cfgId='" + cfgId + '\'' +
                ", location='" + location + '\'' +
                ", refDieX='" + refDieX + '\'' +
                ", refDieY='" + refDieY + '\'' +
                ", refDieInitDt=" + refDieInitDt +
                ", wmcDevice='" + wmcDevice + '\'' +
                '}';
    }
}
