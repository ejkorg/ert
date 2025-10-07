package com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author fg6zdy
 */
@XmlRootElement(name = "waferId")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WaferId {

    public static final String STATUS_MISSING = "MISSING";
    public static final String STATUS_FOUND = "FOUND";
    public static final String STATUS_ERROR = "ERROR";

    private String laserScribe;

    private Integer vid;

    private String lotId;

    private String error;

    private String fetSublot;

    private String status;

    @XmlAttribute(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlAttribute(name = "fetSublot")
    public String getFetSublot() {
        return fetSublot;
    }

    public void setFetSublot(String fetSublot) {
        this.fetSublot = fetSublot;
    }

    public WaferId(Integer vid) {
        this.vid = vid;
    }

    public WaferId() {
    }

    public WaferId(String laserScribe, Integer vid) {
        this.laserScribe = laserScribe;
        this.vid = vid;
    }

    public WaferId(String laserScribe, Integer vid, String lotId) {
        this.laserScribe = laserScribe;
        this.vid = vid;
        this.lotId = lotId;
    }

    public WaferId(String laserScribe) {
        this.laserScribe = laserScribe;
    }

    public WaferId(String laserScribe, String lotId) {
        this.laserScribe = laserScribe;
        this.lotId = lotId;
    }

    @XmlAttribute(name = "laserScribe")
    public String getLaserScribe() {
        return laserScribe;
    }

    public void setLaserScribe(String laserScribe) {
        this.laserScribe = laserScribe;
    }

    @XmlAttribute(name = "lotId")
    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    @XmlAttribute(name = "vid")
    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    @XmlAttribute(name = "error")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "WaferId{" + "laserScribe=" + laserScribe + ", vid=" + vid + ", lotId=" + lotId + ", error=" + error + '}';
    }
}
