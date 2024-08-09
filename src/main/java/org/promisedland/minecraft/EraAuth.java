package org.promisedland.minecraft;

import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class EraAuth extends JavaPlugin {
    public static EraAuth INSTANCE;
    public static PluginLogger LOGGER;

    @Override
    public void onLoad(){
        try {
            init();
        }catch (Throwable throwable){
            stopOrUnload();
        }
    }

    @Override
    public void onEnable() {
        LOGGER.info("正在启动插件");
    }

    @Override
    public void onDisable() {
        LOGGER.info("插件已关闭");
    }
    public void init(){
        INSTANCE = this;
        LOGGER = new PluginLogger(this);
    }
    public void stopOrUnload() {
        setEnabled(false);
    }

}