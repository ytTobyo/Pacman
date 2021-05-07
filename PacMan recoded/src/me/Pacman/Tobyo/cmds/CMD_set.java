package me.Pacman.Tobyo.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Pacman.Tobyo.utils.FileManager;
import me.Pacman.Tobyo.utils.api.LocationAPI;

public class CMD_set implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("pacman.set")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("lobby")) {
						LocationAPI.createConfigLocation(p.getLocation(), "lobby", FileManager.fileloc, FileManager.cfgloc);
						p.sendMessage("§7[§6Pacman§7] Succecfull set the Lobby location!");
					}else if(args[0].equalsIgnoreCase("ghost")) {
						LocationAPI.createConfigLocation(p.getLocation(), "ghost", FileManager.fileloc, FileManager.cfgloc);
						p.sendMessage("§7[§6Pacman§7] Succecfull set the Ghost location!");
					}else if(args[0].equalsIgnoreCase("pacman")) {
						LocationAPI.createConfigLocation(p.getLocation(), "pacman", FileManager.fileloc, FileManager.cfgloc);
						p.sendMessage("§7[§6Pacman§7] Succecfull set the Pacman location!");
					}else if(args[0].equalsIgnoreCase("ending")) {
						LocationAPI.createConfigLocation(p.getLocation(), "ending", FileManager.fileloc, FileManager.cfgloc);
						p.sendMessage("§7[§6Pacman§7] Succecfull set the Ending location!");
					}else 
						p.sendMessage("§cUse /pacman <lobby/ghost/pacman/ending>");
				}else
					p.sendMessage("§cUse /pacman <lobby/ghost/pacman/ending>");
			}
		}else
			sender.sendMessage("§cYou must be a Player on the Server!");
		return false;
	}

}
