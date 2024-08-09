package org.promisedland.minecraft.utils;

public class Message {
    public static String getMessage(String key){
        return ConfigFiles.getConfigContentString(ConfigFiles.MessageConfig,key);
    }
}
