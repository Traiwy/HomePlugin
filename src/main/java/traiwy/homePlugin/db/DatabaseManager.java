package traiwy.homePlugin.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import traiwy.homePlugin.configuration.dto.MySqlData;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    @Getter
    private HikariDataSource ds;
    private final MySqlData config;

    public DatabaseManager(MySqlData mysql) {
        this.config = mysql;
        setup();
    }

    public void setup() {
        createDatabase();
        setupHikari();
        createTables();
    }

    private void createDatabase() {
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() +
                "/?useSSL=false&serverTimezone=UTC";

        try (Connection conn = java.sql.DriverManager.getConnection(
                url, config.getUser(), config.getPassword());
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                    "CREATE DATABASE IF NOT EXISTS `" + config.getDatabase() + "` " +
                            "CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
            );

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database", e);
        }
    }

    private void setupHikari() {
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() +
                "/" + config.getDatabase() +
                "?useSSL=false" +
                "&allowPublicKeyRetrieval=true" +
                "&useUnicode=true" +
                "&characterEncoding=UTF-8" +
                "&serverTimezone=UTC";
        HikariConfig hikari = new HikariConfig();
        hikari.setJdbcUrl(url);
        hikari.setUsername(config.getUser());
        hikari.setPassword(config.getPassword());

        hikari.setMaximumPoolSize(10);
        hikari.setMinimumIdle(2);
        hikari.setPoolName("HomePluginPool");

        ds = new HikariDataSource(hikari);
    }

    private void createTables() {
        String home = """
            CREATE TABLE IF NOT EXISTS homes (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
            
                owner VARCHAR(36) NOT NULL,
                name VARCHAR(32) NOT NULL,
            
                world VARCHAR(64) NOT NULL,
                x DOUBLE NOT NULL,
                y DOUBLE NOT NULL,
                z DOUBLE NOT NULL,
                yaw FLOAT NOT NULL,
                pitch FLOAT NOT NULL,
            
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            
                UNIQUE KEY uk_home_owner_name (owner, name)
            );
        """;

        String members = """
                CREATE TABLE IF NOT EXISTS members ( 
                id BIGINT AUTO_INCREMENT PRIMARY KEY, 
                home_id BIGINT NOT NULL, 
                member VARCHAR(16) NOT NULL,
                role VARCHAR(16) NOT NULL DEFAULT 'MEMBER'
                );
              
                """;

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(home);
            stmt.executeUpdate(members);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables", e);
        }
    }

    public void shutdown() {
        ds.close();
    }
}

