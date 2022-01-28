package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		LimboAuthMain.a().getPlayerCache().addAuthStatus(e.getPlayer(), false);
		if (LimboAuthMain.a().getDatabase().hasPassword(e.getPlayer())) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					LimboAuthMain.a().getMessages().getString("player-login-message").replaceAll("\n", "\n")));
		} else {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
					LimboAuthMain.a().getMessages().getString("player-register-message").replaceAll("\n", "\n")));
		}
	}
}
