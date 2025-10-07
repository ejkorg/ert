package com.onsemi.cim.apps.exensioreftables.ws.service.application.db;

import com.onsemi.cim.apps.exensioreftables.ws.entity.application.DataType;
import com.onsemi.cim.apps.exensioreftables.ws.entity.application.ErtConf;
import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import com.onsemi.cim.apps.exensioreftables.ws.repository.application.ErtConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ErtConfService {

    public static final String WS_LOTG_URL = "WS_LOTG_URL";
    public static final String WS_LTM_URL = "WS_LTM_URL";
    public static final String WS_WMC_URL = "WS_WMC_URL";
    public static final String WS_WMC_ID_URL = "WS_WMC_ID_URL";
    public static final String WS_MATCHUP_SCRIBE_URL = "WS_MATCHUP_SCRIBE_URL";
    public static final String WS_MAP_REPOSITORY_URL = "WS_MAP_REPOSITORY_URL";
    public static final String WS_SCRIBE_TO_VID_URL = "WS_SCRIBE_TO_VID_URL";
    public static final String WS_VID_TO_SCRIBE_URL = "WS_VID_TO_SCRIBE_URL";

    private static final Logger LOG = LoggerFactory.getLogger(ErtConfService.class);

    private final ErtConfRepository ertConfRepository;


    public ErtConfService(ErtConfRepository ertConfRepository) {
        this.ertConfRepository = ertConfRepository;
    }


    public Optional<ErtConf> getByName(String confName) {
        return ertConfRepository.getByConfName(confName);
    }

    public Object getObjectByName(String confName) {
        try {
            return getObjectByName(confName, false);
        } catch (BusinessException e) {
            return null;
        }
    }

    public Object getObjectByName(String confName, boolean exception) throws BusinessException {
        LOG.info("Getting object value by name '{}'", confName);
        Optional<ErtConf> ertConfOptional = ertConfRepository.getByConfName(confName);
        if (ertConfOptional.isPresent()) {
            ErtConf ertConf = ertConfOptional.get();
            switch (ertConf.getDataType()) {
                case STRING:
                    LOG.info("String value '{}' returned for name '{}'", ertConf.getValueString(), confName);
                    return ertConf.getValueString();
                case NUMBER:
                    LOG.info("Number value '{}' returned for name '{}'", ertConf.getValueNumber(), confName);
                    return ertConf.getValueNumber();
                case BOOLEAN:
                    LOG.info("Boolean value '{}' returned for name '{}'", ertConf.isValueBoolean(), confName);
                    return ertConf.isValueBoolean();
                default:
                    if (exception) {
                        throw new BusinessException(String.format("Config property with name '%s' is not of type STRING, NUMBER or BOOLEAN, it is '%s'", confName, ertConf.getDataType()));
                    } else {
                        LOG.info("Config property with name '{}' is not of type STRING, NUMBER or BOOLEAN, it is '{}', returning null", confName, ertConf.getDataType());
                        return null;
                    }
            }
        } else {
            return handleMissingValue(confName, exception);
        }
    }

    private Object handleMissingValue(String confName, boolean exception) throws BusinessException {
        if (exception) {
            throw new BusinessException(String.format("Config property with name '%s' is not present in DB", confName));
        } else {
            LOG.info("Config property with name '{}' is not present in DB, returning null", confName);
            return null;
        }
    }

    public String getStringByName(String confName) {
        try {
            return getStringByName(confName, false);
        } catch (BusinessException e) {
            return null;
        }
    }

    public String getStringByName(String confName, boolean exception) throws BusinessException {
        LOG.info("Getting string value by name '{}'", confName);
        Optional<ErtConf> ertConfOptional = ertConfRepository.getByConfName(confName);
        if (ertConfOptional.isPresent()) {
            ErtConf ertConf = ertConfOptional.get();
            if (ertConf.getDataType() == DataType.STRING) {
                LOG.info("String value '{}' returned for name '{}'", ertConf.getValueString(), confName);
                return ertConf.getValueString();
            } else {
                if (exception) {
                    throw new BusinessException(String.format("Config property with name '%s' is not of type STRING, it is '%s'", confName, ertConf.getDataType()));
                } else {
                    LOG.info("Config property with name '{}' is not of type STRING, it is '{}', returning null", confName, ertConf.getDataType());
                    return null;
                }
            }
        } else {
            return (String) handleMissingValue(confName, exception);
        }
    }

    public Long getNumberByName(String confName) {
        try {
            return getNumberByName(confName, false);
        } catch (BusinessException e) {
            return null;
        }
    }

    public Long getNumberByName(String confName, boolean exception) throws BusinessException {
        LOG.info("Getting number value by name '{}'", confName);
        Optional<ErtConf> ertConfOptional = ertConfRepository.getByConfName(confName);
        if (ertConfOptional.isPresent()) {
            ErtConf ertConf = ertConfOptional.get();
            if (ertConf.getDataType() == DataType.NUMBER) {
                LOG.info("Number value '{}' returned for name '{}'", ertConf.getValueNumber(), confName);
                return ertConf.getValueNumber();
            } else {
                if (exception) {
                    throw new BusinessException(String.format("Config property with name '%s' is not of type NUMBER, it is '%s'", confName, ertConf.getDataType()));
                } else {
                    LOG.info("Config property with name '{}' is not of type NUMBER, it is '{}', returning null", confName, ertConf.getDataType());
                    return null;
                }
            }
        } else {
            return (Long) handleMissingValue(confName, exception);
        }
    }

    public boolean getBooleanByName(String confName) {
        try {
            return getBooleanByName(confName, false);
        } catch (BusinessException e) {
            return false;
        }
    }

    public boolean getBooleanByName(String confName, boolean exception) throws BusinessException {
        LOG.info("Getting boolean value by name '{}'", confName);
        Optional<ErtConf> ertConfOptional = ertConfRepository.getByConfName(confName);
        if (ertConfOptional.isPresent()) {
            ErtConf ertConf = ertConfOptional.get();
            if (ertConf.getDataType() == DataType.BOOLEAN) {
                LOG.info("Boolean value '{}' returned for name '{}'", ertConf.isValueBoolean(), confName);
                return ertConf.isValueBoolean();
            } else {
                if (exception) {
                    throw new BusinessException(String.format("Config property with name '%s' is not of type BOOLEAN, it is '%s'", confName, ertConf.getDataType()));
                } else {
                    LOG.info("Config property with name '{}' is not of type BOOLEAN, it is '{}', returning null", confName, ertConf.getDataType());
                    return false;
                }
            }
        } else {
            return handleMissingValue(confName, exception) != null;
        }
    }
}
