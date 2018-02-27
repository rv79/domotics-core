package org.domotics.domoticscore.netatmo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.domotics.domoticscore.netatmo.config.NetatmoConfig;
import org.domotics.domoticscore.netatmo.model.Netatmo;
import org.domotics.domoticscore.netatmo.model.Token;
import org.domotics.domoticscore.netatmo.model.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
@Log4j2
public class NetatmoService {

    private String urlStation = "https://api.netatmo.net/api/getstationsdata";
    private String urlToken = "https://api.netatmo.com/oauth2/token";

    private NetatmoConfig config;

    private RestTemplate restTemplate;
    private Token token;
    private TokenRequest tokenRequest;

    @Autowired
    public NetatmoService(NetatmoConfig config) {
        this.config = config;
        log.info(config);
        this.restTemplate = new RestTemplate();
        this.tokenRequest = new TokenRequest(
                config.getClientId(),
                config.getClientSecret(),
                config.getUsername(),
                config.getPassword());
    }

    @PostConstruct
    public void init () {
        getToken();
    }

    @Scheduled(fixedRate = 5000L)
    synchronized public void refreshData() {
        Netatmo netatmo = getData();
        log.info(netatmo);

        netatmo.getDevices().forEach(t -> {
            log.info("DateSetup: " + t.getDateSetup());
            log.info("LastSetup: " + t.getLastSetup());
            log.info("LastUpgrade: " + t.getLastUpgrade());
            log.info("Measure: " + t.getMeasures().getTime());
        });
    }

    private Netatmo getData () {
        try {
            String json;
            ObjectMapper mapper = new ObjectMapper();

            ResponseEntity<String> resp = restTemplate.getForEntity(urlStation + "?access_token=" + token.getAccessToken(), String.class);
            json = resp.getBody();
            JsonNode body = mapper.readTree(json);
            return mapper.readValue(body.findValue("body").toString(), Netatmo.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    private void getToken () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenRequest.getRequestParams(), headers);

        token = restTemplate.postForObject(urlToken, request, Token.class);
    }

    @Scheduled(fixedDelay = 10000L)
    synchronized private void refreshToken () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(token.getRefreshParams(tokenRequest), headers);

        token = restTemplate.postForObject(urlToken, request, Token.class);
    }
}
