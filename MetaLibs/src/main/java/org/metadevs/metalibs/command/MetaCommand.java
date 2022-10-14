package org.metadevs.metalibs.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

public interface MetaCommand {

    boolean validateArgs();
    void execute();

    default @Nullable Player validatePlayer(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return (Player) sender;
        }
        return null;
    }
}
