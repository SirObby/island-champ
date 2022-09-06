package net.sirobby.libs.configurator;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Configurator {

    private List<ConfigCategory> Categories = Collections.emptyList();
    private Path configPath;

    public Configurator(String configfile) {

        configPath = FabricLoader.getInstance().getConfigDir().resolve(configfile.concat(".json"));

    }

    public void done() {
        if(Files.exists(configPath)) {
            for ( ConfigCategory cat : Categories ) {

            }
        } else {

        }
    }

    public void add_category(ConfigCategory cat) {
        Categories.add(cat);
    }

    public static <T> Object get_option_value(String name) {

        return 0;
    }

}
