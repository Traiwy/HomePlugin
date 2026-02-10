package traiwy.homePlugin.db;

import traiwy.homePlugin.home.Home;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Repository<T>{
    CompletableFuture<T> save(T entity);
    CompletableFuture<Void> delete(T entity);
    CompletableFuture<Void> update(T entity);
}
