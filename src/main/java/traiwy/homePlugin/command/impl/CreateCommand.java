package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.listener.PlayerChatListener;

@AllArgsConstructor
public class CreateCommand implements SubCommand {
    private PlayerChatListener playerChatListener;


    @Override
    public @NotNull String getName() {
        return "create";
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
        playerChatListener.startHomeNaming(player);
    }
}
