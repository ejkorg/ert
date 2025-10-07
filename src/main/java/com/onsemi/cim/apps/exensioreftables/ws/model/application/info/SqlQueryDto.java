package com.onsemi.cim.apps.exensioreftables.ws.model.application.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Query")
public class SqlQueryDto {

    private String version;
    private String query;

    public SqlQueryDto() {
    }

    public SqlQueryDto(String version, String query) {
        this.version = version;
        this.query = query;
    }

    @XmlAttribute(name="Version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlAttribute(name="Query")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "DbQuery{" +
                "version='" + version + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
