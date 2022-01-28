package com.gamerduck.listeners;

import com.gamerduck.LimboAuthMain;
import com.gamerduck.events.PlayerAuthEvent;
import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;

public class PlayerAuthListener implements Listener {
	
	@EventHandler
	public void onAuth(PlayerAuthEvent e) {
		if (e.isAuthorized()) {
			LimboAuthMain.a().getPlayerCache().changeAuthStatus(e.getPlayer(), true);
			String s = LimboAuthMain.a().getConfig().getString("Commands-On-Auth");
			String[] split = s.split(";");
			for (String cmd : split) {
				String[] splitCMD = cmd.split(":");
				if (splitCMD[0].equals("p")) {
					LimboAuthMain.a().getServer().dispatchCommand(e.getPlayer(), cmd.replaceFirst("p:/", ""));
				} else if (splitCMD[0].equals("c")) {
					LimboAuthMain.a().getServer().dispatchCommand(LimboAuthMain.a().getServer().getConsole(), cmd.replaceFirst("c:/", ""));
				}
			}
		}
	}
}
