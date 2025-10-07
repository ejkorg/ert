package com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fg6zdy
 */
@XmlType(propOrder = {
    "metadataTime",//
    "technology",//
    "process",//
    "family",//
    "partType",//
    "product",//
    "loadCode",//
    "lotType",//
    "part",//
    "prevLotNum",//
    "maskset",//
    "packageCode",//
    "ptiCode",//
    "packageGrpCode"//
})
@XmlRootElement(name = "MetadataInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MetadataInfo {

    static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    final SimpleDateFormat f = new SimpleDateFormat(DATE_TIME_FORMAT);
    /** ltm related */
    private Long id;
    private String lotNumber;
    private String lotType;
    private Date createdTime;
    private Long idLotInfo;
    /** */
    private String prevLotNum;
    /**  */
    private String family;
    private String product;
    private String process;
    private String technology;
    private String partType;
    private String part;
    private String maskset;
    /** loader related */
    private String timeStamp;
    private String loadCode;
    private Date metadataTime;
    /** tracking related */
    private String packageGrpCode;
    private String packageCode;
    private String ptiCode;

    @XmlTransient
    public Long getIdLotInfo() {
        return idLotInfo;
    }

    @XmlAttribute(name = "PrevLot")
    public String getPrevLotNum() {
        return prevLotNum;
    }

    public void setPrevLotNum(String prevLotNum) {
        this.prevLotNum = prevLotNum.toUpperCase();
    }

    public void setIdLotInfo(Long idLotInfo) {
        this.idLotInfo = idLotInfo;
    }

    @XmlAttribute(name = "PtiCode")
    public String getPtiCode() {
        return ptiCode;
    }

    public void setPtiCode(String ptiCode) {
        this.ptiCode = ptiCode.toUpperCase();
    }

    @XmlAttribute(name = "Maskset")
    public String getMaskset() {
        return maskset;
    }

    @XmlAttribute(name = "PackageCode")
    public String getPackageCode() {
        return packageCode;
    }

    @XmlAttribute(name = "PackageGrpCode")
    public String getPackageGrpCode() {
        return packageGrpCode;
    }

    public void setMaskset(String maskset) {
        this.maskset = maskset.toUpperCase();
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode.toUpperCase();
    }

    public void setPackageGrpCode(String packageGrpCode) {
        this.packageGrpCode = packageGrpCode.toUpperCase();
    }

    @XmlAttribute(name = "LoadCode")
    public String getLoadCode() {
        return loadCode;
    }

    public void setLoadCode(String loadCode) {
        this.loadCode = loadCode.toUpperCase();
    }

    @XmlAttribute(name = "CreatedTime")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getMetadataTime() {
        return metadataTime;
    }

    public void setMetadataTime(Date metadataTime) {
        this.metadataTime = metadataTime;
    }

    @XmlAttribute(name = "Part")
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part.toUpperCase();
    }

    @XmlAttribute(name = "PartType")
    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType.toUpperCase();
    }

    @XmlTransient
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date cretead) {
        this.createdTime = cretead;
    }

    @XmlAttribute(name = "Family")
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family.toUpperCase();
    }

    @XmlAttribute(name = "Lot")
    //@XmlTransient
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber.toUpperCase();
    }

    @XmlAttribute(name = "LotType")
    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType.toUpperCase();
    }

    @XmlAttribute(name = "Process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process.toUpperCase();
    }

    @XmlAttribute(name = "Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product.toUpperCase();
    }

    @XmlAttribute(name = "Technology")
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology.toUpperCase();
    }

    @Override
    public String toString() {
        return "MetadataInfo{"
                + "id=" + id
                + ", lotNumber=" + lotNumber
                + ", lotType=" + lotType
                + ", idLotInfo=" + idLotInfo
                + ", prevLotNum=" + prevLotNum
                + ", family=" + family
                + ", product=" + product
                + ", process=" + process
                + ", technology=" + technology
                + ", maskset=" + maskset
                + ", partType=" + partType
                + ", part=" + part
                + ", timeStamp=" + timeStamp
                + ", loadCode=" + loadCode
                + ", packageGrpCode=" + packageGrpCode
                + ", packageCode=" + packageCode
                + ", ptiCode=" + ptiCode
                + ", metadataTime=" + ((getMetadataTime() != null) ? f.format(metadataTime) : "N/A")
                + ", createdTime=" + ((getCreatedTime() != null) ? f.format(getCreatedTime()) : "N/A")
                + '}';
    }
}
