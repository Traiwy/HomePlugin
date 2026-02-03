package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;

@AllArgsConstructor
public class InviteCommand implements SubCommand {
    private final RequestManager requestManager;

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
        final Player player = (Player) sender;


        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("Игрок с таким именем не найден.");
            return;
        }

        final Request request = new Request(player, target, 100);

        requestManager.addRequest(request);
        player.sendMessage("Вы отправили заявку игроку: " + target.getName());
        player.sendMessage("У него есть " + request.getFormattedTime() + " времени, чтобы принять заявку");
    }
}
