package com.onsemi.cim.apps.exensioreftables.ws.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZ";

    private DateUtils() {
    }

    public static Date getSqlDateNow() {
        java.util.Date utilDate = new java.util.Date();
        return getSqlDateFromUtilDate(utilDate);
    }

    public static Date getSqlDateFromUtilDate(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
    }

    public static java.util.Date getUtilDateFromSqlDate(Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static Date convertStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            java.util.Date date = sdf.parse(dateString);
            return new Date(date.getTime());
        } catch (ParseException pe) {
            return null;
        }
    }

    public static String convertDateToString(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DateUtils.DATE_FORMAT).format(date);
    }
}
