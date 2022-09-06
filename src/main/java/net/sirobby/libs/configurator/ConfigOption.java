package net.sirobby.libs.configurator;

import java.lang.reflect.Type;

public class ConfigOption {

    public ConfigTypes optionType;
    public String optionName;
    public Class<?> optionClass;
    public String translateText;
    public ConfigOption (ConfigTypes type, String name, Class<?> c) {
        optionType = type;
        optionName = name;
        optionClass = c;
    }

    public void translateText(String seriously) {
        translateText = seriously;
    }

}
