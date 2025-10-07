package com.onsemi.cim.apps.exensioreftables.ws.model.application.info;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Db")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataSourceDto {

    private String url;
    private String username;
    private SqlQueryDto sqlQueryDto;

    public DataSourceDto() {
    }

    public DataSourceDto(String url, String username, String dbVersion, String dbQuery) {
        this.url = url;
        this.username = username;
        if (dbVersion == null && dbQuery == null) {
            this.sqlQueryDto = null;
        } else {
            this.sqlQueryDto = new SqlQueryDto(dbVersion, dbQuery);
        }
    }

    @XmlAttribute(name="Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute(name="Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name="Query")
    public SqlQueryDto getDbQuery() {
        return sqlQueryDto;
    }

    public void setDbQueryDto(SqlQueryDto sqlQueryDto) {
        this.sqlQueryDto = sqlQueryDto;
    }

    @Override
    public String toString() {
        return "InfoDbDto{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", dbQuery=" + sqlQueryDto +
                '}';
    }
}
