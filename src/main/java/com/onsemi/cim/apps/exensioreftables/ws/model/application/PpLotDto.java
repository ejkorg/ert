package com.onsemi.cim.apps.exensioreftables.ws.model.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpLot;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PpLot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PpLotDto {

    private Long id;
    private String lot;
    private String parentLot;
    private String product;
    private String lotOwner;
    private String parentProduct;
    private String sourceLot;
    private String insertTime;
    private Status status;
    private String errorMessage;

    public PpLotDto() {
    }

    public PpLotDto(PpLot ppLot) {
        id = ppLot.getId();
        lot = ppLot.getLot();
        parentLot = ppLot.getParentLot();
        product = ppLot.getProduct();
        lotOwner = ppLot.getLotOwner();
        parentProduct = ppLot.getParentProduct();
        sourceLot = ppLot.getSourceLot();
        insertTime = DateUtils.convertDateToString(ppLot.getInsertTime());
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return "PpLotDto{" +
                "id=" + id +
                ", lot='" + lot + '\'' +
                ", parentLot='" + parentLot + '\'' +
                ", product='" + product + '\'' +
                ", lotOwner='" + lotOwner + '\'' +
                ", parentProduct='" + parentProduct + '\'' +
                ", sourceLot='" + sourceLot + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
