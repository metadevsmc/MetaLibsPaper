package org.metadevs.metalibs_1_18.manager;


import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class Manager<P extends JavaPlugin> {
    protected P plugin;

    public Manager(P plugin) {
        this.plugin = plugin;
    }

}
