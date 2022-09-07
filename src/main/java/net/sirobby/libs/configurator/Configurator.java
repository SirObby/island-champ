package net.sirobby.libs.configurator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Configurator {

    @SuppressWarnings("all")
    private List<ConfigCategory> Categories = new LinkedList<ConfigCategory>() ;// = Collections.emptyList();
    @SuppressWarnings("all")
    private Path configPath;

    public Configurator(String configfile) {

        configPath = FabricLoader.getInstance().getConfigDir().resolve(configfile.concat(".json"));

    }

    @SuppressWarnings("all")
    public void done() {
        if(Files.exists(configPath)) {
            for ( ConfigCategory cat : Categories ) {
                for ( ConfigOption opshun : cat.cfg_options ) {
                    // we get every goddamn option.
                    System.out.println(opshun.optionName);

                }
            }
        } else {
            JsonObject o = new Gson().fromJson("{ \"configurator\": true }", JsonObject.class);

            for ( ConfigCategory cat : Categories ) {
                JsonObject oc = new JsonObject();
                for ( ConfigOption opshun : cat.cfg_options ) {
                    // we get every goddamn option.
                    System.out.println(opshun.optionName);

                    JsonObject value = new JsonObject();
                    if(opshun.optionType == ConfigTypes.String_Option && opshun.has_default) {
                        value.addProperty(opshun.optionName, opshun.default_s);
                    } else if (opshun.optionType == ConfigTypes.String_Option && !opshun.has_default) {
                        value.addProperty(opshun.optionName, "");
                    }
                    if(opshun.optionType == ConfigTypes.Integer_Option && opshun.has_default) {
                        value.addProperty(opshun.optionName, opshun.default_i);
                    } else if (opshun.optionType == ConfigTypes.Integer_Option && !opshun.has_default) {
                        value.addProperty(opshun.optionName, 0);
                    }
                    if(opshun.optionType == ConfigTypes.Boolean_Option && opshun.has_default) {
                        value.addProperty(opshun.optionName, opshun.default_b);
                    } else if (opshun.optionType == ConfigTypes.Boolean_Option && !opshun.has_default) {
                        value.addProperty(opshun.optionName, true);
                    }
                    if(opshun.optionType == ConfigTypes.Double_Option && opshun.has_default) {
                        value.addProperty(opshun.optionName, opshun.default_d);
                    } else if (opshun.optionType == ConfigTypes.Double_Option && !opshun.has_default) {
                        value.addProperty(opshun.optionName, (double) 0);
                    }
                    oc.add(opshun.optionName, value);
                }
                o.add(cat.name, oc);
            }

            try {
                Files.writeString(configPath, o.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void add_category(ConfigCategory cat) {
        Categories.add(cat);
    }

    public static <T> Object get_option_value(String name) {

        return 0;
    }

}
