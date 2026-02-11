package traiwy.homePlugin.db.member;

import traiwy.homePlugin.db.Repository;
import traiwy.homePlugin.home.Member;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MemberRepository extends Repository<Member> {
    CompletableFuture<List<Member>> findAllByHome(long homeId);
    CompletableFuture<Boolean> isMember(long homeId, String playerName);
    CompletableFuture<Void> deleteAllByHome(long homeId);
    CompletableFuture<List<Long>> findHomesByMember(String playerName);
}
