package traiwy.homePlugin.command.impl.invite;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.command.impl.invite.context.InviteContextManager;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.manager.RequestManager;

@AllArgsConstructor
public class InviteCommand implements SubCommand {
    private final RequestManager requestManager;
    private final InviteContextManager inviteContextManager;
    private final MenuService menuService;

    @Override
    public @NotNull String getName() {
        return "invite";
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
        inviteContextManager.create(player, target);
        menuService.getMenuManager().openMenu(player, menuService.getChooseHomeMenu());


    }
}
