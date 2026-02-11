package traiwy.homePlugin.facade;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.cache.MemberCache;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Location;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.home.Role;
import traiwy.homePlugin.service.RepositoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class HomeFacade {
    private final RepositoryService repositoryService;
    private final HomeCache cache;
    private final MemberCache memberCache;


    public void load(Player player) {
        repositoryService.get(player.getName())
                .thenAccept(homes -> {
                    for (Home home : homes) {
                        cache.add(player.getName(), home);
                        System.out.println(home.homeName() + " был добавлен");

                        repositoryService.getMembers(home).thenAccept(members -> memberCache.addMembers(home.id(), members));
                    }

                    if(homes.isEmpty()) System.out.println("Дома не найдены");
                });

    }

    public CompletableFuture<Void> save(Player player) {
        List<Home> homes = cache.getAllHome(player.getName());
        if (homes.isEmpty()) return CompletableFuture.completedFuture(null);

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Home home : homes) {
            CompletableFuture<Void> future = repositoryService.add(home)
                    .thenCompose(savedHome -> {
                        List<Member> members = memberCache.getMembers(home.id());
                        List<CompletableFuture<Void>> memberFutures = new ArrayList<>();

                        for (Member m : members) {
                            memberFutures.add(repositoryService.addMember(savedHome, m.name())
                                    .thenAccept(saved -> {}));
                        }

                        return CompletableFuture.allOf(memberFutures.toArray(new CompletableFuture[0]));
                    });

            futures.add(future);
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> System.out.println("Сохранение домов и мемберов завершено для " + player.getName()));
    }

    public CompletableFuture<Home> createHome(Player player, String homeName, Location loc) {
        final Home temp = new Home(0L, player.getName(), homeName, loc);

        return repositoryService.add(temp)
                .thenApply(savedHome -> {
                    cache.add(player.getName(), savedHome);
                    return savedHome;
                });
    }

    public CompletableFuture<Void> addMember(Home home, String playerName) {
        Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.addMember(home.id(), member);

        return repositoryService.addMember(home, playerName)
                .thenAccept(saved -> {});
    }


    public CompletableFuture<Void> removeMember(Home home, String playerName) {
        final Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.removeMember(home.id(), member);
        return repositoryService.deleteMember(home, playerName);
    }

    public List<Member> getMembers(Home home) {
        return memberCache.getMembers(home.id());
    }

    public void clearCache(Player player) {
        cache.removeAllHome(player.getName());
    }

}
