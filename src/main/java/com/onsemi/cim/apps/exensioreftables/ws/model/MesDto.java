package com.onsemi.cim.apps.exensioreftables.ws.model;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.MesType;

public class MesDto {

    private final MesType mesType;
    private String lot;
    private String fab;
    private String product;
    private String productVersion;
    private String family;
    private String lotType;
    private String process;
    private String technology;
    private String pti4;
    private String maskSet;


    public MesDto(MesType mesType) {
        this.mesType = mesType;
    }

    public MesType getMesType() {
        return mesType;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getPti4() {
        return pti4;
    }

    public void setPti4(String pti4) {
        this.pti4 = pti4;
    }

    public String getMaskSet() {
        return maskSet;
    }

    public void setMaskSet(String maskSet) {
        this.maskSet = maskSet;
    }
}
