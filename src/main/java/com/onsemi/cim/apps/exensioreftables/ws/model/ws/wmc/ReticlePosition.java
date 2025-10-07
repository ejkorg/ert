package com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc;

import javax.xml.bind.annotation.*;

/**
 *
 * @author fg6zdy
 */
@XmlRootElement(name="Position")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ReticlePosition {

    private Integer coordX;
    private Integer coordY;

    private Long id;

    @XmlAttribute(name="CoordX")
    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    @XmlAttribute(name="CoordY")
    public Integer getCoordY() {
        return coordY;
    }

    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Pos[");

        sb.append(getId());
        sb.append(";");

        sb.append(getCoordX());
        sb.append(",");

        sb.append(getCoordY());
        sb.append(",");

        sb.append("]");
        return sb.toString();
    }
}
