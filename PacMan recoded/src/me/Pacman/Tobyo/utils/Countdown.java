package me.Pacman.Tobyo.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.Pacman.Tobyo.Pacman;
import me.Pacman.Tobyo.utils.Gamestates.GameStates;
import me.Pacman.Tobyo.utils.api.LocationAPI;

public class Countdown {

	static int schedel;
	static int countdown = 10;

	public static void start() {
		schedel = Bukkit.getScheduler().scheduleSyncRepeatingTask(Pacman.getInstace(), new Runnable() {

			@Override
			public void run() {
				// Lobby
				if (GameStates.getGamestate() == GameStates.Lobby) {
					if (Bukkit.getOnlinePlayers().size() >= 2) {
						Bukkit.getOnlinePlayers().forEach(all -> all.setLevel(countdown));
						if (countdown == 30 || countdown == 20 || countdown == 10 || countdown == 5 || countdown == 4
								|| countdown == 3 || countdown == 2) {
							Bukkit.broadcastMessage("§7[§6Pacman§7] The Game start in: " + countdown + " seconds");
						} else if (countdown == 1) {
							Bukkit.broadcastMessage("§7[§6Pacman§7] The Game start in: " + countdown + " second");
						} else if (countdown == 0) {
							GameStates.setGamestate(GameStates.Ingame);
							setCountdown(300);
							randompacman();
							Bukkit.broadcastMessage("§7[§6Pacman§7] The Game start now!");
						}
						countdown--;
					}
					// Ingame
				} else if (GameStates.getGamestate() == GameStates.Ingame) {
					if (countdown == 300 || countdown == 200 || countdown == 100 || countdown == 50 || countdown == 40
							|| countdown == 30 || countdown == 20 || countdown == 10 || countdown == 5 || countdown == 4
							|| countdown == 3 || countdown == 2) {
						Bukkit.broadcastMessage("§7[§6Pacman§7] The Game ends in: " + countdown + " seconds");
					} else if (countdown == 1) {
						Bukkit.broadcastMessage("§7[§6Pacman§7] The Game ends in: " + countdown + " second");
					} else if (countdown == 0) {
						GameStates.setGamestate(GameStates.Ending);
						Bukkit.broadcastMessage("§7[§6Pacman§7] The Game ends now!");
						Bukkit.getOnlinePlayers().forEach(
								all -> all.teleport(LocationAPI.getConfigLocation("ending", FileManager.cfgloc)));
						setCountdown(10);
					}

					countdown--;
					// Ending
				} else if (GameStates.getGamestate() == GameStates.Ending) {
					if (countdown == 10 || countdown == 5 || countdown == 4 || countdown == 3 || countdown == 2) {
						Bukkit.broadcastMessage("§7[§6Pacman§7] The Server Close in: " + countdown + " seconds");
					}else if (countdown == 1) {
						Bukkit.broadcastMessage("§7[§6Pacman§7] The Server Close in: " + countdown + " second");
					}else if(countdown == 0) {
						//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
						stopCountdown();
					}
					countdown--;
				}
			}
		}, 20, 20);
	}

	public static void setCountdown(int countdown) {
		Countdown.countdown = countdown;
	}
	
	public static void stopCountdown() {
		Bukkit.getScheduler().cancelTask(schedel);
	}
	
	public static void randompacman() {
		int i = 0;
		i = FileManager.ghost.size();
		System.out.println(i);
		Random rd = new Random();
		i = rd.nextInt(i);
		Player p = FileManager.ghost.get(i).getPlayer();
		FileManager.ghost.remove(p);
		FileManager.pacman.add(p);
		p.setHealthScale(6);
		
	}
}
