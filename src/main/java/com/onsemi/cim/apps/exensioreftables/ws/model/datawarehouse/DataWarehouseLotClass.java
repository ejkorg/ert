package com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fg6zdy
 */
@XmlRootElement(name="DwLotClass")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataWarehouseLotClass {

    private String lotclassCd;

    @XmlAttribute(name="LOTCLASS_CD")
    public String getLotclassCd() {
        return lotclassCd;
    }

    public void setLotclassCd(String lotclassCd) {
        this.lotclassCd = lotclassCd;
    }

    @Override
    public String toString() {
        return "DataWarehouseLotClass{" +
                "lotclassCd='" + lotclassCd + '\'' +
                '}';
    }
}
