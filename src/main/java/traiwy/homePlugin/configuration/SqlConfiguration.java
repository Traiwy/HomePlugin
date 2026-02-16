package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqlConfiguration {
    String host = "localhost";
    int port = 3306;
    String database = "home";
    String user = "root";
    String password = "root";

}
