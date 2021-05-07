package me.Pacman.Tobyo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import me.Pacman.Tobyo.utils.Countdown;
import me.Pacman.Tobyo.utils.FileManager;
import me.Pacman.Tobyo.utils.Gamestates.GameStates;

public class PacManKillEvent implements Listener{

	public static int pacmanlife = 3;
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player p = (Player) e.getDamager();
			Player enemie = (Player) e.getEntity();
			if (FileManager.pacman.contains(p) && GameStates.getGamestate() == GameStates.Ingame
					&& p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				enemie.setHealth(0.0D);
			} else if (FileManager.ghost.contains(p) && GameStates.getGamestate() == GameStates.Ingame
					&& !enemie.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE) && !FileManager.ghost.contains(enemie)) {
				enemie.setHealth(0.0D);
				pacmanlife--;
				if(pacmanlife >= 1)
					enemie.setHealthScale(pacmanlife *2);
				if(pacmanlife <= 0) {
					Bukkit.broadcastMessage("§7[§6PacMan§7] §7The Ghosts has won the Game!");
					GameStates.setGamestate(GameStates.Ending);
					Countdown.setCountdown(10);
				}
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	public static int getPacmanlife() {
		return pacmanlife;
	}
}
