package com.gamerduck.commands;

import com.gamerduck.Encrypt;
import com.gamerduck.LimboAuthMain;
import com.gamerduck.data.PlayerCache;
import com.gamerduck.events.PlayerAuthEvent;
import com.loohp.limbo.Console;
import com.loohp.limbo.commands.CommandExecutor;
import com.loohp.limbo.commands.CommandSender;
import com.loohp.limbo.player.Player;

public class LoginCommand implements CommandExecutor {
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Console) {
			sender.sendMessage("Player only command!");
			return;
		}
		
		if (args[0].equalsIgnoreCase("login")) {
			Player p = (Player) sender;
			if (args.length < 2) {
				p.sendMessage("Correct usage: /login (password)");
			} else {
				p.sendMessage(args[1]);
				if (args[1].equals(LimboAuthMain.a().getDatabase().getEncryptedPassword(p))) {
					LimboAuthMain.a().getServer().getEventsManager().callEvent(new PlayerAuthEvent(p, true));
				} else {
					p.sendMessage("Incorrect password!");
				}
			}
		}
	}
}
