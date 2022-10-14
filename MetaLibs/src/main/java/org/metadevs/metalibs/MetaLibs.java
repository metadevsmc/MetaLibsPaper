package org.metadevs.metalibs;

import org.bukkit.plugin.java.JavaPlugin;
import org.metadevs.metalibs.database.sqlite.SQLiteManager;
import org.metadevs.metalibs.messages.MessageHandler;

public final class MetaLibs<T extends JavaPlugin> {

    private final T plugin;
    private MessageHandler<T> messageHandler;
    private SQLiteManager<T> sqlite;
    private DataManager<T> databaseManager;

    public MetaLibs(T plugin) {
        this.plugin = plugin;
    }

    public T getPlugin() {
        return plugin;
    }

    public void init() {
        plugin.saveDefaultConfig();
        load();


    }

    public void load() {
        plugin.reloadConfig();
        loadManagers();

    }

    private void loadManagers() {


        sqlite = new SQLiteManager<>(plugin);
        messageHandler = new MessageHandler<T>(plugin);

    }


    public SQLiteManager<T> getSQLite() {
        return sqlite;
    }

    public MessageHandler<T> messageHandler() {
        return messageHandler;
    }



}
