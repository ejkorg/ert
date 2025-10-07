package com.onsemi.cim.apps.exensioreftables.ws.model.application;


import com.onsemi.cim.apps.exensioreftables.ws.entity.ApplicationEntity;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class AbstractDto {

    protected Long id;
    protected Status status;

    public AbstractDto() {
    }

    public AbstractDto(ApplicationEntity applicationEntity) {
        id = applicationEntity.getId();
    }

    @XmlAttribute(name="Id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name="Status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AbstractDto{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
