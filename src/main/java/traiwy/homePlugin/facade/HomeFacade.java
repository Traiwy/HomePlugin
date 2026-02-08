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

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class HomeFacade {
    private final RepositoryService repositoryService;
    private final HomeCache cache;
    private final MemberCache memberCache;


    public void loadHomesForPlayer(Player player) {
        repositoryService.getHomesForPlayer(player.getName())
                .thenAccept(homes -> {
                    for (Home home : homes) {
                        cache.add(player.getName(), home);

                        repositoryService.getMembers(home).thenAccept(members -> memberCache.addMembers(home.id(), members));
                    }
                });

    }

    public void saveHomesForPlayer(Player player) {
        List<Home> homes = cache.getAllHome(player.getName());
        if (homes.isEmpty()) return;

        CompletableFuture<?>[] futures = homes.stream()
                .map(home -> {
                    CompletableFuture<Void> saveHome = repositoryService.createHome(home);

                    List<Member> members = memberCache.getMembers(home.id());
                    CompletableFuture<Void> saveMembers = repositoryService.replaceMembers(home, members);

                    return CompletableFuture.allOf(saveHome, saveMembers);
                })
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures)
                .thenRun(() -> {
                    cache.removeAllHome(player.getName());
                    homes.forEach(h -> memberCache.removeAllMembers(h.id()));
                });
    }

    public CompletableFuture<Home> createHome(Player player, String homeName, Location loc) {
        final Home home = new Home(0L, player.getName(), homeName, loc);

        cache.add(player.getName(), home);

        return repositoryService.createHome(home)
                .thenApply(v -> {
                    cache.remove(player.getName(), home);
                    cache.add(player.getName(), home);
                    return home;
                });
    }

    public void addMember(Home home, String playerName) {
        final Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.addMember(home.id(), member);
    }


    public CompletableFuture<Void> removeMember(Home home, String playerName) {
        Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.removeMember(home.id(), member);
        return repositoryService.removeMember(home, playerName);
    }

    public List<Member> getMembers(Home home) {
        return memberCache.getMembers(home.id());
    }

}
