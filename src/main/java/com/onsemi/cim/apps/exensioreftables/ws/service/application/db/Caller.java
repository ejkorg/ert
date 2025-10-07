package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.*;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.WaferMissingInS2VException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.WaferMissingInV2SException;
import com.onsemi.cim.apps.exensioreftables.ws.model.MesDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.WaferIdSource;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseMfgArea;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehousePlm;
import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.LotGInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg.SourceLotInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm.LotInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferDetail;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferDetails;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.scribe.WaferId;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc.WaferMapConfiguration;
import com.onsemi.cim.apps.exensioreftables.ws.utils.AttributeUtils;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import com.onsemi.cim.apps.exensioreftables.ws.utils.LotgUtils;
import com.onsemi.cim.apps.exensioreftables.ws.ws.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class Caller {

    private static final Logger LOG = LoggerFactory.getLogger(Caller.class);

    private static final String STATUS_NO_DATA = "No_Data";
    private static final String STATUS_FOUND = "FOUND";

    public void assignLotGInfoResults(LotGInfo lotGInfo, String lot, OnLot onLot, OnProd onProd) {

        if (lotGInfo.getStatus() == Status.FOUND || lotGInfo.getStatus() == Status.NO_DATA) {
            onLot.setLot(StringUtils.upperCase(lot));
            String product = AttributeUtils.getProduct(lotGInfo.getProduct());
            onLot.setProduct(StringUtils.upperCase(product));
            onProd.setProduct(StringUtils.upperCase(product));

            final String fab = StringUtils.upperCase(lotGInfo.getFab());
            onLot.setFab(fab);
            onProd.setFab(fab);

            onLot.setInsertTime(DateUtils.getSqlDateFromUtilDate(new Date()));
            onLot.setLotClass(StringUtils.upperCase(lotGInfo.getLotClass()));
            onLot.setSourceLot(StringUtils.upperCase(lotGInfo.getSourceLot()));
            onLot.setAlternateProduct(StringUtils.upperCase(lotGInfo.getWaferPartAlternateProduct()));
            onLot.setProductCode(StringUtils.upperCase(lotGInfo.getProductionCode()));
        }
    }

    public SourceLotInfo getLotg(String lotgUrl, String lot) throws BusinessException {

        if (StringUtils.isNotEmpty(lotgUrl)) {
            SourceLotInfo lotg = LotgLoader.loadSourceLotInfo(lotgUrl, lot);
            if (lotg.getLots() != null && lotg.getLots().get(0) != null && (lotg.getLots().get(0).getFabLot() != null
                    || lotg.getLots().get(0).getFoundryLot() != null)) {
                return lotg;
            }
        }

        return null;
    }

    public LotInfo getLtm(String ltmUrl, String lot) throws BusinessException {

        if (StringUtils.isNotEmpty(ltmUrl)) {
            LotInfo ltm = LtmLoader.loadLotInfo(ltmUrl, lot);
            if (ltm.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
                return ltm;
            }
        }

        return null;
    }

    public void assignLotgResults(SourceLotInfo lotg, OnLot onLot) {

        if (lotg.getLots() != null && !lotg.getLots().isEmpty()) {
            if (lotg.getLots().get(0).getFabLot() != null) {
                onLot.setMfgLot(StringUtils.upperCase(lotg.getLots().get(0).getFabLot()));
            } else if (lotg.getLots().get(0).getFoundryLot() != null) {
                onLot.setMfgLot(StringUtils.upperCase(lotg.getLots().get(0).getFoundryLot()));
            }
        }
    }

    public void assignLtmResults(LotInfo ltm, OnLot onLot) {

        if (ltm.getMetadataInfos() != null && !ltm.getMetadataInfos().isEmpty()) {
            onLot.setLotType(StringUtils.upperCase(ltm.getMetadataInfos().get(0).getLotType()));
        }
    }

    public void assignMesResults(MesDto mesDto, OnLot onLot, OnProd onProd) {

        switch (mesDto.getMesType()) {
            case GENESIS: {
                assignMesResultsForGenesis(mesDto, onLot, onProd);
                break;
            }
            case TORRENT: {
                assignMesResultsForTorrent(mesDto, onLot, onProd);
                break;
            }
        }
    }

    private void assignMesResultsForTorrent(MesDto mesDto, OnLot onLot, OnProd onProd) {
        final String oldLot = onLot.getLot();
        final String oldFab = onLot.getFab();
        final String oldProduct = onLot.getProduct();
        final String oldLotType = onLot.getLotType();

        final String oldProcess = onProd.getProcess();
        final String oldFamily = onProd.getFamily();
        final String oldPti4 = onProd.getPti4();
        final String oldMaskSet = onProd.getPti4();
        final String oldProductVersion = onProd.getProductVersion();
        final String oldTechnology = onProd.getTechnology();

        final StringBuilder loggerLot = new StringBuilder();
        final StringBuilder loggerProd = new StringBuilder();

        if (StringUtils.isNotEmpty(mesDto.getLot())) {
            onLot.setLot(mesDto.getLot());
            addLogToLogger(loggerLot, "lot", oldLot, mesDto.getLot());
        }
        if (StringUtils.isNotEmpty(mesDto.getFab())) {
            onLot.setFab(mesDto.getFab());
            addLogToLogger(loggerLot, "fab", oldFab, mesDto.getFab());
        }
        if (StringUtils.isNotEmpty(mesDto.getProduct())) {
            onLot.setProduct(mesDto.getProduct());
            onProd.setProduct(mesDto.getProduct());
            addLogToLogger(loggerLot, "product", oldProduct, mesDto.getProduct());
            addLogToLogger(loggerProd, "product", oldProduct, mesDto.getProduct());
        }
        if (StringUtils.isNotEmpty(mesDto.getLotType())) {
            onLot.setLotType(mesDto.getLotType());
            addLogToLogger(loggerLot, "lotType", oldLotType, mesDto.getLotType());
        }

        if (StringUtils.isNotEmpty(mesDto.getProductVersion())) {
            onProd.setProductVersion(mesDto.getProductVersion());
            addLogToLogger(loggerProd, "productVersion", oldProductVersion, mesDto.getProductVersion());
        }
        if (StringUtils.isNotEmpty(mesDto.getFab())) {
            onProd.setFab(mesDto.getFab());
            addLogToLogger(loggerProd, "fab", oldFab, mesDto.getFab());
        }
        if (StringUtils.isNotEmpty(mesDto.getFamily())) {
            onProd.setFamily(mesDto.getFamily());
            addLogToLogger(loggerProd, "family", oldFamily, mesDto.getFamily());
        }
        if (StringUtils.isNotEmpty(mesDto.getProcess())) {
            onProd.setProcess(mesDto.getProcess());
            addLogToLogger(loggerProd, "process", oldProcess, mesDto.getProcess());
        }
        if (StringUtils.isNotEmpty(mesDto.getTechnology())) {
            onProd.setTechnology(mesDto.getTechnology());
            addLogToLogger(loggerProd, "technology", oldTechnology, mesDto.getTechnology());
        }
        if (StringUtils.isNotEmpty(mesDto.getPti4())) {
            onProd.setPti4(mesDto.getPti4());
            addLogToLogger(loggerProd, "pti4", oldPti4, mesDto.getPti4());
        }
        if (StringUtils.isNotEmpty(mesDto.getMaskSet())) {
            onProd.setMaskSet(mesDto.getMaskSet());
            addLogToLogger(loggerProd, "maskSet", oldMaskSet, mesDto.getMaskSet());
        }

        LOG.info("Modified onLot:  {}", loggerLot);
        LOG.info("Modified onProd: {}", loggerProd);
    }

    private static void assignMesResultsForGenesis(MesDto mesDto, OnLot onLot, OnProd onProd) {
        final String oldProduct = onLot.getProduct();

        final String oldProcess = onProd.getProcess();
        final String oldFamily = onProd.getFamily();
        final String oldPti4 = onProd.getPti4();

        onLot.setProduct(mesDto.getProduct());

        onProd.setProduct(mesDto.getProduct());
        onProd.setProcess(mesDto.getProcess());
        onProd.setFamily(mesDto.getFamily());
        onProd.setPti4(mesDto.getPti4());

        LOG.info("Modified onLot:  product {} -> {}", oldProduct, mesDto.getProduct());
        LOG.info("Modified onProd: product {} -> {}, process {} -> {}, family {} -> {}, pti4 {} -> {}", oldProduct, mesDto.getProduct(), oldProcess, mesDto.getProcess(), oldFamily, mesDto.getFamily(), oldPti4, mesDto.getPti4());
    }

    private void addLogToLogger(StringBuilder sb, String name, String oldValue, String newValue) {
        if (!sb.toString().isEmpty()) {
            sb.append(", ");
        }
        sb.append(name);
        sb.append(" ");
        sb.append(oldValue);
        sb.append(" -> ");
        sb.append(newValue);
    }

    public void assignDwhpResults(DataWarehousePlm dwhp, OnProd onProd) {

        onProd.setMaskSet(StringUtils.upperCase(dwhp.getWfrFabMaskConfigId()));
        onProd.setProcess(StringUtils.upperCase(dwhp.getWfrTechImplId()));
        if (dwhp.getPal4Cd() != null && dwhp.getPal4Cd().length() >= 2) {
            onProd.setFamily(StringUtils.upperCase(dwhp.getPal4Cd()).substring(0, 2));
        }
        onProd.setPti4(StringUtils.upperCase(dwhp.getPal4Cd()));
        onProd.setTechnology(StringUtils.upperCase(dwhp.getEngWfrTechTypeId()));
    }

    public void assignDwMfgaResults(DataWarehouseMfgArea dwmfga, OnLot onLot, OnProd onProd) {

        final String onLotFab = LotgUtils.getFab(onLot.getFab(), dwmfga);
        onLot.setFab(StringUtils.upperCase(onLotFab));
        final String onProdFab = LotgUtils.getFab(onProd.getFab(), dwmfga);
        onProd.setFab(StringUtils.upperCase(onProdFab));
    }

    public WaferId getWaferIdByLotAndWaferNum(String v2sUrl, ScribeResultType scribeResultType, String lot, Integer vid) throws BusinessException {

        if (StringUtils.isNotEmpty(v2sUrl)) {
            WaferId result;
            try {
                switch (scribeResultType) {
                    case WAFER_DETAILS:
                        result = vidToScribeWaferDetail(v2sUrl, lot, vid);
                        break;
                    case WAFER_ID:
                        result = vidToScribeWaferId(v2sUrl, lot, vid);
                        break;
                    default:
                        LOG.warn("Scribe result type is {}, calling WS for WAFER_DETAILS", scribeResultType);
                        result = vidToScribeWaferDetail(v2sUrl, lot, vid);
                        break;
                }
            } catch (WaferMissingInV2SException wmie) {
                throw new BusinessException("Could not load Scribe for VID from WS.", wmie);
            } catch (RuntimeException rex) {
                throw new BusinessException("VID to Scribe Web Service not available", rex);
            }

            LOG.debug("Scribe for VID found. Lot/Scribe/VID {}/{}/{}", lot, result.getLaserScribe(), result.getVid());
            return result;
        }

        return null;
    }

    public WaferId getWaferIdByScribeId(String s2vUrl, ScribeResultType scribeResultType, String scribeId,
            String lotId) throws BusinessException {

        if (StringUtils.isNotEmpty(s2vUrl)) {
            WaferId result;
            try {
                switch (scribeResultType) {
                    case WAFER_DETAILS:
                        result = scribeToVidWaferDetails(s2vUrl, scribeId);
                        break;
                    case WAFER_ID:
                        result = scribe2VidWaferId(s2vUrl, scribeId, lotId);
                        break;
                    default:
                        LOG.warn("Scribe result type is {}, calling WS for WAFER_DETAILS", scribeResultType);
                        result = scribeToVidWaferDetails(s2vUrl, scribeId);
                        break;
                }

            } catch (WaferMissingInS2VException wmie) {
                throw new BusinessException("Could not load VID for Scribe from WS. " + wmie.getMessage(), wmie);
            } catch (IllegalArgumentException iae) {
                throw new BusinessException("Wrong attributes for Scribe WS. " + iae.getMessage(), iae);
            } catch (RuntimeException rex) {
                throw new BusinessException("Scribe to VID Web Service not available. " + rex.getMessage(), rex);
            }

            LOG.info("VID for Scribe found. Lot/Scribe/VID {}/{}/{}", result.getLotId(), scribeId, result.getVid());
            return result;
        }

        return null;
    }


    public void fillOnScribeByWaferIdResults(WaferId waferId, OnLot onLot, OnScribe onScribe, String fab, String dataType,
           boolean onScribeWaferIdEqualsScribeId, String waferIdCreationPattern) throws BusinessException {
        if (waferId != null) {
            onScribe.setStatus(com.onsemi.cim.apps.exensioreftables.ws.entity.application.Status.FOUND);
            onScribe.setScribeId(StringUtils.upperCase(waferId.getLaserScribe()));
            if (onScribeWaferIdEqualsScribeId) {
                onScribe.setWaferId(StringUtils.upperCase(waferId.getLaserScribe()));
                onScribe.setWaferIdSource(WaferIdSource.FROM_WS);
            } else {
                onScribe.setWaferId(StringUtils.upperCase(AttributeUtils.calculateWaferId(waferId.getLotId(), waferId.getVid(), onLot != null ? onLot.getSourceLot() : null, waferIdCreationPattern)));
                onScribe.setWaferIdSource(WaferIdSource.CALCULATED);
            }
            onScribe.setLot(StringUtils.upperCase(waferId.getLotId()));
            if (onLot != null) {
                onLot.setLot(StringUtils.upperCase(waferId.getLotId()));
            }
            if (waferId.getVid() != null) {
                onScribe.setWaferNum(waferId.getVid());
            }
            onScribe.setFab(StringUtils.upperCase(fab));
        }
    }

    public WaferMapConfiguration getWmcByDevice(String wmcWsUrl, String device) throws BusinessException {

        // WaferMapConfiguration call and assignments of WaferMapConfiguration values
        if (StringUtils.isNotEmpty(wmcWsUrl)) {
            return WmcLoader.loadWmcByDeviceName(wmcWsUrl, device);
        }

        return null;
    }

    public  WaferMapConfiguration getWmcByWmcId(String wmcWsUrl, Long wmcId) throws BusinessException{
        if(StringUtils.isNotEmpty(wmcWsUrl)) {
            return WmcLoader.loadWmcByWmcId(wmcWsUrl, wmcId);
        }
        return null;
    }

    public void assignWmcResults(WaferMapConfiguration waferMapConfiguration, String device, String searchedLotPart,
            OnWmap onWmap) {

        onWmap.setCenterX(waferMapConfiguration.getWfrRowCenter());
        onWmap.setCenterY(waferMapConfiguration.getWfrColCenter());
        onWmap.setDieHeight(waferMapConfiguration.getDieHeight());
        onWmap.setDieWidth(waferMapConfiguration.getDieWidth());
        onWmap.setFlat(StringUtils.upperCase(waferMapConfiguration.getFlatPosition()));
        onWmap.setFlatType(StringUtils.upperCase(waferMapConfiguration.getFlatType()));
        onWmap.setProduct(StringUtils.upperCase(searchedLotPart));
        onWmap.setReticleColOffset(waferMapConfiguration.getColOffset() == null ? null : waferMapConfiguration.getColOffset().longValue());
        onWmap.setReticleRowOffset(waferMapConfiguration.getRowOffset() == null ? null : waferMapConfiguration.getRowOffset().longValue());
        onWmap.setReticleCols(waferMapConfiguration.getCols() == null ? null : waferMapConfiguration.getCols().longValue());
        onWmap.setReticleRows(waferMapConfiguration.getRows() == null ? null : waferMapConfiguration.getRows().longValue());
        onWmap.setWfSize(waferMapConfiguration.getWaferSize().doubleValue());
        onWmap.setWfUnits(waferMapConfiguration.getUnits());
        onWmap.setWmcDevice(StringUtils.upperCase(device));
        onWmap.setCfgId(waferMapConfiguration.getId());

        switch (waferMapConfiguration.getCoordOrientation()) {
            case 1:
                onWmap.setPositiveX("L");
                onWmap.setPositiveY("D");
                break;
            case 2:
                onWmap.setPositiveX("R");
                onWmap.setPositiveY("D");
                break;
            case 3:
                onWmap.setPositiveX("R");
                onWmap.setPositiveY("U");
                break;
            case 4:
                onWmap.setPositiveX("L");
                onWmap.setPositiveY("U");
                break;
            default:
                onWmap.setPositiveX("");
                onWmap.setPositiveY("");
                break;
        }
    }

    private WaferId vidToScribeWaferId(String v2sUrl, String lot, Integer vid) throws WaferMissingInV2SException {
        LOG.info("Calling WS for wafer id to get SCRIBE. URL: {}, LOT: {},  VID: {}", v2sUrl, lot, vid);
        return WaferIdLoader.loadWaferIdByLotIdAndWaferNumber(v2sUrl, lot, vid);
    }

    private WaferId vidToScribeWaferDetail(String v2sUrl, String lot, Integer vid) throws WaferMissingInV2SException {

        LOG.info("Calling WS for wafer details to get SCRIBE. URL: {}, LOT: {},  VID: {}", v2sUrl, lot, vid);

        WaferDetails waferDetails = WaferDetailsLoader.loadWaferDetails(v2sUrl, lot);
        if (waferDetails == null || STATUS_NO_DATA.equalsIgnoreCase(waferDetails.getStatus())) {
            throw new WaferMissingInV2SException(String.format("VID2SCR missing for lot/vid %s/%s", lot, vid));
        }

        Optional<WaferDetail> waferDetail = waferDetails.getWaferDetail().stream().filter(o -> vid.toString().equalsIgnoreCase(o.getWaferNumber())).findFirst();
        if (waferDetail.isEmpty()) {
            throw new WaferMissingInV2SException(String.format("VID2SCR WaferDetails from WS are empty but status is OK. WaferDetails: %s", waferDetails));
        }

        WaferId waferId = new WaferId();
        waferId.setLotId(lot);
        waferId.setVid(vid);
        waferId.setLaserScribe(waferDetail.get().getWaferScribe());
        return waferId;
    }

    private WaferId scribe2VidWaferId(String s2vUrl, String scribe, String lotId) {
        LOG.info("Calling WS for wafer id to get VID. URL: {},  SCRIBE: {}, LOTID: {}", s2vUrl, scribe, lotId);
        return WaferIdLoader.loadWaferIdByScribeId(s2vUrl, scribe, lotId);
    }

    private WaferId scribeToVidWaferDetails(String s2vUrl, String scribe) throws WaferMissingInS2VException {

        LOG.info("Calling WS for wafer details to get VID. URL: {},  SCRIBE: {}", s2vUrl, scribe);

        WaferDetails waferDetails = WaferDetailsLoader.loadWaferDetails(s2vUrl, scribe);
        if (waferDetails == null || STATUS_NO_DATA.equalsIgnoreCase(waferDetails.getStatus())) {
            throw new WaferMissingInS2VException(String.format("SCR2VID missing for scribe %s", scribe));
        }

        WaferDetail waferDetail = waferDetails.getWaferDetail().get(0);
        if (waferDetail == null) {
            throw new WaferMissingInS2VException(String.format("SCR2VID WaferDetails from WS are empty but status is OK. WaferDetails: %s", waferDetails));
        }

        WaferId waferId = new WaferId();
        waferId.setLotId(waferDetail.getLotId());
        waferId.setVid(Integer.parseInt(waferDetail.getWaferNumber()));
        waferId.setLaserScribe(scribe);
        waferId.setStatus(STATUS_FOUND);
        return waferId;
    }

}
