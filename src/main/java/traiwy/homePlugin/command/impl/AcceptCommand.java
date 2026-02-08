package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.HomePlugin;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.RequestError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.error.provider.RequestErrorMessageProvider;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.home.Role;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class AcceptCommand implements SubCommand {
    private final RequestManager requestManager;
    private final MenuService service;

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

        final Request request = requestManager.getRequestFor(receiver);

        if (request == null) {
            receiver.sendMessage(
                    RequestErrorMessageProvider.getMessage(RequestError.REQUEST_NOT_FOUND)
            );
            return;
        }

        final Home home = request.home();
        service.getHomeFacade().addMember(home, receiver.getName());
        receiver.sendMessage("§aВы приняли заявку от " + request.sender().getName());
        requestManager.removeRequest(request);
    }
}
