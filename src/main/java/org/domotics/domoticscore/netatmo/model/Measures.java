package org.domotics.domoticscore.netatmo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measures {
    public static final String STRING_NO_DATA = "No data";

    @JsonProperty("time_utc")
    private Long time;

    @JsonProperty("Temperature")
    private Double temperature;
    @JsonProperty("CO2")
    private int CO2;
    @JsonProperty("Humidity")
    private int humidity;

    @JsonProperty("Pressure")
    private Double pressure;
    @JsonProperty("AbsolutePressure")
    private Double absolutePressure;
    @JsonProperty("pressure_trend")
    private String pressureTrend;
    @JsonProperty("Noise")
    private int noise;

    @JsonProperty("temp_trend")
    private String tempTrend;
    @JsonProperty("date_min_temp")
    private Long minTempTime;
    @JsonProperty("min_temp")
    private Double minTemp;
    @JsonProperty("date_max_temp")
    private Long maxTempTime;
    @JsonProperty("max_temp")
    private Double maxTemp;

    @JsonProperty("Rain")
    private Double rain;
    @JsonProperty("sum_rain_24")
    private Double sumRain24;
    @JsonProperty("sum_rain_1")
    private Double sumRain1;

    @JsonProperty("WindAngle")
    private int windAngle;
    @JsonProperty("WindStrength")
    private int windStrength;
    @JsonProperty("GustAngle")
    private int gustAngle;
    @JsonProperty("GustStrength")
    private int gustStrength;
    @JsonProperty("date_max_wind_str")
    private Long maxWindTime;
    @JsonProperty("max_wind_angle")
    private int maxWindAngle;
    @JsonProperty("max_wind_str")
    private int maxWindStrength;

    public LocalDateTime getTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time * 1000), ZoneId.systemDefault());
    }
}
