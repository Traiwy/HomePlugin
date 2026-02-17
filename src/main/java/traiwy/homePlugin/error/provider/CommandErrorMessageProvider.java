package traiwy.homePlugin.error.provider;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import traiwy.homePlugin.configuration.ErrorMessageConfiguration;
import traiwy.homePlugin.error.CommandError;

import java.util.logging.ErrorManager;

@AllArgsConstructor
public class CommandErrorMessageProvider {
    private final ErrorMessageConfiguration configuration;

    public Component getMessage(CommandError error) {
        return switch (error) {
            case CONSOLE -> Component.text(configuration.console());
            case NO_PERMISSION -> Component.text(configuration.noPermission());
            case ARGS -> Component.text(configuration.args());
            case UNKNOWN_SUBCOMMAND -> Component.text(configuration.unknowSubcommand());
            case NOT_FIND_PLAYER -> Component.text(configuration.notFindPlayer());
            case TARGET_OFFLINE -> Component.text(configuration.targetOffline());
            case PLAYER_NOT_ONLINE -> Component.text(configuration.playerNotOnline());
            case SELF_TARGET -> Component.text(configuration.selfTarget());
        };
    }
}
