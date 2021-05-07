package me.Pacman.Tobyo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Pacman.Tobyo.utils.Countdown;
import me.Pacman.Tobyo.utils.FileManager;
import me.Pacman.Tobyo.utils.Gamestates.GameStates;

public class PacManWinEvent implements Listener{

	public static int coins = 0;
	
	@EventHandler
	public void on(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		try {
			if(GameStates.getGamestate() == GameStates.Ingame && FileManager.pacman.contains(p)) {
				if(e.getClickedBlock().getType() == Material.GOLD_BLOCK && e.getClickedBlock().getType() != Material.AIR) {
					e.getClickedBlock().setType(Material.AIR);
					coins++;
					 if(coins >= FileManager.maxcoins) {
						 Bukkit.broadcastMessage("§7[§6PacMan§7] §6PacMan §7 won the Game!");
						 GameStates.setGamestate(GameStates.Ending);
						 Countdown.setCountdown(10);
					 }
				}else if(e.getClickedBlock().getType() != Material.AIR && e.getClickedBlock().getType() == Material.REDSTONE_BLOCK) {
					Bukkit.broadcastMessage("§7[§6PacMan§7] §6PacMan §7have super power!");
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 140, 0));
					e.getClickedBlock().setType(Material.AIR);
				}
			}
		}catch (Exception exception) {
		}
	}
}
