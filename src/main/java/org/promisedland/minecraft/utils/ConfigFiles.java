package org.promisedland.minecraft.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
