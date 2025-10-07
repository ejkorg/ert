package com.onsemi.cim.apps.exensioreftables.ws.model.lotg;

public class LotGInfo {
    private Status status;
    private String lot;
    private String product;
    private String sourceLot;
    private String waferPartAlternateProduct;
    private String fab;
    private String lotClass;
    private String lotType;
    private String maskSet;
    private String productionCode;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSourceLot() {
        return sourceLot;
    }

    public void setSourceLot(String sourceLot) {
        this.sourceLot = sourceLot;
    }

    public String getWaferPartAlternateProduct() {
        return waferPartAlternateProduct;
    }

    public void setWaferPartAlternateProduct(String waferPartAlternateProduct) {
        this.waferPartAlternateProduct = waferPartAlternateProduct;
    }

    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    public String getLotClass() {
        return lotClass;
    }

    public void setLotClass(String lotClass) {
        this.lotClass = lotClass;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getMaskSet() {
        return maskSet;
    }

    public void setMaskSet(String maskSet) {
        this.maskSet = maskSet;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    @Override
    public String toString() {
        return "LotGInfo{" +
                "status=" + status +
                ", lot='" + lot + '\'' +
                ", product='" + product + '\'' +
                ", sourceLot='" + sourceLot + '\'' +
                ", waferPartAlternateProduct='" + waferPartAlternateProduct + '\'' +
                ", fab='" + fab + '\'' +
                ", lotClass='" + lotClass + '\'' +
                ", lotType='" + lotType + '\'' +
                ", maskSet='" + maskSet + '\'' +
                ", productionCode='" + productionCode + '\'' +
                '}';
    }
}
