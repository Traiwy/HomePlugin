package traiwy.homePlugin.error.provider;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import traiwy.homePlugin.configuration.ErrorMessageConfiguration;
import traiwy.homePlugin.error.RequestError;

@AllArgsConstructor
public class RequestErrorMessageProvider {

    private final ErrorMessageConfiguration configuration;

    public Component getMessage(RequestError error) {
        return switch (error) {
            case REQUEST_ALREADY_SENT -> Component.text(configuration.requestAlreadySent());
            case REQUEST_ALREADY_EXISTS -> Component.text(configuration.requestAlreadyExists());
            case REQUEST_NOT_FOUND -> Component.text(configuration.requestNotFound());
            case REQUEST_EXPIRED -> Component.text(configuration.requestExpired());
            case REQUEST_CANCELLED -> Component.text(configuration.requestCancelled());
            case REQUEST_COOLDOWN -> Component.text(configuration.requestCooldown());
            case REQUEST_BLOCKED -> Component.text(configuration.requestBlocked());
            case TOO_MANY_REQUESTS -> Component.text(configuration.tooManyRequests());
            case MEMBER_ALREADY_EXISTS -> Component.text(configuration.memberAlreadyExists());
        };
    }
}