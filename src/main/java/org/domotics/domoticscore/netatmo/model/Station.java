package org.domotics.domoticscore.netatmo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    @JsonProperty("module_name")
    private String name;
    @JsonProperty("_id")
    private String id;

    @JsonProperty("dashboard_data")
    private Measures measures;

    @JsonProperty("modules")
    private List<Module> modules;

    @JsonProperty("date_setup")
    private Long dateSetup;
    @JsonProperty("last_setup")
    private Long lastSetup;
    @JsonProperty("last_upgrade")
    private Long lastUpgrade;
    @JsonProperty("firmware")
    private int firmware;
    @JsonProperty("wifi_status")
    private int wifiPercent;

    public LocalDateTime getDateSetup() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateSetup * 1000), ZoneId.systemDefault());
    }

    public LocalDateTime getLastSetup() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(lastSetup * 1000), ZoneId.systemDefault());
    }

    public LocalDateTime getLastUpgrade() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(lastUpgrade * 1000), ZoneId.systemDefault());
    }
}
