package traiwy.homePlugin.service;

import lombok.AllArgsConstructor;
import traiwy.homePlugin.db.home.HomeRepository;
import traiwy.homePlugin.db.member.MemberRepository;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.home.Role;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class RepositoryService {
    private final HomeRepository homeRepo;
    private final MemberRepository memberRepo;


    public CompletableFuture<Void> createHome(Home home) {
        return homeRepo.save(home);
    }

    public CompletableFuture<Void> deleteHome(Home home) {
        return memberRepo.deleteAllByHome(home.id())
                .thenCompose(v -> homeRepo.delete(home));
    }

    public CompletableFuture<List<Home>> getHomesForPlayer(String playerName) {
        return homeRepo.findByOwner(playerName);
    }

    public CompletableFuture<Void> addMember(Home home, String playerName) {
        Member member = new Member(home.id(), playerName, Role.MEMBER);
        return memberRepo.save(member);
    }

    public CompletableFuture<Void> removeMember(Home home, String playerName) {
        Member member = new Member(home.id(), playerName, Role.MEMBER);
        return memberRepo.delete(member);
    }

    public CompletableFuture<List<Member>> getMembers(Home home) {
        return memberRepo.findAllBy(String.valueOf(home.id()));
    }

    public CompletableFuture<Boolean> isMember(Home home, String playerName) {
        return memberRepo.isMember(home.id(), playerName);
    }

    public CompletableFuture<Void> replaceMembers(Home home, List<Member> members) {
        return memberRepo.deleteAllByHome(home.id())
                .thenCompose(v -> {
                    CompletableFuture<?>[] futures = members.stream()
                            .map(memberRepo::save)
                            .toArray(CompletableFuture[]::new);
                    return CompletableFuture.allOf(futures);
                });
    }

}
