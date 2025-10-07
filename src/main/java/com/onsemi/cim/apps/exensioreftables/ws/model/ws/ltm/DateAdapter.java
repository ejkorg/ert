
package com.onsemi.cim.apps.exensioreftables.ws.model.ws.ltm;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fg6zdy
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    final DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Date unmarshal(String date) throws Exception {
        if(date == null) return null;
        return df.parse(date);
    }

    @Override
    public String marshal(Date date) throws Exception {
        if(date == null) return null;
        return df2.format(date);
    }
}
