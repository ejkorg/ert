package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnFabConfDto;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "ON_FAB_CONF")
public class OnFabConf extends ApplicationEntity {

    @Column(name = "FOUNDRY_FAB") private String foundryFab;
    @Column(name = "DATA_TYPE") private String dataType;
    @Column(name = "LTM_URL") private String ltmUrl;
    @Column(name = "WMC_URL") private String wmcUrl;
    @Column(name = "VID2SCRIBE_URL") private String vid2ScribeUrl;
    @Column(name = "SCRIBE2VID_URL") private String scribe2VidUrl;
    @Column(name = "SCRIBE_RESULT_TYPE") @Enumerated(EnumType.STRING) private ScribeResultType scribeResultType;
    @Column(name = "ONSCRIBE_WAFERID_EQUALS_SCRIBEID") @Type(type = "yes_no") private boolean onScribeWaferIdEqualsScribeId;
    @Column(name = "LOTID_FOR_ONSCRIBE") @Enumerated(EnumType.STRING) private LotIdForOnScribeType lotIdForOnScribeType;
    @Column(name = "WAFERID_CREATION_PATTERN") private String waferIdCreationPattern;
    @Column(name = "SOURCELOT_ADJUSTMENT_PATTERN") private String sourceLotAdjustmentPattern;
    @Column(name = "SECOND_LOTG_QUERY") @Type(type = "yes_no") private boolean secondLotgQuery;
    @Column(name = "MATCHUP_URL")  private String matchupUrl;
    @Column(name = "LOTID_FOR_LOTG") private String lotidForLotGWS;
    @Column(name = "LOTID_FOR_LTM") private String lotidForLTM;

    public OnFabConf() {
    }

    public OnFabConf(OnFabConfDto onFabConfDto){
        this.foundryFab = onFabConfDto.getFoundryFab();
        this.dataType = onFabConfDto.getDataType();
        this.ltmUrl = onFabConfDto.getLtmUrl();
        this.wmcUrl = onFabConfDto.getWmcUrl();
        this.vid2ScribeUrl = onFabConfDto.getVid2ScribeUrl();
        this.scribe2VidUrl = onFabConfDto.getScribe2VidUrl();
        this.scribeResultType = onFabConfDto.getScribeResultType();
        this.onScribeWaferIdEqualsScribeId = onFabConfDto.isOnScribeWaferIdEqualsScribeId();
        this.lotIdForOnScribeType = onFabConfDto.getLotIdForOnScribeType();
        this.waferIdCreationPattern = onFabConfDto.getWaferIdCreationPattern();
        this.sourceLotAdjustmentPattern = onFabConfDto.getSourceLotAdjustmentPattern();
        this.secondLotgQuery = onFabConfDto.isSecondLotgQuery();
        this.matchupUrl = onFabConfDto.getMatchupUrl();
        this.lotidForLotGWS = onFabConfDto.getLotidForLotGWS();
        this.lotidForLTM = onFabConfDto.getLotidForLTM();

    }


    public String getFoundryFab() {
        return foundryFab;
    }

    public void setFoundryFab(String foundryFab) {
        this.foundryFab = foundryFab;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    public String getLtmUrl() {
        return ltmUrl;
    }

    public void setLtmUrl(String ltmUrl) {
        this.ltmUrl = ltmUrl;
    }


    public String getWmcUrl() {
        return wmcUrl;
    }

    public void setWmcUrl(String waferMapConfigurationUrl) {
        this.wmcUrl = waferMapConfigurationUrl;
    }


    public String getVid2ScribeUrl() {
        return vid2ScribeUrl;
    }

    public void setVid2ScribeUrl(String vid2ScribeUrl) {
        this.vid2ScribeUrl = vid2ScribeUrl;
    }


    public String getScribe2VidUrl() {
        return scribe2VidUrl;
    }

    public void setScribe2VidUrl(String scribe2VidUrl) {
        this.scribe2VidUrl = scribe2VidUrl;
    }


    public ScribeResultType getScribeResultType() {
        return scribeResultType;
    }

    public void setScribeResultType(ScribeResultType scribeResultType) {
        this.scribeResultType = scribeResultType;
    }


    public boolean isOnScribeWaferIdEqualsScribeId() {
        return onScribeWaferIdEqualsScribeId;
    }

    public void setOnScribeWaferIdEqualsScribeId(boolean onScribeWaferIdEqualsScribeId) {
        this.onScribeWaferIdEqualsScribeId = onScribeWaferIdEqualsScribeId;
    }


    public LotIdForOnScribeType getLotIdForOnScribeType() {
        return lotIdForOnScribeType;
    }

    public void setLotIdForOnScribeType(
            LotIdForOnScribeType lotIdForOnScribeType) {
        this.lotIdForOnScribeType = lotIdForOnScribeType;
    }


    public String getWaferIdCreationPattern() {
        return waferIdCreationPattern;
    }

    public void setWaferIdCreationPattern(String waferIdCreationPattern) {
        this.waferIdCreationPattern = waferIdCreationPattern;
    }


    public String getSourceLotAdjustmentPattern() {
        return sourceLotAdjustmentPattern;
    }

    public void setSourceLotAdjustmentPattern(String sourceLotAdjustmentPattern) {
        this.sourceLotAdjustmentPattern = sourceLotAdjustmentPattern;
    }


    public boolean isSecondLotgQuery() {
        return secondLotgQuery;
    }

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
        return "OnFabConf{" +
                "foundryFab='" + foundryFab + '\'' +
                ", dataType='" + dataType + '\'' +
                ", ltmUrl='" + ltmUrl + '\'' +
                ", wmcUrl='" + wmcUrl + '\'' +
                ", vid2ScribeUrl='" + vid2ScribeUrl + '\'' +
                ", scribe2VidUrl='" + scribe2VidUrl + '\'' +
                ", scribeResultType=" + scribeResultType +
                ", onScribeWaferIdEqualsScribeId=" + onScribeWaferIdEqualsScribeId +
                ", lotIdForOnScribeType=" + lotIdForOnScribeType +
                ", waferIdCreationPattern='" + waferIdCreationPattern + '\'' +
                ", sourceLotAdjustmentPattern='" + sourceLotAdjustmentPattern + '\'' +
                ", secondLotgQuery='" + secondLotgQuery + '\'' +
                ", matchupUrl='" + matchupUrl + '\'' +
                ", lotidForLotGWS='" + lotidForLotGWS + '\'' +
                ", lotidForLTM='" + lotidForLTM + '\'' +
                '}';
    }
}
