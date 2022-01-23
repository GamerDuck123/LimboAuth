package com.gamerduck;

import com.gamerduck.commands.LoginCommand;
import com.gamerduck.commands.RegisterCommand;
import com.gamerduck.configs.Config;
import com.gamerduck.data.ConfigYAML;
import com.gamerduck.data.Database;
import com.gamerduck.data.PlayerCache;
import com.gamerduck.listeners.PlayerAuthListener;
import com.gamerduck.listeners.PlayerJoinListener;
import com.gamerduck.listeners.PlayerMoveListener;
import com.gamerduck.listeners.PlayerQuitListener;
import com.loohp.limbo.plugins.LimboPlugin;

public class LimboAuthMain extends LimboPlugin {
	static LimboAuthMain instance;
	ConfigYAML configYAML;
	PlayerCache playerCache;
	Database database;
	Encrypt encrypt;
	private String key;
	@Override
	public void onEnable() {
		instance = this;
		configYAML = new ConfigYAML(this);
		playerCache = new PlayerCache();
		encrypt = new Encrypt();
		if (getConfig().getBoolean("MySQL.Enabled")) {
			try {database = new Database(this, false, getName(), getName(), getName(), getName(), 0);
			} catch (Exception e) {e.printStackTrace();}
		} else {
			try {database = new Database(this, "database");
			} catch (Exception e) {e.printStackTrace();}
		}
		getServer().getPluginManager().registerCommands(this, new LoginCommand());
		getServer().getPluginManager().registerCommands(this, new RegisterCommand());
		getServer().getEventsManager().registerEvents(this, new PlayerAuthListener());
		getServer().getEventsManager().registerEvents(this, new PlayerJoinListener());
		getServer().getEventsManager().registerEvents(this, new PlayerMoveListener());
		getServer().getEventsManager().registerEvents(this, new PlayerQuitListener());
	}
	
	public static LimboAuthMain a() {return instance;}
	public Config getConfig() {return configYAML.getConfig();}
	public void reloadConfig() {configYAML.getConfig().reload();}
	public void saveConfig() {configYAML.getConfig().save();}
	public Config getMessages() {return configYAML.getMessages();}
	public void reloadMessages() {configYAML.getMessages().reload();}
	public void saveMessages() {configYAML.getMessages().save();}
	public PlayerCache getPlayerCache() {return playerCache;}
	public Database getDatabase() {return database;}
	public Encrypt getEncrypter() {return encrypt;}
	
}
