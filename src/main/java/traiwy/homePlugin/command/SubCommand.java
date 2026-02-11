package traiwy.homePlugin.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface SubCommand {
    @NotNull String getName();
    @NotNull String permission();
    void execute(@NotNull CommandSender sender, @NotNull String[] args);
}
