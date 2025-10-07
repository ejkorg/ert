package com.onsemi.cim.apps.exensioreftables.ws.model.application.info;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "DataSources")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataSourcesDto {

    private DataSourceDto dataSourceApplication;

    private DataSourceDto dataSourceDatawarehouse;

    private DataSourceDto dataSourceLotg;

    public DataSourcesDto() {
    }

    public DataSourcesDto(DataSourceDto dataSourceApplication, DataSourceDto dataSourceDatawarehouse, DataSourceDto dataSourceLotg) {
        this.dataSourceApplication = dataSourceApplication;
        this.dataSourceDatawarehouse = dataSourceDatawarehouse;
        this.dataSourceLotg = dataSourceLotg;
    }

    @XmlElement(name="Application")
    public DataSourceDto getDataSourceApplication() {
        return dataSourceApplication;
    }

    public void setDataSourceApplication(DataSourceDto dataSourceApplication) {
        this.dataSourceApplication = dataSourceApplication;
    }

    @XmlElement(name="Datawarehouse")
    public DataSourceDto getDataSourceDatawarehouse() {
        return dataSourceDatawarehouse;
    }

    public void setDataSourceDatawarehouse(DataSourceDto dataSourceDatawarehouse) {
        this.dataSourceDatawarehouse = dataSourceDatawarehouse;
    }

    @XmlElement(name="LotG")
    public DataSourceDto getDataSourceLotg() {
        return dataSourceLotg;
    }

    public void setDataSourceLotg(DataSourceDto dataSourceLotg) {
        this.dataSourceLotg = dataSourceLotg;
    }

    @Override
    public String toString() {
        return "DataSourcesDto{" +
                "dataSourceApplication=" + dataSourceApplication +
                ", dataSourceDatawarehouse=" + dataSourceDatawarehouse +
                ", dataSourceLotg=" + dataSourceLotg +
                '}';
    }
}
