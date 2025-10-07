package com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fg6zdy
 */
@XmlRootElement(name="DwPlm")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataWarehousePlm {

    private String corePartNbrConfigId;
    private String dieDesignConfigId;
    private String engWfrTechFamId;
    private String engWfrTechTypeId;
    private String erpResourceFabCd;
    private String erpResourceFabDesc;
    private String fabDieMapId;
    private String fabSiteId;
    private String famPlatformGroupType;
    private String lfcyclPhase;
    private String maskSetNoPartVal;
    private String mfgPartId;
    private String pal4Cd;
    private String partClassCd;
    private String partDesc;
    private String partId;
    private String partSubTypeCd;
    private String partType;
    private String partUsageCd;
    private String wfrFabMaskConfigId;
    private String wfrTechImplId;
    private String wfrTechVariantId;

    @XmlAttribute(name="CORE_PART_NBR_CONFIG_ID")
    public String getCorePartNbrConfigId() {
        return corePartNbrConfigId;
    }

    public void setCorePartNbrConfigId(String corePartNbrConfigId) {
        this.corePartNbrConfigId = corePartNbrConfigId;
    }

    @XmlAttribute(name="DIE_DESIGN_CONFIG_ID")
    public String getDieDesignConfigId() {
        return dieDesignConfigId;
    }

    public void setDieDesignConfigId(String dieDesignConfigId) {
        this.dieDesignConfigId = dieDesignConfigId;
    }

    @XmlAttribute(name="ENG_WFR_TECH_FAM_ID")
    public String getEngWfrTechFamId() {
        return engWfrTechFamId;
    }

    public void setEngWfrTechFamId(String engWfrTechFamId) {
        this.engWfrTechFamId = engWfrTechFamId;
    }

    @XmlAttribute(name="ENG_WFR_TECH_TYPE_ID")
    public String getEngWfrTechTypeId() {
        return engWfrTechTypeId;
    }

    public void setEngWfrTechTypeId(String engWfrTechTypeId) {
        this.engWfrTechTypeId = engWfrTechTypeId;
    }

    @XmlAttribute(name="ERP_RESOURCE_FAB_CD")
    public String getErpResourceFabCd() {
        return erpResourceFabCd;
    }

    public void setErpResourceFabCd(String erpResourceFabCd) {
        this.erpResourceFabCd = erpResourceFabCd;
    }

    @XmlAttribute(name="ERP_RESOURCE_FAB_DESC")
    public String getErpResourceFabDesc() {
        return erpResourceFabDesc;
    }

    public void setErpResourceFabDesc(String erpResourceFabDesc) {
        this.erpResourceFabDesc = erpResourceFabDesc;
    }

    @XmlAttribute(name="FAB_DIE_MAP_ID")
    public String getFabDieMapId() {
        return fabDieMapId;
    }

    public void setFabDieMapId(String fabDieMapId) {
        this.fabDieMapId = fabDieMapId;
    }

    @XmlAttribute(name="FAB_SITE_ID")
    public String getFabSiteId() {
        return fabSiteId;
    }

    public void setFabSiteId(String fabSiteId) {
        this.fabSiteId = fabSiteId;
    }

    @XmlAttribute(name="FAM_PLATFORM_GROUP_TYPE")
    public String getFamPlatformGroupType() {
        return famPlatformGroupType;
    }

    public void setFamPlatformGroupType(String famPlatformGroupType) {
        this.famPlatformGroupType = famPlatformGroupType;
    }

    @XmlAttribute(name="LFCYCL_PHASE")
    public String getLfcyclPhase() {
        return lfcyclPhase;
    }

    public void setLfcyclPhase(String lfcyclPhase) {
        this.lfcyclPhase = lfcyclPhase;
    }

    @XmlAttribute(name="MASK_SET_NO_PART_VAL")
    public String getMaskSetNoPartVal() {
        return maskSetNoPartVal;
    }

    public void setMaskSetNoPartVal(String maskSetNoPartVal) {
        this.maskSetNoPartVal = maskSetNoPartVal;
    }

    @XmlAttribute(name="MFG_PART_ID")
    public String getMfgPartId() {
        return mfgPartId;
    }

    public void setMfgPartId(String mfgPartId) {
        this.mfgPartId = mfgPartId;
    }

    @XmlAttribute(name="PAL4_CD")
    public String getPal4Cd() {
        return pal4Cd;
    }

    public void setPal4Cd(String pal4Cd) {
        this.pal4Cd = pal4Cd;
    }

    @XmlAttribute(name="PART_CLASS_CD")
    public String getPartClassCd() {
        return partClassCd;
    }

    public void setPartClassCd(String partClassCd) {
        this.partClassCd = partClassCd;
    }

    @XmlAttribute(name="PART_DESC")
    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    @XmlAttribute(name="PART_ID")
    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    @XmlAttribute(name="PART_SUB_TYPE_CD")
    public String getPartSubTypeCd() {
        return partSubTypeCd;
    }

    public void setPartSubTypeCd(String partSubTypeCd) {
        this.partSubTypeCd = partSubTypeCd;
    }

    @XmlAttribute(name="PART_TYPE")
    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @XmlAttribute(name="PART_USAGE_CD")
    public String getPartUsageCd() {
        return partUsageCd;
    }

    public void setPartUsageCd(String partUsageCd) {
        this.partUsageCd = partUsageCd;
    }

    @XmlAttribute(name="WFR_FAB_MASK_CONFIG_ID")
    public String getWfrFabMaskConfigId() {
        return wfrFabMaskConfigId;
    }

    public void setWfrFabMaskConfigId(String wfrFabMaskConfigId) {
        this.wfrFabMaskConfigId = wfrFabMaskConfigId;
    }

    @XmlAttribute(name="WFR_TECH_IMPL_ID")
    public String getWfrTechImplId() {
        return wfrTechImplId;
    }

    public void setWfrTechImplId(String wfrTechImplId) {
        this.wfrTechImplId = wfrTechImplId;
    }

    @XmlAttribute(name="WFR_TECH_VARIANT_ID")
    public String getWfrTechVariantId() {
        return wfrTechVariantId;
    }

    public void setWfrTechVariantId(String wfrTechVariantId) {
        this.wfrTechVariantId = wfrTechVariantId;
    }

    @Override
    public String toString() {
        return "DataWarehousePlm{" +
                "corePartNbrConfigId='" + corePartNbrConfigId + '\'' +
                ", dieDesignConfigId='" + dieDesignConfigId + '\'' +
                ", engWfrTechFamId='" + engWfrTechFamId + '\'' +
                ", engWfrTechTypeId='" + engWfrTechTypeId + '\'' +
                ", erpResourceFabCd='" + erpResourceFabCd + '\'' +
                ", erpResourceFabDesc='" + erpResourceFabDesc + '\'' +
                ", fabDieMapId='" + fabDieMapId + '\'' +
                ", fabSiteId='" + fabSiteId + '\'' +
                ", famPlatformGroupType='" + famPlatformGroupType + '\'' +
                ", lfcyclPhase='" + lfcyclPhase + '\'' +
                ", maskSetNoPartVal='" + maskSetNoPartVal + '\'' +
                ", mfgPartId='" + mfgPartId + '\'' +
                ", pal4Cd='" + pal4Cd + '\'' +
                ", partClassCd='" + partClassCd + '\'' +
                ", partDesc='" + partDesc + '\'' +
                ", partId='" + partId + '\'' +
                ", partSubTypeCd='" + partSubTypeCd + '\'' +
                ", partType='" + partType + '\'' +
                ", partUsageCd='" + partUsageCd + '\'' +
                ", wfrFabMaskConfigId='" + wfrFabMaskConfigId + '\'' +
                ", wfrTechImplId='" + wfrTechImplId + '\'' +
                ", wfrTechVariantId='" + wfrTechVariantId + '\'' +
                '}';
    }
}
