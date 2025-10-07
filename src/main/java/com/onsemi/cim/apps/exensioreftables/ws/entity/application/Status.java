package com.onsemi.cim.apps.exensioreftables.ws.entity.application;

/**
 *
 * @author fg6zdy
 */
public enum Status {
    UNKNOWN(false, false, false, false, false),
    NO_DATA(false, false, false, false, false),
    ERROR(false, false, false, false, false),
    MANUAL(true, true, true, true, true),
    INVALID_LOT(true, true, true, true, true),
  
    LOTG(true, false, false, false, false),
    LTM(false, false, false, true, false),
    DW(false, false, false, false, true),

    LOTG_MFGLOT(true, true, false, false, false),
    LOTG_MES(true, false, true, false, false),
    LOTG_LTM(true, false, false, true, false),
    LOTG_DW(true, false, false, false, true),
    LTM_DW(false, false, false, true, true),

    LOTG_MFGLOT_MES(true, true, true, false, false),
    LOTG_MFGLOT_LTM(true, true, false, true, false),
    LOTG_MFGLOT_DW(true, true, false, false, true),
    LOTG_MES_LTM(true, false, true, true, false),
    LOTG_MES_DW(true, false, true, false, true),
    LOTG_LTM_DW(true, false, false, true, true),

    LOTG_MFGLOT_MES_LTM(true, true, true, true, false),
    LOTG_MFGLOT_MES_DW(true, true, true, false, true),
    LOTG_MES_LTM_DW(true, false, true, true, true),
    LOTG_MFGLOT_LTM_DW(true, true, false, true, true),

    LOTG_MFGLOT_MES_LTM_DW(true, true, true, true, true),

    // For old Statuses
    // TODO: Remove
    FOUND(true, true, false, true, true),
    NO_LOTG(false, false,false, true, true),
    NO_LTM(true, false, false, false, true),
    NO_DW(true, false, false, true, false),
    NO_LOTG_LTM(false, false, false, false, true),
    NO_LOTG_DW(false, false, false, true, false),
    NO_LTM_DW(true, false, false, false, false);

    private final boolean foundLotg;

    private final boolean foundMfgLot;

    private final boolean foundMes;

    private final boolean foundLtm;

    private final boolean foundDw;

    private final boolean foundAll;

    Status(boolean foundLotg, boolean foundMfgLot, boolean foundMes, boolean foundLtm, boolean foundDw) {
        this.foundLotg = foundLotg;
        this.foundMfgLot = foundMfgLot;
        this.foundMes = foundMes;
        this.foundLtm = foundLtm;
        this.foundDw = foundDw;
        this.foundAll = foundLotg && foundMfgLot && foundMes && foundLtm && foundDw;
    }

    public boolean isFoundLotg() {
        return foundLotg;
    }

    public boolean isFoundMfgLot() {
        return foundMfgLot;
    }

    public boolean isFoundMes() { return foundMes; }

    public boolean isFoundLtm() {
        return foundLtm;
    }

    public boolean isFoundDw() {
        return foundDw;
    }

    public boolean isFoundAll() {
        return foundAll;
    }
}
