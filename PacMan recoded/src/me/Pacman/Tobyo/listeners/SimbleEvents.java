package me.Pacman.Tobyo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Pacman.Tobyo.Pacman;
import me.Pacman.Tobyo.utils.Countdown;
import me.Pacman.Tobyo.utils.FileManager;
import me.Pacman.Tobyo.utils.Gamestates.GameStates;
import me.Pacman.Tobyo.utils.api.LocationAPI;

@SuppressWarnings("deprecation")
public class SimbleEvents implements Listener {

	//Player start item update #1
	@EventHandler
	public void on(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		if (GameStates.getGamestate() == GameStates.Lobby) {
			
			try {
				p.teleport(LocationAPI.getConfigLocation("lobby", FileManager.cfgloc));
			} catch (Exception ea) {
				p.sendMessage("§cError set up isn´t completed!");
			}
			
			e.setJoinMessage("§7[§6PacMan§7] §7" + p.getDisplayName() + " has joined the Game!");
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.removePotionEffect(PotionEffectType.SPEED);
			p.setLevel(0);
			p.setFoodLevel(20);
			p.setMaxHealth(20.0d);
			p.setHealth(20.0d);
			FileManager.ghost.add(p);
			
		} else if (GameStates.getGamestate() == GameStates.Ingame) {
			p.setGameMode(GameMode.SPECTATOR);
			try {
				p.teleport(LocationAPI.getConfigLocation("pacman", FileManager.cfgloc));
			} catch (Exception ea) {
				p.sendMessage("§cError set up isn´t completed!");
			}
			
		} else if (GameStates.getGamestate() == GameStates.Ending) {
			p.setGameMode(GameMode.SPECTATOR);
			try {
				p.teleport(LocationAPI.getConfigLocation("ending", FileManager.cfgloc));
			} catch (Exception ea) {
				p.sendMessage("§cError set up isn´t completed!");
			}
		}
	}

	@EventHandler
	public void on(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		if(GameStates.getGamestate() == GameStates.Lobby) {
			if(Bukkit.getOnlinePlayers().size() <2) {
				Countdown.stopCountdown();
				Countdown.setCountdown(60);
			}
		}
		
		if(GameStates.getGamestate() == GameStates.Ingame) {
			if(FileManager.ghost.contains(e.getPlayer()))
				FileManager.ghost.remove(e.getPlayer());
			
			if(FileManager.pacman.contains(e.getPlayer()))
				FileManager.pacman.remove(e.getPlayer());
			System.out.println(FileManager.pacman.size())
			;
			if(FileManager.ghost.size() == 0) {
				GameStates.setGamestate(GameStates.Ending);
				Countdown.setCountdown(10);
				Bukkit.broadcastMessage("§7[§6PacMan§7] §7The game ends because it doesnt give a ghost anymore!");
				
			}else if(FileManager.pacman.size() == 0) {
				GameStates.setGamestate(GameStates.Ending);
				Countdown.setCountdown(10);
				Bukkit.broadcastMessage("§7[§6PacMan§7] §7The game ends because it doesnt give a pacman anymore!");
			}
		}
	}
	
	@EventHandler
	public void on(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		e.setKeepInventory(true);
		Player p = e.getEntity();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Pacman.getInstace(), new Runnable() {

			@Override
			public void run() {
				e.getEntity().spigot().respawn();
				if (FileManager.pacman.contains(p)) {
					p.teleport(LocationAPI.getConfigLocation("pacman", FileManager.cfgloc));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 0));

					Bukkit.getOnlinePlayers().forEach(player -> {
						if (FileManager.ghost.contains(player))
							p.teleport(LocationAPI.getConfigLocation("ghost", FileManager.cfgloc));
					});
				} else if (FileManager.ghost.contains(p)) {
					p.teleport(LocationAPI.getConfigLocation("ghost", FileManager.cfgloc));
				}

			}
		}, 20L);
	}

	
	@EventHandler
	public void on(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(EntitySpawnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(BlockPlaceEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void on(InventoryClickEvent e) {
		e.setCancelled(true);
	}
}
