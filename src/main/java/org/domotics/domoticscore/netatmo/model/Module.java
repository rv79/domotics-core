package org.domotics.domoticscore.netatmo.model;

public class Module {
    public static final String TYPE_OUTDOOR = "NAModule1";
    public static final String TYPE_WIND_GAUGE = "NAModule2";
    public static final String TYPE_RAIN_GAUGE = "NAModule3";
    public static final String TYPE_INDOOR = "NAModule4";

    String name;
    String id;
    String type;
    Measures measures;

    public Module(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures measures) {
        this.measures = measures;
    }
}
