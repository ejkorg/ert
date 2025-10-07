package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fg7x8c
 */

@XmlRootElement(name="LotG")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LotG {
    
    private SourceLotInfo sourceLotInfo;

    public LotG(SourceLotInfo sourceLotInfo) {
        this.sourceLotInfo = sourceLotInfo;
    }

    public LotG() {
    }

    @XmlElement(name="SourceLot")
    public SourceLotInfo getSourceLotInfo() {
        return sourceLotInfo;
    }

    public void setSourceLotInfo(SourceLotInfo sourceLotInfo) {
        this.sourceLotInfo = sourceLotInfo;
    }
    
}
