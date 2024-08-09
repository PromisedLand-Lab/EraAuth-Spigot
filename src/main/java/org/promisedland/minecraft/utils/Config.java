package org.promisedland.minecraft.utils;

public class Config {
    public static String getConfig(String key){
        return ConfigFiles.getConfigContentString(ConfigFiles.PluginConfig,key);
    }
}
