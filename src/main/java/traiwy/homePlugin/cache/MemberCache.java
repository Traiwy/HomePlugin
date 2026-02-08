package traiwy.homePlugin.cache;

import traiwy.homePlugin.home.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberCache {
    private final Map<Long, List<Member>> memberCache = new ConcurrentHashMap<>();

    public List<Member> getMembers(long homeId) {
        return memberCache.getOrDefault(homeId, Collections.emptyList());
    }

    public void addMember(long homeId, Member member) {
        memberCache.compute(homeId, (id, members) -> {
            if (members == null) members = new ArrayList<>();
            members.add(member);
            return members;
        });
    }

    public void addMembers(long homeId, List<Member> members) {
        memberCache.put(homeId, members);
    }

    public void removeMember(long homeId, Member member) {
        List<Member> members = memberCache.get(homeId);
        if (members != null) {
            members.removeIf(m -> m.name().equals(member.name()));
            if (members.isEmpty()) {
                memberCache.remove(homeId);
            }
        }
    }

    public void removeAllMembers(long homeId) {
        memberCache.remove(homeId);
    }

    public void clear() {
        memberCache.clear();
    }
}
