package traiwy.homePlugin.configuration.dto;

import lombok.Data;

@Data
public class MySqlData {
    private String host;
    private int port;
    private String database;
    private String user;
    private String password;

    public MySqlData(String HOST, int PORT, String DATABASE, String USER, String PASSWORD) {
        this.host = HOST;
        this.port = PORT;
        this.database = DATABASE;
        this.user = USER;
        this.password = PASSWORD;
    }

    public static MySqlData defaultConfig(){
        return new MySqlData("127.0.0.1", 3306, "home", "root", "root");
    }

}
