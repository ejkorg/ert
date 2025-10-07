package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.MesType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.OnSiteConf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OnSiteConf")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnSiteConfDto extends AbstractDto {

    private String site;
    private MesType mesType;
    private String driverClassName;
    private String connectionString;
    private String dbUsername;
    private String dbPassword;
    private String sqlLtmProductReplacement;
    private String lotTrimRule;

    public OnSiteConfDto() {
    }

    public OnSiteConfDto(OnSiteConf onSiteConf) {
        super(onSiteConf);
        site = onSiteConf.getSite();
        mesType = onSiteConf.getMesType();
        driverClassName = onSiteConf.getDriverClassName();
        connectionString = onSiteConf.getConnectionString();
        dbUsername = onSiteConf.getDbUsername();
        dbPassword = onSiteConf.getDbPassword();
        sqlLtmProductReplacement = onSiteConf.getSqlLtmProductReplacement();
        lotTrimRule = onSiteConf.getLotTrimRule();
    }

    @XmlAttribute(name="Site")
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @XmlAttribute(name="MesType")
    public MesType getMesType() {
        return mesType;
    }

    public void setMesType(MesType mesType) {
        this.mesType = mesType;
    }

    @XmlAttribute(name="DriverClassName")
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @XmlAttribute(name="ConnectionString")
    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    @XmlAttribute(name="DbUSername")
    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    @XmlAttribute(name="DbPassword")
    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @XmlAttribute(name="SqlLtmProductReplacement")
    public String getSqlLtmProductReplacement() {
        return sqlLtmProductReplacement;
    }

    public void setSqlLtmProductReplacement(String sqlLtmProductReplacement) {
        this.sqlLtmProductReplacement = sqlLtmProductReplacement;
    }

    @XmlAttribute(name="LotTrimRule")
    public String getLotTrimRule() {
        return lotTrimRule;
    }

    public void setLotTrimRule(String lotTrimRule) {
        this.lotTrimRule = lotTrimRule;
    }

    @Override
    public String toString() {
        return "OnSiteConfDto{" +
                "site='" + site + '\'' +
                ", mesType=" + mesType +
                ", driverClassName='" + driverClassName + '\'' +
                ", connectionString='" + connectionString + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", sqlLtmProductReplacement='" + sqlLtmProductReplacement + '\'' +
                ", lotTrimRule='" + lotTrimRule + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
