package org.promisedland.minecraft.eraauth.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.promisedland.minecraft.eraauth.manager.LoginManager;

public class Canceller implements Listener {
    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) { //不让聊天
        if(e.getMessage().substring(0, 1).equals("/")) //这里不拦截玩家用命令, 后面我们会处理一下限制玩家用命令
            return;
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { //不让玩家移动
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) { //不让玩家跟别的东西交互，约等于屏蔽左右键
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerInventory(InventoryOpenEvent e) { //不让玩家打开背包
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }
    private boolean needCancelled(String username) {
        return !LoginManager.isPlayerLogin(username);
    }
}
