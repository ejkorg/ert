package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnSiteConfDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "ON_SITE_CONF")
public class OnSiteConf extends ApplicationEntity {

    @Column(name = "SITE") private String site;
    @Column(name = "MES_TYPE") @Enumerated(EnumType.STRING) private MesType mesType;
    @Column(name = "DRIVER_CLASS_NAME") private String driverClassName;
    @Column(name = "CONNECTION_STRING") private String connectionString;
    @Column(name = "DB_USERNAME") private String dbUsername;
    @Column(name = "DB_PASSWORD") private String dbPassword;
    @Column(name = "SQL_LTM_PRODUCT_REPLACEMENT") private String sqlLtmProductReplacement;
    @Column(name = "LOT_TRIM_RULE") private String lotTrimRule;

    public OnSiteConf() {
    }

    public OnSiteConf(OnSiteConfDto onSiteConfDto){
        site = onSiteConfDto.getSite();
        mesType = onSiteConfDto.getMesType();
        driverClassName = onSiteConfDto.getDriverClassName();
        connectionString = onSiteConfDto.getConnectionString();
        dbUsername = onSiteConfDto.getDbUsername();
        dbPassword = onSiteConfDto.getDbPassword();
        sqlLtmProductReplacement = onSiteConfDto.getSqlLtmProductReplacement();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public MesType getMesType() {
        return mesType;
    }

    public void setMesType(MesType mesType) {
        this.mesType = mesType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String dbUrl) {
        this.connectionString = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getSqlLtmProductReplacement() {
        return sqlLtmProductReplacement;
    }

    public void setSqlLtmProductReplacement(String sqlLtmProductReplacement) {
        this.sqlLtmProductReplacement = sqlLtmProductReplacement;
    }

    public String getLotTrimRule() {
        return lotTrimRule;
    }

    public void setLotTrimRule(String lotTrimRule) {
        this.lotTrimRule = lotTrimRule;
    }

    @Override
    public String toString() {
        return "OnSiteConf{" +
                "site='" + site + '\'' +
                ", mesType=" + mesType +
                ", driverClassName='" + driverClassName + '\'' +
                ", connectionString='" + connectionString + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", sqlLtmProductReplacement='" + sqlLtmProductReplacement + '\'' +
                ", lotTrimRule='" + lotTrimRule + '\'' +
                ", id=" + id +
                '}';
    }
}
