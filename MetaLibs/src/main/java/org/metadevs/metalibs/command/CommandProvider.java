package org.metadevs.metalibs.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

public abstract class CommandProvider<P extends JavaPlugin> implements CommandExecutor {

    protected final P plugin;
    protected final String cmdName;

    protected CommandProvider(P plugin, String name) {
        this.plugin = plugin;
        this.cmdName = name;
        plugin.getCommand(cmdName).setExecutor(this);
    }

    public @Nullable Player validatePlayer(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return (Player) sender;
        }
        return null;
    }




}
