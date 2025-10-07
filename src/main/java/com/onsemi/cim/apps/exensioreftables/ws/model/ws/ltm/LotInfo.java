package com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fg6zdy
 */
@XmlType(propOrder = {
   "metadataInfos", 
   "id",
   "instance",
   "lotGUpdated",
   
   "sourceFab",
   "sourceOrigin",
   "sourceLot",
   "sourcePart",
   "sourceTime",
   
   "reported",
   
   "status",
   "portedToFabLtm",
   
   "lotgLotNumber",
   "altLotNumber",
   "lotNumber",
   "waferPart"
})
@XmlRootElement(name="LotInfo")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LotInfo {

    static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private String status;

    private String lotNumber;
    private String altLotNumber;

    /** src information */
    private Long id;
    private String instance;

    /** src information - may come from MES, CONST, LOTG ... */
    private String sourceFab;
    private String sourceLot;
    private Date sourceTime;
    private String sourceOrigin;
    private String sourcePart;

    /** lotg information */
    private Boolean lotGUpdated;
    private String lotgLotNumber;    
    private Date lotgTime;
    
    /** MES reporting flag */
    private Boolean reported;

    /**  */
    private String portedToFabLtm;
    
    private String waferPart;
    
    /** */
    public static final String Status_Missing_Lot = "MISSING_LOT";
    public static final String Status_FOUND = "FOUND";
    public static final String Status_Missing_Ltm = "MISSING_LTM";

    private Map<String, Object> property = new HashMap<>();
    private List<MetadataInfo> metadataInfos;

    @XmlAttribute(name="PortedToFabLtm")
    public String getPortedToFabLtm() {
        return portedToFabLtm;
    }

    public void setPortedToFabLtm(String portedToFabLtm) {
        this.portedToFabLtm = portedToFabLtm.toUpperCase();
    }

    @XmlAttribute(name="LotgTime")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getLotgTime() {
        return lotgTime;
    }

    public void setLotgTime(Date lotgTime) {
        this.lotgTime = lotgTime;
    }

    @XmlTransient
    public Map<String, Object> getProperty() {
        return property;
    }

    @XmlAttribute(name="AltLot")
    public String getAltLotNumber() {
        return altLotNumber;
    }

    @XmlAttribute(name="Ltm")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance.toUpperCase();
    }

    public void setAltLotNumber(String altLotNumber) {
        this.altLotNumber = altLotNumber.toUpperCase();
    }

    @XmlAttribute(name="LotGLot")
    public String getLotgLotNumber() {
        return lotgLotNumber;
    }

    public void setLotgLotNumber(String lotgLotNumber) {
        this.lotgLotNumber = lotgLotNumber.toUpperCase();
    }

    @XmlAttribute(name="LotGUpdated")
    public Boolean isLotGUpdated() {
        return lotGUpdated;
    }

    public void setLotGUpdated(Boolean lotGUpdated) {
        this.lotGUpdated = lotGUpdated;
    }

    @XmlAttribute(name="Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }

    @XmlAttribute(name="Reported")
    public Boolean isReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    @XmlElement(name="Metadata")
    public List<MetadataInfo> getMetadataInfos() {
        return metadataInfos;
    }

    public void setMetadataInfos(List<MetadataInfo> metadataInfos) {
        this.metadataInfos = metadataInfos;
    }
    
    @XmlAttribute(name="Lot")
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber.toUpperCase();
    }

    @XmlAttribute(name="SourceFab")
    public String getSourceFab() {
        return sourceFab;
    }

    public void setSourceFab(String sourceFab) {
        this.sourceFab = sourceFab.toUpperCase();
    }

    @XmlAttribute(name="SourceLot")
    public String getSourceLot() {
        return sourceLot;
    }

    public void setSourceLot(String sourceLot) {
        this.sourceLot = sourceLot.toUpperCase();
    }

    @XmlAttribute(name="SourceTime")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(Date sourceTime) {
        this.sourceTime = sourceTime;
    }

    @XmlAttribute(name="Id")
    //@XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="SourceOrigin")
    public String getSourceOrigin() {
        return sourceOrigin;
    }

    public void setSourceOrigin(String sourceOrigin) {
        this.sourceOrigin = sourceOrigin.toUpperCase();
    }

    @XmlAttribute(name="SourcePart")
    public String getSourcePart() {
        return sourcePart;
    }

    public void setSourcePart(String sourcePart) {
        this.sourcePart = sourcePart.toUpperCase();
    }
    
    @XmlAttribute(name="WaferPart")
    public String getWaferPart() {
        return waferPart;
    }

    public void setWaferPart(String waferPart){
        this.waferPart = waferPart;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);

        String tmp = "";
        if(getProperty() != null && getProperty().size() > 0) {
            for(String key: getProperty().keySet()) {
                tmp = tmp.concat(key).concat(" = ").concat(String.valueOf(getProperty().get(key))).concat(", ");
            }
        }

        return "LotInfo{"
            + " id=" + id
            + ", lotNumber=" + lotNumber

            + ", sourceFab=" + sourceFab
            + ", sourceLot=" + sourceLot
            + ", sourceTime=" + ((sourceTime != null)? sdf.format(sourceTime): "N/A")
            + ", sourceOrigin=" + sourceOrigin
            + ", sourcePart=" + sourcePart

            + ", lotGUpdated=" + lotGUpdated
            + ", lotgLotNumber=" + lotgLotNumber
            + ", waferPart=" + waferPart

            + ", altLotNumber=" + altLotNumber
            + ", reported=" + reported

            + ", status=" + status
            + ", instance=" + instance
            + ", metadatas[" + ((metadataInfos != null)? String.valueOf(metadataInfos.size()): "-1") + "]"
            + ", properties => " + tmp

            + '}';
    }
}
