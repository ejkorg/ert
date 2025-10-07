package com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tcyjjp
 * @mail bobek@edhouse.cz
 *
 */
@XmlRootElement(name="WaferDetail")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WaferDetail {

    private String waferAttrCode;
    private String lotId;
    private String deviceId;
    private String waferNumber;
    private String waferItemId;
    private String waferScribe;
    private String waferQty;
    private String dataSource;
    private String lastUpdateDate;
    private String lastUpdateBy;

    //getters & setters

    @XmlAttribute(name="WaferAttrCode")
    public String getWaferAttrCode() {
        return waferAttrCode;
    }

    public void setWaferAttrCode(String waferAttrCode) {
        this.waferAttrCode = waferAttrCode;
    }

    @XmlAttribute(name="LotID")
    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    @XmlAttribute(name="DeviceID")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @XmlAttribute(name="WaferNumber")
    public String getWaferNumber() {
        return waferNumber;
    }

    public void setWaferNumber(String waferNumber) {
        this.waferNumber = waferNumber;
    }

    @XmlAttribute(name="WaferItemID")
    public String getWaferItemId() {
        return waferItemId;
    }

    public void setWaferItemId(String waferItemId) {
        this.waferItemId = waferItemId;
    }

    @XmlAttribute(name="WaferScribe")
    public String getWaferScribe() {
        return waferScribe;
    }

    public void setWaferScribe(String waferScribe) {
        this.waferScribe = waferScribe;
    }

    @XmlAttribute(name="WaferQty")
    public String getWaferQty() {
        return waferQty;
    }

    public void setWaferQty(String waferQty) {
        this.waferQty = waferQty;
    }

    @XmlAttribute(name="DataSource")
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @XmlAttribute(name="LastUpdateDate")
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @XmlAttribute(name="LastUpdateBy")
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return "WaferDetail{" +
                "waferAttrCode='" + waferAttrCode + '\'' +
                ", lotId='" + lotId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", waferNumber='" + waferNumber + '\'' +
                ", waferItemId='" + waferItemId + '\'' +
                ", waferScribe='" + waferScribe + '\'' +
                ", waferQty='" + waferQty + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                '}';
    }

}
