package org.promisedland.minecraft.eraauth.utils;

public class Message {
    public static String getMessage(String key){
        return ConfigFiles.getConfigContentString(ConfigFiles.MessageConfig,key);
    }
}
