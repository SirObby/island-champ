package net.sirobby.libs.configurator;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class ConfigOption {

    public ConfigTypes optionType;
    public String optionName;
    public Class<?> optionClass;
    public String translateText;

    // Defaults!
    public boolean has_default;
    public String default_s;
    public int default_i;
    public double default_d;
    public boolean default_b;

    public ConfigOption (ConfigTypes type, String name, Class<?> c) {
        optionType = type;
        optionName = name;
        optionClass = c;

        has_default = false;
    }

    public void translateText(String seriously) {
        translateText = seriously;
    }
    public ConfigOption set_default(String s) {
        default_s = s;
        has_default = true;
        return this;
    }

    public ConfigOption set_default(int i) {
        default_i = i;
        has_default = true;
        return this;
    }

    public ConfigOption set_default(double d) {
        default_d = d;
        has_default = true;
        return this;
    }

    public ConfigOption set_default(boolean b) {
        default_b = b;
        has_default = true;
        return this;
    }

}
