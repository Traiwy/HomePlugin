package traiwy.homePlugin.service;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import traiwy.homePlugin.db.home.HomeRepository;
import traiwy.homePlugin.db.member.MemberRepository;
import traiwy.homePlugin.error.RequestError;
import traiwy.homePlugin.error.provider.RequestErrorMessageProvider;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.home.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class RepositoryService {
    private final HomeRepository homeRepo;
    private final MemberRepository memberRepo;


    public CompletableFuture<Home> add(Home home) {
        return homeRepo.save(home);
    }

    public CompletableFuture<List<Home>> get(String owner) {
        return homeRepo.findByOwner(owner);
    }

    public CompletableFuture<Void> delete(Home home) {
        return memberRepo.deleteAllByHome(home.id())
                .thenCompose(v -> homeRepo.delete(home));
    }

    public CompletableFuture<Member> addMember(Home home, String playerName) {
        Member member = new Member(home.id(), playerName, Role.MEMBER);

        return memberRepo.save(member);
    }

    public CompletableFuture<Void> deleteMember(Home home, String playerName) {
        return memberRepo.delete(
                new Member(home.id(), playerName, Role.MEMBER)
        );
    }

    public CompletableFuture<List<Member>> getMembers(Home home) {
        return memberRepo.findAllByHome(home.id());
    }

    public void updateMember(Home home, Member member) {
        memberRepo.update(member);
    }

    public CompletableFuture<List<Home>> getHomesWhereMember(String playerName) {
        return memberRepo.findHomesByMember(playerName)
                .thenCompose(homeIds -> {
                    List<CompletableFuture<Home>> futures = new ArrayList<>();
                    for (Long homeId : homeIds) {
                        futures.add(homeRepo.findById(homeId));
                    }
                    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                            .thenApply(v -> futures.stream()
                                    .map(CompletableFuture::join)
                                    .toList());
                });
    }




}

