package org.metadevs.metalibs.command;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class SubCommandProvider<P extends JavaPlugin> implements MetaCommand {
    protected final P plugin;

    public SubCommandProvider(final P plugin) {
        this.plugin = plugin;
    }

}
