package traiwy.homePlugin.db.home;

import traiwy.homePlugin.db.Repository;
import traiwy.homePlugin.home.Home;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HomeRepository extends Repository<Home> {
    CompletableFuture<List<Home>> findByOwner(String owner);
    CompletableFuture<Home> findById(long id);
}
