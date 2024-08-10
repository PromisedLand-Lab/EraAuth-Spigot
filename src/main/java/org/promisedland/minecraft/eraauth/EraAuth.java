package org.promisedland.minecraft.eraauth;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.promisedland.minecraft.eraauth.listener.Canceller;
import org.promisedland.minecraft.eraauth.listener.CommondListener;
import org.promisedland.minecraft.eraauth.listener.PlayerActionListener;
import org.promisedland.minecraft.eraauth.utils.DataBase;
import org.promisedland.minecraft.eraauth.utils.Redis;

public class EraAuth extends JavaPlugin {
    public static EraAuth INSTANCE;
    public static PluginLogger LOGGER;
    private static CommandExecutor ce = new CommondListener();

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
        this.saveConfig();
        Bukkit.getPluginManager().registerEvents(new Canceller(),this);
        Bukkit.getPluginManager().registerEvents(new CommondListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerActionListener(),this);

        Bukkit.getPluginCommand("login").setExecutor(ce);
        Bukkit.getPluginCommand("register").setExecutor(ce);
        LOGGER.info("正在启动插件");
    }

    @Override
    public void onDisable() {
        LOGGER.info("插件已关闭");
    }
    public void init() throws Exception {
        INSTANCE = this;
        LOGGER = new PluginLogger(this);
        Redis.initRedis();
        DataBase.initMysql();
    }
    public void stopOrUnload() {
        setEnabled(false);
    }

}