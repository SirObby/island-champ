package net.sirobby.libs.configurator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConfigCategory {

    public String name;
    public List<ConfigOption> cfg_options;// = new LinkedList<ConfigOption>();

    public ConfigCategory (String set_name, List<ConfigOption> options) {
        name = set_name;
        cfg_options = options;
    }

}
