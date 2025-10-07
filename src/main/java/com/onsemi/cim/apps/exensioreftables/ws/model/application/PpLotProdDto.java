package com.onsemi.cim.apps.exensioreftables.ws.model.application;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PpLotProd")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PpLotProdDto {

    private PpLotDto ppLot;
    private PpProdDto ppProd;

    public PpLotProdDto() {
    }

    public PpLotProdDto(PpLotDto ppLot, PpProdDto ppProd) {
        this.ppLot = ppLot;
        this.ppProd = ppProd;
    }

    @XmlElement(name="PpLot")
    public PpLotDto getPpLot() {
        return ppLot;
    }

    public void setPpLot(PpLotDto ppLot) {
        this.ppLot = ppLot;
    }

    @XmlElement(name="PpProd")
    public PpProdDto getPpProd() {
        return ppProd;
    }

    public void setPpProd(PpProdDto ppProd) {
        this.ppProd = ppProd;
    }

    @Override
    public String toString() {
        return "PpLotProdDto{" +
                "ppLotDto=" + ppLot +
                ", ppProdDto=" + ppProd +
                '}';
    }
}
