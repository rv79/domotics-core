package org.domotics.domoticscore.netatmo.model;


import lombok.Data;

import java.util.List;

@Data
public class Station {
    String name;
    String id;
    List<Module> modules;
}
