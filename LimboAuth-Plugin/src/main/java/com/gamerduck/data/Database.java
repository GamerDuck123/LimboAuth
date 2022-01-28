package com.gamerduck.data;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.player.Player;
import com.loohp.limbo.plugins.LimboPlugin;

public class Database {

    private Connection connection;

    public Database(LimboPlugin main, String name) throws Exception {
    	File folder = new File(main.getDataFolder() + File.separator + "databases");
        folder.mkdirs();
        Class.forName("org.sqlite.JDBC").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection("jdbc:sqlite:" + new File(folder, name + ".db"));
        createTable("CREATE TABLE IF NOT EXISTS passwords (UUID VARCHAR(36) UNIQUE, PASSWORD VAR(36));");
    }

    public Database(LimboPlugin main, boolean reconnect, String host, String database, String username, String password, int port) throws Exception {
        Properties info = new Properties();
        info.setProperty("useSSL", "true");

        if (reconnect) {
            info.setProperty("autoReconnect", "true");
        }
        info.setProperty("trustServerCertificate", "true");
        info.setProperty("user", username);
        info.setProperty("password", password);

        Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, info);
        createTable("CREATE TABLE IF NOT EXISTS passwords (UUID VARCHAR(36) UNIQUE, PASSWORD VAR(36));");
    }

    public void createTable(String sqlURL) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sqlURL)) {
            statement.executeUpdate();
        }
    }
    
    public Connection connection() {return connection;}
    
    public void close() {
        try {connection.close();
        } catch (SQLException e) {e.printStackTrace();}
    }
    
    public void setEncryptedPassword(Player p, String password) {
    	password = LimboAuthMain.a().getEncrypter().encrypt(password);
	    	try (PreparedStatement insert = connection.prepareStatement("INSERT OR REPLACE INTO passwords VALUES (?, ?)")) {
	            insert.setString(1, p.getUniqueId().toString());
	            insert.setString(2, password);
	            insert.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
    }
    
    public String getEncryptedPassword(Player p) {
    	String pass = "N/A";
    	if (hasPassword(p)) {
	    	try (PreparedStatement select = connection.prepareStatement("SELECT PASSWORD FROM passwords WHERE UUID = ?")) {
	            select.setString(1, p.getUniqueId().toString());
	            try (ResultSet result = select.executeQuery()) {
	                if (result.next()) {
	                	pass = LimboAuthMain.a().getEncrypter().dencrypt(result.getString(1));
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
    	}
    	return pass;
    }
    
    public boolean hasPassword(Player p) {
    	try (PreparedStatement select = connection.prepareStatement("SELECT UUID FROM passwords WHERE UUID = ?")) {
            select.setString(1, p.getUniqueId().toString());
            try (ResultSet result = select.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 
    
    public boolean isEncryptionKey() {
    	try (PreparedStatement select = connection.prepareStatement("SELECT UUID FROM passwords WHERE UUID = ?")) {
            select.setString(1, "GLOBAL");
            try (ResultSet result = select.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    } 

}
