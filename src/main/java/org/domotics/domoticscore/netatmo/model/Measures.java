package org.domotics.domoticscore.netatmo.model;

public class Measures {
    public static final String STRING_NO_DATA = "No data";

    long beginTime;
    String temperature;
    String CO2;
    String humidity;
    String pressure;
    String noise;
    String minTemp;
    String maxTemp;
    String rain;
    String sum_rain_24;
    String sum_rain_1;
    String windAngle;
    String windStrength;
    String gustAngle;
    String gustStrength;

    public Measures() {
        beginTime = 0;
        temperature = STRING_NO_DATA;
        CO2 = STRING_NO_DATA;
        humidity = STRING_NO_DATA;
        pressure = STRING_NO_DATA;
        noise = STRING_NO_DATA;
        rain = STRING_NO_DATA;
        sum_rain_1 = STRING_NO_DATA;
        sum_rain_24 = STRING_NO_DATA;
        windAngle = STRING_NO_DATA;
        windStrength = STRING_NO_DATA;
        gustAngle = STRING_NO_DATA;
        gustStrength = STRING_NO_DATA;

    }

    public long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCO2() {
        return CO2;
    }

    public void setCO2(String CO2) {
        this.CO2 = CO2;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getSum_rain_24(){ return sum_rain_24;}

    public void setSum_rain_24(String sum_rain_24){this.sum_rain_24 = sum_rain_24;}

    public void setSum_rain_1(String sum_rain_1){ this.sum_rain_1 = sum_rain_1;}

    public String getSum_rain_1(){return sum_rain_1;}

    public void setRain(String rain){this.rain = rain;}

    public String getRain(){return rain;}

    public void setWindAngle(String windAngle){this.windAngle = windAngle;}

    public String getWindAngle(){return windAngle;}

    public void setWindStrength(String windStrength){this.windStrength = windStrength;}

    public String getWindStrength(){return windStrength;}

    public void setGustAngle(String gustAngle){this.gustAngle = gustAngle;}

    public String getGustAngle(){return gustAngle;}

    public void setGustStrength(String gustStrength){this.gustStrength = gustStrength;}

    public String getGustStrength(){return gustStrength;}




}
