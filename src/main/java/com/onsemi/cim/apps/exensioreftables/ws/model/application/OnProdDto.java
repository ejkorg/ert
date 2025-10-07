package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnProd")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnProdDto {

    private Long id;
    private Status status;
    private String product;
    private String productVersion;
    private String itemType;
    private String fab;
    private String fabDesc;
    private String afm;
    private String process;
    private String family;
    private String packageColumn;
    private Long gdpw;
    private String wfUnits;
    private Double wfSize;
    private String dieUnits;
    private Double dieWidth;
    private Double dieHeight;
    private String insertTime;
    private String pti4;
    private String technology;
    private String errorMessage;
    private String maskSet;


    public OnProdDto() {
    }

    public OnProdDto(OnProd onProd) {
        id = onProd.getId();
        status = onProd.getStatus();
        product = onProd.getProduct();
        productVersion = onProd.getProductVersion();
        itemType = onProd.getItemType();
        fab = onProd.getFab();
        fabDesc = onProd.getFabDesc();
        afm = onProd.getAfm();
        process = onProd.getProcess();
        family = onProd.getFamily();
        packageColumn = onProd.getPackage();
        gdpw = onProd.getGdpw();
        wfUnits = onProd.getWfUnits();
        wfSize = onProd.getWfSize();
        dieUnits = onProd.getDieUnits();
        dieWidth = onProd.getDieWidth();
        dieHeight = onProd.getDieHeight();
        insertTime = DateUtils.convertDateToString(onProd.getInsertTime());
        pti4 = onProd.getPti4();
        technology = onProd.getTechnology();
        maskSet = onProd.getMaskSet();
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlAttribute(name="Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @XmlAttribute(name="ProductVersion")
    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    @XmlAttribute(name="ItemType")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @XmlAttribute(name="Fab")
    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    @XmlAttribute(name="FabDesc")
    public String getFabDesc() {
        return fabDesc;
    }

    public void setFabDesc(String fabDesc) {
        this.fabDesc = fabDesc;
    }

    @XmlAttribute(name="Afm")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @XmlAttribute(name="Process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @XmlAttribute(name="Family")
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @XmlAttribute(name="Package")
    public String getPackage() {
        return packageColumn;
    }

    public void setPackage(String packageColumn) {
        this.packageColumn = packageColumn;
    }

    @XmlAttribute(name="Gdpw")
    public Long getGdpw() {
        return gdpw;
    }

    public void setGdpw(Long gdpw) {
        this.gdpw = gdpw;
    }

    @XmlAttribute(name="WfUnits")
    public String getWfUnits() {
        return wfUnits;
    }

    public void setWfUnits(String wfUnits) {
        this.wfUnits = wfUnits;
    }

    @XmlAttribute(name="WfSize")
    public Double getWfSize() {
        return wfSize;
    }

    public void setWfSize(Double wfSize) {
        this.wfSize = wfSize;
    }

    @XmlAttribute(name="DieUnits")
    public String getDieUnits() {
        return dieUnits;
    }

    public void setDieUnits(String dieUnits) {
        this.dieUnits = dieUnits;
    }

    @XmlAttribute(name="DieWidth")
    public Double getDieWidth() {
        return dieWidth;
    }

    public void setDieWidth(Double dieWidth) {
        this.dieWidth = dieWidth;
    }

    @XmlAttribute(name="DieHeight")
    public Double getDieHeight() {
        return dieHeight;
    }

    public void setDieHeight(Double dieHeight) {
        this.dieHeight = dieHeight;
    }

    @XmlAttribute(name="InsertTime")
    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @XmlAttribute(name="Pti4")
    public String getPti4() {
        return pti4;
    }

    public void setPti4(String pti4) {
        this.pti4 = pti4;
    }

    @XmlAttribute(name="Technology")
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @XmlAttribute(name="ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @XmlAttribute(name="MaskSet")
    public String getMaskSet() {
        return maskSet;
    }

    public void setMaskSet(String maskSet) {
        this.maskSet = maskSet;
    }

    @Override
    public String toString() {
        return "OnProdModel{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", product='" + product + '\'' +
                ", itemType='" + itemType + '\'' +
                ", fab='" + fab + '\'' +
                ", fabDesc='" + fabDesc + '\'' +
                ", afm='" + afm + '\'' +
                ", process='" + process + '\'' +
                ", family='" + family + '\'' +
                ", packageColumn='" + packageColumn + '\'' +
                ", gdpw='" + gdpw + '\'' +
                ", wfUnits='" + wfUnits + '\'' +
                ", wfSize=" + wfSize +
                ", dieUnits='" + dieUnits + '\'' +
                ", dieWidth=" + dieWidth +
                ", dieHeight=" + dieHeight +
                ", insertTime='" + insertTime + '\'' +
                ", pti4='" + pti4 + '\'' +
                ", technology='" + technology + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", maskSet='" + maskSet + '\'' +
                '}';
    }
}
