package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "ON_LOT")
public class OnLot extends ApplicationEntity implements Cloneable {

    @Column(name = "STATUS") @Enumerated(EnumType.STRING) private Status status;
    @Column(name = "LOT") private String lot;
    @Column(name = "PARENT_LOT") private String parentLot;
    @Column(name = "PRODUCT") private String product;
    @Column(name = "LOT_OWNER") private String lotOwner;
    @Column(name = "PARENT_PRODUCT") private String parentProduct;
    @Column(name = "SOURCE_LOT") private String sourceLot;
    @Column(name = "INSERT_TIME") private Date insertTime;
    @Column(name = "ALTERNATE_PRODUCT") private String alternateProduct;
    @Column(name = "FAB") private String fab;
    @Column(name = "LOT_TYPE") private String lotType;
    @Column(name = "LOT_CLASS") private String lotClass;
    @Column(name = "ALTERNATE_LOT") private String alternateLot;
    @Column(name = "SUBCON_LOT") private String subconLot;
    @Column(name = "SUBCON_PRODUCT") private String subconProduct;
    @Column(name = "PRODUCT_CODE") private String productCode;
    @Column(name = "MFG_LOT") private String mfgLot;

    @Transient private boolean mfgLotCalled;
    @Transient private boolean mesAvailable;
    @Transient private boolean mesCalled;

    public OnLot() {
    }

    public OnLot(OnLotDto onLotDto) {
        this.status = onLotDto.getStatus();
        this.lot = StringUtils.upperCase(onLotDto.getLot());
        this.parentLot = StringUtils.upperCase(onLotDto.getParentLot());
        this.product = StringUtils.upperCase(onLotDto.getProduct());
        this.lotOwner = StringUtils.upperCase(onLotDto.getLotOwner());
        this.parentProduct = StringUtils.upperCase(onLotDto.getParentProduct());
        this.sourceLot = StringUtils.upperCase(onLotDto.getSourceLot());
        this.insertTime = DateUtils.convertStringToDate(onLotDto.getInsertTime());
        this.alternateProduct = StringUtils.upperCase(onLotDto.getAlternateProduct());
        this.fab = StringUtils.upperCase(onLotDto.getFab());
        this.lotType = StringUtils.upperCase(onLotDto.getLotType());
        this.lotClass = StringUtils.upperCase(onLotDto.getLotClass());
        this.alternateLot = StringUtils.upperCase(onLotDto.getAlternateLot());
        this.subconLot = StringUtils.upperCase(onLotDto.getSubconLot());
        this.subconProduct = StringUtils.upperCase(onLotDto.getSubconProduct());
        this.productCode = StringUtils.upperCase(onLotDto.getProductCode());
        this.mfgLot = StringUtils.upperCase(onLotDto.getMfgLot());
    }

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


    public String getParentLot() {
        return parentLot;
    }

    public void setParentLot(String parentLot) {
        this.parentLot = parentLot;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public String getLotOwner() {
        return lotOwner;
    }

    public void setLotOwner(String lotOwner) {
        this.lotOwner = lotOwner;
    }


    public String getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(String parentProduct) {
        this.parentProduct = parentProduct;
    }


    public String getSourceLot() {
        return sourceLot;
    }

    public void setSourceLot(String sourceLot) {
        this.sourceLot = sourceLot;
    }


    public java.sql.Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(java.sql.Date insertTime) {
        this.insertTime = insertTime;
    }


    public String getAlternateProduct() {
        return alternateProduct;
    }

    public void setAlternateProduct(String alternateLot) {
        this.alternateProduct = alternateLot;
    }


    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }


    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }


    public String getLotClass() {
        return lotClass;
    }

    public void setLotClass(String lotClass) {
        this.lotClass = lotClass;
    }


    public String getAlternateLot() {
        return alternateLot;
    }

    public void setAlternateLot(String alternateLot) {
        this.alternateLot = alternateLot;
    }


    public String getSubconLot() {
        return subconLot;
    }

    public void setSubconLot(String subconLot) {
        this.subconLot = subconLot;
    }


    public String getSubconProduct() {
        return subconProduct;
    }

    public void setSubconProduct(String subconProduct) {
        this.subconProduct = subconProduct;
    }


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public String getMfgLot() {
        return mfgLot;
    }

    public void setMfgLot(String mfgLot) {
        this.mfgLot = mfgLot;
    }


    public boolean isMfgLotCalled() {
        return mfgLotCalled;
    }

    public void setMfgLotCalled(boolean mfgLotCalled) {
        this.mfgLotCalled = mfgLotCalled;
    }


    public boolean isMesAvailable() {
        return mesAvailable;
    }

    public void setMesAvailable(boolean mesAvailable) {
        this.mesAvailable = mesAvailable;
    }


    public boolean isMesCalled() {
        return mesCalled;
    }

    public void setMesCalled(boolean mesCalled) {
        this.mesCalled = mesCalled;
    }

    @Override
    public String toString() {
        return "OnLot{" +
                "status=" + status +
                ", lot='" + lot + '\'' +
                ", parentLot='" + parentLot + '\'' +
                ", product='" + product + '\'' +
                ", lotOwner='" + lotOwner + '\'' +
                ", parentProduct='" + parentProduct + '\'' +
                ", sourceLot='" + sourceLot + '\'' +
                ", insertTime=" + insertTime +
                ", alternateProduct='" + alternateProduct + '\'' +
                ", fab='" + fab + '\'' +
                ", lotType='" + lotType + '\'' +
                ", lotClass='" + lotClass + '\'' +
                ", alternateLot='" + alternateLot + '\'' +
                ", subconLot='" + subconLot + '\'' +
                ", subconProduct='" + subconProduct + '\'' +
                ", productCode='" + productCode + '\'' +
                ", mfgLot='" + mfgLot + '\'' +
                ", mfgLotCalled=" + mfgLotCalled +
                ", mesAvailable=" + mesAvailable +
                ", mesCalled=" + mesCalled +
                ", id=" + id +
                '}';
    }


    public OnLot getClone() throws BusinessException {

        try {
            return (OnLot) clone();
        } catch (CloneNotSupportedException e) {
            throw new BusinessException("Cloning of OnLot not supported");
        }
    }
}
