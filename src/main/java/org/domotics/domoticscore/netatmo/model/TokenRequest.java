package org.domotics.domoticscore.netatmo.model;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class TokenRequest {

    private String clientId;
    private String clientSecret;
    private String username;
    private String password;

    public TokenRequest(String clientId, String clientSecret, String username, String password) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public MultiValueMap<String, String> getRequestParams () {

        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();

        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("username", username);
        params.add("password", password);

        return params;
    }
}
