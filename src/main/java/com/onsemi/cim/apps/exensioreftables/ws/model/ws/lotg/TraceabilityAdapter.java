package com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg;

import java.util.Date;

/**
 *
 * @author ffv7xh
 */
public interface TraceabilityAdapter {

    /**
     * Lot Number
     * @return
     */
    String getLotNumber();

    /**
     * Lot class
     * @return
     */
    String getLotClass();

    /**
     * Bank where lot is
     * @return
     */
    String getLotBank();

    /**
     * Transaction time
     * @return
     */
    Date getLotTransaction();

    /**
     * Part type
     * @return
     */
    PartTypes getPartType();

    /**
     * SQL for mine next lots. It depends on direction.
     * @return
     */
    String getSql();

    /**
     * Direction of lookup in tree, it means if we look up up or down.
     * @return
     */
    TraceabilityDirection getDirection();

    /**
     * Create copy of adapter but for input Traceability, other like SQL are copied.
     * @param tr
     * @return
     */
    TraceabilityAdapter copy(Traceability tr);

    /**
     * Get wraped Traceability
     * @return
     */
    Traceability getTraceability();

    /**
     * Prepare full lot number.
     * @return
     */
    String getQualifiedLotNumber();

    /**
     * This can be usefull for construct path in tree.
     * @return
     */
    String getParentQualifiedLotNumber();

    /**
     * This can be usefull for construct path in tree.
     * @param p
     */
    void setParentQualifiedLotNumber(String p);


    int getRefId();
    void setRefId(int r);

    int getParentRefId();
    void setParentRefId(int r);

    int getLevel();
    void setLevel(int r);
    
    /**
     * Get other type part.
     * @return
     */
    PartTypes getOtherPartType();

    String getPart();
}
