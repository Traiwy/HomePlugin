package traiwy.homePlugin.db.member;

import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.home.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MySqlMemberRepository implements MemberRepository{
    private final DatabaseManager database;
    private final Executor executor = Executors.newFixedThreadPool(2);

    public MySqlMemberRepository(DatabaseManager database) {
        this.database = database;
    }

    @Override
    public CompletableFuture<Member> save(Member member) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = """
            INSERT INTO members (home_id, member, role)
            VALUES (?, ?, ?)
        """;

            try (Connection connection = database.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setLong(1, member.homeId());
                ps.setString(2, member.name());
                ps.setString(3, member.role().name());
                ps.executeUpdate();

                return member;

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка сохранения мембера", e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> delete(Member member) {
        return CompletableFuture.runAsync(() -> {
            String sql = """
                DELETE FROM members
                WHERE home_id = ? AND member = ?
            """;

            try (Connection connection = database.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setLong(1, member.homeId());
                ps.setString(2, member.name());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка удаления мембера", e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> update(Member member) {
        return CompletableFuture.runAsync(() -> {
            String sql = "UPDATE members SET role = ? WHERE home_id = ? AND member = ?";

            try (Connection conn = database.getDs().getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, member.role().name());
                ps.setLong(2, member.homeId());
                ps.setString(3, member.name());
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка обновления мембера: " + member.name(), e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<Member>> findAllByHome(long homeId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Member> members = new ArrayList<>();
            String sql = "SELECT member, role FROM members WHERE home_id = ?";

            try (Connection connection = database.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setLong(1, homeId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        members.add(new Member(
                                0L,
                                rs.getString("member"),
                                Role.valueOf(rs.getString("role"))
                        ));
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка загрузки мемберов для дома " + homeId, e);
            }

            return members;
        }, executor);
    }

    @Override
    public CompletableFuture<Boolean> isMember(long homeId, String playerName) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT COUNT(*) FROM members WHERE home_id = ? AND member = ?";
            try (Connection connection = database.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setLong(1, homeId);
                ps.setString(2, playerName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка проверки мембера", e);
            }

            return false;
        }, executor);
    }

    @Override
    public CompletableFuture<Void> deleteAllByHome(long homeId) {
        return CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM members WHERE home_id = ?";
            try (Connection connection = database.getDs().getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setLong(1, homeId);
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Ошибка удаления всех мемберов дома " + homeId, e);
            }
        }, executor);
    }

}

