package com.gamerduck.commands;

import com.gamerduck.LimboAuthMain;
import com.gamerduck.events.PlayerAuthEvent;
import com.loohp.limbo.Console;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;

import net.md_5.bungee.api.ChatColor;

public class LoginCommand implements CommandExecutor {
	public void execute(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("login")) {
			if (sender instanceof Console) {
				sender.sendMessage("Player only command!");
				return;
			}
			Player p = (Player) sender;
			if (args.length < 2) {
				p.sendMessage("Correct usage: /login (password)"); 
			} else {
				if (args[1].equals(LimboAuthMain.a().getDatabase().getEncryptedPassword(p))) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', 
							LimboAuthMain.a().getMessages().getString("player-successful-login").replaceAll("\n", "\n")));
					LimboAuthMain.a().getServer().getEventsManager().callEvent(new PlayerAuthEvent(p, true));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							LimboAuthMain.a().getMessages().getString("player-incorrect-password").replaceAll("\n", "\n")));
				}
			}
		}
	}
}
