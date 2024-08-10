package org.promisedland.minecraft.eraauth.utils;

public class Config {
    public static String getConfig(String key){
        return ConfigFiles.getConfigContentString(ConfigFiles.PluginConfig,key);
    }
}
