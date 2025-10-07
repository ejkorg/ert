package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.application.DataType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ErtConf;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ErtConf")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ErtConfDto {

    private Long id;
    private String confName;
    private String valueString;
    private Long valueNumber;
    private boolean valueBoolean;
    private DataType dataType;
    private Status status;

    public ErtConfDto() {
    }

    public ErtConfDto(ErtConf config) {
        id = config.getId();
        confName = config.getConfName();
        valueString = config.getValueString();
        valueNumber = config.getValueNumber();
        valueBoolean = config.isValueBoolean();
        dataType = config.getDataType();
        status = Status.FOUND;
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Name")
    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    @XmlAttribute(name="ValueString")
    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    @XmlAttribute(name="ValueNumber")
    public Long getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(Long valueNumber) {
        this.valueNumber = valueNumber;
    }

    @XmlAttribute(name="ValueBoolean")
    public boolean isValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }

    @XmlAttribute(name="DataType")
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErtConfDto{" +
                "id=" + id +
                ", confName='" + confName + '\'' +
                ", valueString='" + valueString + '\'' +
                ", valueNumber=" + valueNumber +
                ", valueBoolean=" + valueBoolean +
                ", dataType=" + dataType +
                ", status=" + status +
                '}';
    }
}
