package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.*;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.LotMissingInLtmException;
import com.onsemi.cim.apps.exensioreftables.ws.model.MesDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.application.OnLotDto;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehouseMfgArea;
import com.onsemi.cim.apps.exensioreftables.ws.model.datawarehouse.DataWarehousePlm;
import com.onsemi.cim.apps.exensioreftables.ws.model.lotg.LotGInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.lotg.SourceLotInfo;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm.LotInfo;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnLotRepository;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnProdRepository;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.OnSiteConfRepository;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.mes.GenesisService;
import com.onsemi.cim.apps.exensioreftables.ws.service.application.mes.TorrentService;
import com.onsemi.cim.apps.exensioreftables.ws.service.datawarehouse.DataWarehouseService;
import com.onsemi.cim.apps.exensioreftables.ws.service.lotg.LotgInfoService;
import com.onsemi.cim.apps.exensioreftables.ws.utils.AttributeUtils;
import com.onsemi.cim.apps.exensioreftables.ws.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class OnLotService {

    private static final Logger LOG = LoggerFactory.getLogger(OnLotService.class);

    private final OnFabConfService onFabConfService;
    private final OnLotRepository onLotRepository;
    private final OnProdRepository onProdRepository;
    private final OnSiteConfRepository onSiteConfRepository;
    private final Caller caller;

    private final LotgInfoService lotGInfoService;
    private final DataWarehouseService dataWarehouseService;
    private final ErtConfService ertConfService;

    private final GenesisService genesisService;
    private final TorrentService torrentService;

    private final String USME_SPLIT_LOT_LIKE_REGEX = "^M0[a-zA-Z]";
    private final String USME_LOT_LIKE_REGEX = "^M0";
    private final int USME_SPLIT_LOT_LENGTH = 10;
    private final char USME_SPLIT_LOT_THIRD_CHAR_REPLACEMENT = '0';
    private final int USME_SPLIT_LOT_INDEX = 2;


    public OnLotService(
            OnFabConfService onFabConfService,
            OnLotRepository onLotRepository,
            OnProdRepository onProdRepository,
            Caller caller,
            LotgInfoService lotGInfoService,
            DataWarehouseService dataWarehouseService,
            ErtConfService ertConfService,
            OnSiteConfRepository onSiteConfRepository,
            GenesisService genesisService,
            TorrentService torrentService
    ) {

        this.onFabConfService = onFabConfService;
        this.onLotRepository = onLotRepository;
        this.onProdRepository = onProdRepository;
        this.caller = caller;
        this.lotGInfoService = lotGInfoService;
        this.dataWarehouseService = dataWarehouseService;
        this.ertConfService = ertConfService;
        this.onSiteConfRepository = onSiteConfRepository;
        this.genesisService = genesisService;
        this.torrentService = torrentService;
    }

    public List<OnLot> findAll() {
        return onLotRepository.findAll();
    }

    public Optional<OnLot> findById(Long id) {
        return onLotRepository.findById(id);
    }

    public Optional<OnLot> findByLotId(String lotId, String alternateProduct, String fab, String dataType, String site, boolean onlyFromDb) throws BusinessException {

        lotId = StringUtils.upperCase(lotId);
        alternateProduct = StringUtils.upperCase(alternateProduct);
        fab = StringUtils.upperCase(fab);
        dataType = StringUtils.upperCase(dataType);

        Optional<OnLot> byLot = onLotRepository.findByLot(lotId);

        if (onlyFromDb) {
            return byLot;
        }

        if (byLot.isEmpty() || byLot.get().getStatus() == null || !byLot.get().getStatus().isFoundAll()) {
            byLot = getLotGInfo(lotId, alternateProduct, fab, dataType, byLot, site);
        }

        //modify sourceLot in the output if sourceLotAdjustmentPattern is defined  for combination fab+datatype
        if (byLot.isPresent()) {
            final OnLot onLotClone = byLot.get().getClone();
            if (StringUtils.isNotBlank(byLot.get().getFab()) && StringUtils.isNotBlank(dataType)) {
                Optional<OnFabConf> onfabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(byLot.get().getFab(), dataType);
                //Here we want to modify sourceLot just in data returned to user but in DB record keep the original one. Because of transactional behavior and usage of Spring Data we cannot modify the original entity.
                //It would reflect in DB regardless we have already called the .save() and will not call it again. So using a clone here. More info about this behavior: https://www.baeldung.com/spring-data-crud-repository-save
                if (onfabConf.isPresent()) {
                    final String sourceLotAdjustmentPattern = onfabConf.get().getSourceLotAdjustmentPattern();
                    if (StringUtils.isNotBlank(sourceLotAdjustmentPattern)) {
                        final String modifiedSourceLot = AttributeUtils.adjustSourceLot(byLot.get().getSourceLot(), sourceLotAdjustmentPattern);
                        final String oldSourceLot = onLotClone.getSourceLot();
                        onLotClone.setSourceLot(modifiedSourceLot);
                        LOG.info("Modified the source lot from {} to {}", oldSourceLot, modifiedSourceLot);
                    }
                }
            }
            return Optional.of(onLotClone);
        }

        return byLot;
    }

    public long count() {
        return onLotRepository.count();
    }

    public void delete(OnLot onLot) {
        onLotRepository.delete(onLot);
    }

    public OnLot save(OnLot onLot) {
        if (onLot == null) {
            LOG.error("OnLot is null when saving it");
            return null;
        }
        return onLotRepository.save(onLot);
    }

    public ResponseEntity<OnLotDto> createFromDto(OnLotDto onLotDto) {
        final java.sql.Date insertTime = DateUtils.convertStringToDate(onLotDto.getInsertTime());
        if (insertTime == null) {
            final OnLotDto onLotDtoResult = new OnLotDto();
            onLotDtoResult.setStatus(Status.ERROR);
            onLotDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
            return new ResponseEntity<>(onLotDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
        }

        final OnLot onLot = save(new OnLot(onLotDto));
        final OnLotDto onLotDtoResult = new OnLotDto(onLot);
        final ResponseEntity<OnLotDto> responseModel = new ResponseEntity<>(onLotDtoResult, HttpStatus.CREATED);
        LOG.info("OnLot entity created");
        return responseModel;
    }

    public ResponseEntity<OnLotDto> updateFromDto(OnLot onLot, OnLotDto onLotDto) {
        if (onLotDto.getStatus() != null) {
            onLot.setStatus(onLotDto.getStatus());
        }
        if (onLotDto.getStatus() != null) {
            onLot.setStatus(onLotDto.getStatus());
        }
        if (onLotDto.getLot() != null) {
            onLot.setLot(StringUtils.upperCase(onLotDto.getLot()));
        }
        if (onLotDto.getParentLot() != null) {
            onLot.setParentLot(StringUtils.upperCase(onLotDto.getParentLot()));
        }
        if (onLotDto.getProduct() != null) {
            onLot.setProduct(StringUtils.upperCase(onLotDto.getProduct()));
        }
        if (onLotDto.getLotOwner() != null) {
            onLot.setLotOwner(StringUtils.upperCase(onLotDto.getLotOwner()));
        }
        if (onLotDto.getParentProduct() != null) {
            onLot.setParentProduct(StringUtils.upperCase(onLotDto.getParentProduct()));
        }
        if (onLotDto.getSourceLot() != null) {
            onLot.setSourceLot(StringUtils.upperCase(onLotDto.getSourceLot()));
        }
        if (onLotDto.getInsertTime() != null) {
            final Date insertTime = DateUtils.convertStringToDate(onLotDto.getInsertTime());
            if (insertTime == null) {
                final OnLotDto onLotDtoResult = new OnLotDto();
                onLotDtoResult.setStatus(Status.ERROR);
                onLotDtoResult.setErrorMessage(String.format("Error parsing InsertTime, format '%s'", DateUtils.DATE_FORMAT));
                return new ResponseEntity<>(onLotDtoResult, HttpStatus.METHOD_NOT_ALLOWED);
            }
            onLot.setInsertTime(insertTime);
        }
        if (onLotDto.getAlternateProduct() != null) {
            onLot.setAlternateProduct(StringUtils.upperCase(onLotDto.getAlternateProduct()));
        }
        if (onLotDto.getFab() != null) {
            onLot.setFab(StringUtils.upperCase(onLotDto.getFab()));
        }
        if (onLotDto.getLotType() != null) {
            onLot.setLotType(StringUtils.upperCase(onLotDto.getLotType()));
        }
        if (onLotDto.getLotClass() != null) {
            onLot.setLotClass(StringUtils.upperCase(onLotDto.getLotClass()));
        }
        if (onLotDto.getAlternateLot() != null) {
            onLot.setAlternateLot(StringUtils.upperCase(onLotDto.getAlternateLot()));
        }
        if (onLotDto.getSubconLot() != null) {
            onLot.setSubconLot(StringUtils.upperCase(onLotDto.getSubconLot()));
        }
        if (onLotDto.getSubconProduct() != null) {
            onLot.setSubconProduct(StringUtils.upperCase(onLotDto.getSubconProduct()));
        }
        if (onLotDto.getProductCode() != null) {
            onLot.setProductCode(StringUtils.upperCase(onLotDto.getProductCode()));
        }

        try {
            final OnLotDto onLotDtoResult = new OnLotDto(save(onLot));
            LOG.info("OnLot entity with id='{}' updated", onLot.getId());
            return new ResponseEntity<>(onLotDtoResult, HttpStatus.OK);
        } catch (Exception e) {
            final OnLotDto onLotDtoResult = new OnLotDto();
            onLotDtoResult.setStatus(Status.ERROR);
            onLotDtoResult.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(onLotDtoResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Optional<OnLot> getLotGInfo(String lot, String alternateProduct, String fab, String dataType, Optional<OnLot> byLot, String site) throws BusinessException {
        LotGInfo lotGInfo;
        final Optional<OnFabConf> onfabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fab, dataType);
        final boolean isSecondLotgQuery = onfabConf.map(OnFabConf::isSecondLotgQuery).orElse(false);

        if (byLot.isEmpty() || byLot.get().getStatus() == null || !byLot.get().getStatus().isFoundLotg()) {
            LOG.info("No data from on_lot refdb table.");
            LOG.info("Try to get from lotG WS Database via query by lot={}.", lot);
            // Fetch site configuration once (if site provided)
            final Optional<OnSiteConf> onSiteConf = StringUtils.isNotBlank(site) ? onSiteConfRepository.getBySite(site) : Optional.empty();

            // Pre-parse the site rule once per request (parse-on-demand, no caching across requests)
            final String siteRuleStr = onSiteConf.filter(c -> StringUtils.isNotBlank(c.getLotTrimRule())).map(OnSiteConf::getLotTrimRule).orElse(null);
            final com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule parsedSiteRule;
            if (siteRuleStr != null) {
                com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule tmp;
                try {
                    tmp = com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule.parse(siteRuleStr);
                } catch (Exception e) {
                    LOG.warn("Failed to parse site-level lotTrimRule='{}'. Ignoring site rule for this request.", siteRuleStr, e);
                    tmp = com.onsemi.cim.apps.exensioreftables.ws.utils.LotTrimRule.none();
                }
                parsedSiteRule = tmp;
            } else {
                parsedSiteRule = null;
            }

            // Conditional behavior: if dataType is provided AND a site-level lotTrimRule exists -> try trimmed-first.
            // Otherwise use untrimmed-first then trimmed fallback. In all cases, if assembly fails we fall back to the other
            // attempt and finally to second-lotg logic.
            boolean assemblyFailed = false;

            final boolean hasDataType = StringUtils.isNotBlank(dataType);
            final boolean hasSiteRule = parsedSiteRule != null;

            if (hasDataType && hasSiteRule) {
                // Trimmed-first when dataType is present
                java.util.Optional<String> maybeTrimmed = java.util.Optional.empty();
                try {
                    maybeTrimmed = parsedSiteRule.apply(lot);
                } catch (Exception e) {
                    LOG.warn("Failed to apply site-level lotTrimRule='{}'. Skipping trimmed attempt.", siteRuleStr, e);
                }

                if (maybeTrimmed.isPresent()) {
                    final String trimmed = maybeTrimmed.get();
                    LOG.info("[conditional trimmed-first] Attempting LotG lookup with trimmed lot '{}' using siteLotTrimRule='{}'", trimmed, siteRuleStr);
                    lotGInfo = lotGInfoService.getLotGInfo(trimmed);
                    if (lotGInfo != null && lotGInfo.getStatus() != com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status.NO_DATA) {
                        try {
                            return getLotEntityFromLotGInfoAndDataType(lotGInfo, trimmed, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
                        } catch (BusinessException be) {
                            throw be;
                        } catch (RuntimeException re) {
                            assemblyFailed = true;
                            LOG.error("Runtime error while building OnLot from trimmed LotG info for trimmedLot='{}'. Will attempt untrimmed fallback.", trimmed, re);
                        }
                    }
                } else {
                    LOG.info("Site-level lotTrimRule produced no candidate for lot='{}'.", lot);
                }

                // Fallback to untrimmed
                LOG.info("[conditional trimmed-first] Attempting LotG lookup with untrimmed lot '{}' (fallback)", lot);
                lotGInfo = lotGInfoService.getLotGInfo(lot);
                if (lotGInfo != null && lotGInfo.getStatus() != com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status.NO_DATA) {
                    try {
                        return getLotEntityFromLotGInfoAndDataType(lotGInfo, lot, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
                    } catch (BusinessException be) {
                        throw be;
                    } catch (RuntimeException re) {
                        assemblyFailed = true;
                        LOG.error("Runtime error while building OnLot from untrimmed LotG info for lot='{}'. Falling back to second-lotg logic.", lot, re);
                    }
                }

            } else {
                // Untrimmed-first flow (either no dataType or no site rule)
                LOG.info("[untrimmed-first] Attempting LotG lookup with untrimmed lot '{}' (primary)", lot);
                lotGInfo = lotGInfoService.getLotGInfo(lot);
                if (lotGInfo != null && lotGInfo.getStatus() != com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status.NO_DATA) {
                    try {
                        return getLotEntityFromLotGInfoAndDataType(lotGInfo, lot, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
                    } catch (BusinessException be) {
                        throw be;
                    } catch (RuntimeException re) {
                        assemblyFailed = true;
                        LOG.error("Runtime error while building OnLot from untrimmed LotG info for lot='{}'. Will attempt trimmed fallback if site rule exists.", lot, re);
                    }
                }

                // Attempt trimmed fallback if site rule exists
                if (hasSiteRule) {
                    java.util.Optional<String> maybeTrimmed = java.util.Optional.empty();
                    try {
                        maybeTrimmed = parsedSiteRule.apply(lot);
                    } catch (Exception e) {
                        LOG.warn("Failed to apply site-level lotTrimRule='{}'. Skipping trimmed fallback.", siteRuleStr, e);
                    }
                    if (maybeTrimmed.isPresent()) {
                        final String trimmed = maybeTrimmed.get();
                        LOG.info("[untrimmed-first fallback] Attempting LotG lookup with trimmed lot '{}' using siteLotTrimRule='{}'", trimmed, siteRuleStr);
                        lotGInfo = lotGInfoService.getLotGInfo(trimmed);
                        if (lotGInfo != null && lotGInfo.getStatus() != com.onsemi.cim.apps.exensioreftables.ws.model.lotg.Status.NO_DATA) {
                            try {
                                return getLotEntityFromLotGInfoAndDataType(lotGInfo, trimmed, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
                            } catch (BusinessException be) {
                                throw be;
                            } catch (RuntimeException re) {
                                assemblyFailed = true;
                                LOG.error("Runtime error while building OnLot from trimmed LotG info for trimmedLot='{}'. Falling back to second-lotg logic.", trimmed, re);
                            }
                        }
                    } else {
                        LOG.info("Site-level lotTrimRule produced no candidate for lot='{}'.", lot);
                    }
                }
            }
            if (lotGInfo == null && isSecondLotgQuery && fab.startsWith("JND")) {  //JND only
                final String calculatedNewJNDLot = AttributeUtils.getNewJNDLotFromOldLot(lot);
                LOG.info("No LotG data found via a query with lotId={} from LotG Database", lot);
                LOG.info("Try to use calculated new JND lotid={} derived from original lotid={}", calculatedNewJNDLot, lot);
                LOG.info("Initiate second LotG call with the new JND calculated LotId={}", calculatedNewJNDLot);
                lotGInfo = lotGInfoService.getLotGInfo(calculatedNewJNDLot);
                return getLotEntityFromLotGInfoAndDataType(lotGInfo, calculatedNewJNDLot, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
            } else if (lotGInfo == null && isSecondLotgQuery && fab.matches(".*BUCHEON.*") && fab.startsWith("KR")) { // BUCHEON only
                String modifiedLot = "";
                final Pattern usmeSplitLotLikePattern = Pattern.compile(USME_SPLIT_LOT_LIKE_REGEX);
                final Pattern usmeLotLikePattern = Pattern.compile(USME_LOT_LIKE_REGEX);
                final Matcher usmeSplitLotLikeMatchResult = usmeSplitLotLikePattern.matcher(lot);
                final Matcher usmeLotLikeMatchResult = usmeLotLikePattern.matcher(lot);

                if (usmeSplitLotLikeMatchResult.matches() && lot.length() == USME_SPLIT_LOT_LENGTH) {
                    //replace third char to zero
                    modifiedLot = AttributeUtils.replaceCharInStrPerIndex(lot, USME_SPLIT_LOT_THIRD_CHAR_REPLACEMENT, USME_SPLIT_LOT_INDEX);
                } else if (!usmeLotLikeMatchResult.matches()) {
                    //strip last character
                    modifiedLot = StringUtils.chop(lot);
                }
                LOG.info("No LotG data found using lotId={} from LotG DB", lot);
                LOG.info("Try to use modified LotId from LotId={} to modified LotId={}", lot, modifiedLot);
                LOG.info("Initiate second LotG call with modified LotId={}", modifiedLot);
                lotGInfo = lotGInfoService.getLotGInfo(modifiedLot);
                return getLotEntityFromLotGInfoAndDataType(lotGInfo, modifiedLot, alternateProduct, fab, dataType, byLot, lot, true, onfabConf, site);
            }
        } else {
            lotGInfo = null;
        }
        LOG.info("Found lot info from refdb.on_lot table with lot={}. Did not pass thru or execute the second lotG Database query.", lot);
        return getLotEntityFromLotGInfoAndDataType(lotGInfo, lot, alternateProduct, fab, dataType, byLot, lot, isSecondLotgQuery, onfabConf, site);
    }

    private Optional<OnLot> getLotEntityFromLotGInfoAndDataType(LotGInfo lotGInfo, String lotForWS, String alternateProduct, String fab, String dataType, Optional<OnLot> byLot, String originalLot, boolean isSecondLotgQuery, Optional<OnFabConf> onfabConf, String site) throws BusinessException {
        // DB entities created
        final OnLot onLot = byLot.orElseGet(OnLot::new);

        final String productFinal;
        final String productDwFinal;
        final String fabFinal;
        String lotForLotG = lotForWS;
        String lotForLTM = lotForWS;
        final String lotForMes = lotForWS;


        if (lotGInfo != null) {
            productFinal = AttributeUtils.getProduct(StringUtils.upperCase(lotGInfo.getProduct()));
            productDwFinal = StringUtils.upperCase(lotGInfo.getWaferPartAlternateProduct());
            fabFinal = StringUtils.upperCase(lotGInfo.getFab());
            //for JND and lots starts with 7G, there is no need to check in here because there is a second call using a calculated lotid which will be passed in this method ("getLotEntityFromLotGInfoAndDataType")

        } else if (byLot.isPresent()) {
            productFinal = AttributeUtils.getProduct(StringUtils.upperCase(onLot.getProduct()));
            productDwFinal = StringUtils.upperCase(onLot.getAlternateProduct());
            fabFinal = StringUtils.upperCase(onLot.getFab());
            //in here we got a result from refdb.on_lot table, check if on_fab_conf is configured to use mfg in calling LotG Web Service and LTM Web Service

            if (onfabConf.isPresent()) {
                if (onfabConf.get().getLotidForLotGWS().equalsIgnoreCase("MFG_LOT")) {
                    LOG.info("It is configured in on_fab_conf to use mfgLot={} from refdb.on_lot table to call lotG Web Service", byLot.get().getMfgLot());
                    lotForLotG = byLot.get().getMfgLot();
                }
                if (onfabConf.get().getLotidForLTM().equalsIgnoreCase("MFG_LOT")) {
                    LOG.info("It is configured in on_fab_conf to use mfgLot={} from on_lot table to call LTM Web Service", byLot.get().getMfgLot());
                    lotForLTM = byLot.get().getMfgLot();
                }
            }

        } else {
            productFinal = StringUtils.upperCase(alternateProduct);
            productDwFinal = StringUtils.upperCase(alternateProduct);
            fabFinal = StringUtils.upperCase(fab);
        }

        final SourceLotInfo lotg = getSourceLotInfo(lotForLotG, dataType, byLot, fabFinal);
        final LotInfo ltm = getLotInfo(lotForLTM, dataType, byLot, fabFinal);

        final DataWarehousePlm dwhp;
        final DataWarehouseMfgArea dwmfga;
        if (byLot.isEmpty() || byLot.get().getStatus() == null || !byLot.get().getStatus().isFoundDw()) {
            if (StringUtils.isNotEmpty(productDwFinal)) {
                dwhp = dataWarehouseService.getDwPlmByPartId(productDwFinal);
            } else {
                dwhp = null;
            }
            dwmfga = dataWarehouseService.getMfgAreaByMfgAreaCd(fabFinal);
        } else {
            dwhp = null;
            dwmfga = null;
        }

        if (onLot.getStatus() != null && onLot.getStatus().isFoundMfgLot()) {
            onLot.setMfgLotCalled(true);
        }

        final OnProd onProdMes;
        final MesDto mesDto;
        if (onLot.getStatus() == null || !onLot.getStatus().isFoundMes()) {
            final Optional<OnSiteConf> onSiteConf;
            if (StringUtils.isBlank(site)) {
                LOG.info("Site is not defined");
                onSiteConf = Optional.empty();
            } else {
                LOG.info("Site is {}", site);
                onSiteConf = onSiteConfRepository.getBySite(site);
            }
            if (onSiteConf.isPresent()) {
                LOG.info("OnSiteConf data for site='{}' obtained", site);
                final OnSiteConf conf = onSiteConf.get();
                if (conf.getMesType() == null) {
                    // Defensive: if mesType was not configured, do not attempt MES calls
                    onProdMes = null;
                    mesDto = null;
                    LOG.info("OnSiteConf for site='{}' has no mesType defined. Not calling the query", site);
                } else {
                    switch (conf.getMesType()) {
                        case GENESIS:
                            onLot.setMesAvailable(true);
                            mesDto = genesisService.getMesDto(site, lotForMes);
                            if (mesDto != null && StringUtils.isNotBlank(mesDto.getProduct())) {
                                onProdMes = onProdRepository.findByProduct(mesDto.getProduct()).orElseGet(OnProd::new);
                            } else {
                                onProdMes = new OnProd();
                                LOG.info("MES call (GENESIS) returned no product for site='{}', lot='{}'. Skipping onProd lookup.", site, lotForMes);
                            }
                            onLot.setMesCalled(true);
                            break;
                        case TORRENT:
                            onLot.setMesAvailable(true);
                            mesDto = torrentService.getMesDto(site, lotForMes);
                            if (mesDto != null && StringUtils.isNotBlank(mesDto.getProduct())) {
                                onProdMes = onProdRepository.findByProduct(mesDto.getProduct()).orElseGet(OnProd::new);
                            } else {
                                onProdMes = new OnProd();
                                LOG.info("MES call (TORRENT) returned no product for site='{}', lot='{}'. Skipping onProd lookup.", site, lotForMes);
                            }
                            onLot.setMesCalled(true);
                            break;
                        default:
                            onProdMes = null;
                            mesDto = null;
                            LOG.info("OnSiteConf does not have GENESIS or TORRENT MES system. Not calling the query");
                            break;
                    }
                }
            } else {
                onProdMes = null;
                mesDto = null;
                LOG.info("OnSiteConf does not exist for site '{}'. Not calling the query", site);
            }
        } else {
            // this information will be used in the setStatuses() method to set the status correctly to *_MES_*
            onLot.setMesAvailable(true);
            onLot.setMesCalled(true);
            onProdMes = null;
            mesDto = null;
        }

        final OnProd onProd;
        if (onProdMes != null) {
            onProd = onProdMes;
        } else if (StringUtils.isNotEmpty(productFinal)) {
            onProd = onProdRepository.findByProduct(productFinal).orElseGet(OnProd::new);
        } else {
            onProd = new OnProd();
        }

        // set the values to onLot and onProd according to the priority; the lower the highr priority
        if (dwhp != null) {
            caller.assignDwhpResults(dwhp, onProd);
        }
        if (dwmfga != null) {
            caller.assignDwMfgaResults(dwmfga, onLot, onProd);
        }
        if (lotGInfo != null) {
            caller.assignLotGInfoResults(lotGInfo, originalLot, onLot, onProd);
        } else if (StringUtils.isNotEmpty(productFinal)) {
            onLot.setProduct(productFinal);
            onProd.setProduct(productFinal);
        }
        if (lotg != null) {
            caller.assignLotgResults(lotg, onLot);
        }
        if (ltm != null) {
            caller.assignLtmResults(ltm, onLot);
        }
        if (mesDto != null) {
            caller.assignMesResults(mesDto, onLot, onProd);
        }

        setStatuses(onLot, onProd, lotGInfo, lotg, dwhp, ltm);

        if (onLot.getStatus() != Status.NO_DATA && (onLot.isMesAvailable() == onLot.isMesCalled() || StringUtils.isNotBlank(onLot.getProduct()))) {
            final java.util.Date now = new java.util.Date();
            onLot.setInsertTime(DateUtils.getSqlDateFromUtilDate(now));
            onProd.setInsertTime(DateUtils.getSqlDateFromUtilDate(now));

            if (StringUtils.isNotBlank(onLot.getLot())) {
                if (isSecondLotgQuery) {
                    onLot.setLot(originalLot);
                }
                onLotRepository.save(onLot);
            }
            if (StringUtils.isNotBlank(onProd.getProduct())) {
                onProdRepository.save(onProd);
            }
        }

        return Optional.of(onLot);
    }

    private LotInfo getLotInfo(String lot, String dataType, Optional<OnLot> byLot, String fabFinal) {
        LotInfo ltm;
        if (byLot.isEmpty() || byLot.get().getStatus() == null || !byLot.get().getStatus().isFoundLtm()) {
            Optional<OnFabConf> onFabConf = onFabConfService.getByFabAndDataTypeOrFabOnly(fabFinal, dataType);
            if (onFabConf.isPresent()) {
                if (onFabConf.get().getLtmUrl() != null) {
                    try {
                        ltm = caller.getLtm(onFabConf.get().getLtmUrl(), lot);
                    } catch (BusinessException | LotMissingInLtmException e) {
                        LOG.error(String.format("Cannot load LTM data for url='%s', lotId='%s' because of an error", onFabConf.get().getLtmUrl(), lot));
                        ltm = null;
                    }
                } else {
                    LOG.error("Cannot load LTM data lotId='{}', because URL is not defined for fab='{}' and dataType='{}'", lot, fabFinal, dataType);
                    ltm = null;
                }
            } else {
                LOG.error("Cannot load LTM data for lotId='{}', because ON_FAB_CONF is not defined for fab='{}' and dataType='{}'", lot, fabFinal, dataType);
                ltm = null;
            }
        } else {
            ltm = null;
        }
        return ltm;
    }

    private SourceLotInfo getSourceLotInfo(String lot, String dataType, Optional<OnLot> byLot, String fabFinal) {
        SourceLotInfo lotg;
        String lotgWsUrl = ertConfService.getStringByName(ErtConfService.WS_LOTG_URL);
        if (byLot.isEmpty() || byLot.get().getStatus() == null || !byLot.get().getStatus().isFoundMfgLot()) {
            if (lotgWsUrl != null) {
                try {
                    lotg = caller.getLotg(lotgWsUrl, lot);
                } catch (BusinessException | LotMissingInLtmException e) {
                    LOG.error(String.format("Cannot load LotG data for url='%s', lotId='%s' because of an error", lotgWsUrl, lot));
                    lotg = null;
                }
            } else {
                LOG.error("Cannot load LotG data lotId='{}', because URL is not defined for fab='{}' and dataType='{}'", lot, fabFinal, dataType);
                lotg = null;
            }
        } else {
            lotg = null;
        }
        return lotg;
    }

    private void setStatuses(OnLot onLot, OnProd onProd, LotGInfo lotGInfo, SourceLotInfo lotg, DataWarehousePlm dwhp, LotInfo ltm) {
        Status status = null;
        if (onLot != null && onLot.getStatus() != null) {
            status = onLot.getStatus();
        } else if (onProd != null && onProd.getStatus() != null) {
            status = onProd.getStatus();
        }
        if (status == null) {
            status = Status.NO_DATA;
        }
        if (lotGInfo != null || status.isFoundLotg()) {
            if (dwhp != null || status.isFoundDw()) {
                if (ltm != null || status.isFoundLtm()) {
                    if (lotg != null && lotg.getLots() != null && !lotg.getLots().isEmpty() && (lotg.getLots().get(0).getFabLot() != null || lotg.getLots().get(0).getFoundryLot() != null) || onLot != null && onLot.isMfgLotCalled()) {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_MES_LTM_DW);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.FOUND);
                        }
                    } else {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MES_LTM_DW);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_LTM_DW);
                        }
                    }
                } else {
                    if (lotg != null && lotg.getLots() != null && !lotg.getLots().isEmpty() && (lotg.getLots().get(0).getFabLot() != null || lotg.getLots().get(0).getFoundryLot() != null) || onLot != null && onLot.isMfgLotCalled()) {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_MES_DW);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_DW);
                        }
                    } else {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MES_DW);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_DW);
                        }
                    }
                }
            } else {
                if (ltm != null || status.isFoundLtm()) {
                    if (lotg != null && lotg.getLots() != null && !lotg.getLots().isEmpty() && (lotg.getLots().get(0).getFabLot() != null || lotg.getLots().get(0).getFoundryLot() != null) || onLot != null && onLot.isMfgLotCalled()) {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_MES_LTM);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_LTM);
                        }
                    } else {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MES_LTM);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_LTM);
                        }
                    }
                } else {
                    if (lotg != null && lotg.getLots() != null && !lotg.getLots().isEmpty() && (lotg.getLots().get(0).getFabLot() != null || lotg.getLots().get(0).getFoundryLot() != null) || onLot != null && onLot.isMfgLotCalled()) {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT_MES);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MFGLOT);
                        }
                    } else {
                        if (onLot != null && onLot.isMesAvailable() && onLot.isMesCalled()) {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG_MES);
                        } else {
                            setOnLotOnProdStatus(onLot, onProd, Status.LOTG);
                        }
                    }
                }
            }
        } else {
            if (dwhp != null || status.isFoundDw()) {
                if (ltm != null || status.isFoundLtm()) {
                    setOnLotOnProdStatus(onLot, onProd, Status.LTM_DW);
                } else {
                    setOnLotOnProdStatus(onLot, onProd, Status.DW);
                }
            } else {
                if (ltm != null || status.isFoundLtm()) {
                    setOnLotOnProdStatus(onLot, onProd, Status.LTM);
                } else {
                    setOnLotOnProdStatus(onLot, onProd, Status.NO_DATA);
                }
            }
        }
    }

    private void setOnLotOnProdStatus(OnLot onLot, OnProd onProd, Status status) {
        if (onLot != null && onLot.getStatus() != Status.MANUAL) {
            onLot.setStatus(status);
        }
        if (onProd != null && onProd.getStatus() != Status.MANUAL) {
            onProd.setStatus(status);
        }
    }
}
