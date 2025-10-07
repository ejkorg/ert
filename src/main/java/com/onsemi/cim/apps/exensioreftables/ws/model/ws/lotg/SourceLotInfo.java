package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ffv7xh
 */
@XmlType(propOrder = {
   "lot", "status", "processedNodes", "lots", "deadEnds", "recursionLevels", "detectedIssue"   
})
@XmlRootElement(name="SourceLot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SourceLotInfo {

    private String lot;
    private List<BasicLotInfo> lots;
    private Status status;
    private TraceabilityToken token;

    private String processedNodes;
    private String deadEnds;

    private String searchedLotPart;

    private Integer recursionLevels;
    private Traceability_Issue detectedIssue;

    @XmlAttribute(name="SearchedLot")
    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    @XmlElement(name="Lot")
    public List<BasicLotInfo> getLots() {
        return lots;
    }

    public void setLots(List<BasicLotInfo> lots) {
        this.lots = lots;
    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public TraceabilityToken getToken() {
        return token;
    }

    public void setToken(TraceabilityToken token) {
        this.token = token;
    }

    @XmlAttribute(name="DeadEnds")
    public String getDeadEnds() {
        return deadEnds;
    }

    public void setDeadEnds(String deadEnds) {
        this.deadEnds = deadEnds;
    }

    @XmlAttribute(name="ProcessedNodes")
    public String getProcessedNodes() {
        return processedNodes;
    }

    public void setProcessedNodes(String processedNodes) {
        this.processedNodes = processedNodes;
    }

       @XmlAttribute(name="RecursionLevels")
    public Integer getRecursionLevels() {
        return recursionLevels;
    }

    public void setRecursionLevels(Integer recursionLevels) {
        this.recursionLevels = recursionLevels;
    }

    @XmlAttribute(name="DetectedIssue")
    public Traceability_Issue getDetectedIssue() {
        return detectedIssue;
    }

    public void setDetectedIssue(Traceability_Issue detectedIssue) {
        this.detectedIssue = detectedIssue;
    }

    @XmlAttribute(name="SearchedLotPart")
    public String getSearchedLotPart() {
        return searchedLotPart;
    }

    public void setSearchedLotPart(String searchedLotPart) {
        this.searchedLotPart = searchedLotPart;
    }

    @Override
    public String toString() {
        return "SourceLotInfo{" + "lot=" + lot + ", lots=" + ( lots!=null? Arrays.deepToString(lots.toArray()) : "[]" ) + ", status=" + status + ", token=" + token + ", processedNodes=" + processedNodes + ", deadEnds=" + deadEnds + '\'' + ", searchedLotPart='" + searchedLotPart + '\'' +  ", recursionLevels=" + recursionLevels + ", detectedIssue=" + detectedIssue + '}';
    }

    public enum Traceability_Issue{
        RECURSION_LIMIT_REACHED,
        NONE,
        TIMEWARP,
        BONUSING,
        RMR,
        WEAK_CONNECTION
    }
    
}
