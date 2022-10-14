package org.metadevs.metalibs.database;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.metadevs.metalibs.database.mysql.MySqlConfig;
import org.metadevs.metalibs.database.sqlite.SQLiteConfig;

import static org.metadevs.metalibs.Utils.color;

public class Database<T extends JavaPlugin> {
    private final T plugin;

    private DatabaseType type;
    private MySqlConfig mysqlConfig;
    private SQLiteConfig sqliteConfig;




    public Database(T plugin) {
        this.plugin = plugin;
    }

    /**
     * load all the database info from the config
     */
    public boolean loadDatabase() {
        FileConfiguration config = plugin.getConfig();


        ConfigurationSection dbSection = config.getConfigurationSection("database");

        if (dbSection == null) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid database secion, check the file config.yml"));
            return false;
        }
        String type = dbSection.getString("type");
        if (type == null) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid database type section, check the file config.yml"));
            return false;
        }

        switch (type) {
            case "mysql":
                this.type = DatabaseType.MYSQL;

                return setupMySQL(dbSection);
            case "sqlite":
                setupSQLite(dbSection);
                this.type = DatabaseType.SQLITE;
                break;
            default:
                Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid database type, check the file config.yml, using default: sqlite"));
                this.type = DatabaseType.SQLITE;
                break;
        }





        return true;


    }

    private void setupSQLite(ConfigurationSection dbSection) {

    }

    private boolean setupMySQL(ConfigurationSection dbSection) {

        mysqlConfig = new MySqlConfig();

        if (!dbSection.isConfigurationSection("mysql")) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid mysql section, check the file config.yml"));
            return false;
        }
        ConfigurationSection mysqlSection = dbSection.getConfigurationSection("mysql");

        String host  = mysqlSection.getString("host", "INVALID");
        if (host.equals("INVALID")) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid host , check the file config.yml"));
            return false;
        }

        Integer port = mysqlSection.getInt("port", Integer.MIN_VALUE);
        if (port == Integer.MIN_VALUE) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid port , check the file config.yml"));
            return false;
        }

        String database = mysqlSection.getString("database", "INVALID");
        if (database.equals("INVALID")) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid database name, check the file config.yml"));
            return false;
        }

        String username = mysqlSection.getString("username", "INVALID");
        if (username.equals("INVALID")) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid username, check the file config.yml"));
            return false;
        }

        String password = mysqlSection.getString("password", "INVALID");
        if (password.equals("INVALID")) {
            Bukkit.getConsoleSender().sendMessage(color("&cError: Invalid password, check the file config.yml"));
            return false;
        }
        mysqlConfig.setHost(host);
        mysqlConfig.setPort(port);
        mysqlConfig.setDatabase(database);
        mysqlConfig.setUsername(username);
        mysqlConfig.setPassword(password);

        return true;
    }

}