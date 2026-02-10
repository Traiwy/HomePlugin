package traiwy.homePlugin.service;

import lombok.AllArgsConstructor;
import traiwy.homePlugin.db.home.HomeRepository;
import traiwy.homePlugin.db.member.MemberRepository;
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


    public CompletableFuture<Home> save(Home home) {
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
        return memberRepo.save(
                new Member(home.id(), playerName, Role.MEMBER)
        );
    }

    public CompletableFuture<Void> removeMember(Home home, String playerName) {
        return memberRepo.delete(
                new Member(home.id(), playerName, Role.MEMBER)
        );
    }

    public CompletableFuture<List<Member>> getMembers(Home home) {
        return memberRepo.findAllByHome(home.id());
    }

    public CompletableFuture<Void> replaceMembers(long homeId, List<Member> members) {
        return memberRepo.deleteAllByHome(homeId)
                .thenCompose(v -> {
                    final List<CompletableFuture<Member>> saves = new ArrayList<>();
                    for (Member m : members) {
                        saves.add(memberRepo.save(m));
                    }
                    return CompletableFuture.allOf(
                            saves.toArray(new CompletableFuture[0])
                    );
                });
    }
}

