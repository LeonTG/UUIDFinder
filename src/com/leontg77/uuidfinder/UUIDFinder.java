package com.leontg77.uuidfinder;

import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.uuidfinder.utils.NameFetcher;
import com.leontg77.uuidfinder.utils.UUIDFetcher;

/**
 * The main class of UUID finder
 * @author LeonTG77
 */
public class UUIDFinder extends JavaPlugin {
	private final Logger logger = Logger.getLogger("Minecraft");
	public static UUIDFinder plugin;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile file = this.getDescription();
		logger.info(file.getName() + " is now disabled.");
		plugin = null;
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile file = this.getDescription();
		logger.info(file.getName() + " v" + file.getVersion() + " is now enabled.");
		plugin = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	/**
	 * Gets the configs prefix. Note: Supports color codes with &(0-9|a-f)
	 * @return if found, the prefix, if null, default prefix
	 */
	public static String prefix() {
		if (plugin.getConfig().getString("UUIDFinder.prefix") == null) {
			return ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "UUID Finder" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
		} else {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("UUIDFinder.prefix"));
		}
	}
	
	/**
	 * Gets the message for when the player don't have the required permission.
	 * @return if found, the message, if null, default message.
	 */
	public static String noPermMsg() {
		if (plugin.getConfig().getString("UUIDFinder.NoPermissionMsg") == null) {
			return ChatColor.RED + "You do not have access to that command.";
		} else {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("UUIDFinder.NoPermissionMsg"));
		}
	}
	
	/**
	 * Gets the name of a given uuid.
	 * @param uuid the uuid given.
	 * @return the player name.
	 */
	public static String getName(String uuid) {
		String name;
		
		try {
			name = new NameFetcher(Arrays.asList(UUID.fromString(uuid))).call().get(UUID.fromString(uuid));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
        }
		return name;
	}
	
	/**
	 * Gets the name of the given player.
	 * @param player the player given.
	 * @return The UUID.
	 */
	public static String getUUID(Player player) {
		String uuid;
		try {
			uuid = new UUIDFetcher(Arrays.asList(player.getName())).call().get(player.getName()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return uuid;
	}
	
	/**
	 * Gets the name of the given name.
	 * @param name the name given.
	 * @return The UUID of offline player.
	 */
	public static String getUUID(String name) {
		String uuid;
		try {
			uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return uuid;
	}
}