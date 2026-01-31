package traiwy.homePlugin.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.configuration.dto.ConfigData;
import traiwy.homePlugin.configuration.dto.MySqlData;


import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Config {
    private final JavaPlugin plugin;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File config;
    private final File configMySql;

    @Getter
    private ConfigData configData;
    @Getter
    private MySqlData mySqlData;

    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = new File(plugin.getDataFolder(), "config.json");
        this.configMySql = new File(plugin.getDataFolder(), "mysql.json");
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
                configData = gson.fromJson(reader, ConfigData.class);
                if (configData == null) configData = ConfigData.defaultConfig();

                log.info("Config loaded successfully");
            }

            if (!configMySql.exists()) {
                log.info("Config file not found, creating default config");
                createDefault();
            }

            try (Reader readerMySql = new InputStreamReader(
                    new FileInputStream(configMySql),
                    StandardCharsets.UTF_8
            )) {
                mySqlData = gson.fromJson(readerMySql, MySqlData.class);
                if (mySqlData == null) mySqlData = MySqlData.defaultConfig();

                log.info("ConfigMySql config loaded successfully");
            }
        } catch (Exception e) {
            log.error("Failed to load config, using fallback values", e);
            configData = ConfigData.defaultConfig();
            mySqlData = MySqlData.defaultConfig();
        }
    }

    private void createDefault() {
        try {
            configData = ConfigData.defaultConfig();
            mySqlData = MySqlData.defaultConfig();

            try (Writer writer = new OutputStreamWriter(
                    new FileOutputStream(config),
                    StandardCharsets.UTF_8
            )) {
                gson.toJson(configData, writer);
            }

            if (!configMySql.exists()) {
                log.info("MySQL config not found, creating default mysql.json");
                try (Writer writer = new OutputStreamWriter(
                        new FileOutputStream(configMySql),
                        StandardCharsets.UTF_8
                )) {
                    gson.toJson(MySqlData.defaultConfig(), writer);
                }
            }

            log.info("Default config created");
        } catch (IOException e) {
            throw new RuntimeException("Failed to create default config");
        }
    }
}
