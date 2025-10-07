package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.DeviceMissingInWmcException;
import com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc.WaferMapConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Offers methods for loading data from WMC web service
 *
 * @author fg6zdy
 */
public class WmcLoader {

    private static final Logger LOG = LoggerFactory.getLogger(WmcLoader.class);

    private static final String STATUS_FOUND = "FOUND";


    private WmcLoader() {
    }

    /**
     * Loads Wafer map configuration from WMC Web service for given device. First check if url or lot input variables
     * are not null or empty. Then query WMC and returns WaferMapConfiguration
     *
     * @param url    WMC url
     * @param device Device to query
     * @return
     */
    public static WaferMapConfiguration loadWmcByDeviceName(
            String url,
            String device) throws BusinessException {

        LOG.info("Obtaining Wafer map configuration SourceLotInfo by url='{}', device='{}'", url, device);

        if (device == null || device.isEmpty()) {
            throw new BusinessException("WMC device is null or is not set");
        }

        if (url == null || url.isEmpty()) {
            throw new BusinessException("WMC URL is not set");
        }

        LOG.debug("WMC {}; values {}", url, device);
        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class, device);

        if (config == null) {
            throw new BusinessException(String.format("LTM Web service is not available: %s", url));
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("Device %s is missing in WMC", device));
        }

        LOG.info("Wafer ma configuration obtained by device='{}', WaferMapConfiguration='{}'", device, config);
        return config;
    }

    /**
     * Loads Wafer map configuration from WMC Web service for given params.
     * First check. if url or other params are empty or null. Then query WMC config
     *
     * @param url
     * @param device
     * @param testerLocation
     * @param testerDataType
     * @param flat
     * @param coordOrientation
     * @param endDate
     * @return WaferMapConfiguration for given params
     * @throws RuntimeException            if either url or parameters are null or empty or WaferMapConfiguration
     *                                     returned from Web service is null
     * @throws DeviceMissingInWmcException if device is missing in WMC
     */
    public static WaferMapConfiguration loadWmcByWaferDimensions(
            String url,
            String device,
            String testerLocation,
            String testerDataType,
            String flat,
            Integer coordOrientation,
            Date endDate) throws BusinessException {

        return loadWmcByWaferDimensions(url, device, testerLocation, testerDataType, flat, coordOrientation, endDate, false);
    }

    /**
     * Loads Wafer map configuration from WMC Web service for given params.
     * First check. if url or other params are empty or null. Then query WMC config
     *
     * @param url
     * @param device
     * @param testerLocation
     * @param testerDataType
     * @param flat
     * @param coordOrientation
     * @param endDate
     * @param force            Force query WMC. Do not look for WMAP config in the cache
     * @return WaferMapConfiguration for given params
     * @return
     * @throws RuntimeException            if either url or parameters are null or empty or WaferMapConfiguration
     *                                     returned from Web service is null
     * @throws DeviceMissingInWmcException if device is missing in WMC
     */
    public static WaferMapConfiguration loadWmcByWaferDimensions(
            String url,
            String device,
            String testerLocation,
            String testerDataType,
            String flat,
            Integer coordOrientation,
            Date endDate,
            boolean force) throws BusinessException {

        if (endDate == null) {
            throw new BusinessException("WMC End date is null or is not set");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String endDateString = formatter.format(endDate);

        String key = device
                .concat("-")
                .concat(testerLocation)
                .concat("-")
                .concat(testerDataType)
                .concat("-")
                .concat(flat)
                .concat("-")
                .concat(String.valueOf(coordOrientation))
                .concat("-")
                .concat(endDateString);

        if (url == null || url.isEmpty()) {
            throw new BusinessException("WMC URL is not set");
        }

        if (device.isEmpty()) {
            throw new BusinessException("WMC device is null or is not set");
        }

        if (testerLocation.isEmpty()) {
            throw new BusinessException("WMC Tester location is null or is not set");
        }

        if (testerDataType.isEmpty()) {
            throw new BusinessException("WMC Tester data type is null or is not set");
        }

        if (flat.isEmpty()) {
            throw new BusinessException("WMC Flat is null or is not set");
        }

        if (coordOrientation == null || coordOrientation == 0) {
            throw new BusinessException("WMC CO is null or is not set");
        }

        /* Query WMC */
        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class,
                device, testerLocation, testerDataType, flat, coordOrientation, endDateString);

        if (config == null) {
            throw new BusinessException("LTM Web service is not available: " + url);
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("WMC device %s is missing in WMC", device));
        }

        return config;
    }

    /**
     * Loads WMAP config or null if not exist
     *
     * @param url
     * @param device
     * @param flat
     * @param coordOrientation
     * @return
     */
    public static WaferMapConfiguration loadPcmWmc(
            String url,
            String device,
            String flat,
            Integer coordOrientation) throws BusinessException {

        return loadPcmWmc(url, device, flat, coordOrientation, false);
    }

    /**
     * Loads WMAP config or null if not exist
     *
     * @param url
     * @param device
     * @param flat
     * @param coordOrientation
     * @param force
     * @return
     */
    public static WaferMapConfiguration loadPcmWmc(
            String url,
            String device,
            String flat,
            Integer coordOrientation,
            boolean force) throws BusinessException {

        String key = device.concat("-").concat(flat).concat("-").concat(String.valueOf(coordOrientation));
        LOG.debug("WMC key {}", key);

        if (url == null || url.isEmpty()) {
            throw new BusinessException("WMC URL is not set");
        }

        if (device.isEmpty()) {
            throw new BusinessException("WMC device is null or is not set");
        }

        if (flat.isEmpty()) {
            throw new BusinessException("WMC Flat is null or is not set");
        }

        if (coordOrientation == null || coordOrientation == 0) {
            throw new BusinessException("WMC CO is null or is not set");
        }

        /* Query WMC */
        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class,
                device, coordOrientation, flat);

        if (config == null) {
            throw new BusinessException("WMC Web service is not available: " + url);
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("WMC device %s is missing in WMC", device));
        }

        return config;
    }

    public static WaferMapConfiguration loadWmcOnager(
            String url,
            String device,
            Integer coordOrientation,
            String flat,
            Integer refDieX,
            Integer refDieY) throws BusinessException {

        return loadWmcOnager(url, device, coordOrientation, flat, refDieX, refDieY, false);
    }

    /**
     * Loads WMAP config for USR onager tester data
     *
     * @param url
     * @param device
     * @param coordOrientation
     * @param flat
     * @param refDieX
     * @param refDieY
     * @param force
     * @return
     */
    public static WaferMapConfiguration loadWmcOnager(
            String url,
            String device,
            Integer coordOrientation,
            String flat,
            Integer refDieX,
            Integer refDieY,
            boolean force) throws BusinessException {

        String key = device
                .concat("-")
                .concat(flat)
                .concat("-")
                .concat(String.valueOf(coordOrientation))
                .concat("-")
                .concat(String.valueOf(refDieX))
                .concat("-")
                .concat(String.valueOf(refDieY));

        /* Query WMC */
        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class,
                device, coordOrientation, flat, refDieX, refDieY);

        if (config == null) {
            throw new BusinessException("WMC Web service is not available: " + url);
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("WMC device %s is missing in WMC", device));
        }

        return config;
    }

    /**
     * USR specific WMAP config loading for mapper and integrator data.
     *
     * @param url
     * @param device
     * @param quadrant
     * @param flat
     * @param refDieX
     * @param refDieY
     * @param refDieXOffset
     * @param refDieYOffset
     * @param firstActiveDieX
     * @param firstActiveDieY
     * @param platform
     * @return
     */
    public static WaferMapConfiguration loadWmcByRefDie(
            String url,
            String device,
            Integer quadrant,
            String flat,
            Integer refDieX,
            Integer refDieY,
            Integer refDieXOffset,
            Integer refDieYOffset,
            Integer firstActiveDieX,
            Integer firstActiveDieY,
            String platform) throws BusinessException {

        return loadWmcByRefDie(url, device, quadrant, flat, refDieX, refDieY, refDieXOffset, refDieYOffset, firstActiveDieX, firstActiveDieY, platform, false);
    }

    /**
     * USR specific WMAP config loading for mapper and integrator data.
     *
     * @param url
     * @param device
     * @param quadrant
     * @param flat
     * @param refDieX
     * @param refDieY
     * @param refDieXOffset
     * @param refDieYOffset
     * @param firstActiveDieX
     * @param firstActiveDieY
     * @param platform
     * @param force
     * @return WMAP config
     */
    public static WaferMapConfiguration loadWmcByRefDie(
            String url,
            String device,
            Integer quadrant,
            String flat,
            Integer refDieX,
            Integer refDieY,
            Integer refDieXOffset,
            Integer refDieYOffset,
            Integer firstActiveDieX,
            Integer firstActiveDieY,
            String platform,
            boolean force) throws BusinessException {

        if (url == null || url.isEmpty()) {
            throw new BusinessException("WMC URL is not set");
        }

        if (device == null || device.isEmpty()) {
            throw new BusinessException("WMC Device is null or is not set");
        }

        if (quadrant == null) {
            throw new BusinessException("WMC Quadrant location is null or is not set");
        }

        if (flat == null || flat.isEmpty()) {
            throw new BusinessException("WMC Flat is null or is not set");
        }

        if (platform == null || platform.isEmpty()) {
            throw new BusinessException("Platform is null or is not set");
        }

        /* Query WMC */
        String key = device
                .concat("-")
                .concat(String.valueOf(quadrant))
                .concat("-")
                .concat(flat)
                .concat("-")
                .concat(String.valueOf(refDieX))
                .concat("-")
                .concat(String.valueOf(refDieY))
                .concat("-")
                .concat(String.valueOf(refDieXOffset))
                .concat("-")
                .concat(String.valueOf(refDieYOffset))
                .concat("-")
                .concat(String.valueOf(firstActiveDieX))
                .concat("-")
                .concat(String.valueOf(firstActiveDieY))
                .concat("-")
                .concat(platform);

        LOG.debug("WMC {}; values {}", url, key);
        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class,
                device, quadrant, flat,
                refDieX, refDieY, refDieXOffset, refDieYOffset, firstActiveDieX, firstActiveDieY, platform
        );

        if (config == null) {
            throw new BusinessException(String.format("LTM Web service is not available: %s", url));
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("WMC device %s is missing in WMC", device));
        }

        if (config.getStatus().equals(WaferMapConfiguration.GENERATOR_ERROR)) {
            throw new BusinessException(String.format("WMC device %s generator fails", device));
        }

        return config;
    }

    /**
     * Loads Wafer map configuration from WMC Web service for given WMC id obtained from matchup Web service.
     * First check if url or lot input variables are not null or empty. Then query WMC and returns WaferMapConfiguration
     *
     * @param url
     * @param idWmc
     * @return WaferMapConfiguration for given ID
     * @throws RuntimeException            if either url or id is null or empty or WaferMapConfiguration
     *                                     returned from Web service is null
     * @throws DeviceMissingInWmcException if Id is missing in WMC
     */
    public static WaferMapConfiguration loadWmcByWmcId(
            String url,
            Long idWmc) throws BusinessException {

        return loadWmcByWmcId(url, idWmc, false);
    }

    /**
     * Loads Wafer map configuration from WMC Web service for given WMC id obtained from matchup Web service.
     * First check if url or lot input variables are not null or empty. Then query WMC and returns WaferMapConfiguration
     *
     * @param url
     * @param idWmc
     * @param force Force load WMAP config. Do not look for it in cache
     * @return WaferMapConfiguration for given ID
     * @throws RuntimeException            if either url or id is null or empty or WaferMapConfiguration
     *                                     returned from Web service is null
     * @throws DeviceMissingInWmcException if Id is missing in WMC
     */
    public static WaferMapConfiguration loadWmcByWmcId(
            String url,
            Long idWmc,
            boolean force) throws BusinessException {

        if (idWmc == null || idWmc == 0) {
            throw new BusinessException("WMC ID is null or is not set");
        }

        if (url == null || url.isEmpty()) {
            throw new BusinessException("WMC URL is not set");
        }

        WaferMapConfiguration config = new RestTemplate().getForObject(url, WaferMapConfiguration.class, idWmc);

        if (config == null) {
            throw new BusinessException("LTM Web service is not available: " + url);
        }

        if (!config.getStatus().equalsIgnoreCase(STATUS_FOUND)) {
            throw new DeviceMissingInWmcException(String.format("WMC ID %d is missing in WMC", idWmc));
        }

        return config;
    }
}
