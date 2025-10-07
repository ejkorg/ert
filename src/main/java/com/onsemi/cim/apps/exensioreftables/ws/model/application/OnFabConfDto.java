package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.LotIdForOnScribeType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnFabConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ScribeResultType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnFabConf")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnFabConfDto {

    private Long id;
    private String foundryFab;
    private String dataType;
    private String ltmUrl;
    private String wmcUrl;
    private String vid2ScribeUrl;
    private String scribe2VidUrl;
    private ScribeResultType scribeResultType;
    private boolean onScribeWaferIdEqualsScribeId;
    private Status status;
    private LotIdForOnScribeType lotIdForOnScribeType;
    private String waferIdCreationPattern;
    private String sourceLotAdjustmentPattern;
    private boolean secondLotgQuery;
    private String matchupUrl;
    private String lotidForLotGWS;
    private String lotidForLTM;

    public OnFabConfDto() {
    }

    public OnFabConfDto(OnFabConf onFabConf) {
        id = onFabConf.getId();
        foundryFab = onFabConf.getFoundryFab();
        dataType = onFabConf.getDataType();
        ltmUrl = onFabConf.getLtmUrl();
        wmcUrl = onFabConf.getWmcUrl();
        vid2ScribeUrl = onFabConf.getVid2ScribeUrl();
        scribe2VidUrl = onFabConf.getScribe2VidUrl();
        scribeResultType = onFabConf.getScribeResultType();
        status = Status.FOUND;
        lotIdForOnScribeType = onFabConf.getLotIdForOnScribeType();
        onScribeWaferIdEqualsScribeId = onFabConf.isOnScribeWaferIdEqualsScribeId();
        waferIdCreationPattern = onFabConf.getWaferIdCreationPattern();
        sourceLotAdjustmentPattern = onFabConf.getSourceLotAdjustmentPattern();
        secondLotgQuery = onFabConf.isSecondLotgQuery();
        matchupUrl = onFabConf.getMatchupUrl();
        lotidForLotGWS = onFabConf.getLotidForLotGWS();
        lotidForLTM = onFabConf.getLotidForLTM();
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="FoundryFab")
    public String getFoundryFab() {
        return foundryFab;
    }

    public void setFoundryFab(String foundryFab) {
        this.foundryFab = foundryFab;
    }

    @XmlAttribute(name="DataType")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @XmlAttribute(name="LtmUrl")
    public String getLtmUrl() {
        return ltmUrl;
    }

    public void setLtmUrl(String ltmUrl) {
        this.ltmUrl = ltmUrl;
    }

    @XmlAttribute(name="WmcUrl")
    public String getWmcUrl() {
        return wmcUrl;
    }

    public void setWmcUrl(String wmcUrl) {
        this.wmcUrl = wmcUrl;
    }

    @XmlAttribute(name="Vid2ScribeUrl")
    public String getVid2ScribeUrl() {
        return vid2ScribeUrl;
    }

    public void setVid2ScribeUrl(String vid2ScribeUrl) {
        this.vid2ScribeUrl = vid2ScribeUrl;
    }

    @XmlAttribute(name="Scribe2VidUrl")
    public String getScribe2VidUrl() {
        return scribe2VidUrl;
    }

    public void setScribe2VidUrl(String scribe2VidUrl) {
        this.scribe2VidUrl = scribe2VidUrl;
    }

    @XmlAttribute(name="ScribeResultType")
    public ScribeResultType getScribeResultType() {
        return scribeResultType;
    }

    public void setScribeResultType(ScribeResultType scribeResultType) {
        this.scribeResultType = scribeResultType;
    }

    @XmlAttribute(name="OnScribeWaferIdEqualsScribeId")
    public boolean isOnScribeWaferIdEqualsScribeId() {
        return onScribeWaferIdEqualsScribeId;
    }

    public void setOnScribeWaferIdEqualsScribeId(boolean onScribeWaferIdEqualsScribeId) {
        this.onScribeWaferIdEqualsScribeId = onScribeWaferIdEqualsScribeId;
    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlAttribute(name="LotIdForOnScribeType")
    public LotIdForOnScribeType getLotIdForOnScribeType() {
        return lotIdForOnScribeType;
    }

    public void setLotIdForOnScribeType(
            LotIdForOnScribeType lotIdForOnScribeType) {
        this.lotIdForOnScribeType = lotIdForOnScribeType;
    }

    @XmlAttribute(name="WaferIdCreationPattern")
    public String getWaferIdCreationPattern() {
        return waferIdCreationPattern;
    }

    public void setWaferIdCreationPattern(String waferIdCreationPattern) {
        this.waferIdCreationPattern = waferIdCreationPattern;
    }

    @XmlAttribute(name="SourceLotAdjustmentPattern")
    public String getSourceLotAdjustmentPattern() {
        return sourceLotAdjustmentPattern;
    }

    public void setSourceLotAdjustmentPattern(String sourceLotAdjustmentPattern) {
        this.sourceLotAdjustmentPattern = sourceLotAdjustmentPattern;
    }

    @XmlAttribute(name="SecondLotgQuery")
    public boolean isSecondLotgQuery() {  return secondLotgQuery;  }

    public void setSecondLotgQuery(boolean secondLotgQuery) {
        this.secondLotgQuery = secondLotgQuery;
    }

    public String getMatchupUrl() {
        return matchupUrl;
    }

    public void setMatchupUrl(String matchupUrl) {
        this.matchupUrl = matchupUrl;
    }

    public String getLotidForLotGWS() {
        return lotidForLotGWS;
    }

    public void setLotidForLotGWS(String lotidForLotGWS) {
        this.lotidForLotGWS = lotidForLotGWS;
    }

    public String getLotidForLTM() {
        return lotidForLTM;
    }

    public void setLotidForLTM(String lotidForLTM) {
        this.lotidForLTM = lotidForLTM;
    }

    @Override
    public String toString() {
        return "OnFabConfDto{" +
                "id=" + id +
                ", foundryFab='" + foundryFab + '\'' +
                ", dataType='" + dataType + '\'' +
                ", ltmUrl='" + ltmUrl + '\'' +
                ", wmcUrl='" + wmcUrl + '\'' +
                ", vid2ScribeUrl='" + vid2ScribeUrl + '\'' +
                ", scribe2VidUrl='" + scribe2VidUrl + '\'' +
                ", scribeResultType=" + scribeResultType +
                ", onScribeWaferIdEqualsScribeId=" + onScribeWaferIdEqualsScribeId +
                ", status=" + status +
                ", lotIdForOnScribeType=" + lotIdForOnScribeType +
                ", waferIdCreationPattern='" + waferIdCreationPattern + '\'' +
                ", sourceLotAdjustmentPattern='" + sourceLotAdjustmentPattern + '\'' +
                ", secondLotgQuery=" + secondLotgQuery +
                ", matchupUrl='" + matchupUrl + '\'' +
                ", lotidForLotGWS='" + lotidForLotGWS + '\'' +
                ", lotidForLTM='" + lotidForLTM + '\'' +
                '}';
    }
}
