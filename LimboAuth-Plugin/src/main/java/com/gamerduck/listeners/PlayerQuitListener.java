package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		LimboAuthMain.a().getPlayerCache().removeAuthStatus(e.getPlayer());	
	}
}
