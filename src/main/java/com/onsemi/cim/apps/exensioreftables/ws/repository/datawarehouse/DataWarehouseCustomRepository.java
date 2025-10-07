package com.onsemi.cim.apps.exensioreftables.ws.repository.datawarehouse;

import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseLotClass;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseMfgArea;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehousePlm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;

@Repository("DataWarehouseCustomRepository")
public class DataWarehouseCustomRepository  {

    private static final String SQL_DW_PLM_BY_PART_ID = "SELECT TECH.FAB_SITE_ID, TECH.WFR_TECH_IMPL_ID, TECH.WFR_TECH_VARIANT_ID, "
            + "ENG.ENG_WFR_TECH_TYPE_ID, FAM.ENG_WFR_TECH_FAM_ID, FAM.FAM_PLATFORM_GROUP_TYPE, "
            + "PART.CORE_PART_NBR_CONFIG_ID, PART.DIE_DESIGN_CONFIG_ID,PART.ERP_RESOURCE_FAB_CD, "
            + "PART.ERP_RESOURCE_FAB_DESC,PART.FAB_DIE_MAP_ID, PART.LFCYCL_PHASE,PART.MASK_SET_NO_PART_VAL, "
            + "PART.MFG_PART_ID, PART.PAL4_CD, PART.PART_CLASS_CD, PART.PART_DESC,PART.PART_ID, "
            + "PART.PART_SUB_TYPE_CD, PART.PART_TYPE, PART.PART_USAGE_CD,PART.WFR_FAB_MASK_CONFIG_ID "
            + "FROM BIWHUB.PDM_PART_MV PART "
            + "LEFT JOIN BIWHUB.PDM_WAFER_TECH_IMPL_MV TECH ON PART.WFR_TECH_IMPL_1_ID = TECH.WFR_TECH_IMPL_ID "
            + "LEFT JOIN BIWHUB.PDM_WAFER_TECH_VARIANT_MV VAR on TECH.WFR_TECH_VARIANT_ID = VAR.WFR_TECH_VARIANT_ID "
            + "LEFT JOIN BIWHUB.PDM_ENG_WAFER_TECH_TYPE_MV ENG on VAR.WFR_TECH_TYPE_ID = ENG.ENG_WFR_TECH_TYPE_ID "
            + "LEFT JOIN BIWHUB.PDM_ENG_WAFER_TECH_FAM_MV FAM on ENG.ENG_WFR_TECH_FAM_ID = FAM.ENG_WFR_TECH_FAM_ID "
            + "WHERE PART.PART_ID = :partId";

    private static final String SQL_LOT_CLASS_BY_LOT = "SELECT LOTCLASS_CD FROM MFGLOT_OPER_ACTVTY WHERE LOT_NUM = :lotNum GROUP BY LOTCLASS_CD";

    private static final String SQL_DW_MFG_AREA_BY_MFG_AREA_CD = "SELECT MFG_AREA_CD, MFG_AREA_DESC, MFG_AREA_TYPE_CD, COUNTRY_CD, UPDATE_DTME "
            + "FROM MFG_AREA "
            + "WHERE MFG_AREA_CD = :mfgAreaCd";

    private static final String FAB_SITE_ID = "FAB_SITE_ID";
    private static final String WFR_TECH_IMPL_ID = "WFR_TECH_IMPL_ID";
    private static final String WFR_TECH_VARIANT_ID = "WFR_TECH_VARIANT_ID";
    private static final String ENG_WFR_TECH_TYPE_ID = "ENG_WFR_TECH_TYPE_ID";
    private static final String ENG_WFR_TECH_FAM_ID = "ENG_WFR_TECH_FAM_ID";
    private static final String FAM_PLATFORM_GROUP_TYPE = "FAM_PLATFORM_GROUP_TYPE";
    private static final String CORE_PART_NBR_CONFIG_ID = "CORE_PART_NBR_CONFIG_ID";
    private static final String DIE_DESIGN_CONFIG_ID = "DIE_DESIGN_CONFIG_ID";
    private static final String ERP_RESOURCE_FAB_CD = "ERP_RESOURCE_FAB_CD";
    private static final String ERP_RESOURCE_FAB_DESC = "ERP_RESOURCE_FAB_DESC";
    private static final String FAB_DIE_MAP_ID = "FAB_DIE_MAP_ID";
    private static final String LFCYCL_PHASE = "LFCYCL_PHASE";
    private static final String MASK_SET_NO_PART_VAL = "MASK_SET_NO_PART_VAL";
    private static final String MFG_PART_ID = "MFG_PART_ID";
    private static final String PAL_4_CD = "PAL4_CD";
    private static final String PART_CLASS_CD = "PART_CLASS_CD";
    private static final String PART_DESC = "PART_DESC";
    private static final String PART_ID = "PART_ID";
    private static final String PART_SUB_TYPE_CD = "PART_SUB_TYPE_CD";
    private static final String PART_TYPE = "PART_TYPE";
    private static final String PART_USAGE_CD = "PART_USAGE_CD";
    private static final String WFR_FAB_MASK_CONFIG_ID = "WFR_FAB_MASK_CONFIG_ID";
    private static final String LOTCLASS_CD = "LOTCLASS_CD";
    private static final String MFG_AREA_CD = "MFG_AREA_CD";
    private static final String MFG_AREA_DESC = "MFG_AREA_DESC";
    private static final String MFG_AREA_TYPE_CD = "MFG_AREA_TYPE_CD";
    private static final String COUNTRY_CD = "COUNTRY_CD";
    private static final String UPDATE_DTME = "UPDATE_DTME";

    private final EntityManager entityManager;

    public DataWarehouseCustomRepository(
            @Qualifier("dataWarehouseEntityManager") EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    public DataWarehousePlm getDwPlmByPartId(String partId) {
        try {
            Query query = entityManager.createNativeQuery(SQL_DW_PLM_BY_PART_ID, Tuple.class);

            query.setParameter("partId", partId);
            return convertResultToDataWarehousePlm((Tuple) query.getSingleResult());
        } catch (NoResultException nre) {
            return null;
        }
    }

    private DataWarehousePlm convertResultToDataWarehousePlm(Tuple tuple) {
        DataWarehousePlm dataWarehousePlm = new DataWarehousePlm();
        if (tuple != null && tuple.getElements() != null) {
            dataWarehousePlm.setFabSiteId((String) tuple.get(FAB_SITE_ID));
            dataWarehousePlm.setWfrTechImplId((String) tuple.get(WFR_TECH_IMPL_ID));
            dataWarehousePlm.setWfrTechVariantId((String) tuple.get(WFR_TECH_VARIANT_ID));
            dataWarehousePlm.setEngWfrTechTypeId((String) tuple.get(ENG_WFR_TECH_TYPE_ID));
            dataWarehousePlm.setEngWfrTechFamId((String) tuple.get(ENG_WFR_TECH_FAM_ID));
            dataWarehousePlm.setFamPlatformGroupType((String) tuple.get(FAM_PLATFORM_GROUP_TYPE));
            dataWarehousePlm.setCorePartNbrConfigId((String) tuple.get(CORE_PART_NBR_CONFIG_ID));
            dataWarehousePlm.setDieDesignConfigId((String) tuple.get(DIE_DESIGN_CONFIG_ID));
            dataWarehousePlm.setErpResourceFabCd((String) tuple.get(ERP_RESOURCE_FAB_CD));
            dataWarehousePlm.setErpResourceFabDesc((String) tuple.get(ERP_RESOURCE_FAB_DESC));
            dataWarehousePlm.setFabDieMapId((String) tuple.get(FAB_DIE_MAP_ID));
            dataWarehousePlm.setLfcyclPhase((String) tuple.get(LFCYCL_PHASE));
            dataWarehousePlm.setMaskSetNoPartVal((String) tuple.get(MASK_SET_NO_PART_VAL));
            dataWarehousePlm.setMfgPartId((String) tuple.get(MFG_PART_ID));
            dataWarehousePlm.setPal4Cd((String) tuple.get(PAL_4_CD));
            dataWarehousePlm.setPartClassCd((String) tuple.get(PART_CLASS_CD));
            dataWarehousePlm.setPartDesc((String) tuple.get(PART_DESC));
            dataWarehousePlm.setPartId((String) tuple.get(PART_ID));
            dataWarehousePlm.setPartSubTypeCd((String) tuple.get(PART_SUB_TYPE_CD));
            dataWarehousePlm.setPartType((String) tuple.get(PART_TYPE));
            dataWarehousePlm.setPartUsageCd((String) tuple.get(PART_USAGE_CD));
            dataWarehousePlm.setWfrFabMaskConfigId((String) tuple.get(WFR_FAB_MASK_CONFIG_ID));
        }

        return dataWarehousePlm;
    }

    public DataWarehouseLotClass getLotClassByLot(String lotNum) {
        try {
            Query query = entityManager.createNativeQuery(SQL_LOT_CLASS_BY_LOT, Tuple.class);

            query.setParameter("lotNum", lotNum);
            return convertResultToDataWarehouseClass((Tuple) query.getSingleResult());
        } catch (NoResultException nre) {
            return null;
        }
    }

    private DataWarehouseLotClass convertResultToDataWarehouseClass(Tuple tuple) {
        DataWarehouseLotClass dataWarehouseLotClass = new DataWarehouseLotClass();
        if (tuple != null && tuple.getElements() != null) {
            dataWarehouseLotClass.setLotclassCd((String) tuple.get(LOTCLASS_CD));
        }

        return dataWarehouseLotClass;
    }

    public DataWarehouseMfgArea getDwMfgAreaByMfgAreaCd(String mfgAreaCd) {
        try {
            Query query = entityManager.createNativeQuery(SQL_DW_MFG_AREA_BY_MFG_AREA_CD, Tuple.class);

            query.setParameter("mfgAreaCd", mfgAreaCd);
            return convertResultToDataWarehouseMfgArea((Tuple) query.getSingleResult());
        } catch (NoResultException nre) {
            return null;
        }
    }

    private DataWarehouseMfgArea convertResultToDataWarehouseMfgArea(Tuple tuple) {
        DataWarehouseMfgArea dataWarehouseMfgArea = new DataWarehouseMfgArea();
        if (tuple != null && tuple.getElements() != null) {
            dataWarehouseMfgArea.setMfgAreaCd((String) tuple.get(MFG_AREA_CD));
            dataWarehouseMfgArea.setMfgAreaDesc((String) tuple.get(MFG_AREA_DESC));
            dataWarehouseMfgArea.setMfgAreaTypeCd((String) tuple.get(MFG_AREA_TYPE_CD));
            dataWarehouseMfgArea.setCountryCd((String) tuple.get(COUNTRY_CD));
            dataWarehouseMfgArea.setUpdateDateTime((String) tuple.get(UPDATE_DTME));
        }

        return dataWarehouseMfgArea;
    }
}
