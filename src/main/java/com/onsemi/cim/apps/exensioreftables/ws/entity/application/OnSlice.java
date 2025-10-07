package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnSliceDto;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * @author fg7ngj - Outericky, 5/26/2022
 */

@Entity(name = "ON_SLICE")
public class OnSlice extends ApplicationEntity {

    @Column(name = "SLICE") private String slice;
    @Column(name = "PUCK_ID") private String puckId;
    @Column(name = "RUN_ID") private String runId;
    @Column(name = "SLICE_SOURCE_LOT") private String sliceSourceLot;
    @Column(name = "START_LOT") private String startLot;
    @Column(name = "FAB_WAFER_ID") private String fabWaferId;
    @Column(name = "GLOBAL_WAFER_ID") private String globalWaferId;
    @Column(name = "FAB_SOURCE_LOT") private String fabSourceLot;
    @Column(name = "SLICE_START_TIME") private Date sliceStartTime;
    @Column(name = "SLICE_PARTNAME") private String slicePartname;
    @Column(name = "SLICE_LOTTYPE") private String sliceLottype;
    @Column(name = "SLICE_SUPPLIERID") private String sliceSuplierid;
    @Column(name = "PUCK_HEIGHT") private Long puckHeight;
    @Column(name = "INSERT_TIME") private Date insertTime;



    public OnSlice() {
    }

    public OnSlice(OnSliceDto onSliceDto) {

        //TODO - (logic copied other entity objects, cheange in all cases) when calling inserting, the service stores values as uppercase into DB but returns dto with original ones.

        this.slice = StringUtils.upperCase(onSliceDto.getSlice());
        this.puckId = StringUtils.upperCase(onSliceDto.getPuckId());
        this.runId = StringUtils.upperCase(onSliceDto.getRunId());
        this.sliceSourceLot = StringUtils.upperCase(onSliceDto.getSliceSourceLot());
        this.startLot = StringUtils.upperCase(onSliceDto.getStartLot());
        this.fabWaferId = StringUtils.upperCase(onSliceDto.getFabWaferId());
        this.globalWaferId = StringUtils.upperCase(onSliceDto.getGlobalWaferId());
        this.fabSourceLot = StringUtils.upperCase(onSliceDto.getFabSourceLot());
        this.sliceStartTime = DateUtils.convertStringToDate(onSliceDto.getSliceStartTime());
        this.slicePartname = StringUtils.upperCase(onSliceDto.getSlicePartname());
        this.sliceLottype = StringUtils.upperCase(onSliceDto.getSliceLottype());
        this.sliceSuplierid = StringUtils.upperCase(onSliceDto.getSliceSuplierid());
        this.puckHeight = onSliceDto.getPuckHeight();
        this.insertTime = DateUtils.convertStringToDate(onSliceDto.getInsertTime());
    }

    public String getSlice() {
        return slice;
    }

    public void setSlice(String slice) {
        this.slice = slice;
    }

    public String getPuckId() {
        return puckId;
    }

    public void setPuckId(String puckId) {
        this.puckId = puckId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getSliceSourceLot() {
        return sliceSourceLot;
    }

    public void setSliceSourceLot(String sliceSourceLot) {
        this.sliceSourceLot = sliceSourceLot;
    }

    public String getStartLot() {
        return startLot;
    }

    public void setStartLot(String startLot) {
        this.startLot = startLot;
    }

    public String getFabWaferId() {
        return fabWaferId;
    }

    public void setFabWaferId(String fabWaferId) {
        this.fabWaferId = fabWaferId;
    }

    public String getGlobalWaferId() {
        return globalWaferId;
    }

    public void setGlobalWaferId(String globalWaferId) {
        this.globalWaferId = globalWaferId;
    }

    public String getFabSourceLot() {
        return fabSourceLot;
    }

    public void setFabSourceLot(String fabSourceLot) {
        this.fabSourceLot = fabSourceLot;
    }

    public Date getSliceStartTime() {
        return sliceStartTime;
    }

    public void setSliceStartTime(Date sliceStartTime) {
        this.sliceStartTime = sliceStartTime;
    }

    public String getSlicePartname() {
        return slicePartname;
    }

    public void setSlicePartname(String slicePartname) {
        this.slicePartname = slicePartname;
    }

    public String getSliceLottype() {
        return sliceLottype;
    }

    public void setSliceLottype(String sliceLottype) {
        this.sliceLottype = sliceLottype;
    }

    public String getSliceSuplierid() {
        return sliceSuplierid;
    }

    public void setSliceSuplierid(String sliceSuplierid) {
        this.sliceSuplierid = sliceSuplierid;
    }

    public Long getPuckHeight() {
        return puckHeight;
    }

    public void setPuckHeight(Long puckHeight) {
        this.puckHeight = puckHeight;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "OnSlice{" +
                "slice='" + slice + '\'' +
                ", puckId='" + puckId + '\'' +
                ", runId='" + runId + '\'' +
                ", sliceSourceLot='" + sliceSourceLot + '\'' +
                ", startLot='" + startLot + '\'' +
                ", fabWaferId='" + fabWaferId + '\'' +
                ", globalWaferId='" + globalWaferId + '\'' +
                ", fabSourceLot='" + fabSourceLot + '\'' +
                ", sliceStartTime=" + sliceStartTime +
                ", slicePartname='" + slicePartname + '\'' +
                ", sliceLottype='" + sliceLottype + '\'' +
                ", sliceSuplierid='" + sliceSuplierid + '\'' +
                ", puckHeight=" + puckHeight +
                ", insertTime=" + insertTime +
                '}';
    }
}
