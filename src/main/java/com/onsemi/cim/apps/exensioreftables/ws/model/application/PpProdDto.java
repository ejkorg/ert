package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.PpProd;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PpProd")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PpProdDto {

    private Long id;
    private String product;
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
    private Status status;
    private String errorMessage;


    public PpProdDto() {
    }

    public PpProdDto(PpProd ppProd) {
        id = ppProd.getId();
        product = ppProd.getProduct();
        itemType = ppProd.getItemType();
        fab = ppProd.getFab();
        fabDesc = ppProd.getFabDesc();
        afm = ppProd.getAfm();
        process = ppProd.getProcess();
        family = ppProd.getFamily();
        packageColumn = ppProd.getPackage();
        gdpw = ppProd.getGdpw();
        wfUnits = ppProd.getWfUnits();
        wfSize = ppProd.getWfSize();
        dieUnits = ppProd.getDieUnits();
        dieWidth = ppProd.getDieWidth();
        dieHeight = ppProd.getDieHeight();
        insertTime = DateUtils.convertDateToString(ppProd.getInsertTime());
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Product")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlAttribute(name="ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "PpProdDto{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", itemType='" + itemType + '\'' +
                ", fab='" + fab + '\'' +
                ", fabDesc='" + fabDesc + '\'' +
                ", afm='" + afm + '\'' +
                ", process='" + process + '\'' +
                ", family='" + family + '\'' +
                ", packageColumn='" + packageColumn + '\'' +
                ", gdpw=" + gdpw +
                ", wfUnits='" + wfUnits + '\'' +
                ", wfSize=" + wfSize +
                ", dieUnits='" + dieUnits + '\'' +
                ", dieWidth=" + dieWidth +
                ", dieHeight=" + dieHeight +
                ", insertTime='" + insertTime + '\'' +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
