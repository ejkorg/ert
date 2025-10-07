/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onsemi.cim.apps.exensioreftables.ws.ws;

import com.onsemi.cim.apps.exensioreftables.ws.exception.ws.MissingSetupInMatchupException;
import com.onsemi.cim.apps.wafer.configuration.info.WaferMapMatchup;
import org.springframework.web.client.RestTemplate;

/**
 * Loads Info from Matchup Web service
 */
public class MatchupLoader {
    private MatchupLoader(){}

     /**
     * Loads matchup by given url, lot, scribe and end date.
     * @param url
     * @param lot
     * @param scribe
     * @param endDate
     * @return WaferMapMatchup object
     * @throws RuntimeException if either url or parameters are null or empty or WaferMapMatchup returned from Web service is null.
     * @throws MissingSetupInMatchupException if the matchup status is equal to MISSING_SETUP.
     */
    public static WaferMapMatchup loadMatchupByLotScribeEndDate(String url, String lot, String scribe, String endDate){

        if(url == null || url.isEmpty()){
            throw new RuntimeException("Matchup URL parameter is not set");
        }

        if(lot == null || lot.isEmpty()){
            throw new RuntimeException("Matchup lot parameter is not set");
        }

        if(scribe == null || scribe.isEmpty()){
            throw new RuntimeException("Matchup scribe parameter is not set");
        }

        if(endDate == null){
            throw new RuntimeException("Matchup end date parameter is not set");
        }

        WaferMapMatchup matchup = new RestTemplate().getForObject(url, WaferMapMatchup.class, lot, scribe, endDate);

        if(matchup == null){
            throw new RuntimeException("WMC config matchup Web service is not available: " + url);
        }

        if("MISSING_SETUP".equals(matchup.getStatus())){
            throw new MissingSetupInMatchupException("MISSING_SETUP status of matchup for scribe " + scribe + " for lot " + lot + " for endDate " + endDate + ".");
        }

        if("ERROR".equals(matchup.getStatus())){
            throw new RuntimeException("ERROR status of matchup for scribe " + scribe + " for lot " + lot + " for endDate " + endDate + ".");
        }

        return matchup;
    }


}
