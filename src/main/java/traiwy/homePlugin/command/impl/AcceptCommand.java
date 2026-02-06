package traiwy.homePlugin.command.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.HomePlugin;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.RequestError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.error.provider.RequestErrorMessageProvider;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;

import java.util.Set;

public class AcceptCommand implements SubCommand {
    private RequestManager requestManager;
    @Override
    public @NotNull String getName() {
        return "accept";
    }

    @Override
    public @NotNull String getDescription() {
        return "";
    }

    @Override
    public @NotNull String permission() {
        return "";
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        if(!(sender instanceof Player receiver)) {
            sender.sendMessage(CommandErrorMessageProvider.getMessage(CommandError.CONSOLE));
            return;
        }

        final Set<Request> requests = requestManager.getRequests();
        for(Request r : requests) {
            if(r.receiver().equals(receiver)) {


            }else{
                receiver.sendMessage(RequestErrorMessageProvider.getMessage(RequestError.REQUEST_NOT_FOUND));
            }
        }
    }
}
