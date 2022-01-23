package com.gamerduck.data;

import java.util.HashMap;
import java.util.UUID;

import com.loohp.limbo.player.Player;

public class PlayerCache {
	
	HashMap<UUID, Boolean> authStatus;
	
	public PlayerCache() {
		authStatus = new HashMap<UUID, Boolean>();
	}
	
	public void addAuthStatus(Player p, Boolean status) {
		authStatus.put(p.getUniqueId(), status);
	}
	
	public void removeAuthStatus(Player p) {
		authStatus.remove(p.getUniqueId());
	}
	
	public Boolean getAuthStatus(Player p) {
		return authStatus.get(p.getUniqueId());
	}
	
	public void changeAuthStatus(Player p, Boolean status) {
		authStatus.put(p.getUniqueId(), status);
	}

}
