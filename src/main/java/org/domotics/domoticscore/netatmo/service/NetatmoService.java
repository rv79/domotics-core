package org.domotics.domoticscore.netatmo.service;

import org.domotics.domoticscore.netatmo.model.Netatmo;
import org.domotics.domoticscore.netatmo.model.Station;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NetatmoService {

    private String token;
    private String url;
    private RestTemplate restTemplate;


    public Netatmo getData () {
        ResponseEntity<String> resp = restTemplate.getForEntity("https://api.netatmo.net/api/getstationdata?access_token=" + token, String.class);

        String json = resp.getBody();
    }
}
