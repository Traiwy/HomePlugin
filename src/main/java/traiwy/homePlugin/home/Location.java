package traiwy.homePlugin.home;

public record Location(
        String world,
        double x,
        double y,
        double z,
        float yaw,
        float pitch
) {}
