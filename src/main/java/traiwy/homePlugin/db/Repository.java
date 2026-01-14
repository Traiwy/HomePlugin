package traiwy.homePlugin.db;

import traiwy.homePlugin.home.Home;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Repository {
    void addHome(Home home);
    CompletableFuture<List<Home> > getHomes(String name);
    void deleteHome(Home home);
}
