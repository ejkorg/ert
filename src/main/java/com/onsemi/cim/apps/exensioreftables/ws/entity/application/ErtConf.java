package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.ErtConfDto;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "ERT_CONF")
public class ErtConf extends ApplicationEntity {

    @Column(name = "CONF_NAME") private String confName;
    @Column(name = "VALUE_STRING") private String valueString;
    @Column(name = "VALUE_NUMBER") private Long valueNumber;
    @Column(name = "VALUE_BOOLEAN") @Type(type = "yes_no") private boolean valueBoolean;
    @Column(name = "DATA_TYPE") @Enumerated(EnumType.STRING) private DataType dataType;

    public ErtConf() {
    }

    public ErtConf(ErtConfDto configDto){
        this.id = configDto.getId();
        this.confName = configDto.getConfName();
        this.valueString = configDto.getValueString();
        this.valueNumber = configDto.getValueNumber();
        this.valueBoolean = configDto.isValueBoolean();
        this.dataType = configDto.getDataType();
    }


    public String getConfName() {
        return confName;
    }

    public void setConfName(String name) {
        this.confName = name;
    }


    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }


    public Long getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(Long valueNumber) {
        this.valueNumber = valueNumber;
    }


    public boolean isValueBoolean() {
        return valueBoolean;
    }

    public void setValueBoolean(boolean valueBool) {
        this.valueBoolean = valueBool;
    }


    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }


    @Override
    public String toString() {
        return "ErtConf{" +
                "confName='" + confName + '\'' +
                ", valueString='" + valueString + '\'' +
                ", valueNumber=" + valueNumber +
                ", valueBoolean=" + valueBoolean +
                ", dataType=" + dataType +
                ", id=" + id +
                '}';
    }
}
