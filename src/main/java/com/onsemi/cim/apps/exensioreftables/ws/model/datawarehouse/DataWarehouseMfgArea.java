package com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fg6zdy
 */
@XmlRootElement(name="DwMfgArea")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataWarehouseMfgArea {

    private String mfgAreaCd;
    private String mfgAreaDesc;
    private String mfgAreaTypeCd;
    private String countryCd;
    private String updateDateTime;

    @XmlAttribute(name="MFG_AREA_CD")
    public String getMfgAreaCd() {
        return mfgAreaCd;
    }

    public void setMfgAreaCd(String mfgAreaCd) {
        this.mfgAreaCd = mfgAreaCd;
    }

    @XmlAttribute(name="MFG_AREA_DESC")
    public String getMfgAreaDesc() {
        return mfgAreaDesc;
    }

    public void setMfgAreaDesc(String mfgAreaDesc) {
        this.mfgAreaDesc = mfgAreaDesc;
    }

    @XmlAttribute(name="MFG_AREA_TYPE_CD")
    public String getMfgAreaTypeCd() {
        return mfgAreaTypeCd;
    }

    public void setMfgAreaTypeCd(String mfgAreaTypeCd) {
        this.mfgAreaTypeCd = mfgAreaTypeCd;
    }

    @XmlAttribute(name="COUNTRY_CD")
    public String getCountryCd() {
        return countryCd;
    }

    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    @XmlAttribute(name="UPDATE_DTME")
    public String getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString() {
        return "DataWarehouseMfgArea{" +
                "mfgAreaCd='" + mfgAreaCd + '\'' +
                ", mfgAreaDesc='" + mfgAreaDesc + '\'' +
                ", mfgAreaTypeCd='" + mfgAreaTypeCd + '\'' +
                ", countryCd='" + countryCd + '\'' +
                ", updateDateTime='" + updateDateTime + '\'' +
                '}';
    }
}
