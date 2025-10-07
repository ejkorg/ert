package com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe;

import javax.xml.bind.annotation.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tcyjjp
 * @mail bobek@edhouse.cz
 *
 */
@XmlRootElement(name="WaferDetails")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WaferDetails {

    private List<WaferDetail> waferDetail;
    private String searchedLot;
    private String searchedWaferScribe;
    private String status;

    //getters & setters

    @XmlElement(name="WaferDetail")
    public List<WaferDetail> getWaferDetail() {
        return waferDetail;
    }

    public void setWaferDetail(List<WaferDetail> waferDetail) {
        this.waferDetail = waferDetail;
    }

    @XmlAttribute(name="SearchedWaferScribe")
    public String getSearchedWaferScribe() {
        return searchedWaferScribe;
    }

    public void setSearchedWaferScribe(String searchedWaferScribe) {
        this.searchedWaferScribe = searchedWaferScribe;
    }

    @XmlAttribute(name="Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlAttribute(name="SearchedLot")
    public String getSearchedLot() {
        return searchedLot;
    }

    public void setSearchedLot(String searchedLot) {
        this.searchedLot = searchedLot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(waferDetail != null) {
            Iterator<WaferDetail> wdi = waferDetail.iterator();
            while (wdi.hasNext()) {
                sb.append(wdi.next());
                if (wdi.hasNext()) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return "WaferDetails{" +
                "waferDetail=" + sb.toString() +
                ", searchedLot='" + searchedLot + '\'' +
                ", searchedWaferScribe='" + searchedWaferScribe + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
