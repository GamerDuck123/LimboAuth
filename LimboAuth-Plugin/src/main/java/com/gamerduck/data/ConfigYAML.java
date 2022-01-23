package com.gamerduck.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.gamerduck.configs.Config;
import com.loohp.limbo.file.FileConfiguration;
import com.loohp.limbo.plugins.LimboPlugin;

public class ConfigYAML {
	
    private Config config;
    private Config messages;
    
	public ConfigYAML(LimboPlugin plug) {
		if (!plug.getDataFolder().exists()) {plug.getDataFolder().mkdir();}
		File configFile = new File(plug.getDataFolder() + "/" + "config.yml");
        FileConfiguration temp = null;
        if (!configFile.exists()) {
        	try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml")) {
                Files.copy(in, configFile.toPath());
                temp = new FileConfiguration(configFile);
            } catch (IOException e) {e.printStackTrace();}
        } else {
            try {temp = new FileConfiguration(configFile);
			} catch (IOException e) {e.printStackTrace();}
        }
        config = new Config(temp, configFile);
        

		File messageFile = new File(plug.getDataFolder() + "/" + "messages.yml");
        FileConfiguration msgTemp = null;
        if (!messageFile.exists()) {
        	try (InputStream in = getClass().getClassLoader().getResourceAsStream("messages.yml")) {
                Files.copy(in, messageFile.toPath());
                msgTemp = new FileConfiguration(messageFile);
            } catch (IOException e) {e.printStackTrace();}
        } else {
            try {msgTemp = new FileConfiguration(messageFile);
			} catch (IOException e) {e.printStackTrace();}
        }
        messages = new Config(msgTemp, messageFile);
	}
	
	public Config getConfig() {return config;}
	public Config getMessages() {return messages;}
	
}
