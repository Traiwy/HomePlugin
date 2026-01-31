package traiwy.homePlugin.home;

import java.util.List;

public record Home(
        String ownerName,
        String homeName,
        LocationData location,
        List<Member> members
) {}
