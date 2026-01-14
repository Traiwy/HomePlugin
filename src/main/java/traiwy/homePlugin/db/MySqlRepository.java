package traiwy.homePlugin.db;

import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.LocationData;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MySqlRepository implements Repository {
    private final DatabaseManager dataSource;
    private Executor executor = Executors.newFixedThreadPool(3);

    public MySqlRepository(DatabaseManager dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void addHome(Home home) {
        CompletableFuture.runAsync(() -> {
            final String sql = "INSERT INTO homes (\n" +
                    "    owner, name, world, x, y, z, yaw, pitch\n" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            try(Connection connection = dataSource.getDs().getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, home.name());
                ps.setString(2, home.homeName());
                ps.setString(3, home.location().world());
                ps.setDouble(4, home.location().x());
                ps.setDouble(5, home.location().y());
                ps.setDouble(6, home.location().z());
                ps.setDouble(7, home.location().yaw());
                ps.setDouble(8, home.location().pitch());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<Home>> getHomes(String owner) {
        return CompletableFuture.supplyAsync(() -> {
            List<Home> homes = new ArrayList<>();

            String sql = """
            SELECT name, world, x, y, z, yaw, pitch
            FROM homes
            WHERE owner = ?
        """;

            try (Connection connection = dataSource.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, owner);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        LocationData location = new LocationData(
                                rs.getString("world"),
                                rs.getDouble("x"),
                                rs.getDouble("y"),
                                rs.getDouble("z"),
                                rs.getFloat("yaw"),
                                rs.getFloat("pitch")
                        );

                        homes.add(new Home(
                                owner,
                                rs.getString("name"),
                                location
                        ));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to load homes for owner: " + owner, e);
            }

            return homes;
        }, executor);
    }


    @Override
    public void deleteHome(Home home) {
        CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM homes WHERE owner = ? AND name = ?";

            try (Connection connection = dataSource.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, home.name());
                ps.setString(2, home.homeName());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Дом не найден для удаления: " + home.homeName() + " у игрока " + home.name());
                }
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось удалить дом: " + home.homeName() + " у игрока " + home.name(), e);
            }
        }, executor);
    }
}
