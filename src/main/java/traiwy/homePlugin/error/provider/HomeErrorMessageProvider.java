package traiwy.homePlugin.error.provider;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import traiwy.homePlugin.configuration.ErrorMessageConfiguration;
import traiwy.homePlugin.error.HomeError;

@AllArgsConstructor
public class HomeErrorMessageProvider {

    private final ErrorMessageConfiguration configuration;

    public Component getMessage(HomeError error) {
        return switch (error) {
            case HOME_NOT_FOUND -> Component.text(configuration.homeNotFound());
            case HOME_ALREADY_SHARED -> Component.text(configuration.homeAlreadyShared());
            case ALREADY_IN_HOME -> Component.text(configuration.alreadyInHome());
            case NOT_HOME_OWNER -> Component.text(configuration.notHomeOwner());
            case HOME_LIMIT_REACHED -> Component.text(configuration.homeLimitReached());
            case INTERNAL_ERROR -> Component.text(configuration.internalError());
        };
    }
}
