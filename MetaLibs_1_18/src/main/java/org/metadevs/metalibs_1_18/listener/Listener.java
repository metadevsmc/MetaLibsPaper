package org.metadevs.metalibs_1_18.listener;

import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public abstract class Listener<P extends JavaPlugin> {

    protected P plugin;

    public Listener(P plugin) {
        this.plugin = plugin;
    }

}
