package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import javax.xml.bind.annotation.*;


/**
 * It holds information about lot. Not all properties are used at one time.
 * @author ffv7xh
 */
@XmlType(propOrder = {
    
    "lotNumber",
    "lotClass",
        
    "location",
    "fabLot",
    "waferPart",
    
    "foundry",
    "foundryPart",
    "foundryFab",
    "foundryLot",
    
    "shippedQty",
    "shippedDateCode",
    "shippedLots",
    
    "locationType",
    "bank",    
    "shipped",
    "finalGoodPart"
})
@XmlRootElement(name="Lot")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BasicLotInfo {

    private String lotClass;

    private String lotNumber;

    private String location;

    private String locationType;

    private String bank;

    private String fabLot;

    private Boolean foundry;

    private Boolean shipped;

    private String shippedLots;

    private String shippedQty;

    private String shippedDateCode;

    private String waferPart;

    private String finalGoodPart;

    private String foundryLot;

    private String foundryPart;

    private String foundryFab;

    @XmlAttribute(name="FoundryFab")
    public String getFoundryFab() {
        return foundryFab;
    }

    public void setFoundryFab(String foundryFab) {
        this.foundryFab = foundryFab;
    }

    @XmlAttribute(name="FoundryLotNumber")
    public String getFoundryLot() {
        return foundryLot;
    }

    public void setFoundryLot(String foundryLot) {
        this.foundryLot = foundryLot;
    }

    @XmlAttribute(name="FoundryPart")
    public String getFoundryPart() {
        return foundryPart;
    }

    public void setFoundryPart(String foundryPart) {
        this.foundryPart = foundryPart;
    }

    @XmlAttribute(name="FinalGoodPart")
    public String getFinalGoodPart() {
        return finalGoodPart;
    }

    public void setFinalGoodPart(String finalGoodPart) {
        this.finalGoodPart = finalGoodPart;
    }

    @XmlAttribute(name="WaferPart")
    public String getWaferPart() {
        return waferPart;
    }

    public void setWaferPart(String waferPart) {
        this.waferPart = waferPart;
    }

    @XmlAttribute(name="PromisLot")
    public String getFabLot() {
        return fabLot;
    }

    public void setFabLot(String promisLot) {
        this.fabLot = promisLot;
    }

    @XmlAttribute(name="LotFab")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlAttribute(name="LotClass")
    public String getLotClass() {
        return lotClass;
    }

    public void setLotClass(String lotClass) {
        this.lotClass = lotClass;
    }

    @XmlAttribute(name="LotNumber")
    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    @XmlAttribute(name="Bank")
    //@XmlTransient
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @XmlAttribute(name="BankType")
    public String getLocationType() {
        return locationType;
    }

    @XmlAttribute(name="FoundryLot")
    public Boolean isFoundry() {
        return foundry;
    }

    public void setFoundry(Boolean foundry) {
        this.foundry = foundry;
    }


    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    @XmlAttribute(name="Shipped")
    public Boolean isShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    @XmlAttribute(name="ShipDemand")
    public String getShippedLots() {
        return shippedLots;
    }

    public void setShippedLots(String shippedLots) {
        this.shippedLots = shippedLots;
    }

    @XmlAttribute(name="ShippedDateCode")
    public String getShippedDateCode() {
        return shippedDateCode;
    }

    public void setShippedDateCode(String shippedDateCode) {
        this.shippedDateCode = shippedDateCode;
    }

    @XmlAttribute(name="ShippedQty")
    public String getShippedQty() {
        return shippedQty;
    }

    public void setShippedQty(String shippedQty) {
        this.shippedQty = shippedQty;
    }

    public BasicLotInfo() {
    }

    @Override
    public String toString() {
        return "BasicLotInfo{" + "lotClass=" + lotClass + ", lotNumber=" + lotNumber + ", location=" + location + ", locationType=" + locationType + ", bank=" + bank + ", fabLot=" + fabLot + ", foundry=" + foundry + ", shipped=" + shipped + ", shippedLots=" + shippedLots + ", shippedQty=" + shippedQty + ", shippedDateCode=" + shippedDateCode + ", waferPart=" + waferPart + ", finalGoodPart=" + finalGoodPart + ", foundryLot=" + foundryLot + ", foundryPart=" + foundryPart + ", foundryFab=" + foundryFab + '}';
    }

          
}
