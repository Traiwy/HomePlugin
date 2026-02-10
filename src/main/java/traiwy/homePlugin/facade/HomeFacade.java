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

    public void save(Player player) {
        final List<Home> homes = cache.getAllHome(player.getName());

        if (homes.isEmpty()) return;

        final List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Home cachedHome : homes) {
            CompletableFuture<Void> future =
                    repositoryService.save(cachedHome)
                            .thenCompose(savedHome -> {
                                List<Member> members = memberCache.getMembers(cachedHome.id());

                                List<Member> fixedMembers = members.stream()
                                        .map(m -> new Member(
                                                savedHome.id(),
                                                m.name(),
                                                m.role()
                                        ))
                                        .toList();

                                return repositoryService.replaceMembers(
                                        savedHome.id(),
                                        fixedMembers
                                );
                            });

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> System.out.println("Сохранение всех домов и их членов завершено для игрока " + player.getName()));
    }

    public CompletableFuture<Home> createHome(Player player, String homeName, Location loc) {
        final Home temp = new Home(0L, player.getName(), homeName, loc);

        return repositoryService.save(temp)
                .thenApply(savedHome -> {
                    cache.add(player.getName(), savedHome);
                    return savedHome;
                });
    }

    public void addMember(Home home, String playerName) {
        final Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.addMember(home.id(), member);
    }


    public CompletableFuture<Void> removeMember(Home home, String playerName) {
        final Member member = new Member(home.id(), playerName, Role.MEMBER);

        memberCache.removeMember(home.id(), member);
        return repositoryService.removeMember(home, playerName);
    }

    public List<Member> getMembers(Home home) {
        return memberCache.getMembers(home.id());
    }

}
