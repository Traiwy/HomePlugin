package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;

@AllArgsConstructor
public class InviteCommand implements SubCommand {
    private final RequestManager requestManager;
    private final MenuService menuService;

    @Override
    public @NotNull String getName() {
        return "invite";
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
        if(!(sender instanceof Player player)) {
            sender.sendMessage(CommandErrorMessageProvider.getMessage(CommandError.CONSOLE));
            return;
        }
        if (args.length < 1) {
            player.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.ARGS)
            );
            return;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CommandErrorMessageProvider.getMessage(CommandError.NOT_FIND_PLAYER));
            return;
        }

        if (player.equals(target)) {
            player.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.SELF_TARGET)
            );
            return;
        }
        menuService.getMenuManager().openMenu(player, menuService.getChooseHomeMenu());


        //final Request request = new Request(player, target, 100);
//
        //requestManager.addRequest(request);
        //player.sendMessage("Вы отправили заявку игроку: " + target.getName());
        //player.sendMessage("У него есть " + request.getFormattedTime() + " времени, чтобы принять заявку");
    }
}
