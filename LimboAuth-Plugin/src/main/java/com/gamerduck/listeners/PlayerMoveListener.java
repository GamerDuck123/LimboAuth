package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (!LimboAuthMain.a().getPlayerCache().getAuthStatus(e.getPlayer())) {
			e.setCancelled(true);
			if (LimboAuthMain.a().getDatabase().hasPassword(e.getPlayer())) {
				e.getPlayer().sendMessage("/login");
//				e.getPlayer().setTitleSubTitle(ChatColor.GREEN + "/login (password)", "Thank You!", 10, 10, 10);
			} else {
				e.getPlayer().sendMessage("/register");
//				e.getPlayer().setTitleSubTitle(ChatColor.GREEN + "/register (password) (password)", "Thank You!", 10, 10, 10);
			}
		}
	}
}
