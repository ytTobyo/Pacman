package me.Pacman.Tobyo.cmds;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Pacman.Tobyo.utils.FileManager;

public class CMD_maxcoins implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("pacman.maxcoins")) {
				if (args.length == 1) {
					try {
						int i = Integer.parseInt(args[0]);
						try {
							FileManager.cfgconfig.set("maxcoins", i);
							FileManager.cfgconfig.save(FileManager.filconfig);
						} catch (IOException e) {
							e.printStackTrace();
						}
						p.sendMessage("§7[§6Pacman§7] You set the maxcoins to: "+i);
					} catch (NumberFormatException e) {
						p.sendMessage("§7[§6Pacman§7] §cUse /maxcoins <number>");
					}
				}else
					p.sendMessage("§7[§6Pacman§7] §cUse /maxcoins <number>");
			}
		} else
			sender.sendMessage("§7[§6Pacman§7] §cYou must be a Player on the Server!");
		return false;
	}

}
