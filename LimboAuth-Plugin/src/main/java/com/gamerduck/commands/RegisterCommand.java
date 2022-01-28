package com.gamerduck.commands;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.Console;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;

import net.md_5.bungee.api.ChatColor;

public class RegisterCommand implements CommandExecutor {
	public void execute(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("register")) {
			if (sender instanceof Console) {
				sender.sendMessage("Player only command!");
				return;
			}
			Player p = (Player) sender;
			if (args.length < 3) {
				p.sendMessage("Correct usage: /register (password) (password)");
			} else {
				if (args[1].equals(args[2])) {
					LimboAuthMain.a().getDatabase().setEncryptedPassword(p, args[1]);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							LimboAuthMain.a().getMessages().getString("player-successful-register").replaceAll("\n", "\n")));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							LimboAuthMain.a().getMessages().getString("player-register-passwords-do-not-match").replaceAll("\n", "\n")));
				}
			}
		}
	}
}
