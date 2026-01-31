package traiwy.homePlugin.db;

import traiwy.homePlugin.home.Home;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Repository<T>{
    CompletableFuture<Void> save(T entity);
    CompletableFuture<List<T>> findAllBy(String key);
    CompletableFuture<Void> delete(T entity);
}
