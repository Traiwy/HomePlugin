package traiwy.homePlugin.db.home;


import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MySqlHomeRepository implements HomeRepository {

    private final DatabaseManager dataSource;
    private final Executor executor = Executors.newFixedThreadPool(3);

    public MySqlHomeRepository(DatabaseManager dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CompletableFuture<Void> save(Home home) {
        return CompletableFuture.runAsync(() -> {
            String sql = """
                INSERT INTO homes (owner, name, world, x, y, z, yaw, pitch)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try (Connection conn = dataSource.getDs().getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, home.ownerName());
                ps.setString(2, home.homeName());
                ps.setString(3, home.location().world());
                ps.setDouble(4, home.location().x());
                ps.setDouble(5, home.location().y());
                ps.setDouble(6, home.location().z());
                ps.setFloat(7, home.location().yaw());
                ps.setFloat(8, home.location().pitch());

                ps.executeUpdate();

                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()) {
                        long id = rs.getLong(1);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<Home>> findAllBy(String key) {
        return null;
    }

    @Override
    public CompletableFuture<List<Home>> findByOwner(String owner) {
        return CompletableFuture.supplyAsync(() -> {
            List<Home> homes = new ArrayList<>();

            String sql = """
                SELECT id, name, world, x, y, z, yaw, pitch
                FROM homes
                WHERE owner = ?
            """;

            try (Connection con = dataSource.getDs().getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, owner);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        homes.add(new Home(
                                rs.getLong("id"),
                                owner,
                                rs.getString("name"),
                                new Location(
                                        rs.getString("world"),
                                        rs.getDouble("x"),
                                        rs.getDouble("y"),
                                        rs.getDouble("z"),
                                        rs.getFloat("yaw"),
                                        rs.getFloat("pitch")
                                )
                        ));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return homes;
        }, executor);
    }

    @Override
    public CompletableFuture<Void> delete(Home home) {
        return CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM homes WHERE id = ?";

            try (Connection con = dataSource.getDs().getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, home.ownerName());
                ps.setString(2, home.homeName());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> replaceAll(String owner, List<Home> homes) {
        return CompletableFuture.runAsync(() -> {
            String deleteSql = "DELETE FROM homes WHERE owner = ?";
            String insertSql = """
            INSERT INTO homes (owner, name, world, x, y, z, yaw, pitch)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

            try (Connection conn = dataSource.getDs().getConnection()) {
                conn.setAutoCommit(false);

                try (PreparedStatement deletePs = conn.prepareStatement(deleteSql)) {
                    deletePs.setString(1, owner);
                    deletePs.executeUpdate();
                }

                try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                    for (Home home : homes) {
                        insertPs.setString(1, home.ownerName());
                        insertPs.setString(2, home.homeName());
                        insertPs.setString(3, home.location().world());
                        insertPs.setDouble(4, home.location().x());
                        insertPs.setDouble(5, home.location().y());
                        insertPs.setDouble(6, home.location().z());
                        insertPs.setFloat(7, home.location().yaw());
                        insertPs.setFloat(8, home.location().pitch());
                        insertPs.addBatch();
                    }
                    insertPs.executeBatch();
                }

                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to replace homes for " + owner, e);
            }
        }, executor);
    }
}

