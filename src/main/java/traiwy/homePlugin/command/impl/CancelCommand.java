package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.RequestError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.error.provider.RequestErrorMessageProvider;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;

@AllArgsConstructor
public class CancelCommand implements SubCommand{
    private final RequestManager requestManager;
    @Override
    public @NotNull String getName() {
        return "cansel";
    }

    @Override
    public @NotNull String permission() {
        return "";
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.CONSOLE)
            );
            return;
        }
        final Request request = requestManager.getRequestFor(player);
        if(requestManager.hasRequest(request)) {
            requestManager.removeRequest(request);
            player.sendMessage(RequestErrorMessageProvider.getMessage(RequestError.REQUEST_CANCELLED));
            return;
        }

        player.sendMessage(RequestErrorMessageProvider.getMessage(RequestError.REQUEST_NOT_FOUND));


    }
}
