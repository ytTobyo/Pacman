package me.Pacman.Tobyo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FileManager {

	public static ArrayList<Player> ghost = new ArrayList<>(), pacman = new ArrayList<>();

	public static File fileloc = new File("plugins//Pacman//location.yml"),
			filconfig = new File("plugins//Pacman//config.yml");

	public static YamlConfiguration cfgloc = YamlConfiguration.loadConfiguration(fileloc),
			cfgconfig = YamlConfiguration.loadConfiguration(filconfig);

	public static int maxcoins;

	public static void setdefaultcfg() {
		cfgconfig.options().copyDefaults(true);
		cfgconfig.addDefault("maxcoins", 1);
		
		
		try {
			cfgconfig.save(filconfig);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		read();
	}

	private static void read() {
		maxcoins = cfgconfig.getInt("maxcoins");
	}
}
