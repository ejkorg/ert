package com.onsemi.cim.apps.exensioreftables.ws.entity.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpProdDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity(name = "PP_PROD")
public class PpProd extends ApplicationEntity {

    @Column(name = "PRODUCT") private String product;
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


    public PpProd() {
    }


    public PpProd(PpProdDto ppProdDto) {
        this.product = StringUtils.upperCase(ppProdDto.getProduct());
        this.itemType = StringUtils.upperCase(ppProdDto.getItemType());
        this.fab = StringUtils.upperCase(ppProdDto.getFab());
        this.fabDesc = StringUtils.upperCase(ppProdDto.getFabDesc());
        this.afm = StringUtils.upperCase(ppProdDto.getAfm());
        this.process = StringUtils.upperCase(ppProdDto.getProcess());
        this.family = StringUtils.upperCase(ppProdDto.getFamily());
        this.packageColumn = StringUtils.upperCase(ppProdDto.getPackage());
        this.gdpw = ppProdDto.getGdpw();
        this.wfUnits = ppProdDto.getWfUnits();
        this.wfSize = ppProdDto.getWfSize();
        this.dieUnits = StringUtils.upperCase(ppProdDto.getDieUnits());
        this.dieWidth = ppProdDto.getDieWidth();
        this.dieHeight = ppProdDto.getDieHeight();
        this.insertTime = DateUtils.convertStringToDate(ppProdDto.getInsertTime());
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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


    @Override
    public String toString() {
        return "PpProd{" +
                "id=" + id +
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
                '}';
    }
}
