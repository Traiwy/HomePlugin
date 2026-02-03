package traiwy.homePlugin.command.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;

public class InviteCommand implements SubCommand {
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

    }
}
