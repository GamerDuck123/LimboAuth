package com.gamerduck.events;

import com.loohp.limbo.events.player.PlayerEvent;
import com.loohp.limbo.player.Player;

public class PlayerAuthEvent extends PlayerEvent {
	
	private Player player;
	private Boolean isAuth;
	
	public PlayerAuthEvent(Player player, Boolean isAuth) {
		super(player);
		this.player = player;
		this.isAuth = isAuth;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	public Boolean isAuthorized() {
		return isAuth;
	}
}
