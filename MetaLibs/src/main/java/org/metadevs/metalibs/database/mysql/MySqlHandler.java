package org.metadevs.metalibs.database.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.metadevs.metalibs.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlHandler <T extends JavaPlugin> {
    private final T plugin;
    private final HikariDataSource dataSource;
    private Database<T> database;


    public MySqlHandler(T plugin) {
        this.plugin = plugin;
        dataSource = new HikariDataSource();
    }

    /**
     * Checks the database credentials and host
     *
     * @return true if they are valid
     * @return false if they are not valid
     */
    public boolean checkDatabaseInfo() {
        database = new Database(plugin);
        return database.loadDatabase();
    }

    /**
     * Set up the credentials for the connection pool
     */
    public boolean setupDataSource() {

        dataSource.setServerName(database.getHost());
        dataSource.setPortNumber(database.getPort());
        dataSource.setDatabaseName(database.getDatabase());
        dataSource.setUser(database.getUsername());
        dataSource.setPassword(database.getPassword());
        try {
            dataSource.setAllowMultiQueries(true);

        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage( color(plugin.getPrefix() + "§cError while setting up the connection pool"));
            return false;
        }

        try (Connection connection = dataSource.getConnection()) {
            if (!connection.isValid(1000)) {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }

        return true;




    }

    public void loadTables() {

        try (Connection conn = dataSource.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(SQL.LOADTABLES)) {
            preparedStatement.execute();

        } catch (SQLException e1) {
            Bukkit.getConsoleSender().sendMessage(color(plugin.getPrefix() + "§cError while loading tables"));
            plugin.setActive( false );
        }
    }

}
}
