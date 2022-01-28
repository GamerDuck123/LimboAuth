package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (!LimboAuthMain.a().getPlayerCache().getAuthStatus(e.getPlayer())) {
			e.setCancelled(true);
			if (LimboAuthMain.a().getDatabase().hasPassword(e.getPlayer())) {
				e.getPlayer().setTitleSubTitle(LimboAuthMain.a().getMessages().getString("player-title-login-to-move"),
						   LimboAuthMain.a().getMessages().getString("player-subtitle-login-to-move"), 
						   0, 100, 0);
			} else {
				e.getPlayer().setTitleSubTitle(LimboAuthMain.a().getMessages().getString("player-title-register-to-move"),
						   LimboAuthMain.a().getMessages().getString("player-subtitle-register-to-move"), 
						   0, 100, 0);
			}
		}
	}
}