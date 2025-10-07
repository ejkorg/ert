package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnLot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnLotDto {

    private Long id;
    private Status status;
    private String lot;
    private String parentLot;
    private String product;
    private String lotOwner;
    private String parentProduct;
    private String sourceLot;
    private String insertTime;
    private String alternateProduct;
    private String fab;
    private String lotType;
    private String lotClass;
    private String alternateLot;
    private String subconLot;
    private String subconProduct;
    private String productCode;
    private String mfgLot;
    private String errorMessage;


    public OnLotDto() {
    }

    public OnLotDto(OnLot onLot) {
        id = onLot.getId();
        status = onLot.getStatus();
        lot = onLot.getLot();
        parentLot = onLot.getParentLot();
        product = onLot.getProduct();
        lotOwner = onLot.getLotOwner();
        parentProduct = onLot.getParentProduct();
        sourceLot = onLot.getSourceLot();
        insertTime = DateUtils.convertDateToString(onLot.getInsertTime());
        alternateProduct = onLot.getAlternateProduct();
        fab = onLot.getFab();
        lotType = onLot.getLotType();
        lotClass = onLot.getLotClass();
        alternateLot = onLot.getAlternateLot();
        subconLot = onLot.getSubconLot();
        subconProduct = onLot.getSubconProduct();
        productCode = onLot.getProductCode();
        mfgLot = onLot.getMfgLot();
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

    @XmlAttribute(name="Lot")
    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @XmlAttribute(name="ParentLot")
    public String getParentLot() {
        return parentLot;
    }

    public void setParentLot(String parentLot) {
        this.parentLot = parentLot;
    }

    @XmlAttribute(name="Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @XmlAttribute(name="LotOwner")
    public String getLotOwner() {
        return lotOwner;
    }

    public void setLotOwner(String lotOwner) {
        this.lotOwner = lotOwner;
    }

    @XmlAttribute(name="ParentProduct")
    public String getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(String parentProduct) {
        this.parentProduct = parentProduct;
    }

    @XmlAttribute(name="SourceLot")
    public String getSourceLot() {
        return sourceLot;
    }

    public void setSourceLot(String sourceLot) {
        this.sourceLot = sourceLot;
    }

    @XmlAttribute(name="InsertTime")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @XmlAttribute(name="AlternateProduct")
    public String getAlternateProduct() {
        return alternateProduct;
    }

    public void setAlternateProduct(String alternateProduct) {
        this.alternateProduct = alternateProduct;
    }

    @XmlAttribute(name="Fab")
    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    @XmlAttribute(name="LotType")
    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    @XmlAttribute(name="LotClass")
    public String getLotClass() {
        return lotClass;
    }

    public void setLotClass(String lotClass) {
        this.lotClass = lotClass;
    }

    @XmlAttribute(name="AlternateLot")
    public String getAlternateLot() {
        return alternateLot;
    }

    public void setAlternateLot(String alternateLot) {
        this.alternateLot = alternateLot;
    }

    @XmlAttribute(name="SubconLot")
    public String getSubconLot() {
        return subconLot;
    }

    public void setSubconLot(String subconLot) {
        this.subconLot = subconLot;
    }

    @XmlAttribute(name="SubconProduct")
    public String getSubconProduct() {
        return subconProduct;
    }

    public void setSubconProduct(String subconProduct) {
        this.subconProduct = subconProduct;
    }

    @XmlAttribute(name="ProductCode")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @XmlAttribute(name="MfgLot")
    public String getMfgLot() {
        return mfgLot;
    }

    public void setMfgLot(String mfgLot) {
        this.mfgLot = mfgLot;
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
        return "OnLotModel{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", lot='" + lot + '\'' +
                ", parentLot='" + parentLot + '\'' +
                ", product='" + product + '\'' +
                ", lotOwner='" + lotOwner + '\'' +
                ", parentProduct='" + parentProduct + '\'' +
                ", sourceLot='" + sourceLot + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", alternateProduct='" + alternateProduct + '\'' +
                ", fab='" + fab + '\'' +
                ", lotType='" + lotType + '\'' +
                ", lotClass='" + lotClass + '\'' +
                ", alternateLot='" + alternateLot + '\'' +
                ", subconLot='" + subconLot + '\'' +
                ", subconProduct='" + subconProduct + '\'' +
                ", productCode='" + productCode + '\'' +
                ", mfgLot='" + mfgLot + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
