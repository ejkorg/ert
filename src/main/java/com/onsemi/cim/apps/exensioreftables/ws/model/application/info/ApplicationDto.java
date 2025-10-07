package com.onsemi.cim.apps.exensioreftables.ws.model.application.info;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Application")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ApplicationDto {

    private String version;

    public ApplicationDto() {
    }

    public ApplicationDto(String version) {
        this.version = version;
    }

    @XmlAttribute(name="Version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "InfoApplicationDto{" +
                "version='" + version + '\'' +
                '}';
    }
}
