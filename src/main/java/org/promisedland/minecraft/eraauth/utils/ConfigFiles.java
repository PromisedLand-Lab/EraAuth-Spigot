package org.promisedland.minecraft.eraauth.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFiles {
    public static String ConfigPath = "plugins/EraAuth";
    public static File MessageConfig = new File(ConfigPath+"/MessageConfig.yml");
    public static File PluginConfig = new File(ConfigPath+"/Config.yml");

    public static String getConfigContentString(File config,String key){
        return (String) getFileConfiguration(config).get(key);
    }
    public static Object getConfigContentObject(File config,String key){
        return getFileConfiguration(config).get(key);
    }

    public static FileConfiguration getFileConfiguration(File file){
        return YamlConfiguration.loadConfiguration(file);
    }
}
