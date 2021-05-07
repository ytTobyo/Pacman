package me.Pacman.Tobyo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Pacman.Tobyo.cmds.CMD_maxcoins;
import me.Pacman.Tobyo.cmds.CMD_set;
import me.Pacman.Tobyo.listeners.PacManKillEvent;
import me.Pacman.Tobyo.listeners.PacManWinEvent;
import me.Pacman.Tobyo.listeners.SimbleEvents;
import me.Pacman.Tobyo.utils.Countdown;
import me.Pacman.Tobyo.utils.FileManager;
import me.Pacman.Tobyo.utils.Gamestates.GameStates;
import me.Pacman.Tobyo.utils.bstats.Metrics;

public class Pacman extends JavaPlugin{

	static Pacman instace;
	
	@Override
	public void onEnable() {
		System.out.println("Pacman is starting...");
		instace = this;
		GameStates.setGamestate(GameStates.Lobby);
		FileManager.setdefaultcfg();
		cmds();
		listeners();
		Countdown.start();
		
		int pluginId = 10989; 
	    Metrics metrics = new Metrics(this, pluginId);
	      
		System.out.println("Pacman had Succecfull started!");
		
		  
		super.onEnable();
	}
	
	private void cmds() {
		getCommand("set").setExecutor(new CMD_set());
		getCommand("maxcoins").setExecutor(new CMD_maxcoins());
		
	}
	
	private void listeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PacManKillEvent(), this);
		pm.registerEvents(new PacManWinEvent(), this);
		pm.registerEvents(new SimbleEvents(), this);
	}
	
	public static Pacman getInstace() {
		return instace;
	}
	
	@Override
	public void onDisable() {
		GameStates.setGamestate(GameStates.Ending);
		super.onDisable();
	}
}
