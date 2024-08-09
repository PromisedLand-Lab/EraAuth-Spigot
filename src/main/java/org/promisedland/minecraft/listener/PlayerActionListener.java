package org.promisedland.minecraft.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.promisedland.minecraft.manager.LoginManager;
import org.promisedland.minecraft.utils.ConfigFiles;
import org.promisedland.minecraft.utils.Message;
import org.promisedland.minecraft.utils.MessageKey;

import java.sql.SQLException;


public class PlayerActionListener implements Listener {
    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED){
            return;
        }
        final String name = event.getName();

        if(event.getAddress() == null){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Message.getMessage(MessageKey.KICK_UNRESOLVED_HOSTNAME.getKey()));
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(
                LoginManager.isPlayerRegister(event.getPlayer().getName())?
                        "欢迎回来！请输入/login 密码 登录服务器！":
                        "欢迎第一次来到本服务器！请输入/register 密码 注册账号！"
        );
    }
}
