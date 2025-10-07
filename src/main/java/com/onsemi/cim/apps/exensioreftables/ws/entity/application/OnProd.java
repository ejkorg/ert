package com.onsemi.cim.apps.exensioreftables.ws.entity.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Entity(name = "ON_PROD")
public class OnProd extends ApplicationEntity {

    @Column(name = "STATUS") @Enumerated(EnumType.STRING) private Status status;
    @Column(name = "PRODUCT") private String product;
    @Column(name = "PRODUCT_VERSION") private String productVersion;
    @Column(name = "ITEM_TYPE") private String itemType;
    @Column(name = "FAB") private String fab;
    @Column(name = "FAB_DESC") private String fabDesc;
    @Column(name = "AFM") private String afm;
    @Column(name = "PROCESS") private String process;
    @Column(name = "FAMILY") private String family;
    @Column(name = "PACKAGE") private String packageColumn;
    @Column(name = "GDPW") private Long gdpw;
    @Column(name = "WF_UNITS") private String wfUnits;
    @Column(name = "WF_SIZE") private Double wfSize;
    @Column(name = "DIE_UNITS") private String dieUnits;
    @Column(name = "DIE_WIDTH") private Double dieWidth;
    @Column(name = "DIE_HEIGHT") private Double dieHeight;
    @Column(name = "INSERT_TIME") private Date insertTime;
    @Column(name = "PTI4") private String pti4;
    @Column(name = "TECHNOLOGY") private String technology;
    @Column(name = "MASKSET") private String maskSet;


    public OnProd() {
    }


    public OnProd(OnProdDto onProdDto) {
        this.status = onProdDto.getStatus();
        this.product = StringUtils.upperCase(onProdDto.getProduct());
        this.productVersion = StringUtils.upperCase(onProdDto.getProductVersion());
        this.itemType = StringUtils.upperCase(onProdDto.getItemType());
        this.fab = StringUtils.upperCase(onProdDto.getFab());
        this.fabDesc = StringUtils.upperCase(onProdDto.getFabDesc());
        this.afm = StringUtils.upperCase(onProdDto.getAfm());
        this.process = StringUtils.upperCase(onProdDto.getProcess());
        this.family = StringUtils.upperCase(onProdDto.getFamily());
        this.packageColumn = StringUtils.upperCase(onProdDto.getPackage());
        this.gdpw = onProdDto.getGdpw();
        this.wfUnits = onProdDto.getWfUnits();
        this.wfSize = onProdDto.getWfSize();
        this.dieUnits = StringUtils.upperCase(onProdDto.getDieUnits());
        this.dieWidth = onProdDto.getDieWidth();
        this.dieHeight = onProdDto.getDieHeight();
        this.insertTime = DateUtils.convertStringToDate(onProdDto.getInsertTime());
        this.pti4 = StringUtils.upperCase(onProdDto.getPti4());
        this.technology = StringUtils.upperCase(onProdDto.getTechnology());
        this.maskSet = StringUtils.upperCase(onProdDto.getMaskSet());
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


    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }


    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }


    public String getFabDesc() {
        return fabDesc;
    }

    public void setFabDesc(String fabDesc) {
        this.fabDesc = fabDesc;
    }


    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }


    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }


    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }


    public String getPackage() {
        return packageColumn;
    }

    public void setPackage(String packageColumn) {
        this.packageColumn = packageColumn;
    }


    public Long getGdpw() {
        return gdpw;
    }

    public void setGdpw(Long gdpw) {
        this.gdpw = gdpw;
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


    public String getDieUnits() {
        return dieUnits;
    }

    public void setDieUnits(String dieUnits) {
        this.dieUnits = dieUnits;
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


    public java.sql.Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(java.sql.Date insertTime) {
        this.insertTime = insertTime;
    }


    public String getPti4() {
        return pti4;
    }

    public void setPti4(String pti4) {
        this.pti4 = pti4;
    }


    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }


    public String getMaskSet() {
        return maskSet;
    }

    public void setMaskSet(String maskSet) {
        this.maskSet = maskSet;
    }


    @Override
    public String toString() {
        return "OnProd{" +
                "id=" + id +
                ", status=" + status +
                ", status2=" + status +
                ", product='" + product + '\'' +
                ", itemType='" + itemType + '\'' +
                ", fab='" + fab + '\'' +
                ", fabDesc='" + fabDesc + '\'' +
                ", afm='" + afm + '\'' +
                ", process='" + process + '\'' +
                ", family='" + family + '\'' +
                ", packageColumn='" + packageColumn + '\'' +
                ", gdpw='" + gdpw + '\'' +
                ", wfUnits='" + wfUnits + '\'' +
                ", wfSize=" + wfSize +
                ", dieUnits='" + dieUnits + '\'' +
                ", dieWidth=" + dieWidth +
                ", dieHeight=" + dieHeight +
                ", insertTime=" + insertTime +
                ", pti4='" + pti4 + '\'' +
                ", technology='" + technology + '\'' +
                ", maskSet='" + maskSet + '\'' +
                '}';
    }
}
