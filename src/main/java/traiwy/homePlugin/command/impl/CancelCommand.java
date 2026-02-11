package traiwy.homePlugin.command.impl;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;

public class CancelCommand implements SubCommand{
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

    }
}
