package com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fg6zdy
 */
@XmlType(propOrder = {
    "firstDieXOffset",
    "refDieXAdjust",
    "firstDieYOffset",
    "refDieYAdjust",
    "instance",
    "testerLocation",
    "testerDataType",
    "diceOnCenterRow",
    "diceOnCenterCol",
    "diceOnCenterRowWithoutSkips",
    "diceOnCenterColWithoutSkips",
    "realCenterRow",
    "realCenterCol",
    "skipsOnCenterRowFromLeft",
    "skipsOnCenterColFromTop",
    "positions",
    "cMapName",    
    "rowOffset",
    "colOffset",
    "rows",
    "cols",
    "retType",
    "reticleInfo",
    "wfrCenterY",
    "wfrCenterX",
    "wfrRowCenter",
    "wfrColCenter",
    "coordRefDieY",
    "coordRefDieX",
    "colCount",
    "rowCount",
    "refDieY",
    "refDieX",
    "rowMin",
    "colMin",
    "units",
    "cMapLastModified",
    "stage",
    "dieHeight",
    "dieWidth",
    "flatType",
    "flatPosition",
    "coordOrientation",
    "waferSize",
    "status",
    "device",
    "id"
})
@XmlRootElement(name="WaferMapConfig")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WaferMapConfiguration {

    private Long id;
    private String device;

    private String instance;

    private String status;

    //* reticle
    private Integer cols;
    private Integer rows;
    private Integer rowOffset;
    private Integer colOffset;
    private String  retType;

    //* wafer map
    private Double dieWidth;
    private Double dieHeight;
    private String flatPosition;
    private String flatType;

    private Integer waferSize;
    private Integer coordOrientation;

    private String stage;

    //* ref die
    private Integer refDieX;
    private Integer refDieY;

    private Integer coordRefDieX;
    private Integer coordRefDieY;

    //* wafer center
    private Double wfrColCenter;
    private Double wfrRowCenter;

    private Double wfrCenterX;
    private Double wfrCenterY;

    private Boolean reticleInfo;

    private Date created;
    private Date cMapLastModified;

    private String units;

    private String cMapName;

    private Integer rowMin;
    private Integer colMin;

    private Integer rowCount;
    private Integer colCount;

    private Integer diceOnCenterRow;
    private Integer diceOnCenterCol;

    private Integer diceOnCenterRowWithoutSkips;
    private Integer diceOnCenterColWithoutSkips;

    private Integer realCenterRow;
    private Integer realCenterCol;

    private Integer skipsOnCenterRowFromLeft;
    private Integer skipsOnCenterColFromTop;

    private String testerLocation;
    private String testerDataType;

    private Integer firstDieXOffset;
    private Integer refDieXAdjust;
    private Integer firstDieYOffset;
    private Integer refDieYAdjust;
    

    public static final String Status_Missing_Setup = "MISSING_SETUP";
    public static final String Status_Found = "FOUND";
    public static final String GENERATOR_ERROR = "GENERATOR_ERROR";
    public static final String WS_ERROR_INPUT = "WS_ERROR_INPUT";
    public static final String WS_ERROR = "WS_ERROR";

    @XmlAttribute(name="instance")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @XmlAttribute(name="TesterDataType")
    public String getTesterDataType() {
        return testerDataType;
    }

    public void setTesterDataType(String testerDataType) {
        this.testerDataType = testerDataType;
    }

    @XmlAttribute(name="TesterLocation")
    public String getTesterLocation() {
        return testerLocation;
    }

    public void setTesterLocation(String testerLocation) {
        this.testerLocation = testerLocation;
    }



    @XmlAttribute(name="DiceOnCenterCol")
    public Integer getDiceOnCenterCol() {
        return diceOnCenterCol;
    }

    public void setDiceOnCenterCol(Integer diceOnCenterCol) {
        this.diceOnCenterCol = diceOnCenterCol;
    }

    @XmlAttribute(name="DiceOnCenterColWithoutSkips")
    public Integer getDiceOnCenterColWithoutSkips() {
        return diceOnCenterColWithoutSkips;
    }

    public void setDiceOnCenterColWithoutSkips(Integer diceOnCenterColWithoutSkips) {
        this.diceOnCenterColWithoutSkips = diceOnCenterColWithoutSkips;
    }

    @XmlAttribute(name="DiceOnCenterRow")
    public Integer getDiceOnCenterRow() {
        return diceOnCenterRow;
    }

    public void setDiceOnCenterRow(Integer diceOnCenterRow) {
        this.diceOnCenterRow = diceOnCenterRow;
    }

    @XmlAttribute(name="DiceOnCenterRowWithoutSkips")
    public Integer getDiceOnCenterRowWithoutSkips() {
        return diceOnCenterRowWithoutSkips;
    }

    public void setDiceOnCenterRowWithoutSkips(Integer diceOnCenterRowWithoutSkips) {
        this.diceOnCenterRowWithoutSkips = diceOnCenterRowWithoutSkips;
    }

    @XmlAttribute(name="RealCenterCol")
    public Integer getRealCenterCol() {
        return realCenterCol;
    }

    public void setRealCenterCol(Integer realCenterCol) {
        this.realCenterCol = realCenterCol;
    }

    @XmlAttribute(name="RealCenterRow")
    public Integer getRealCenterRow() {
        return realCenterRow;
    }

    public void setRealCenterRow(Integer realCenterRow) {
        this.realCenterRow = realCenterRow;
    }

    @XmlAttribute(name="SkipsOnCenterColFromTop")
    public Integer getSkipsOnCenterColFromTop() {
        return skipsOnCenterColFromTop;
    }

    public void setSkipsOnCenterColFromTop(Integer skipsOnCenterColFromTop) {
        this.skipsOnCenterColFromTop = skipsOnCenterColFromTop;
    }

    @XmlAttribute(name="SkipsOnCenterRowFromLeft")
    public Integer getSkipsOnCenterRowFromLeft() {
        return skipsOnCenterRowFromLeft;
    }

    public void setSkipsOnCenterRowFromLeft(Integer skipsOnCenterRowFromLeft) {
        this.skipsOnCenterRowFromLeft = skipsOnCenterRowFromLeft;
    }

    @XmlAttribute(name="ColCount")
    public Integer getColCount() {
        return colCount;
    }

    public void setColCount(Integer colCount) {
        this.colCount = colCount;
    }

    @XmlAttribute(name="RowCount")
    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }



    @XmlAttribute(name="Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //@XmlTransient
    @XmlAttribute(name="CMapName")
    public String getcMapName() {
        return cMapName;
    }

    public void setcMapName(String cMapName) {
        this.cMapName = cMapName;
    }

    @XmlAttribute(name="LastModified")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getcMapLastModified() {
        return cMapLastModified;
    }

    @XmlAttribute(name="ColMin")
    public Integer getColMin() {
        return colMin;
    }

    public void setColMin(Integer colMin) {
        this.colMin = colMin;
    }

    @XmlAttribute(name="RowMin")
    public Integer getRowMin() {
        return rowMin;
    }

    public void setRowMin(Integer rowMin) {
        this.rowMin = rowMin;
    }

    public void setcMapLastModified(Date cMapLastModified) {
        this.cMapLastModified = cMapLastModified;
    }

    @XmlAttribute(name="Units")
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    
    private List<ReticlePosition> positions;

    @XmlAttribute(name="CoordOrientation")
    public Integer getCoordOrientation() {
        return coordOrientation;
    }

    public void setCoordOrientation(Integer coordOrientation) {
        this.coordOrientation = coordOrientation;
    }

    @XmlAttribute(name="CoordRefdieX")
    public Integer getCoordRefDieX() {
        return coordRefDieX;
    }

    public void setCoordRefDieX(Integer coordRefDieX) {
        this.coordRefDieX = coordRefDieX;
    }

    @XmlAttribute(name="CoordRefdieY")
    public Integer getCoordRefDieY() {
        return coordRefDieY;
    }

    public void setCoordRefDieY(Integer coordRefDieY) {
        this.coordRefDieY = coordRefDieY;
    }

    @XmlAttribute(name="DieHeight")
    public Double getDieHeight() {
        return dieHeight;
    }

    public void setDieHeight(Double dieHeight) {
        this.dieHeight = dieHeight;
    }

    @XmlAttribute(name="DieWidth")
    public Double getDieWidth() {
        return dieWidth;
    }

    public void setDieWidth(Double dieWidth) {
        this.dieWidth = dieWidth;
    }

    @XmlAttribute(name="FlatPosition")
    public String getFlatPosition() {
        return flatPosition;
    }

    public void setFlatPosition(String flatPosition) {
        this.flatPosition = flatPosition;
    }

    @XmlAttribute(name="FlatType")
    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    @XmlAttribute(name="RefdieX")
    public Integer getRefDieX() {
        return refDieX;
    }

    public void setRefDieX(Integer refDieX) {
        this.refDieX = refDieX;
    }

    @XmlAttribute(name="RefdieY")
    public Integer getRefDieY() {
        return refDieY;
    }

    public void setRefDieY(Integer refDieY) {
        this.refDieY = refDieY;
    }

    @XmlAttribute(name="ReticleType")
    public String getRetType() {
        return retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
    }

    @XmlAttribute(name="HasReticleInfo")
    public Boolean isReticleInfo() {
        return reticleInfo;
    }

    public void setReticleInfo(Boolean reticleInfo) {
        this.reticleInfo = reticleInfo;
    }

    @XmlAttribute(name="Stage")
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @XmlAttribute(name="WaferSize")
    public Integer getWaferSize() {
        return waferSize;
    }

    public void setWaferSize(Integer waferSize) {
        this.waferSize = waferSize;
    }

    @XmlAttribute(name="WaferCenterX")
    public Double getWfrCenterX() {
        return wfrCenterX;
    }

    public void setWfrCenterX(Double wfrCenterX) {
        this.wfrCenterX = wfrCenterX;
    }

    @XmlAttribute(name="WaferCenterY")
    public Double getWfrCenterY() {
        return wfrCenterY;
    }

    public void setWfrCenterY(Double wfrCenterY) {
        this.wfrCenterY = wfrCenterY;
    }

    @XmlAttribute(name="WaferCenterCol")
    public Double getWfrColCenter() {
        return wfrColCenter;
    }

    public void setWfrColCenter(Double wfrColCenter) {
        this.wfrColCenter = wfrColCenter;
    }

    @XmlAttribute(name="WaferCenterRow")
    public Double getWfrRowCenter() {
        return wfrRowCenter;
    }

    public void setWfrRowCenter(Double wfrRowCenter) {
        this.wfrRowCenter = wfrRowCenter;
    }

    @XmlAttribute(name="ReticleColOffset")
    public Integer getColOffset() {
        return colOffset;
    }

    public void setColOffset(Integer colOffset) {
        this.colOffset = colOffset;
    }

    @XmlAttribute(name="ReticleRowOffset")
    public Integer getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(Integer rowOffset) {
        this.rowOffset = rowOffset;
    }

    @XmlTransient
    //@XmlAttribute(name="Created")
    //@XmlJavaTypeAdapter(DateAdapter.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlAttribute(name="ReticleCols")
    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    @XmlAttribute(name="Device")
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    //@XmlTransient
    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="Reticles")
    public List<ReticlePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<ReticlePosition> positions) {
        this.positions = positions;
    }
 

    @XmlAttribute(name="ReticleRows")
    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @XmlAttribute(name="FirstDieXOffset")
    public Integer getFirstDieXOffset() {
        return firstDieXOffset;
    }

    public void setFirstDieXOffset(Integer firstDieXOffset) {
        this.firstDieXOffset = firstDieXOffset;
    }

    @XmlAttribute(name="FirstDieYOffset")
    public Integer getFirstDieYOffset() {
        return firstDieYOffset;
    }

    public void setFirstDieYOffset(Integer firstDieYOffset) {
        this.firstDieYOffset = firstDieYOffset;
    }

    @XmlAttribute(name="RefDieXAdjust")
    public Integer getRefDieXAdjust() {
        return refDieXAdjust;
    }

    public void setRefDieXAdjust(Integer refDieXAdjust) {
        this.refDieXAdjust = refDieXAdjust;
    }

    @XmlAttribute(name="RefDieYAdjust")
    public Integer getRefDieYAdjust() {
        return refDieYAdjust;
    }

    public void setRefDieYAdjust(Integer refDieYAdjust) {
        this.refDieYAdjust = refDieYAdjust;
    }

    @Override
    public String toString() {
        return "WaferMapConfiguration{" + "id=" + id + ", device=" + device + ", instance=" + instance + ", status=" + status + ", cols=" + cols + ", rows=" + rows + ", rowOffset=" + rowOffset + ", colOffset=" + colOffset + ", retType=" + retType + ", dieWidth=" + dieWidth + ", dieHeight=" + dieHeight + ", flatPosition=" + flatPosition + ", flatType=" + flatType + ", waferSize=" + waferSize + ", coordOrientation=" + coordOrientation + ", stage=" + stage + ", refDieX=" + refDieX + ", refDieY=" + refDieY + ", coordRefDieX=" + coordRefDieX + ", coordRefDieY=" + coordRefDieY + ", wfrColCenter=" + wfrColCenter + ", wfrRowCenter=" + wfrRowCenter + ", wfrCenterX=" + wfrCenterX + ", wfrCenterY=" + wfrCenterY + ", reticleInfo=" + reticleInfo + ", created=" + created + ", cMapLastModified=" + cMapLastModified + ", units=" + units + ", cMapName=" + cMapName + ", rowMin=" + rowMin + ", colMin=" + colMin + ", rowCount=" + rowCount + ", colCount=" + colCount + ", diceOnCenterRow=" + diceOnCenterRow + ", diceOnCenterCol=" + diceOnCenterCol + ", diceOnCenterRowWithoutSkips=" + diceOnCenterRowWithoutSkips + ", diceOnCenterColWithoutSkips=" + diceOnCenterColWithoutSkips + ", realCenterRow=" + realCenterRow + ", realCenterCol=" + realCenterCol + ", skipsOnCenterRowFromLeft=" + skipsOnCenterRowFromLeft + ", skipsOnCenterColFromTop=" + skipsOnCenterColFromTop + ", testerLocation=" + testerLocation + ", testerDataType=" + testerDataType + ", firstDieXOffset=" + firstDieXOffset + ", refDieXAdjust=" + refDieXAdjust + ", firstDieYOffset=" + firstDieYOffset + ", refDieYAdjust=" + refDieYAdjust + ", positions=" + positions + '}';
    }

    

    @Override public WaferMapConfiguration clone() {

        WaferMapConfiguration tmp = new WaferMapConfiguration();
        tmp.setColCount(colCount);
        tmp.setColMin(colMin);
        tmp.setColOffset(colOffset);
        tmp.setCols(cols);
        tmp.setCoordOrientation(coordOrientation);
        tmp.setCoordRefDieX(coordRefDieX);
        tmp.setCoordRefDieY(coordRefDieY);
        tmp.setCreated(created);
        tmp.setDevice(device);
        tmp.setDieHeight(dieHeight);
        tmp.setDieWidth(dieWidth);
        tmp.setFlatPosition(flatPosition);
        tmp.setFlatType(flatType);
        tmp.setPositions(positions);
        tmp.setRefDieX(refDieX);
        tmp.setRefDieY(refDieY);
        tmp.setRetType(retType);
        tmp.setReticleInfo(reticleInfo);
        tmp.setRowCount(rowCount);
        tmp.setRowMin(rowMin);
        tmp.setRowOffset(rowOffset);
        tmp.setRows(rows);
        tmp.setStage(stage);
        tmp.setStatus(status);
        tmp.setUnits(units);
        tmp.setWaferSize(waferSize);
        tmp.setWfrCenterX(wfrCenterX);
        tmp.setWfrCenterY(wfrCenterY);
        tmp.setWfrColCenter(wfrColCenter);
        tmp.setWfrRowCenter(wfrRowCenter);
        tmp.setcMapLastModified(cMapLastModified);
        tmp.setcMapName(cMapName);
        tmp.setDiceOnCenterRow(diceOnCenterRow);
        tmp.setDiceOnCenterCol(diceOnCenterCol);
        tmp.setDiceOnCenterRowWithoutSkips(diceOnCenterRowWithoutSkips);
        tmp.setDiceOnCenterColWithoutSkips(diceOnCenterColWithoutSkips);
        tmp.setRealCenterRow(realCenterRow);
        tmp.setRealCenterCol(realCenterCol);
        tmp.setSkipsOnCenterRowFromLeft(skipsOnCenterRowFromLeft);
        tmp.setSkipsOnCenterColFromTop(skipsOnCenterColFromTop);
        tmp.setFirstDieXOffset(firstDieXOffset);
        tmp.setFirstDieYOffset(firstDieYOffset);
        tmp.setRefDieXAdjust(refDieXAdjust);
        tmp.setRefDieYAdjust(refDieYAdjust);
        
        return tmp;
    }
}
