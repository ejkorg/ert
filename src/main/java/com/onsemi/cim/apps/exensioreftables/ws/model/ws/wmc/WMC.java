package com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc;

import com.onsemi.cim.apps.dataport.entity.web.WMCEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 *
 * @author fg6zdy
 */

@XmlRootElement(name="Wmc")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WMC {
    
    private String partName;
    private String cmapName;
    private String cmapVersion;
    private Date cmapValidFrom;
    private String cmapFilePath;
    private String positiveX;
    private String positiveY;
    private Double dieSizeX;
    private Double dieSizeY;
    private String flatPosition;
    private Integer refX;
    private Integer refY;
    private Integer refDieCoordX;
    private Integer refDieCoordY;
    private Integer rowCount;
    private Integer columnCount;
    private Integer diameter;
    private Double refDieDistanceX;
    private Double refDieDistanceY;
    private Integer reticleWidth;
    private Integer reticleHeight;
    private String units;
    private String flatType;
    private Double centerX;
    private Double centerY;
    private Integer rowOffset;
    private Integer columnOffset;
    
    private Integer wmapId;
    

    @XmlAttribute(name="partName")
    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @XmlAttribute(name="cmapName")
    public String getCmapName() {
        return cmapName;
    }

    public void setCmapName(String cmapName) {
        this.cmapName = cmapName;
    }

    @XmlAttribute(name="cmapVersion")
    public String getCmapVersion() {
        return cmapVersion;
    }

    public void setCmapVersion(String cmapVersion) {
        this.cmapVersion = cmapVersion;
    }

    @XmlAttribute(name="cmapValidFrom")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getCmapValidFrom() {
        return cmapValidFrom;
    }

    public void setCmapValidFrom(Date cmapValidFrom) {
        this.cmapValidFrom = cmapValidFrom;
    }

    @XmlAttribute(name="cmapFilePath")
    public String getCmapFilePath() {
        return cmapFilePath;
    }

    public void setCmapFilePath(String cmapFilePath) {
        this.cmapFilePath = cmapFilePath;
    }

    @XmlAttribute(name="positiveX")
    public String getPositiveX() {
        return positiveX;
    }

    public void setPositiveX(String positiveX) {
        this.positiveX = positiveX;
    }

    @XmlAttribute(name="positiveY")
    public String getPositiveY() {
        return positiveY;
    }

    public void setPositiveY(String positiveY) {
        this.positiveY = positiveY;
    }

    @XmlAttribute(name="diesizex")
    public Double getDieSizeX() {
        return dieSizeX;
    }

    public void setDieSizeX(Double dieSizeX) {
        this.dieSizeX = dieSizeX;
    }

    @XmlAttribute(name="diesizey")
    public Double getDieSizeY() {
        return dieSizeY;
    }

    public void setDieSizeY(Double dieSizeY) {
        this.dieSizeY = dieSizeY;
    }

    @XmlAttribute(name="flatPosition")
    public String getFlatPosition() {
        return flatPosition;
    }

    public void setFlatPosition(String flatPosition) {
        this.flatPosition = flatPosition;
    }

    @XmlAttribute(name="refX")
    public Integer getRefX() {
        return refX;
    }

    public void setRefX(Integer refX) {
        this.refX = refX;
    }

    @XmlAttribute(name="refY")
    public Integer getRefY() {
        return refY;
    }

    public void setRefY(Integer refY) {
        this.refY = refY;
    }

    @XmlAttribute(name="refDieCoordX")
    public Integer getRefDieCoordX() {
        return refDieCoordX;
    }

    public void setRefDieCoordX(Integer refDieCoorX) {
        this.refDieCoordX = refDieCoorX;
    }

    @XmlAttribute(name="refDieCoordY")
    public Integer getRefDieCoordY() {
        return refDieCoordY;
    }

    public void setRefDieCoordY(Integer refDieCoorY) {
        this.refDieCoordY = refDieCoorY;
    }

    @XmlAttribute(name="rowCount")
    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    @XmlAttribute(name="columnCount")
    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    @XmlAttribute(name="diameter")
    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    @XmlAttribute(name="refDieDistanceX",  required = false)
    public Double getRefDieDistanceX() {
        return refDieDistanceX;
    }

    public void setRefDieDistanceX(Double refDieDistanceX) {
        this.refDieDistanceX = refDieDistanceX;
    }

    @XmlAttribute(name="refDieDistanceY", required = false)
    public Double getRefDieDistanceY() {
        return refDieDistanceY;
    }

    public void setRefDieDistanceY(Double refDieDistanceY) {
        this.refDieDistanceY = refDieDistanceY;
    }

    @XmlAttribute(name="reticleWidth", required = false)
    public Integer getReticleWidth() {
        return reticleWidth;
    }

    public void setReticleWidth(Integer reticleWidth) {
        this.reticleWidth = reticleWidth;
    }

    @XmlAttribute(name="reticleHeight", required = false)
    public Integer getReticleHeight() {
        return reticleHeight;
    }

    public void setReticleHeight(Integer reticleHeight) {
        this.reticleHeight = reticleHeight;
    }

    @XmlAttribute(name="units")
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @XmlAttribute(name="flatType")
    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    @XmlAttribute(name="centerX")
    public Double getCenterX() {
        return centerX;
    }

    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    @XmlAttribute(name="centerY")
    public Double getCenterY() {
        return centerY;
    }

    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    @XmlAttribute(name="rowOffset")
    public Integer getRowOffset() {
        return rowOffset;
    }

    public void setRowOffset(Integer rowOffset) {
        this.rowOffset = rowOffset;
    }

    @XmlAttribute(name="columnOffset")
    public Integer getColumnOffset() {
        return columnOffset;
    }

    public void setColumnOffset(Integer columnOffset) {
        this.columnOffset = columnOffset;
    }

    @XmlAttribute(name="wmapId")
    public Integer getWmapId() {
        return wmapId;
    }

    public void setWmapId(Integer wmapId) {
        this.wmapId = wmapId;
    }
    
    
        
    public WMCEntity toWMCEntity() {
        WMCEntity wmcEntity = new WMCEntity();
        wmcEntity.setPartName(partName);
        wmcEntity.setCmapName(cmapName);
        wmcEntity.setCmapVersion(cmapVersion);
        wmcEntity.setCmapValidFrom(cmapValidFrom);
        wmcEntity.setCmapFilePath(cmapFilePath);

        Short coordQuad = null;

        if (positiveX != null && positiveY != null) {
            if (positiveX.equals("L") && positiveY.equals("D")) {
                coordQuad = 1;
            } else if (positiveX.equals("R") && positiveY.equals("D")) {
                coordQuad = 2;
            } else if (positiveX.equals("R") && positiveY.equals("U")) {
                coordQuad = 3;
            } else if (positiveX.equals("L") && positiveY.equals("U")) {
                coordQuad = 4;
            }
        }
        
        wmcEntity.setCoordQuad(coordQuad);
        wmcEntity.setDieSizeX(dieSizeX);
        wmcEntity.setDieSizeY(dieSizeY);
        wmcEntity.setFlatPosition(flatPosition);
        wmcEntity.setRefX(refX);
        wmcEntity.setRefY(refY);
        wmcEntity.setRefDieCoordX(refDieCoordX);
        wmcEntity.setRefDieCoordY(refDieCoordY);
        wmcEntity.setRowCount(rowCount);
        wmcEntity.setColumnCount(columnCount);
        wmcEntity.setDiameter(diameter);
        wmcEntity.setRefDieDistanceX(refDieDistanceX);
        wmcEntity.setRefDieDistanceY(refDieDistanceY);
        wmcEntity.setReticleHeight(reticleHeight);
        wmcEntity.setReticleWidth(reticleWidth);
        wmcEntity.setCenterX(centerX);
        wmcEntity.setCenterY(centerY);
        wmcEntity.setRowOffset(rowOffset);
        wmcEntity.setColumnOffset(columnOffset);

        return wmcEntity;
    }

    @Override
    public String toString() {
        return "WMC{" + "partName=" + partName + ", cmapName=" + cmapName + ", cmapVersion=" + cmapVersion + ", cmapValidFrom=" + cmapValidFrom + ", cmapFilePath=" + cmapFilePath + ", positiveX=" + positiveX + ", positiveY=" + positiveY + ", dieSizeX=" + dieSizeX + ", dieSizeY=" + dieSizeY + ", flatPosition=" + flatPosition + ", refX=" + refX + ", refY=" + refY + ", refDieCoordX=" + refDieCoordX + ", refDieCoordY=" + refDieCoordY + ", rowCount=" + rowCount + ", columnCount=" + columnCount + ", diameter=" + diameter + ", refDieDistanceX=" + refDieDistanceX + ", refDieDistanceY=" + refDieDistanceY + ", reticleWidth=" + reticleWidth + ", reticleHeight=" + reticleHeight + ", units=" + units + ", flatType=" + flatType + ", centerX=" + centerX + ", centerY=" + centerY + ", rowOffset=" + rowOffset + ", columnOffset=" + columnOffset + ", wmapId=" + wmapId +'}';
    }

}
