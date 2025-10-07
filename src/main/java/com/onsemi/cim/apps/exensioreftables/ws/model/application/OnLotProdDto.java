package com.onsemi.cim.apps.exensioreftables.ws.model.application;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnLotProd")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnLotProdDto {

    private OnLotDto onLot;
    private OnProdDto onProd;

    @XmlElement(name="OnLot")
    public OnLotDto getOnLot() {
        return onLot;
    }

    public void setOnLot(OnLotDto onLot) {
        this.onLot = onLot;
    }

    @XmlElement(name="OnProd")
    public OnProdDto getOnProd() {
        return onProd;
    }

    public void setOnProd(OnProdDto onProd) {
        this.onProd = onProd;
    }

    @Override
    public String toString() {
        return "OnLotProdDto{" +
                "onLot=" + onLot +
                ", onProd=" + onProd +
                '}';
    }
}
