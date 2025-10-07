package com.onsemi.cim.apps.exensioreftables.ws.model.ws.wmc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author fg6zdy
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public Date unmarshal(String date) throws Exception {
        if(date == null) return null;
        return df.parse(date);
    }

    public String marshal(Date date) throws Exception {
        if(date == null) return null;
        return df.format(date);
    }
}
