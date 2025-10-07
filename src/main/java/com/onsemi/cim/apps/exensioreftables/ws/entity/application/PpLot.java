package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.PpLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity(name = "PP_LOT")
public class PpLot extends ApplicationEntity {

    @Column(name = "LOT") private String lot;
    @Column(name = "PARENT_LOT") private String parentLot;
    @Column(name = "PRODUCT") private String product;
    @Column(name = "LOT_OWNER") private String lotOwner;
    @Column(name = "PARENT_PRODUCT") private String parentProduct;
    @Column(name = "SOURCE_LOT") private String sourceLot;
    @Column(name = "INSERT_TIME") private Date insertTime;


    public PpLot() {
    }

    public PpLot(PpLotDto ppLotDto) {
        this.lot = StringUtils.upperCase(ppLotDto.getLot());
        this.parentLot = StringUtils.upperCase(ppLotDto.getParentLot());
        this.product = StringUtils.upperCase(ppLotDto.getProduct());
        this.lotOwner = StringUtils.upperCase(ppLotDto.getLotOwner());
        this.parentProduct = StringUtils.upperCase(ppLotDto.getParentProduct());
        this.sourceLot = StringUtils.upperCase(ppLotDto.getSourceLot());
        this.insertTime = DateUtils.convertStringToDate(ppLotDto.getInsertTime());
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


    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }


    @Override
    public String toString() {
        return "PpLot{" +
                "id='" + id + '\'' +
                ", lot='" + lot + '\'' +
                ", parentLot='" + parentLot + '\'' +
                ", product='" + product + '\'' +
                ", lotOwner='" + lotOwner + '\'' +
                ", parentProduct='" + parentProduct + '\'' +
                ", sourceLot='" + sourceLot + '\'' +
                ", insertTime=" + insertTime +
                '}';
    }
}
