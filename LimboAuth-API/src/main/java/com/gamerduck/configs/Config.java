package com.gamerduck.configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.loohp.limbo.file.FileConfiguration;

public class Config  implements Cloneable {
	FileConfiguration config;
	File configFile;
	
	public Config(FileConfiguration config, File configFile) {
		this.config = config;
		this.configFile = configFile;
	}
	
	public void reload() {
		try {config.reloadConfig(configFile);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	public void save() {
		try {config.saveConfig(configFile);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public boolean set(String path, Object value) {
		config.set(path, value);
		return config.get(path, value.getClass()) != null;
	}
	
	public String getString(String path) {return config.get(path, String.class);}
	public Integer getInteger(String path) {return config.get(path, Integer.class);}
	public Double getDouble(String path) {return config.get(path, Double.class);}
	public Float getFloat(String path) {return config.get(path, Float.class);}
	public Boolean getBoolean(String path) {return config.get(path, Boolean.class);}
	public List<?> getList(String path) {return config.get(path, List.class);}
}
