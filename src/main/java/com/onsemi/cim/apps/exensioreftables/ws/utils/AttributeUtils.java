package com.onsemi.cim.apps.exensioreftables.ws.utils;

import com.onsemi.cim.apps.exensioreftables.ws.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttributeUtils {

    //So far it is possible to use these four placeholders in the pattern.
    //If there will be need in future to enhance this solution by any more sophisticated options (like possibility to define leading/trailing zeros/characters etc.) - still keep also these placeholders for backward compatibility.
    private static final String PATTERN_PLACEHOLDER_REGEX = "(?<placeholder>\\{(\\w*)})(\\[(?<charactersCount>-?\\d{1,2})])?";
    private static final String PLACEHOLDER_WAFER_NUMBER = "{waferNumber}";
    private static final String PLACEHOLDER_WAFER_NUMBER_WITH_ZERO = "{waferNumberWithZero}";
    private static final String PLACEHOLDER_LOT_ID = "{lotId}";
    private static final String PLACEHOLDER_SOURCE_LOT = "{sourceLot}";
    private static final String PLACEHOLDER_SOURCE_LOT_WITHOUT_DOT_S = "{sourceLotWithoutDotS}";
    private static final List<String> ALL_PLACEHOLDERS = Arrays.asList(PLACEHOLDER_WAFER_NUMBER, PLACEHOLDER_WAFER_NUMBER_WITH_ZERO, PLACEHOLDER_LOT_ID, PLACEHOLDER_SOURCE_LOT, PLACEHOLDER_SOURCE_LOT_WITHOUT_DOT_S);

    private static final Logger LOG = LoggerFactory.getLogger(AttributeUtils.class);

    private AttributeUtils() {
    }

    public static String getProduct(final String product) {
        String value = product;
        if (value != null && value.length() >= 4 && value.matches(".*-[A-Z]{3}$")) {
            value = value.substring(0, value.length() - 4);
            return value;
        }
        return product;
    }

    public static String calculateWaferId(String lotId, Integer waferNumber, String sourceLot, String pattern) throws BusinessException {

        if (waferNumber == null) {
            throw new BusinessException(String.format("Cannot calculate waferId for lotId %s. WaferNumber is null.", lotId));
        }

        String waferNumberString = Integer.toString(waferNumber);
        String waferNumberPadded = StringUtils.leftPad(waferNumberString, 2, '0');

        if (StringUtils.isBlank(pattern)) {
            // no pattern defined  - use default implementation {lotId}-{waferNumberWithZero}
            String result = lotId + "-" + waferNumberPadded;
            LOG.info("Generating WaferId by default pattern => '{}'", result);
            return result;

        } else {
            final String sourceLotWithoutDotS;
            if (sourceLot != null && sourceLot.endsWith(".S")) {
                sourceLotWithoutDotS = sourceLot.substring(0, sourceLot.length() - 2);
            } else {
                sourceLotWithoutDotS = sourceLot;
            }

            final Map<String, String> values = new HashMap<>();
            values.put(PLACEHOLDER_LOT_ID, lotId);
            values.put(PLACEHOLDER_SOURCE_LOT, sourceLot);
            values.put(PLACEHOLDER_SOURCE_LOT_WITHOUT_DOT_S, sourceLotWithoutDotS);
            values.put(PLACEHOLDER_WAFER_NUMBER, waferNumberString);
            values.put(PLACEHOLDER_WAFER_NUMBER_WITH_ZERO, waferNumberPadded);

            String result = pattern;
            // pattern defined, check if OK and use it.
            final Pattern regexToFindPlaceholders = Pattern.compile(PATTERN_PLACEHOLDER_REGEX);
            final Matcher matcher = regexToFindPlaceholders.matcher(pattern);
            while (matcher.find()) {
                final String placeholder = matcher.group("placeholder");
                checkPlaceholders(placeholder, pattern);
                String characterCountString;
                int charactersCount;
                characterCountString = matcher.group("charactersCount");
                charactersCount = getCharactersCount(pattern, placeholder, characterCountString);

                if (values.get(placeholder) != null) {
                    result = StringUtils.replaceOnce(result, placeholder, getCharacters(values.get(placeholder), charactersCount));
                } else {
                    throw new BusinessException(String.format("WAFERID_CREATION_PATTERN '%s': Value for placeholder '%s' is not defined ", pattern, placeholder));
                }
                if (characterCountString != null) {
                    result = StringUtils.replaceOnce(result, "[" + characterCountString + "]", "");
                }
            }

            // use the pattern
            LOG.info("Generating WaferId by pattern '{}' => '{}'", pattern, result);
            return result;
        }
    }

    /**
     * returns Characters count to be taken from the beginning of the placeholder value
     */
    private static int getCharactersCount(String pattern, String placeholder, String charactersCountString) throws BusinessException {
        int charactersCount;
        if (charactersCountString != null) {
            try {
                charactersCount = Integer.parseInt(charactersCountString);
                if (charactersCount < 0) {
                    throw new BusinessException(String.format("WAFERID_CREATION_PATTERN '%s' contains placeholder '%s' with incorrectly defined characters count '%s'. Must be an integer number greater than 0.", pattern, placeholder, charactersCount));
                }
            } catch (NumberFormatException nfe) {
                throw new BusinessException(String.format("WAFERID_CREATION_PATTERN '%s' contains placeholder '%s' with incorrectly defined characters count '%s'. Must be an integer number greater than 0.", pattern, placeholder, charactersCountString));
            }
        } else {
            charactersCount = 0;
        }
        return charactersCount;
    }

    private static void checkPlaceholders(String placeholder, String pattern) throws BusinessException {
        if (!ALL_PLACEHOLDERS.contains(placeholder)) {
            throw new BusinessException(String.format("WAFERID_CREATION_PATTERN '%s' contains invalid placeholder: %s Can be used one of these: %s.",
                    pattern, placeholder, ALL_PLACEHOLDERS));
        }
    }

    private static String getCharacters(String value, int count) {
        if (count > 0) {
            return StringUtils.substring(value, 0, count);
        }
        return value;
    }

    /**
     * Adjusts sourceLot according to the pattern.
     * Pattern must be in format {prefix}[N]{suffix} where N is number of characters used from the original sourceLot.
     * <p>
     * Example:
     * sourceLot = ABCD12345
     * pattern = [8].S
     * result = ABCD1234.S
     * removed the business exception setup if charactersCount from on_fab_conf.source_lot_adjustment pattern
     * is greater than sourceLot length, in this case it will just return the source lot without doing an adjustment.
     *
     * @param sourceLot
     * @param pattern
     * @return
     * @throws BusinessException
     */
    public static String adjustSourceLot(String sourceLot, String pattern) throws BusinessException {

        if (StringUtils.countMatches(pattern, "[") != 1 || StringUtils.countMatches(pattern, "]") != 1 || !pattern.matches(".*\\[\\d{0,2}].*")) {
            throw new BusinessException(String.format("Tried to adjust SourceLot value but have incorrect SOURCELOT_ADJUSTMENT_PATTERN in DB: %s. " +
                    "Pattern must be in format {suffix}[N]{prefix} - sufix/prefix can be any text and N must be max two digits number which means how many characters from the original sourceLot should be used. " +
                    "If want to use whole lot, use empty brackets. Example: [8].S means that will be used first 8 characters of original sourceLot and added suffix .S", pattern));
        }

        int leftBracketIndex = pattern.indexOf("[");
        int rightBracketIndex = pattern.indexOf("]");

        String resultLotPart = sourceLot;
        if ((rightBracketIndex - leftBracketIndex) > 1) { //if brackets in pattern are empty, use whole sourceLot, otherwise use just part of it defined by the number in the brackets
            String charactersCountString = pattern.substring(leftBracketIndex + 1, rightBracketIndex).trim();
            int charactersCount = Integer.parseInt(charactersCountString);
            if (charactersCount > sourceLot.length()) {
                LOG.info("Source Lot adjustment pattern length is greater than the actual Source Lot length, no need to undergo Source Lot adjustment process, actual" +
                        "Source Lot will be returned.");
                return resultLotPart;
            }
            resultLotPart = sourceLot.substring(0, charactersCount);
        }
        return pattern.replaceAll("\\[.*]", resultLotPart);
    }

    public static String replaceCharInStrPerIndex(String str, char ch, int index) {
        StringBuilder replacedCharStr = new StringBuilder(str);
        replacedCharStr.setCharAt(index, ch);
        return replacedCharStr.toString();
    }

    public static String removeLastCharRegexOptional(String s) {
        return Optional.ofNullable(s)
                .map(str -> str.replaceAll(".$", ""))
                .orElse(s);
    }

    /*
        return constructed new JND Lotid out from JND old Lotid.
    */
    public static String getNewJNDLotFromOldLot(String jndOldLot) {
        String middleLotValue = "";
        String lotIdSuffix = "";
        String jndOldLotSubstring = "";

        if (jndOldLot.length() >= 16) {
            middleLotValue = jndOldLot.substring(9, 13);
            lotIdSuffix = jndOldLot.substring(14, 16);
            if (jndOldLot.matches("^7G.*-A.*")) {
                return ("JM1" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-B.*")) {
                return ("JM2" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-C.*")) {
                return ("JM3" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-E.*")) {
                return ("JM4" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-F.*")) {
                return ("JM5" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-G.*")) {
                return ("JM6" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-H.*")) {
                return ("JM7" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-J.*")) {
                return ("JM8" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.matches("^7G.*-K.*")) {
                return ("JM9" + middleLotValue + lotIdSuffix);
            }
        } else if (jndOldLot.length() == 14) { //7G0FKT2H910501
            middleLotValue = jndOldLot.substring(8, 12);
            lotIdSuffix = jndOldLot.substring(12, 14);
            jndOldLotSubstring = jndOldLot.substring(7, 8);
            if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("A")) {
                return ("JM1" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("B")) {
                return ("JM2" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("C")) {
                return ("JM3" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("E")) {
                return ("JM4" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("F")) {
                return ("JM5" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("G")) {
                return ("JM6" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("H")) {
                return ("JM7" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("J")) {
                return ("JM8" + middleLotValue + lotIdSuffix);
            } else if (jndOldLot.startsWith("7G") && jndOldLotSubstring.matches("K")) {
                return ("JM9" + middleLotValue + lotIdSuffix);
            }
        } else if (jndOldLot.matches("^7G.*") || jndOldLot.matches("^4E.*")) {
            return jndOldLot;
        }
        return jndOldLot;
    }
}
