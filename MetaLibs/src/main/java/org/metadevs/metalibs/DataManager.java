package org.metadevs.metalibs;

import org.bukkit.plugin.java.JavaPlugin;
import org.metadevs.metalibs.database.Database;

public class DataManager<T extends JavaPlugin> {

    private final Database<T> database;
    private final T plugin;


    public DataManager(T plugin) {
        this.plugin = plugin;
        this.database = new Database<>(plugin);
    }
}
