package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is helper token holds necessary information about processing.
 * @author ffv7xh
 */
@XmlRootElement(name="Token")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TraceabilityToken {

    private static final Logger log = LoggerFactory.getLogger(TraceabilityToken.class);

    private PartTypes partType;
    private int levelCounter;
    private boolean increaseLevel;
    private boolean ignoreUnknown;    
    private int traceabilityCounter;
    private List<Traceability> lots;
    private Set<TraceabilityAdapter> notReachedLots;
    private List<String> lotList = new ArrayList<>();

    private TraceabilityAdapter traceabilityAdapter;

    @XmlTransient
    public TraceabilityAdapter getTraceabilityAdapter() {
        return traceabilityAdapter;
    }

    public void setTraceabilityAdapter(TraceabilityAdapter traceabilityAdapter) {
        this.traceabilityAdapter = traceabilityAdapter;
    }

    @XmlTransient
    public PartTypes getPartType() {
        return partType;
    }

    public void setPartType(PartTypes partType) {
        this.partType = partType;
    }

    /**
     * It holds each already searched node. It helps avoid multiple search
     * on same sub tree. Full lot id is stored here (with lot class).
     */
    private TraceabilityCache searchedCache;

    @XmlAttribute(name="ProcessedNodeCount")
    public int getTraceabilityCounter(){
        return traceabilityCounter;
    }

    @XmlElement(name="DeadEndLots")
    public String getDeadEndLots(){
        StringBuilder buf = new StringBuilder();
        for(TraceabilityAdapter a: getNotReachedLots()){
                buf.append(a.getQualifiedLotNumber());
                buf.append(" ");
        }
        return buf.toString();
    }

    @XmlTransient
    public boolean isIncreaseLevel() {
        return increaseLevel;
    }
    public void setIncreaseLevel(boolean increaseLevel) {
        this.increaseLevel = increaseLevel;
    }        
    
    public void increaseLevelCounter(){
        if(isIncreaseLevel()){
            levelCounter++;
            log.debug("Level was increased to " + levelCounter);
        } else {
            setIncreaseLevel(true);
            log.debug("Level counter increase was enabled.");
        }
    }

    @XmlTransient
    public List<Traceability> getLots() {
        return lots;
    }
    
    public void setLots(List<Traceability> lots) {
        this.lots = lots;
    }

    public TraceabilityToken() {
        searchedCache = new TraceabilityCache();
        lots = new ArrayList<>();
        notReachedLots = new HashSet<>();
        traceabilityCounter = 0;
        levelCounter = 0;
    }

    @XmlTransient
    public Set<TraceabilityAdapter> getNotReachedLots() {
        return notReachedLots;
    }
    
    public void setNotReachedLots(Set<TraceabilityAdapter> notReachedLots) {
        this.notReachedLots = notReachedLots;
    }

    public void increaseTraceabilityCounter(){
        traceabilityCounter++;
    }

    @XmlAttribute(name="Levels")
    public int getLevelCounter() {
        return levelCounter;
    }

    public void addTraceability(Traceability t, String qualifiedLotNumber){
        if(!lotList.contains(qualifiedLotNumber)){
            lotList.add(qualifiedLotNumber);
            getLots().add(t);
        } else {
            log.debug("replacing traceability: ");
            //* store only last occurance
            List<Traceability> tmp = getLots();
            TraceabilityAdapter tmpAdapter;
            TraceabilityAdapter tmpAdapter2 = getTraceabilityAdapter().copy(t);

            for(int i = 0; i < tmp.size(); i++){
                if(qualifiedLotNumber.equals(tmp.get(i).getQualifiedLotName())){                    
                    tmpAdapter = getTraceabilityAdapter().copy(tmp.get(i));

                    if (tmpAdapter.getPartType() != getPartType()){
                        tmp.set(i, t);
                        log.debug("traceability replaced.");
                    } else {
                        log.debug("traceability wasn't replaced because already existing traceability has requested part type.");
                    }
                }
            }
        }
    }

    public boolean containsTraceability(Traceability tr){
        return getLots().contains(tr);
    }

    @XmlTransient
    public TraceabilityCache getSearchedCache() {
        return searchedCache;
    }

    public void setSearchedCache(TraceabilityCache searchedCache) {
        this.searchedCache = searchedCache;
    }

    @XmlAttribute(name="ignoreUnknown")
    public boolean isIgnoreUnknown() {
        return ignoreUnknown;
    }

    public void setIgnoreUnknown(boolean ignoreUnknown) {
        this.ignoreUnknown = ignoreUnknown;
    }
}