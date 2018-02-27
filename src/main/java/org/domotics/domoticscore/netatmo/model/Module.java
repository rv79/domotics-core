package org.domotics.domoticscore.netatmo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Module {
    public static final String TYPE_OUTDOOR = "NAModule1";
    public static final String TYPE_WIND_GAUGE = "NAModule2";
    public static final String TYPE_RAIN_GAUGE = "NAModule3";
    public static final String TYPE_INDOOR = "NAModule4";

    @JsonProperty("module_name")
    private String name;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("last_seen")
    private Long lastSeen;
    @JsonProperty("last_message")
    private Long lastMessage;
    @JsonProperty("battery_vp")
    private int batteryVolt;
    @JsonProperty("battery_percent")
    private int batteryPercent;
    @JsonProperty("rf_status")
    private int rfStatus;
    @JsonProperty("firmware")
    private int firmware;

    @JsonProperty("dashboard_data")
    private Measures measures;
}
