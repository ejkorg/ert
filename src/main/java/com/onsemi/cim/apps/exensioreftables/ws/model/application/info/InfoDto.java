package com.onsemi.cim.apps.exensioreftables.ws.model.application.info;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Info")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class InfoDto {

    private ApplicationDto application;

    private DataSourcesDto dataSources;

    public InfoDto() {
    }

    public InfoDto(ApplicationDto applicationDto, DataSourcesDto dataSources) {
        this.application = applicationDto;
        this.dataSources = dataSources;
    }

    @XmlElement(name="Application")
    public ApplicationDto getApplication() {
        return application;
    }

    public void setApplication(ApplicationDto application) {
        this.application = application;
    }

    @XmlElement(name="DataSources")
    public DataSourcesDto getDataSources() {
        return dataSources;
    }

    public void setDataSources(DataSourcesDto dataSources) {
        this.dataSources = dataSources;
    }

    @Override
    public String toString() {
        return "InfoDto{" +
                "application=" + application +
                ", dataSources=" + dataSources +
                '}';
    }
}
