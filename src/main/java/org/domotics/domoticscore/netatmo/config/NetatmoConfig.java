package org.domotics.domoticscore.netatmo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "netatmo")
public class NetatmoConfig {

    String clientId;
    String clientSecret;
    String username;
    String password;
}
