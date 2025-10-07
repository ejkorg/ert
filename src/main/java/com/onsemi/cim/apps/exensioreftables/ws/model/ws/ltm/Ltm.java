package com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * LTM wrapper.
 *
 * @author fg6zdy
 * @mail
 */
@XmlRootElement(name="Ltm")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ltm {

    private LotInfo lotInfo;

    public Ltm(LotInfo lotInfo) {
        this.lotInfo = lotInfo;
    }

    public Ltm() {
    }

    @XmlElement(name="LotInfo")
    public LotInfo getLotInfo() {
        return lotInfo;
    }

    public void setLotInfo(LotInfo lotInfo) {
        this.lotInfo = lotInfo;
    }
    
}
