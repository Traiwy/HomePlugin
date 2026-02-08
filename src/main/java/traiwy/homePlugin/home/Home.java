package traiwy.homePlugin.home;

public record Home(
        long id,
        String ownerName,
        String homeName,
        Location location
) {}
