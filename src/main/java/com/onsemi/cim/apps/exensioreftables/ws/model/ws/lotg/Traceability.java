package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DTO represends one recort from 'GENO_SRC_TGT_XREF' main LotG recursion table.
 * @author ffv7xh
 */

@XmlType(propOrder = {
    "transaction", "parentTransaction", "toBank", "fromBank",
    "part", "partType", "lotNumber", "lotClass",
    "parentPart", "parentPartType", "parentLotNumber", "parentLotClass",
    "level", "parentRefId", "refId"
})

@XmlRootElement(name="Traceability")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Traceability {

    @XmlTransient
    private static SimpleDateFormat dfTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");

    private String lotNumber;
    private String lotClass;
    private String part;
    private String partType;
    private Date transaction;

    private String parentLotNumber;
    private String parentLotClass;
    private String parentPart;
    private String parentPartType;
    private Date parentTransaction;

    private String fromBank;
    private String toBank;

    private int refId;
    private int parentRefId;
    private int level;

    private boolean stoppedBecauseOfOrionBank;

    public static SimpleDateFormat getDfTime() {
        return dfTime;
    }

    public static void setDfTime(SimpleDateFormat dfTime) {
        Traceability.dfTime = dfTime;
    }

    @XmlTransient
    public boolean isStoppedBecauseOfOrionBank() {
        return stoppedBecauseOfOrionBank;
    }

    public void setStoppedBecauseOfOrionBank(boolean stoppedBecauseOfOrionBank) {
        this.stoppedBecauseOfOrionBank = stoppedBecauseOfOrionBank;
    }
    
    public Traceability() {
    }

    @XmlAttribute(name="FromBank")
    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    @XmlAttribute(name="LotClass")
    public String getLotClass() {
        return lotClass;
    }

    public void setLotClass(String lotClass) {
        this.lotClass = lotClass;
    }

    @XmlAttribute(name="LotNumber")
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    @XmlAttribute(name="ParentLotClass")
    public String getParentLotClass() {
        return parentLotClass;
    }

    public void setParentLotClass(String parentLotClass) {
        this.parentLotClass = parentLotClass;
    }

    @XmlAttribute(name="ParentLotNumber")
    public String getParentLotNumber() {
        return parentLotNumber;
    }

    public void setParentLotNumber(String parentLotNumber) {
        this.parentLotNumber = parentLotNumber;
    }

    @XmlAttribute(name="ParentPart")
    public String getParentPart() {
        return parentPart;
    }

    public void setParentPart(String parentPart) {
        this.parentPart = parentPart;
    }

    @XmlAttribute(name="ParentPartType")
    public String getParentPartType() {
        return parentPartType;
    }

    public void setParentPartType(String parentPartType) {
        this.parentPartType = parentPartType;
    }

    @XmlAttribute(name="ParentTransaction")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(Date parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    @XmlAttribute(name="Part")
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @XmlAttribute(name="PartType")
    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @XmlAttribute(name="ToBank")
    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    @XmlAttribute(name="Transaction")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getTransaction() {
        return transaction;
    }

    public void setTransaction(Date transaction) {
        this.transaction = transaction;
    }

    @XmlTransient
    public String getQualifiedLotName(){
        return parentLotClass + parentLotNumber;
    }

    @XmlAttribute(name="Parent")
    public int getParentRefId() {
        return parentRefId;
    }

    public void setParentRefId(int parentRefId) {
        this.parentRefId = parentRefId;
    }

    @XmlAttribute(name="Id")
    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    @XmlAttribute(name="Level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Traceability && getQualifiedLotName().equals(((Traceability) obj).getQualifiedLotName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.lotNumber != null ? this.lotNumber.hashCode() : 0);
        hash = 97 * hash + (this.lotClass != null ? this.lotClass.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("Traceability[");

        buff.append(getParentLotClass());
        buff.append(",");
        buff.append(getParentLotNumber());
        buff.append(",");
        buff.append(getParentPart());
        buff.append(",");
        buff.append(getParentPartType());
        buff.append(",");
        if(getParentTransaction()!=null){
            buff.append(dfTime.format(getParentTransaction()));
        }
        buff.append(" | ");

        buff.append(getLotClass());
        buff.append(",");
        buff.append(getLotNumber());
        buff.append(",");
        buff.append(getPart());
        buff.append(",");
        buff.append(getPartType());
        buff.append(",");
        
        if(getTransaction()!=null){
            buff.append(dfTime.format(getTransaction()));
        }

        buff.append("; ");
        buff.append(getFromBank());
        buff.append("=>");
        buff.append(getToBank());
        buff.append("]");
        return buff.toString();
    }
}
