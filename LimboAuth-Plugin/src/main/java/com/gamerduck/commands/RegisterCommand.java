package com.gamerduck.commands;

import com.gamerduck.LimboAuthMain;
import com.loohp.limbo.Console;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;

public class RegisterCommand implements CommandExecutor {
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Console) {
			sender.sendMessage("Player only command!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("register")) {
			Player p = (Player) sender;
			if (args.length < 3) {
				p.sendMessage("Correct usage: /register (password) (password)");
			} else {
				if (args[1].equals(args[2])) {
					LimboAuthMain.a().getDatabase().setEncryptedPassword(p, args[1]);
					p.sendMessage("Now Login: /login (password)");
				} else {
					p.sendMessage("Passwords do not match!");
				}
			}
		}
	}
}
