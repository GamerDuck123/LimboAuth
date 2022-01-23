package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		LimboAuthMain.a().getPlayerCache().addAuthStatus(e.getPlayer(), false);
		if (LimboAuthMain.a().getDatabase().hasPassword(e.getPlayer())) {
			e.getPlayer().sendMessage("/login (password)");
		} else {
			e.getPlayer().sendMessage("/register (password) (password)");
			
		}
	}
}
