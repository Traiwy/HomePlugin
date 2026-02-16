package traiwy.homePlugin.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Configuration {
    private final JavaPlugin plugin;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File config;

    @Getter
    private PluginConfiguration configuration;

    public Configuration(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = new File(plugin.getDataFolder(), "config.json");
        load();
    }

    private void load() {
        try {
            if (!plugin.getDataFolder().exists()) {
                boolean created = plugin.getDataFolder().mkdirs();
                if (created) log.info("Plugin data folder created");
                else log.warn("Failed to create plugin data folder");
            }

            if (!config.exists()) {
                log.info("Config file not found, creating default config");
                createDefault();
            }

            try (Reader reader = new InputStreamReader(
                    new FileInputStream(config),
                    StandardCharsets.UTF_8
            )) {
                configuration = gson.fromJson(reader, PluginConfiguration.class);
                if (configuration == null)
                    configuration = new PluginConfiguration();

                log.info("Config loaded successfully");
            }

        } catch (Exception e) {
            log.error("Failed to load config, using fallback values", e);
            configuration = new PluginConfiguration();
        }
    }

    private void createDefault() {
        try {
            configuration = new PluginConfiguration();

            try (Writer writer = new OutputStreamWriter(
                    new FileOutputStream(config),
                    StandardCharsets.UTF_8
            )) {
                gson.toJson(configuration, writer);
            }

            log.info("Default config created");
        } catch (IOException e) {
            throw new RuntimeException("Failed to create default config");
        }
    }
}
