package com.onsemi.cim.apps.exensioreftables.ws.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Miroslav Warchil (warchil@edhouse.cz)
 */
public class CustomJdbcConverters {

    public static List<Converter> getCustomConversions() {
        return Arrays.asList(
                TimestampToZonedDateTimeConverter.INSTANCE,
                ZonedDateTimeToTimestampConverter.INSTANCE,
                BigDecimalToBooleanConverter.INSTANCE,
                BooleanToBigDecimalConverter.INSTANCE
        );
    }

    /**
     * Custom implementation of reading converter performing conversion from {@link Timestamp} to {@link ZonedDateTime}.
     */
    @ReadingConverter
    enum TimestampToZonedDateTimeConverter implements Converter<Timestamp, ZonedDateTime> {

        INSTANCE;

        @Override
        public ZonedDateTime convert(Timestamp source) {
            LocalDateTime localDateTimeNoTimeZone = source.toLocalDateTime();
            return localDateTimeNoTimeZone.atZone(ZoneId.systemDefault());
        }
    }

    /**
     * Custom implementation of reading converter performing conversion from {@link ZonedDateTime} to {@link Timestamp}.
     */
    @WritingConverter
    enum ZonedDateTimeToTimestampConverter implements Converter<ZonedDateTime, Timestamp> {

        INSTANCE;

        @Override
        public Timestamp convert(ZonedDateTime source) {
            return Timestamp.valueOf(source.toLocalDateTime());
        }
    }

    /**
     * Custom implementation of reading converter performing conversion from {@link BigDecimal} to {@link Boolean}.
     */
    @ReadingConverter
    enum BigDecimalToBooleanConverter implements Converter<BigDecimal, Boolean> {

        INSTANCE;

        @Override
        public Boolean convert(BigDecimal source) {
            if (source == null) {
                return null;
            }

            if (source.equals(new BigDecimal(0))) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Custom implementation of reading converter performing conversion from {@link Boolean} to {@link BigDecimal}.
     */
    @WritingConverter
    enum BooleanToBigDecimalConverter implements Converter<Boolean, BigDecimal> {

        INSTANCE;

        @Override
        public BigDecimal convert(Boolean source) {
            if (source == null) {
                return null;
            }
            if (source) {
                return new BigDecimal(1);
            } else {
                return new BigDecimal(0);
            }
        }
    }
}
