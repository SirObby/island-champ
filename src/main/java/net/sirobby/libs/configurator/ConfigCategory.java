package net.sirobby.libs.configurator;

import java.util.Collections;
import java.util.List;

public class ConfigCategory {

    public static String name;
    public static List<ConfigOption> cfg_options = Collections.emptyList();

    public ConfigCategory (String name, List<ConfigOption> options) {
        cfg_options = options;
    }

}
